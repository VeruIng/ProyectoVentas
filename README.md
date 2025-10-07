Tercera Entrega - Proyecto "Gestión de Ventas"

Descripción del proyecto
Este proyecto tiene como propósito desarrollar un programa en **Java** que procese información relacionada con **productos, vendedores y ventas**, para generar reportes automáticos que permitan visualizar las ventas totales por producto y por vendedor.  

Durante esta tercera entrega, se logró consolidar las funcionalidades principales del sistema, incluyendo la **lectura, validación, procesamiento y generación de reportes** de los datos almacenados en archivos `.txt`.  
Funciones principales
1. **Lectura de productos:**
   - El programa lee el archivo `productos.txt`, validando que los datos sean correctos y que los precios sean válidos.  
   - Si se encuentra un precio inválido o un formato incorrecto, el sistema muestra un mensaje de advertencia en consola.

2. **Lectura de vendedores:**  
   - Se leen los datos desde `vendedores.txt` y se validan los campos de documento, nombre y apellido.  
   - Los datos son almacenados en un `HashMap` para fácil acceso por identificador.

3. **Cálculo de ventas:**  
   - El sistema procesa los archivos de ventas generados automáticamente (`ventas_XXXX.txt`) y acumula las ventas por vendedor y por producto.  

4. **Generación de reportes:**  
   - Se generan dos reportes finales:
     - `reporte_vendedores.txt`: muestra el total de ventas por cada vendedor.  
     - `reporte_productos.txt`: muestra las cantidades vendidas por producto y el total de ventas calculado.  

---

Estructura del proyecto
```
ProyectoVentas/
│
├── src/
│   └── com.ventas/
│       ├── Main.java
│       ├── GenerateInfoFiles.java
│       └── module-info.java
│
├── productos.txt
├── vendedores.txt
├── ventas_XXXX.txt
├── reporte_vendedores.txt
├── reporte_productos.txt
└── README.md
```

---

##  Avances logrados en la tercera entrega
- Se implementaron métodos separados para cada proceso (lectura, validación, cálculo y generación de reportes).  
- Se añadieron validaciones para evitar errores de formato y datos faltantes.  
- Se organizaron las estructuras de datos mediante `HashMap`.  
- Se generaron correctamente los reportes de vendedores y productos, mostrando mensajes en consola.  

---

##  Aspectos pendientes o posibles mejoras
- Implementar una interfaz gráfica para la visualización de reportes.  
- Incorporar manejo de excepciones más detallado y registro en archivo de errores.  
- Permitir ingreso dinámico de datos desde el usuario.  

---

## 🧩 Integrantes del grupo
- **Veruzka Aguas Castelar**  
- **Mayerli Agudelo Monje**
- **Lina Marcela Pérez Murcia**

##  Evidencias
El programa genera en consola mensajes de verificación, como:
```
Precio inválido en: P1;Shampoo;18706
Reportes generados correctamente.
```
Además, los reportes se crean en la carpeta del proyecto con los datos validados.  
