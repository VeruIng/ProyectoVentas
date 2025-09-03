package com.ventas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    public static void main(String[] args) {
        try {
            createSalesManInfoFile(5);   // Crea archivo con 5 vendedores
            createProductsFile(5);       // Crea archivo con 5 productos
            createSalesMenFile(5, "Laura", 12345);  // Crea archivo de ventas para un vendedor
            createSalesMenFile(3, "Andres", 67890); // Otro vendedor

            System.out.println("✅ Archivos generados correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al generar archivos: " + e.getMessage());
        }
    }

    // Archivo de información de vendedores
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("vendedores.txt"));
        String[] nombres = {"Laura", "Andres", "Maria", "Carlos", "Luisa"};
        String[] apellidos = {"Perez", "Gomez", "Lopez", "Martinez", "Rodriguez"};

        Random rand = new Random();
        for (int i = 0; i < salesmanCount; i++) {
            String tipoDoc = (i % 2 == 0) ? "CC" : "CC";
            int numeroDoc = 10000 + rand.nextInt(90000);
            String nombre = nombres[rand.nextInt(nombres.length)];
            String apellido = apellidos[rand.nextInt(apellidos.length)];
            writer.write(tipoDoc + ";" + numeroDoc + ";" + nombre + ";" + apellido);
            writer.newLine();
        }
        writer.close();
    }

    // Archivo de productos
    public static void createProductsFile(int productsCount) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("productos.txt"));
        String[] nombresProductos = {"Shampoo", "Jabon", "Crema", "Perfume", "Labial"};
        Random rand = new Random();

        for (int i = 0; i < productsCount; i++) {
            String id = "P" + (i + 1);
            String nombre = nombresProductos[rand.nextInt(nombresProductos.length)];
            int precio = 1000 + rand.nextInt(20000);
            writer.write(id + ";" + nombre + ";" + precio);
            writer.newLine();
        }
        writer.close();
    }

    // Archivo de ventas de un vendedor
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        String fileName = "ventas_" + id + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        Random rand = new Random();

        // Primera línea con documento del vendedor
        writer.write("CC;" + id);
        writer.newLine();

        // Ventas aleatorias
        for (int i = 0; i < randomSalesCount; i++) {
            String idProducto = "P" + (1 + rand.nextInt(5)); // IDs de productos P1...P5
            int cantidad = 1 + rand.nextInt(10); // cantidad vendida
            writer.write(idProducto + ";" + cantidad);
            writer.newLine();
        }
        writer.close();
    }
}

