package org.example;

import org.example.GestionContenido.*;
import org.example.Produccion.DeProService;
import org.example.Produccion.DesarrolladorProducto;
import org.example.Produccion.Fabrica;
import org.example.Produccion.FabricaService;
import org.example.SubsistemaComercial.*;
import org.example.SubsistemaComercial.Carrito;
import org.example.SubsistemaComercial.CarritoService;



import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;



@SpringBootApplication
public class Main {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static Usuario usuarioActivo = null;
    private DeProService deProService;
    private CarritoService carritoService;
    private FabricaService fabricaService;
    private CompraService compraService;
    private LineaCompraService lineaCompraService;
    private LineaCarritoService lineaCarritoService;
    //private DesarrolladorProducto desarrolladorProducto;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        JOptionPane.showMessageDialog(null, "¡Bienvenido a Cabrita Sakura!");

        int opcion;
        do {
            if (usuarioActivo == null) {

                String menu = """
                        1. Registrar usuario
                        2. Iniciar sesión
                        0. Salir
                        """;
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu));

                switch (opcion) {
                    case 1 -> registrarUsuario();
                    case 2 -> login();
                    case 0 -> JOptionPane.showMessageDialog(null, "Adiós");
                    default -> JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } else {

                String menuUsuario = """
                        1. Agregar producto
                        2. Listar productos
                        3. Cerrar sesión
                        0. Salir
                        """;
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menuUsuario));

                switch (opcion) {
                    case 1 -> agregarProducto();
                    case 2 -> listarProductos();
                    case 3 -> cerrarSesion();
                    case 0 -> JOptionPane.showMessageDialog(null, "Adiós");
                    default -> JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            }
        } while (opcion != 0);
    }

    private static void registrarUsuario() {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String email = JOptionPane.showInputDialog("Email:");
        int ID = usuarios.size() + 1;
        int password = Integer.parseInt(JOptionPane.showInputDialog("Password (número entero):"));
        String direccion = JOptionPane.showInputDialog("Dirección de envío:");
        String metodoPago = JOptionPane.showInputDialog("Método de pago:");

        Cliente nuevo = new Cliente(nombre, email, "Cliente", "Activo", ID, password, direccion, metodoPago);
        usuarios.add(nuevo);

        JOptionPane.showMessageDialog(null, "Usuario registrado con ID: " + ID);
    }

    private static void login() {
        String email = JOptionPane.showInputDialog("Email:");
        int password = Integer.parseInt(JOptionPane.showInputDialog("Password (número):"));

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getPasswordHash() == password) {
                usuarioActivo = u;
                JOptionPane.showMessageDialog(null, "Bienvenido, " + u.getNombre());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Credenciales inválidas.");
    }

    private static void agregarProducto() {
        String nombre = JOptionPane.showInputDialog("Nombre del producto:");
        String descripcion = JOptionPane.showInputDialog("Descripción:");
        String fecha = JOptionPane.showInputDialog("Fecha de lanzamiento:");
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock:"));

        Producto p = new Producto(productos.size() + 1, stock, nombre, descripcion, fecha, null);
        productos.add(p);

        JOptionPane.showMessageDialog(null, "Producto agregado.");
    }

    private static void listarProductos() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos.");
            return;
        }

        StringBuilder sb = new StringBuilder("Productos:\n");
        for (Producto p : productos) {
            sb.append(p.getId()).append(". ")
              .append(p.getNombre()).append(" (stock: ")
              .append(p.getStock()).append(")\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }


    private static void cerrarSesion() {
        JOptionPane.showMessageDialog(null, "Sesión cerrada de " + usuarioActivo.getNombre());
        usuarioActivo = null;
    }

    @Bean
    public CommandLineRunner run(ProductoService productoService, UsuarioService usuarioService,
                                 DesarrolladorProducto desarrolladorProducto, Carrito carrito, Fabrica fabrica) {
        return args -> {

            // PRODUCTO CON CATEGORIA

            System.out.println("-- Bienvenido al Sistema --");

            System.out.println("Listado de productos");
            List<Producto> productos = productoService.getAllProducts();
            productos.forEach(System.out::println);

            Categoria categoria = new Categoria(1L, "Cepillo", "Cepillo cepilloso");


            Producto nuevoProducto = new Producto
                    (0, 8, "CepilloGiratorio",
                            "Un cepillo que gira y te peina",
                            "10/10/24", categoria);

            productoService.saveProducto(nuevoProducto);

            System.out.println("Producto agregado: " + nuevoProducto);

            System.out.println("Traer producto por id");
            Integer id_a_consultar = 1;

            productoService.getProductById(id_a_consultar).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No se encontró el producto")
            );

            Producto informacionParaActualizar = new Producto(
                    0,
                    8, // nuevo stock
                    "CepilloGiratorioPro",
                    "Cepillo mejorado que gira más rápido",
                    "10/10/24",
                    categoria
            );

            Producto informacionYaActualizada = productoService.updateProductInfo(id_a_consultar, informacionParaActualizar);

            if (informacionYaActualizada != null) {
                System.out.println("Producto actualizado: " + informacionYaActualizada);
            } else {
                System.out.println("No se pudo actualizar el producto con id " + id_a_consultar);
            }



            System.out.println("Eliminar el producto con id 2");
            Integer id_a_eliminar = 2;

            boolean resultado = productoService.deleteProductInfo(id_a_eliminar);

       //     resultado = productoService.deleteProductInfo(7L);

            if(resultado){
                System.out.println("Se ha eliminado el producto: " + resultado );
            }else{
                System.out.println("No se pudo eliminar el producto");
            }

            System.out.println("Productos finales en la base de datos:");
            productoService.getAllProducts().forEach(System.out::println);


            //USUARIOS


            System.out.println("=== Listado de Usuarios ===");
            List<Usuario> usuarios = usuarioService.getAllUsers();
            usuarios.forEach(System.out::println);

            Usuario nuevoUsuario = new Usuario(
                    0, "Pedro", "pedrito@gmail", "Comprador estrella", "Abierto", 1234
            );
            usuarioService.saveUser(nuevoUsuario);
            System.out.println("Usuario agregado: " + nuevoUsuario);

            //Dueña
            Dueña nuevaDueña = new Dueña(
                    "Benedicto ", "ElBeneDictoSHHHH@gmail.com ", "Dueña ", "Oculto", 0, 6666,
                    1245, "2/10/2025"
            );

            usuarioService.saveUser(nuevaDueña);
            System.out.println(" Dueña agregada "+ nuevaDueña);

            //AdministadorUsuario
            AdministradorUsuario nuevoAdministadorUsuario = new AdministradorUsuario(
                    "Pepe ", "pepeeeee@gmail.com ", "Administrador ", "Activo", 0, 8864,
                    5
            );

            usuarioService.saveUser(nuevoAdministadorUsuario);
            System.out.println(" Administrador de usuario agregado " + nuevoAdministadorUsuario);

            //AdministradorContenido
            AdministradorContenido nuevoAdministradorContenido = new AdministradorContenido(
                    "Raul ", "rawwwwl@gmail.com ", "Administrador ", "Activo", 0, 6647,
                    "Todos"
            );

            usuarioService.saveUser(nuevoAdministradorContenido);
            System.out.println(" Administrador de contenido agregado " + nuevoAdministradorContenido);

            //Cliente agregado
            Cliente nuevoCliente = new Cliente(
                    "Sofia ", "sofiiiz@gmail.com ", "Cliente ", "Activo", 0, 5678,
                    "Calle 45 #10-30", " Efectivo"
            );

            usuarioService.saveUser(nuevoCliente);
            System.out.println(" Cliente agregado "+ nuevoCliente);

            System.out.println("Traer usuario por id");
            Integer id_a_consultar_usuario = 1;
            usuarioService.getUserById(id_a_consultar_usuario).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No se encontró el usuario")
            );

            Usuario informacionParaActualizarUsuario = new Usuario(
                    0, "Esteban", "estebananote@gmail.com", "Comprador VIP", "Cerrado", 4545
            );
            Usuario informacionYaActualizadaUsuario =
                    usuarioService.updateUserInfo(id_a_consultar_usuario, informacionParaActualizarUsuario);

            if (informacionYaActualizadaUsuario != null) {
                System.out.println("Usuario actualizado: " + informacionYaActualizadaUsuario);
            } else {
                System.out.println("No se pudo actualizar el usuario con id " + id_a_consultar_usuario);
            }

            System.out.println("Eliminar el usuario con id 2");
            Integer id_a_eliminar_usuario = 2;
            boolean resultadoUsuario = usuarioService.deleteUserInfo(id_a_eliminar_usuario);

            if (resultadoUsuario) {
                System.out.println("Se ha eliminado el usuario correctamente.");
            } else {
                System.out.println("No se pudo eliminar el usuario.");

            }

            System.out.println("Usuarios finales en la base de datos:");
            usuarioService.getAllUsers().forEach(System.out::println);



            // Desarrollador Producto

            System.out.println("=== Listado de Desarrolladores de producto ===");
            List<DesarrolladorProducto> desarrolladorProductos = deProService.getAllDePro();
            desarrolladorProductos.forEach(System.out::println);

            DesarrolladorProducto nuevoDesarrollador = new DesarrolladorProducto(
                    "Juguetes"
            );
            deProService.saveDePro(nuevoDesarrollador);
            System.out.println("Desarrollador de producto agregado: " + nuevoDesarrollador);

            System.out.println("Traer desarrollador por id");
            Integer id_a_consultar_desarrollador = 1;
            deProService.getDeProById(id_a_consultar_usuario).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No se encontró el desarrollador")
            );

            DesarrolladorProducto informacionParaActualizarDesarrollador = new DesarrolladorProducto(
                    "Cajas"
            );
            DesarrolladorProducto informacionYaActualizadaDesarrollador =
                    deProService.updateDeProInfo(id_a_consultar_desarrollador, informacionParaActualizarDesarrollador);

            if (informacionYaActualizadaUsuario != null) {
                System.out.println("Desarrollador actualizado: " + informacionYaActualizadaDesarrollador);
            } else {
                System.out.println("No se pudo actualizar el desarrollador con id " + id_a_consultar_desarrollador);
            }

            System.out.println("Eliminar el desarrollador con id 2");
            Integer id_a_eliminar_desarrollador = 2;
            boolean resultadoDesarrollador = deProService.deleteDeProInfo(id_a_eliminar_desarrollador);

            if (resultadoDesarrollador) {
                System.out.println("Se ha eliminado el desarrollador correctamente.");
            } else {
                System.out.println("No se pudo eliminar el desarrollador.");

            }

            System.out.println("Desarrollador finales en la base de datos:");
            deProService.getAllDePro().forEach(System.out::println);

            // CARRITO

            System.out.println("=== Listado de Carritos ===");
            List<Carrito> carritos = carritoService.getAllCarts();
            carritos.forEach(System.out::println);

            Carrito nuevoCarrito = new Carrito(
                    12, "19/01/2025"
            );
            carritoService.saveCarrito(nuevoCarrito);
            System.out.println("Carrito agregado: " + nuevoCarrito);

            System.out.println("Traer carrito por id");
            Integer id_a_consultar_carrito = 1;
            carritoService.getCarritoById(id_a_consultar_carrito).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No se encontró el carrito")
            );

            Carrito informacionParaActualizarCarrito = new Carrito(
                    22, "31/10/2025"
            );
            Carrito informacionYaActualizadaCarrito =
                    carritoService.updateCarritoInfo(id_a_consultar_carrito, informacionParaActualizarCarrito);

            if (informacionYaActualizadaCarrito != null) {
                System.out.println("Carrito actualizado: " + informacionYaActualizadaCarrito);
            } else {
                System.out.println("No se pudo actualizar el carrito con id " + id_a_consultar_carrito);
            }

            System.out.println("Eliminar el carrito con id 2");
            Integer id_a_eliminar_carrito = 2;
            boolean resultadoCarrito = carritoService.deleteCartInfo(id_a_eliminar_carrito);

            if (resultadoCarrito) {
                System.out.println("Se ha eliminado el carrito correctamente.");
            } else {
                System.out.println("No se pudo eliminar el carrito.");

            }

            System.out.println("Carrito finales en la base de datos:");
            carritoService.getAllCarts().forEach(System.out::println);

            // FABRICA

            System.out.println("=== Listado de Fabricas ===");
            List<Fabrica> fabricas = fabricaService.getAllFabric();
            fabricas.forEach(System.out::println);

            Fabrica nuevaFabrica = new Fabrica(
                    2, 25, 3, "Paises Bajos", "La Haya "
            );
            fabricaService.saveFabrica(nuevaFabrica);
            System.out.println("Fabrica agregada: " + nuevaFabrica);

            System.out.println("Traer fabrica por id");
            Integer id_a_consultar_fabrica = 2;
            fabricaService.getFabricaById(id_a_consultar_fabrica).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No se encontró la fabrica")
            );

            Fabrica informacionParaActualizarFabrica = new Fabrica(
                    3, 30, 2, "Paises Bajos", "Assen "
            );
            Fabrica informacionYaActualizadaFabrica =
                    fabricaService.updateFabricaInfo(id_a_consultar_fabrica, informacionParaActualizarFabrica);

            if (informacionYaActualizadaCarrito != null) {
                System.out.println("Fabrica actualizada: " + informacionYaActualizadaFabrica);
            } else {
                System.out.println("No se pudo actualizar el carrito con id " + id_a_consultar_fabrica);
            }

            System.out.println("Eliminar la fabrica con id 3");
            Integer id_a_eliminar_fabrica = 3;
            boolean resultadoFabrica = fabricaService.deleteFabricInfo(id_a_eliminar_fabrica);

            if (resultadoFabrica) {
                System.out.println("Se ha eliminado la fabrica correctamente.");
            } else {
                System.out.println("No se pudo eliminar la fabrica.");

            }

            System.out.println("Fabrica finales en la base de datos:");
            fabricaService.getAllFabric().forEach(System.out::println);


            //COMPRA

            System.out.println("=== Listado de Compras ===");
            List<Compra> compras = compraService.getAllSales();
            compras.forEach(System.out::println);

            Compra nuevaCompra = new Compra(
                    0, "30/07/2025", 300
            );
            compraService.saveSales(nuevaCompra);
            System.out.println("Compra agregada: " + nuevaCompra);

            System.out.println("Traer compra por id");
            Integer id_a_consultar_compra = 2;
            compraService.getSaleById(id_a_consultar_compra).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No se encontró la fabrica")
            );

            Compra informacionParaActualizarCompra = new Compra(
                    3, "5/08/2025", 500
            );
            Compra informacionYaActualizadaCompra =
                    compraService.updateSalesInfo(id_a_consultar_compra, informacionParaActualizarCompra);

            if (informacionYaActualizadaCompra != null) {
                System.out.println("Compra actualizada: " + informacionYaActualizadaCompra);
            } else {
                System.out.println("No se pudo actualizar la compra con id " + id_a_consultar_compra);
            }

            System.out.println("Eliminar la compra con id 3");
            Integer id_a_eliminar_compra = 3;
            boolean resultadoCompra = compraService.deleteSalesInfo(id_a_eliminar_compra);

            if (resultadoCompra) {
                System.out.println("Se ha eliminado la compra correctamente.");
            } else {
                System.out.println("No se pudo eliminar la compra.");

            }

            System.out.println("Compras finales en la base de datos:");
            compraService.getAllSales().forEach(System.out::println);

            //Linea Compra

            System.out.println("=== Listado de Lineas de Compra ===");
            List<LineaCompra> lineaCompras = lineaCompraService.getAllSalesLine();
            lineaCompras.forEach(System.out::println);

            LineaCompra nuevaLineaCompra = new LineaCompra(
                    10, 300
            );
            lineaCompraService.saveSalesLine(nuevaLineaCompra);
            System.out.println("Linea Compra agregada: " + nuevaLineaCompra);

            System.out.println("Traer Linea compra por id");
            Integer id_a_consultar_linea_compra = 2;
            lineaCompraService.getSaleLineById(id_a_consultar_linea_compra).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No se encontró la linea compra")
            );

            LineaCompra informacionParaActualizarLineaCompra = new LineaCompra(
                    50, 800
            );
            LineaCompra informacionYaActualizadaLineaCompra =
                    lineaCompraService.updateSalesLineInfo(id_a_consultar_linea_compra, informacionParaActualizarLineaCompra);

            if (informacionYaActualizadaLineaCompra != null) {
                System.out.println("Linea actualizada: " + informacionYaActualizadaLineaCompra);
            } else {
                System.out.println("No se pudo actualizar la linea compra con id " + id_a_consultar_linea_compra);
            }

            System.out.println("Eliminar la linea compra con id 3");
            Integer id_a_eliminar_linea_compra = 3;
            boolean resultadoLineaCompra = lineaCompraService.deleteSalesLineInfo(id_a_eliminar_linea_compra);

            if (resultadoLineaCompra) {
                System.out.println("Se ha eliminado la linea compra correctamente.");
            } else {
                System.out.println("No se pudo eliminar la linea compra.");

            }

            System.out.println("Lineas compras finales en la base de datos:");
            lineaCompraService.getAllSalesLine().forEach(System.out::println);

            //Linea Carrito

            System.out.println("=== Listado de Lineas de carrito ===");
            List<LineaCarrito> lineaCarritos = lineaCarritoService.getAllCartsLine();
            lineaCarritos.forEach(System.out::println);

            LineaCarrito nuevaLineaCarrito = new LineaCarrito(
                    15, 350
            );
            lineaCarritoService.saveCartsLine(nuevaLineaCarrito);
            System.out.println("Linea Carrito agregada: " + nuevaLineaCarrito);

            System.out.println("Traer Linea carrito por id");
            Integer id_a_consultar_linea_carrito = 1;
            lineaCarritoService.getCartLineById(id_a_consultar_linea_carrito).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No se encontró la linea carrito")
            );

            LineaCarrito informacionParaActualizarLineaCarrito = new LineaCarrito(
                    55, 850
            );
            LineaCarrito informacionYaActualizadaLineaCarrito =
                    lineaCarritoService.updateCartsLineInfo(id_a_consultar_linea_compra, informacionParaActualizarLineaCarrito);

            if (informacionYaActualizadaLineaCarrito != null) {
                System.out.println("Linea actualizada: " + informacionYaActualizadaLineaCarrito);
            } else {
                System.out.println("No se pudo actualizar la linea carrito con id " + id_a_consultar_linea_carrito);
            }

            System.out.println("Eliminar la linea carrito con id 3");
            Integer id_a_eliminar_linea_carrito = 3;
            boolean resultadoLineaCarrito = lineaCarritoService.deleteCartLineInfo(id_a_eliminar_linea_carrito);

            if (resultadoLineaCarrito) {
                System.out.println("Se ha eliminado la linea carrito correctamente.");
            } else {
                System.out.println("No se pudo eliminar la linea carrito.");

            }

            System.out.println("Lineas carritos finales en la base de datos:");
            lineaCarritoService.getAllCartsLine().forEach(System.out::println);







        };



    }


}




