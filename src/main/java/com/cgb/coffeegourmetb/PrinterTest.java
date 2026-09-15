package com.cgb.coffeegourmetb;

import javax.print.*;

public class PrinterTest {

    public static void main(String[] args) {

        String printerName = "SAT38TUSE";

        PrintService selectedPrinter = null;

        System.out.println("=== IMPRESORAS DISPONIBLES ===");

        for (PrintService printer :
                PrintServiceLookup.lookupPrintServices(null, null)) {

            System.out.println("Nombre: " + printer.getName());

            if (printer.getName().equalsIgnoreCase(printerName)) {
                selectedPrinter = printer;
            }
        }

        if (selectedPrinter == null) {

            System.out.println();
            System.out.println(
                    "❌ NO SE ENCONTRO LA IMPRESORA: "
                            + printerName
            );

            return;
        }

        System.out.println();
        System.out.println(
                "✅ Impresora seleccionada: "
                        + selectedPrinter.getName()
        );

        /*
         * Comando ESC/POS para abrir el cajón.
         *
         * ESC p 0 25 250
         *
         * 0x1B = ESC
         * 0x70 = p
         * 0x00 = pin 2
         * 0x19 = tiempo ON
         * 0xFA = tiempo OFF
         */
        byte[] openDrawer = new byte[]{
                0x1B, 0x70, 0x00, 0x19, (byte) 0xFA
        };




        try {

            DocPrintJob job =
                    selectedPrinter.createPrintJob();

            Doc doc = new SimpleDoc(
                    openDrawer,
                    DocFlavor.BYTE_ARRAY.AUTOSENSE,
                    null
            );

            job.print(doc, null);

            System.out.println();
            System.out.println(
                    "✅ COMANDO DE APERTURA ENVIADO"
            );

        } catch (PrintException e) {

            System.out.println();
            System.out.println(
                    "❌ ERROR AL ENVIAR COMANDO AL CAJON"
            );

            e.printStackTrace();
        }
    }
}