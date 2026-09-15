package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.entity.Sale;
import com.cgb.coffeegourmetb.entity.SaleDetail;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.repository.SaleRepository;
import com.cgb.coffeegourmetb.service.interfaces.ReceiptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private static final String PRINTER_NAME = "SAT38TUSE";

    private final SaleRepository saleRepository;

    public ReceiptServiceImpl(
            SaleRepository saleRepository) {

        this.saleRepository = saleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void print(Long saleId) {

        Sale sale = saleRepository
                .findById(saleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una venta registrada con id: "
                                        + saleId));

        PrintService printer = findPrinter();

        String ticket = buildTicket(sale);

        byte[] data = ticket.getBytes(
                StandardCharsets.UTF_8
        );

        try {

            DocPrintJob job =
                    printer.createPrintJob();

            Doc doc = new SimpleDoc(
                    data,
                    DocFlavor.BYTE_ARRAY.AUTOSENSE,
                    null
            );

            job.print(doc, null);

        } catch (PrintException e) {

            throw new RuntimeException(
                    "No fue posible imprimir el ticket.",
                    e
            );
        }
    }

    private PrintService findPrinter() {

        for (PrintService printer :
                PrintServiceLookup.lookupPrintServices(
                        null,
                        null)) {

            if (printer.getName()
                    .equalsIgnoreCase(PRINTER_NAME)) {

                return printer;
            }
        }

        throw new RuntimeException(
                "No se encontró la impresora: "
                        + PRINTER_NAME
        );
    }

    private String normalizarNombreProducto(String nombre) {
        if (nombre == null) {return "";}
        return nombre
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U")
                .replace("ñ", "n")
                .replace("Ñ", "N");
    }


    private String buildTicket(Sale sale) {

        StringBuilder ticket =
                new StringBuilder();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm"
                );

        ticket.append("\u001B\u0045\u0001");
        ticket.append("\u001D\u0021\u0011");
        ticket.append(" COFFEE GOURMET\n");
        ticket.append("\u001D\u0021\u0000");
        ticket.append("\u001B\u0045\u0000");

        ticket.append("\n");
        ticket.append("\u001B\u0045\u0001");
        ticket.append("         RECIBO DE VENTA\n");
        ticket.append("\u001B\u0045\u0000");
        ticket.append("--------------------------------\n");
        ticket.append("\u001B\u0045\u0001");
        ticket.append("Venta: ");
        ticket.append("\u001B\u0021\u0000");
        ticket.append("#")
                .append(sale.getId())
                .append("\n");
        ticket.append("\u001B\u0045\u0001");
        ticket.append("Fecha: ");
        ticket.append("\u001B\u0021\u0000");
        ticket.append(sale.getFechaHora().format(formatter))
                .append("\n");
        ticket.append("\u001B\u0045\u0001");
        ticket.append("Operador: ");
        ticket.append("\u001B\u0021\u0000");
        ticket.append(sale.getUsuario().getNombre())
                .append("\n");

        ticket.append("--------------------------------\n");

        for (SaleDetail detalle : sale.getDetalles()) {

            ticket.append(detalle.getCantidad())
                    .append(" x ")
                    .append(normalizarNombreProducto( detalle.getProducto().getNombre() ))
                    .append("\n");

            ticket.append("   $")
                    .append(String.format( "%,.2f",detalle.getSubtotal() ).replace(",", "."))
                    .append("\n");
        }

        ticket.append("--------------------------------\n");
        ticket.append("\u001B\u0045\u0001");
        ticket.append("TOTAL: $")
                .append(String.format( "%,.2f",sale.getTotal() ).replace(",", "."));
        ticket.append("\u001B\u0021\u0000");
        ticket.append("\n");
        ticket.append("Medio de pago: ");
        ticket.append("\u001B\u0045\u0001");
        ticket.append(sale.getMetodoPago().getNombre())
                .append("\n");
        ticket.append("\u001B\u0021\u0000");

        ticket.append("--------------------------------\n");
        ticket.append("\u001B\u0045\u0001");
        ticket.append("         GRACIAS POR SU\n");
        ticket.append("             COMPRA\n");
        ticket.append("\u001B\u0045\u0000")
                .append("\n");
        ticket.append("\u001B\u000F");
        ticket.append("      Comprobante de venta\n");
        ticket.append("\u001B\u0012");
        ticket.append("\n\n\n\n\n");

        return ticket.toString();
    }
}
