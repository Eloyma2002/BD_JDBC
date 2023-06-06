package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.dao.UsuariosDAOImpl;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.Connection;
import java.util.Scanner;

public class DatosPersonales {
    public static void main(Usuario usuario) {
        Connection con = ConexionJDBC.creaConexion();
        UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(Color.RESET);
            System.out.println(Color.YELLOW + "*****************************");
            System.out.println("**         Datos personales        **");
            System.out.println("Usuario: " + usuario.getNombre() + "   " + Color.RED_BOLD + usuario.getUsername() + Color.RESET);
            System.out.println("*****************************" + Color.RESET);
            System.out.println();
            System.out.println(Color.BLUE_BOLD + "d) " + Color.RESET + "Setting");
            System.out.println(Color.BLUE_BOLD + "c) " + Color.RESET + "Compras realizadas");
            System.out.println(Color.BLUE_BOLD + "a) " + Color.RESET + "Direcciones" + Color.BLACK);
            System.out.println(Color.BLUE_BOLD + "t) " + Color.RESET + "Targetas" + Color.BLACK);
            System.out.println(Color.BLUE_BOLD + "x) " + Color.RESET + "Salir" + Color.BLACK);

            String elecion = scanner.nextLine();
            if (elecion.equalsIgnoreCase("d")) {

            } else if (elecion.equalsIgnoreCase("c")) {
            } else if (elecion.equalsIgnoreCase("a")) {

            } else if (elecion.equalsIgnoreCase("t")){
            } else if (elecion.equalsIgnoreCase("h")) {
                return;
            }
        }
    }
}
