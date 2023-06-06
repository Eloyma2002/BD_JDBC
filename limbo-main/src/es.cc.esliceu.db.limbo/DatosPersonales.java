package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.dao.UsuariosDAOImpl;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DatosPersonales {
    public static void main(Usuario usuario) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();
        UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(Color.RESET);
            System.out.println(Color.YELLOW + "*****************************");
            System.out.println("**     Datos personales        **");
            System.out.println("Usuario: " + usuario.getNombre() + "   " + Color.RED_BOLD + usuario.getUsername() + Color.RESET);
            System.out.println(Color.YELLOW + "*****************************" + Color.RESET);
            System.out.println(Color.BLUE_BOLD + "d) " + Color.RESET + "Setting");
            System.out.println(Color.BLUE_BOLD + "c) " + Color.RESET + "Compras realizadas");
            System.out.println(Color.BLUE_BOLD + "a) " + Color.RESET + "Direcciones" + Color.BLACK);
            System.out.println(Color.BLUE_BOLD + "t) " + Color.RESET + "Targetas" + Color.BLACK);
            System.out.println(Color.BLUE_BOLD + "x) " + Color.RESET + "Salir" + Color.BLACK);

            String elecion = scanner.nextLine();
            if (elecion.equalsIgnoreCase("d")) {
                usuario = settings(usuario);
            } else if (elecion.equalsIgnoreCase("c")) {
            } else if (elecion.equalsIgnoreCase("a")) {

            } else if (elecion.equalsIgnoreCase("t")){
            } else if (elecion.equalsIgnoreCase("h")) {
                return;
            }
        }
    }

    private static Usuario settings(Usuario usuario) throws SQLException {

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println(Color.RESET);
            System.out.println(Color.YELLOW + "*****************************");
            System.out.println("**          Settings        **");
            System.out.println("Usuario: " + usuario.getNombre() + "   " + Color.RED_BOLD + usuario.getUsername() + Color.RESET);
            System.out.println(Color.YELLOW + "*****************************" + Color.RESET);
            System.out.println(Color.BLUE_BOLD + "a) " + Color.RESET + "Modifica datos personales");
            System.out.println(Color.BLUE_BOLD + "b) " + Color.RESET + "Cambio contraseña");
            System.out.println(Color.BLUE_BOLD + "x) " + Color.RESET + "Salir" + Color.BLACK);

            String elecion = scanner.nextLine();
            if (elecion.equalsIgnoreCase("a")) {
                usuario = modificaDatosPersonales(usuario);
            } else if (elecion.equalsIgnoreCase("b")) {
                usuario = cambiaContrasenaUsuario(usuario);
            } else if (elecion.equalsIgnoreCase("x")) {
                break;
            }
        }
        return usuario;
    }

    private static Usuario cambiaContrasenaUsuario(Usuario usuario) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();
        Scanner scanner = new Scanner(System.in);
        UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl();

        System.out.print(Color.BLACK);
        System.out.println(Color.YELLOW_BACKGROUND + "Contraseña antigua:" + Color.RESET);
        String antiguaPass = scanner.nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "Contraseña nueva:" + Color.RESET);
        String nuevaContrasena = scanner.nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "Repite la nueva contraseña:" + Color.RESET);
        String repiteContrasena = scanner.nextLine();

        if (!usuario.getPassword().equals(antiguaPass)) {
            Limbo.errada("La contraseña antigua no coincide con la de tu usuario");
            return usuario;
        }
        if (!nuevaContrasena.equals(repiteContrasena)) {
            Limbo.errada("Las contraseñas no coinciden");
            return usuario;
        }

        System.out.println("Email enviado");
        Limbo.info("Se ha enviado un correo electrónico con una referencia a la dirección: " + usuario.getEmail());

        System.out.println(Color.YELLOW_BACKGROUND + "Referencia?:" + Color.RESET);
        String referencia = scanner.nextLine();

        usuario.setPassword(nuevaContrasena);
        usuariosDAO.cambiaContrasena(usuario);

        return usuario;
    }

    private static Usuario modificaDatosPersonales(Usuario usuario) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();
        Scanner scanner = new Scanner(System.in);
        UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl();

        System.out.println(Color.BLACK);
        System.out.println(Color.YELLOW_BACKGROUND + "Nom:" + Color.RESET + Color.BLUE_BOLD + "(" + usuario.getNombre() + ")" + Color.RESET + Color.BLUE_BOLD);
        String newNombre = scanner.nextLine();
        usuario.setNombre(newNombre);
        System.out.println(Color.YELLOW_BACKGROUND + "Llinatge1: " + Color.RESET + Color.BLUE_BOLD + "(" + usuario.getPrimerApellido() + ")" + Color.RESET + Color.BLUE_BOLD);
        String newPrimerApellido = scanner.nextLine();
        usuario.setPrimerApellido(newPrimerApellido);
        System.out.println(Color.YELLOW_BACKGROUND + "Llinatge2: " + Color.RESET + Color.BLUE_BOLD + "(" + usuario.getSegonApellido() + ")" + Color.RESET + Color.BLUE_BOLD);
        String newSegonApellido = scanner.nextLine();
        usuario.setSegonApellido(newSegonApellido);
        usuario = usuariosDAO.cambiaDatos(usuario);
        return usuario;
    }
}
