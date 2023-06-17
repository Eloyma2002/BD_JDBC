package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Productos;

import java.util.List;

public class Pagos {

    public static void pantallaPago(List<Productos> cesta) {
        System.out.println(Color.YELLOW + "*****************************");
        System.out.println("**         Pagos            **");
        System.out.println(Color.YELLOW + "*****************************" + Color.RESET);
        int total = 0;
        for (int i = 0; i < cesta.size(); i++) {
            System.out.println(Color.YELLOW);
            System.out.print(i + "   " + Color.CYAN +cesta.get(i).getNombre() + Color.RESET + " " + cesta.get(i).getPvp() + "€ " + cesta.get(i).getUnidades() + " unidades");
            total += cesta.get(i).getPvp() * cesta.get(i).getUnidades();
        }
        System.out.println(Color.RESET + "-----------------------------");
        System.out.println("Total cesta: " + Color.BLUE_BOLD + total + "€" + Color.RESET);
        System.out.println(Color.RESET + "-----------------------------");
        System.out.println("Tarjetas disponibles");
        // muestraTarjetas:
    }
}
