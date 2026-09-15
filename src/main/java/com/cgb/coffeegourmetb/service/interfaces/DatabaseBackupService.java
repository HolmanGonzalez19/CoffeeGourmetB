package com.cgb.coffeegourmetb.service.interfaces;

/**
 * Servicio encargado de generar respaldos de la base de datos.
 */
public interface DatabaseBackupService {

    /**
     * Genera un respaldo completo de la base de datos CoffeeGourmet.
     *
     * @return nombre del archivo de respaldo generado.
     */
    String createBackup();
}