package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.dao.ProductosDAOImpl;
import es.cc.esliceu.db.limbo.dao.UsuariosDAOImpl;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Productos;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PantallaPrincipal {

    public static void main(String username, String password) throws SQLException {

        UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl();
        boolean lineaCesta = false;
        List<Productos> cesta = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario();
        ProductosDAOImpl productosDAO = new ProductosDAOImpl();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario = usuariosDAO.asignarValoresUsuario(usuario);

        while (true) {
            System.out.println(Color.RESET);
            System.out.println(Color.YELLOW + "*****************************");
            System.out.println("**         Opciones        **");
            System.out.println("Usuari: " + usuario.getNombre() + "   " + Color.RED_BOLD + usuario.getUsername() + Color.RESET);
            if (lineaCesta) {
                System.out.print(Color.BLUE + "Cesta: " + Color.RESET + Color.RED + calculaTotalCesta(cesta) + "€");
                System.out.println();
            }
            System.out.println(Color.YELLOW + "*****************************" + Color.RESET);
            System.out.println(Color.BLUE_BOLD + "c) " + Color.RESET + "Buscar productos");
            System.out.println(Color.BLUE_BOLD + "v) " + Color.RESET + "Ver cesta");
            System.out.println(Color.BLUE_BOLD + "d) " + Color.RESET + "Datos personales" + Color.BLACK);
            System.out.println(Color.BLUE_BOLD + "h) " + Color.RESET + "Ayuda" + Color.BLACK);
            System.out.println(Color.BLUE_BOLD + "x) " + Color.RESET + "Salir" + Color.BLACK);
            Limbo.errada("---------------   Productos sugeridos ---------------");
            List<Productos> sugerencias = new ArrayList<>();
            sugerencias = productosDAO.sugiereProductos();
            Limbo.errada("-----------------------------------------------------");
            System.out.println(Color.BLUE_BOLD + "(0-4) " + Color.RESET + "Productos sugeridos" + Color.BLACK);
            System.out.print(Color.YELLOW_BACKGROUND + "Elige una opción: " + Color.RESET);

            String eleccion = scanner.nextLine();
            if (eleccion.equalsIgnoreCase("c")) {
                buscarYAgregarProducto(cesta);
                lineaCesta = true;
            } else if (eleccion.equalsIgnoreCase("v")) {
                verCesta(cesta, usuario);
            } else if (eleccion.equalsIgnoreCase("d")) {
                DatosPersonales.main(usuario);
            } else if (eleccion.equalsIgnoreCase("h")) {
                Ayuda.main();
            } else if (eleccion.equalsIgnoreCase("x")) {
                return;
            } else if (Integer.parseInt(eleccion) >= 0 && Integer.parseInt(eleccion) <= 4){
                añadirProducto(Integer.parseInt(eleccion), cesta, sugerencias);
                lineaCesta = true;
            } else {
                Limbo.errada("Opción no válida");
            }
        }
    }

    public static void verCesta(List<Productos> cesta, Usuario usuario) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.RESET);
        System.out.println(Color.YELLOW + "*****************************");
        System.out.println("**         Cesta           **");
        System.out.println(Color.YELLOW + "*****************************" + Color.RESET);

        if (cesta.size() == 0) {
            Limbo.errada("No hay productos en tu cesta");
        } else {

            int total = 0;
            for (int i = 0; i < cesta.size(); i++) {
                System.out.println(Color.YELLOW);
                System.out.print(i + "   " + Color.CYAN +cesta.get(i).getNombre() + Color.RESET + " " + cesta.get(i).getPvp() + "€ " + cesta.get(i).getUnidades() + " unidades");
                total += cesta.get(i).getPvp() * cesta.get(i).getUnidades();
            }

            while (true) {
                System.out.println();
                System.out.println("-----------------------------");
                System.out.println("Precio total: " + Color.BLUE_BOLD + total + "€" + Color.RESET);
                System.out.println("-----------------------------");
                System.out.println("e) Eliminar producto");
                System.out.println("p) Pagar");
                System.out.println("x) Salir de la cesta");
                String eleccion = scanner.nextLine();

                if (eleccion.equalsIgnoreCase("e")) {
                    eliminaProducto(cesta);
                } else if (eleccion.equalsIgnoreCase("p")) {
                    Pagos.pantallaPago(cesta, usuario);
                } else if (eleccion.equalsIgnoreCase("x")) {
                    return;
                }
            }
        }
    }

    public static void eliminaProducto(List<Productos> cesta) {
        Scanner scanner = new Scanner(System.in);
        int i;
        while (true) {
            for (i = 0; i < cesta.size(); i++) {
                System.out.println(Color.YELLOW);
                System.out.print(i + "   " + Color.CYAN + cesta.get(i).getNombre() + Color.RESET + " " + cesta.get(i).getPvp() + "€ " + cesta.get(i).getUnidades() + " unidades");
            }
            System.out.println("Que producto desea eliminar?");
            int eleccion = scanner.nextInt();

            if (eleccion >= 0 && eleccion <= cesta.size()) {
                System.out.println(Color.YELLOW + "Se ha elimidado el producto " + Color.CYAN + cesta.get(eleccion).getNombre());
                cesta.remove(eleccion);
                break;
            } else {
                Limbo.errada("No hay ningún objeto con ese índice en la cesta");
            }
        }
    }

    private static double calculaTotalCesta(List<Productos> cesta) {
        double total = 0;
        for (Productos productos : cesta) {
            total += productos.getPvp() * productos.getUnidades();
        }
        return total;
    }

    private static void buscarYAgregarProducto(List<Productos> cesta) throws SQLException {
        ProductosDAOImpl productosDAO = new ProductosDAOImpl();
        Scanner scanner = new Scanner(System.in);
        List<Productos> list = new ArrayList<>();

        System.out.println(Color.RESET);
        System.out.println(Color.YELLOW + "*****************************");
        System.out.println("**         Busca           **");
        System.out.println(Color.YELLOW + "*****************************" + Color.RESET);
        System.out.println(Color.BLACK);
        System.out.print(Color.YELLOW_BACKGROUND + "Nombre del producto: " + Color.RESET);
        String nombreProducto = scanner.nextLine();
        System.out.println(Color.BLACK);
        System.out.print(Color.YELLOW_BACKGROUND + "Descripción: " + Color.RESET);
        String descripcionProducto = scanner.nextLine();
        System.out.println(Color.BLACK);
        System.out.print(Color.YELLOW_BACKGROUND + "Marca: " + Color.RESET);
        String marcaProducto = scanner.nextLine();
        System.out.println(Color.BLACK);
        System.out.println(Color.YELLOW_BACKGROUND + "Categoría: " + Color.RESET);
        System.out.println();
        muestraCategorias();
        int categoriaProducto = scanner.nextInt();

        if (nombreProducto.matches("\\s*")) {
            nombreProducto = "";
        }
        if (descripcionProducto.matches("\\s*")) {
            descripcionProducto = "";
        }
        if (marcaProducto.matches("\\s*")) {
            marcaProducto = "";
        }

        if (nombreProducto.equals("") && descripcionProducto.equals("")
                && marcaProducto.equals("") && categoriaProducto == 0) {
            list = productosDAO.obtenTodosLosProductosOPorCategoria(categoriaProducto);
        } else if (nombreProducto.length() > 0 || descripcionProducto.length() > 0 || marcaProducto.length() > 0) {
            list = productosDAO.buscarProductos(nombreProducto, descripcionProducto, marcaProducto);
        } else if (categoriaProducto > 0 && categoriaProducto < 9) {
            list = productosDAO.obtenTodosLosProductosOPorCategoria(categoriaProducto);
        }

        System.out.println(Color.BLACK);
        System.out.print(Color.YELLOW_BACKGROUND + "Elige una opción: " + Color.RESET);
        int eleccion = scanner.nextInt();
        añadirProducto(eleccion, cesta, list);
    }

    private static void añadirProducto(int eleccion, List<Productos> cesta, List<Productos> list) {
        int indice;
        if (eleccion >= 0 && eleccion < list.size()) {
            Limbo.info("Has seleccionado el producto " + eleccion + " " + list.get(eleccion).getNombre()
                    + " " + list.get(eleccion).getPvp() + "€");
            indice = saberArticulo(cesta, list.get(eleccion));
            if (indice == -1 && !productoEnLaCesta(cesta, list.get(eleccion))) {
                cesta.add(list.get(eleccion));
                cesta.get(cesta.size()-1).setUnidades(1);
            } else {
                int unidades = modificaUnidades();
                int index = 0;
                for (int i = 0; i < cesta.size(); i++) {
                    if (cesta.get(i).getId() == list.get(eleccion).getId()) {
                        index = i;
                    }
                }
                cesta.get(index).setUnidades(unidades);
            }
        } else {
            Limbo.errada("Valor no válido");
        }
    }

    private static int saberArticulo(List<Productos> cesta, Productos producto) {
        for (Productos articuloCesta : cesta) {
            if (articuloCesta.getId() == producto.getId()) {
                return cesta.indexOf(producto);
            }
        }
        return -1;
    }

    private static boolean productoEnLaCesta(List<Productos> cesta, Productos producto) {
        for (Productos articuloCesta : cesta) {
            if (articuloCesta.getId() == producto.getId()) {
                return true;
            }
        }
        return false;
    }

    private static int modificaUnidades() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(Color.BLACK);
            System.out.print(Color.YELLOW_BACKGROUND + "Cuantas unidades quieres de este articulo?" + Color.RESET);
            int unidades = scanner.nextInt();
            if (unidades < 1) {
                Limbo.errada("Has de indicar una cantidad superior a 0");
                return 1;
            } else {
                return unidades;
            }
        }
    }

    private static void muestraCategorias() {
        System.out.println("(" + Color.YELLOW + "0" + Color.RESET + ") No seleccionar categoría");
        System.out.println("(" + Color.YELLOW + "1" + Color.RESET + ") Beverages Soft drinks, coffes, teas, beers, and ales");
        System.out.println("(" + Color.YELLOW + "2" + Color.RESET + ") Condiments Sweet and savory sauces, relishes, spreads, and seasonings");
        System.out.println("(" + Color.YELLOW + "3" + Color.RESET + ") Confections Desserts, candies, and sweet breads");
        System.out.println("(" + Color.YELLOW + "4" + Color.RESET + ") Dairy Products Cheeses");
        System.out.println("(" + Color.YELLOW + "5" + Color.RESET + ") Grains/Cereals Breads, crackers, pasta, and cereal");
        System.out.println("(" + Color.YELLOW + "6" + Color.RESET + ") Meat/Poultry Prepared meats");
        System.out.println("(" + Color.YELLOW + "7" + Color.RESET + ") Produce Dried fruit and bean curd");
        System.out.println("(" + Color.YELLOW + "8" + Color.RESET + ") Seafood Seaweed and fish");
    }
}
