package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.service.interfaces.DatabaseBackupService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class DatabaseBackupServiceImpl
        implements DatabaseBackupService {

    private static final String BACKUP_PREFIX =
            "CoffeeGourmet_backup_";

    private static final String BACKUP_EXTENSION =
            ".backup";

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final Pattern BACKUP_PATTERN =
            Pattern.compile(
                    "^CoffeeGourmet_backup_(\\d{4}-\\d{2}-\\d{2})_(\\d{3})\\.backup$"
            );

    @Value("${backup.postgresql.pg-dump-path}")
    private String pgDumpPath;

    @Value("${backup.directory}")
    private String backupDirectory;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Override
    public String createBackup() {

        try {

            Path directory =
                    Paths.get(backupDirectory);

            Files.createDirectories(directory);

            LocalDate today =
                    LocalDate.now();

            String date =
                    today.format(DATE_FORMATTER);

            int consecutive =
                    obtainNextConsecutive(
                            directory,
                            date);

            String fileName =
                    BACKUP_PREFIX
                            + date
                            + "_"
                            + String.format(
                            "%03d",
                            consecutive)
                            + BACKUP_EXTENSION;

            Path backupFile =
                    directory.resolve(fileName);

            DatabaseConnectionData connection =
                    parseDatasourceUrl(datasourceUrl);

            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            pgDumpPath,
                            "-h",
                            connection.host(),
                            "-p",
                            connection.port(),
                            "-U",
                            datasourceUsername,
                            "-d",
                            connection.database(),
                            "-F",
                            "c",
                            "-f",
                            backupFile.toAbsolutePath().toString()
                    );

            processBuilder.environment()
                    .put(
                            "PGPASSWORD",
                            datasourcePassword);

            processBuilder.redirectErrorStream(true);

            Process process =
                    processBuilder.start();

            String output =
                    readProcessOutput(process);

            int exitCode =
                    process.waitFor();

            if (exitCode != 0) {

                Files.deleteIfExists(
                        backupFile);

                throw new BusinessException(
                        "No fue posible generar el respaldo de la base de datos. "
                                + output);
            }

            if (!Files.exists(backupFile)
                    || Files.size(backupFile) == 0) {

                throw new BusinessException(
                        "El respaldo fue ejecutado pero el archivo generado "
                                + "no es válido.");
            }

            return fileName;

        } catch (IOException e) {

            throw new BusinessException(
                    "Error al generar el respaldo de la base de datos: "
                            + e.getMessage());

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new BusinessException(
                    "La generación del respaldo fue interrumpida.");
        }
    }

    /**
     * Obtiene el siguiente consecutivo disponible para la fecha actual.
     */
    private int obtainNextConsecutive(
            Path directory,
            String date) throws IOException {

        try (Stream<Path> files =
                     Files.list(directory)) {

            return files
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .map(BACKUP_PATTERN::matcher)
                    .filter(Matcher::matches)
                    .filter(matcher ->
                            matcher.group(1)
                                    .equals(date))
                    .mapToInt(matcher ->
                            Integer.parseInt(
                                    matcher.group(2)))
                    .max()
                    .orElse(0) + 1;
        }
    }

    /**
     * Lee la salida generada por pg_dump.
     */
    private String readProcessOutput(
            Process process) throws IOException {

        StringBuilder output =
                new StringBuilder();

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     process.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            String line;

            while ((line = reader.readLine()) != null) {

                output.append(line)
                        .append(System.lineSeparator());
            }
        }

        return output.toString().trim();
    }

    /**
     * Obtiene la información necesaria desde la URL JDBC.
     *
     * Ejemplo:
     * jdbc:postgresql://localhost:5432/CoffeeGourmet?currentSchema=coffeegourmet
     */
    private DatabaseConnectionData parseDatasourceUrl(
            String url) {

        try {

            String jdbcUrl =
                    url.substring(
                            "jdbc:postgresql://".length());

            String withoutParameters =
                    jdbcUrl.split("\\?")[0];

            String[] hostAndDatabase =
                    withoutParameters.split("/");

            String hostAndPort =
                    hostAndDatabase[0];

            String database =
                    hostAndDatabase[1];

            String[] hostAndPortParts =
                    hostAndPort.split(":");

            String host =
                    hostAndPortParts[0];

            String port =
                    hostAndPortParts.length > 1
                            ? hostAndPortParts[1]
                            : "5432";

            return new DatabaseConnectionData(
                    host,
                    port,
                    database);

        } catch (Exception e) {

            throw new BusinessException(
                    "La URL de conexión de PostgreSQL no tiene "
                            + "un formato válido para generar el respaldo.");
        }
    }

    private record DatabaseConnectionData(
            String host,
            String port,
            String database) {
    }
}