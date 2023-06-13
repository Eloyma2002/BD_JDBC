package es.cc.esliceu.db.limbo;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.util.Scanner;

public class Ayuda {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println(Color.YELLOW + "*****************************");
        System.out.println("**          Ayuda          **");
        System.out.println("*****************************" + Color.RESET);
        System.out.println(Color.YELLOW + "De parte del equipo directivo: Esperemos que Limbo esté junto a tus espectativas, encontrarás");
        System.out.println("infinidad de productos que esperemos que te puedan ayudar, si quieres más información contactnos" + Color.RESET);
        System.out.println(Color.BLUE_BOLD + "x) " + Color.RESET + "Salir" + Color.BLACK);
        System.out.print(Color.YELLOW_BACKGROUND + "Elige una opció: " + Color.RESET);


        while (true) {
            String eleccion = scanner.nextLine();
            if (eleccion.equalsIgnoreCase("x")) {
                return;
            } else {
                Limbo.errada("Elección no válida");
            }
        }
    }
}

