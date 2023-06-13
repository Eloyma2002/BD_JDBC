package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Limbo {
    public static void main(String[] args) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();
        Scanner scanner = new Scanner(System.in);

        while (true){

            System.out.println(Color.YELLOW + "*****************************");
            System.out.println("**         Limbo app       **");
            System.out.println("*****************************" + Color.RESET);
            System.out.println(Color.BLUE_BOLD + "1) " + Color.RESET + "Login");
            System.out.println(Color.BLUE_BOLD + "2) " + Color.RESET + "Registrarse");
            System.out.println(Color.BLUE_BOLD + "x) " + Color.RESET + "Salida" + Color.BLACK);
            System.out.print(Color.YELLOW_BACKGROUND + "Elige una opció: " + Color.RESET);

            String elecion = scanner.nextLine();

            if (Objects.equals(elecion, "1")) {
                Login.logearse();
            } else if (Objects.equals(elecion, "2")) {
                Registro.registrarse();
            } else if (elecion.equalsIgnoreCase("x")) {
                return;
            } else {
                Limbo.errada("Opción no válida");
                System.out.println();
            }
        }
    }

    public static void info(String texte) {
        System.out.println(Color.BLUE_BOLD + texte + Color.RESET);
    }

    public static void errada(String texte) {
        System.out.println(Color.RED_BOLD + texte + Color.RESET);
    }
}
