package com.ventas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            // 1️⃣ Leer productos con validación
            Map<String, Integer> preciosProductos = leerProductos("productos.txt");

            // 2️⃣ Leer vendedores con validación
            Map<String, String> vendedores = leerVendedores("vendedores.txt");

            // 3️⃣ Calcular ventas totales
            Map<String, Integer> ventasPorVendedor = new HashMap<>();
            Map<String, Integer> ventasPorProducto = new HashMap<>();

            File folder = new File(".");
            File[] files = folder.listFiles((dir, name) -> name.startsWith("ventas_"));

            if (files != null && files.length > 0) {
                for (File archivo : files) {
                    procesarArchivoVentas(archivo, ventasPorVendedor, ventasPorProducto, preciosProductos);
                }
            }

            // 4️⃣ Generar reportes
            generarReporteVendedores("reporte_vendedores.txt", vendedores, ventasPorVendedor);
            generarReporteProductos("reporte_productos.txt", ventasPorProducto, preciosProductos);

            System.out.println("✅ Reportes generados correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error general: " + e.getMessage());
        }
    }

    // ✅ Leer productos
    public static Map<String, Integer> leerProductos(String archivo) {
        Map<String, Integer> productos = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length < 2) continue;
                try {
                    productos.put(partes[0], Integer.parseInt(partes[1]));
                } catch (NumberFormatException e) {
                    System.out.println("⚠ Precio inválido en: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer productos: " + e.getMessage());
        }
        return productos;
    }

    // ✅ Leer vendedores
    public static Map<String, String> leerVendedores(String archivo) {
        Map<String, String> vendedores = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length < 4) continue;
                vendedores.put(partes[1], partes[2] + " " + partes[3]);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer vendedores: " + e.getMessage());
        }
        return vendedores;
    }

    // ✅ Procesar ventas
    public static void procesarArchivoVentas(File archivo, Map<String, Integer> ventasPorVendedor,
                                             Map<String, Integer> ventasPorProducto,
                                             Map<String, Integer> preciosProductos) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length < 3) continue;

                String idProducto = partes[1];
                String idVendedor = partes[0];
                int cantidad = Integer.parseInt(partes[2]);

                ventasPorVendedor.put(idVendedor, ventasPorVendedor.getOrDefault(idVendedor, 0) + cantidad);
                ventasPorProducto.put(idProducto, ventasPorProducto.getOrDefault(idProducto, 0) + cantidad);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al procesar archivo: " + archivo.getName());
        }
    }

    // ✅ Generar reporte de vendedores
    public static void generarReporteVendedores(String archivo, Map<String, String> vendedores,
                                                Map<String, Integer> ventasPorVendedor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String id : ventasPorVendedor.keySet()) {
                bw.write(vendedores.getOrDefault(id, "Desconocido") + ": " + ventasPorVendedor.get(id));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error al generar reporte de vendedores: " + e.getMessage());
        }
    }

    // ✅ Generar reporte de productos
    public static void generarReporteProductos(String archivo, Map<String, Integer> ventasPorProducto,
                                               Map<String, Integer> preciosProductos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String id : ventasPorProducto.keySet()) {
                int cantidad = ventasPorProducto.get(id);
                int precio = preciosProductos.getOrDefault(id, 0);
                int total = cantidad * precio;
                bw.write(id + " - Cantidad: " + cantidad + " - Total: " + total);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error al generar reporte de productos: " + e.getMessage());
        }
    }
}




