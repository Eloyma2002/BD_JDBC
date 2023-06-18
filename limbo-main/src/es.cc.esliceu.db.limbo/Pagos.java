package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.dao.ComprasDAO;
import es.cc.esliceu.db.limbo.dao.ComprasDAOImpl;
import es.cc.esliceu.db.limbo.dao.DireccionesDAOImpl;
import es.cc.esliceu.db.limbo.dao.TarjetasDAOImpl;
import es.cc.esliceu.db.limbo.util.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pagos {

    public static void pantallaPago(List<Productos> cesta, Usuario usuario) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        TarjetasDAOImpl tarjetasDAO = new TarjetasDAOImpl();
        List<Tarjetas> tarjetas = new ArrayList<>();

        while (true) {

            System.out.println(Color.YELLOW + "*****************************");
            System.out.println("**         Pagos           **");
            System.out.println(Color.YELLOW + "*****************************" + Color.RESET);

            int total = 0;
            for (int i = 0; i < cesta.size(); i++) {
                System.out.println(Color.YELLOW);
                System.out.print(i + "   " + Color.CYAN + cesta.get(i).getNombre() + Color.RESET + " " + cesta.get(i).getPvp() + "€ " + cesta.get(i).getUnidades() + " unidades");
                total += cesta.get(i).getPvp() * cesta.get(i).getUnidades();
            }

            System.out.println();
            System.out.println(Color.RESET + "-----------------------------");
            System.out.println("Total cesta: " + Color.BLUE_BOLD + total + "€" + Color.RESET);
            System.out.println(Color.RESET + "-----------------------------");
            System.out.println("Tarjetas disponibles");

            tarjetas = tarjetasDAO.obtenTarjetas(usuario);
            muestraTarjetas(tarjetas);

            System.out.println(Color.RESET + "a) Añadir tarjeta");
            System.out.println("x) Salir" + Color.BLACK);
            System.out.print(Color.YELLOW_BACKGROUND + "Elige una opció: " + Color.RESET);
            String eleccion = scanner.nextLine();

            if (Integer.parseInt(eleccion) >= 0 && Integer.parseInt(eleccion) <= tarjetas.size()) {
                direcciones(tarjetas.get(Integer.parseInt(eleccion)), usuario, cesta);
            } else if (eleccion.equalsIgnoreCase("a")) {
                datosAñadirTarjeta(tarjetas, usuario);
            } else if (eleccion.equalsIgnoreCase("x")) {
                return;
            }
        }
    }

    private static void direcciones(Tarjetas tarjetas, Usuario usuario, List<Productos> cesta) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Direcciones direccion = new Direcciones();
        List<Direcciones> direcciones = new ArrayList<>();
        DireccionesDAOImpl direccionesDAO = new DireccionesDAOImpl();

        System.out.println(Color.RESET + "Direcciones de envio disponibles: ");
        direcciones = direccionesDAO.muestraDirecciones(usuario);
        System.out.println("a) Añade una dirección de envio");
        System.out.println("x) Salir");
        String eleccion = scanner.nextLine();

        if (eleccion.equalsIgnoreCase("a")) {
            infoAñadeDireccion(usuario, direccion);
        } else if (Integer.parseInt(eleccion) >= 0 && Integer.parseInt(eleccion) <= direcciones.size()) {
            pasarACompra(usuario, direcciones.get(Integer.parseInt(eleccion)), cesta, tarjetas);
        }
    }

    private static void pasarACompra(Usuario usuario, Direcciones direccion, List<Productos> cesta, Tarjetas tarjeta) throws SQLException {

        System.out.println(Color.RESET + "Se hará el siguiente pago a:");
        System.out.println(usuario.getNombre() + " " + usuario.getPrimerApellido() + " " + usuario.getSegonApellido() + " " + usuario.getEmail());
        int total = 0;
        for (int i = 0; i < cesta.size(); i++) {
            System.out.println(Color.YELLOW);
            System.out.print(i + "   " + Color.CYAN + cesta.get(i).getNombre() + Color.RESET + " " + cesta.get(i).getPvp() + "€ " + cesta.get(i).getUnidades() + " unidades");
            total += cesta.get(i).getPvp() * cesta.get(i).getUnidades();
        }

        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("Precio total: " + Color.BLUE_BOLD + total + "€" + Color.RESET);
        System.out.println("-----------------------------");
        System.out.println("Con la tarjeta:");
        System.out.println(Color.CYAN + "     " + tarjeta.getNumero() + " " + tarjeta.getTipo() + " " + tarjeta.getFecha());
        System.out.println(Color.RESET + "Con la dirección:");
        System.out.println(Color.CYAN + "     " + direccion.getCalle() + " " + direccion.getNumero() + " " + direccion.getCP() + Color.BLACK);
        System.out.print(Color.YELLOW_BACKGROUND + "Desea continuar con el pago? (S/N)" + Color.RESET);
        Scanner scanner = new Scanner(System.in);
        String eleccion = scanner.nextLine();
        ComprasDAOImpl comprasDAO = new ComprasDAOImpl();

        if (eleccion.equalsIgnoreCase("s")) {
            comprasDAO.añadeCompra(usuario, tarjeta, direccion, cesta);
            Limbo.info("COMPRA REALIZADA CORRECTAMENTE");
            System.out.println(Color.BLACK);
            System.out.print(Color.YELLOW_BACKGROUND + "Pulsa una tecla para continuar" + Color.RESET);
            String pulsa = scanner.nextLine();
            PantallaPrincipal.main(usuario.getUsername(), usuario.getPassword());
        } else if (eleccion.equalsIgnoreCase("n")) {
            PantallaPrincipal.verCesta(cesta, usuario);
        }

    }

    private static void infoAñadeDireccion(Usuario usuario, Direcciones direccion) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DireccionesDAOImpl direccionesDAO = new DireccionesDAOImpl();

        System.out.println("Inserte los datos de la dirección:" + Color.BLACK);

        System.out.print(Color.YELLOW_BACKGROUND + "Calle:" + Color.RESET + Color.BLACK);
        String calle = scanner.nextLine();

        System.out.print(Color.YELLOW_BACKGROUND + "Número:" + Color.RESET + Color.BLACK);
        String numero = scanner.nextLine();

        System.out.print(Color.YELLOW_BACKGROUND + "Puerta:" + Color.RESET + Color.BLACK);
        String puerta = scanner.nextLine();

        System.out.print(Color.YELLOW_BACKGROUND + "Piso:" + Color.RESET + Color.BLACK);
        int piso = scanner.nextInt();

        String ciudad;
        int idCiudad;
        while (true) {
            System.out.print(Color.YELLOW_BACKGROUND + "Ciudad:" + Color.RESET + Color.BLACK);
            Scanner city = new Scanner(System.in);
            ciudad = city.nextLine();
            idCiudad = direccionesDAO.obtenIdCiudad(ciudad);
            if (idCiudad > 0) {
                break;
            }
        }

        String codigoPostal;
        while (true) {
            System.out.print(Color.YELLOW_BACKGROUND + "Código postal:" + Color.RESET);
            codigoPostal = scanner.nextLine();
            if (codigoPostal.length() == 5) {
                break;
            }
        }

        direccion.setCalle(calle);
        direccion.setPiso(piso);
        direccion.setPuerta(puerta);
        direccion.setNumero(numero);
        direccion.setIdCiudad(idCiudad);
        direccion.setCP(codigoPostal);
        direccion.setIdCliente(usuario.getNumero_client());
        direccionesDAO.insertarDireccion(direccion);

    }

    private static void muestraTarjetas(List<Tarjetas> tarjetas) {

        for (int i = 0; i < tarjetas.size(); i++) {
            String j = String.valueOf(i);
            System.out.println(Color.YELLOW + j + "   " + Color.CYAN + tarjetas.get(i).getNumero() + " "
                    + tarjetas.get(i).getTipo() + " " + tarjetas.get(i).getFecha());
        }
    }

    private static List<Tarjetas> datosAñadirTarjeta(List<Tarjetas> tarjetas, Usuario usuario) throws SQLException {
        Tarjetas tarjetas1 = new Tarjetas();
        Scanner scanner = new Scanner(System.in);
        TarjetasDAOImpl tarjetasDAO = new TarjetasDAOImpl();

        System.out.println(Color.YELLOW + "*****************************");
        System.out.println("**     Añadir tarjeta      **");
        System.out.println(Color.YELLOW + "*****************************" + Color.BLACK);
        System.out.print(Color.YELLOW_BACKGROUND + "Indica el tipo de tarjeta (VISA, MASTERCARD, MAESTRO)" + Color.RESET + Color.BLACK);
        String tipo = scanner.nextLine();
        System.out.print(Color.YELLOW_BACKGROUND + "Indica el numero de tarjeta" + Color.RESET + Color.BLACK);
        String numTarjeta = scanner.nextLine();
        System.out.print(Color.YELLOW_BACKGROUND + "Indica la fecha de caducidad" + Color.RESET + Color.BLACK);
        String fechaCad = scanner.nextLine();
        System.out.print(Color.YELLOW_BACKGROUND + "Indica el codigo de seguridad" + Color.RESET + Color.BLACK);
        int numSeguridad = scanner.nextInt();

        if (tipo.equalsIgnoreCase("VISA")) {
            tarjetas1.setTipo(String.valueOf(Tarjetas.tipoTarjeta.VISA));
        } else if (tipo.equalsIgnoreCase("MASTERCARD")) {
            tarjetas1.setTipo(String.valueOf(Tarjetas.tipoTarjeta.MASTERCARD));
        } else if (tipo.equalsIgnoreCase("MAESTRO")) {
            tarjetas1.setTipo(String.valueOf(Tarjetas.tipoTarjeta.MAESTRO));
        } else {
            Limbo.errada("Tipo de tarjeta no válida");
            return null;
        }

        tarjetas1.setNumero(numTarjeta);
        tarjetas1.setFecha(fechaCad);
        tarjetas1.setCodigoSeguridad(numSeguridad);
        tarjetas1.setIdCliente(usuario.getNumero_client());
        tarjetasDAO.insertaTarjetas(tarjetas1);
        return tarjetas;
    }
}
