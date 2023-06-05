package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;

import java.sql.Connection;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Limbo {
    public static void main(String[] args) {
        Connection con = ConexionJDBC.creaConexion();
        Scanner scanner = new Scanner(System.in);

        System.out.println(Color.YELLOW + "*****************************");
        System.out.println("**         Limbo app       **");
        System.out.println("*****************************" + Color.RESET);
        System.out.println(Color.BLUE_BOLD + "1) " + Color.RESET + "Login");
        System.out.println(Color.BLUE_BOLD + "2) " + Color.RESET + "Registrar-se");
        System.out.println(Color.BLUE_BOLD + "x) " + Color.RESET + "Sortit" + Color.BLACK);
        System.out.print(Color.YELLOW_BACKGROUND + "Esculli una opció: ");

         String elecion = scanner.nextLine();

         if (Objects.equals(elecion, "1")) {
             System.out.println();
         } else if (Objects.equals(elecion, "2")) {
             Registro.registrarse();
         } else if (elecion.equals("x")) {
             return;
         } else {
             System.out.println("Opción no válida");
         }

    }

    public static void info(String texte) {
        System.out.println(Color.BLUE_BOLD + "\t" + texte + Color.RESET);
    }

    public static void errada(String texte) {
        System.out.println(Color.RED_BOLD + "\t" + texte + Color.RESET);
    }
}
