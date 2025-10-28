package org.example;

import org.example.GestionContenido.UsuarioService;
import org.example.Produccion.DeProService;
import org.example.Produccion.DesarrolladorProducto;
import org.example.SubsistemaComercial.Categoria;
import org.example.SubsistemaComercial.Cliente;
import org.example.SubsistemaComercial.Producto;
import org.example.GestionContenido.Usuario;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


import org.example.SubsistemaComercial.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;



@SpringBootApplication
public class Main {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static Usuario usuarioActivo = null;

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
    public CommandLineRunner run(ProductoService productoService, UsuarioService usuarioService, DesarrolladorProducto desarrolladorProducto) {
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

        };

        // Desarrollador Producto

        System.out.println("=== Listado de Desarrolladores de producto ===");            List<Usuario> usuarios = usuarioService.getAllUsers();
        List<DesarrolladorProducto> desarrolladorProductos = ;
        usuarios.forEach(System.out::println);

        DesarrolladorProducto nuevoDesarrollador = new DesarrolladorProducto(
                "Juguetes"
        );
        usuarioService.saveUser(nuevoUsuario);
        System.out.println("Usuario agregado: " + nuevoUsuario);

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

    };


    };

}


