package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.Connection;
import java.util.Scanner;

public class Settings {
    public static void main(Usuario usuario) {
        Connection con = ConexionJDBC.creaConexion();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(Color.RESET);
            System.out.println(Color.YELLOW + "*****************************");
            System.out.println("**         Settings        **");
            System.out.println("Usuari: " + usuario.getNombre() + "   " + Color.RED_BOLD + usuario.getUsername() + Color.RESET);
            System.out.println("*****************************" + Color.RESET);
            System.out.println();
            System.out.println(Color.BLUE_BOLD + "a) " + Color.RESET + "Modifica datos personales");
            System.out.println(Color.BLUE_BOLD + "b) " + Color.RESET + "Cambia contraseña");
            System.out.println(Color.BLUE_BOLD + "x) " + Color.RESET + "Salir" + Color.BLACK);
            System.out.print(Color.YELLOW_BACKGROUND + "Elige una opción: " + Color.RESET);

            String elecion = scanner.nextLine();
            if (elecion.equalsIgnoreCase("a")) {
            } else if (elecion.equalsIgnoreCase("b")) {
            } else if (elecion.equalsIgnoreCase("x")) {
                return;
            }
        }
    }
}

