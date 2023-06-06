package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.dao.UsuariosDAOImpl;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class PantallaPrincipal {
    public static void main(String username, String password) throws SQLException {

        Connection con = ConexionJDBC.creaConexion();
        UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl();
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario = usuariosDAO.asignarValoresUsuario(usuario);


        while (true) {
            System.out.println(Color.RESET);
            System.out.println(Color.YELLOW + "*****************************");
            System.out.println("**         Opciones        **");
            System.out.println("** Usuari: " + usuario.getNombre() + "   " + Color.RED_BOLD + usuario.getUsername() + Color.RESET +
                               Color.YELLOW + "           **"+ Color.RESET);
            System.out.println(Color.YELLOW + "*****************************" + Color.RESET);
            System.out.println(Color.BLUE_BOLD + "c) " + Color.RESET + "Buscar productos");
            System.out.println(Color.BLUE_BOLD + "v) " + Color.RESET + "Ver cesta");
            System.out.println(Color.BLUE_BOLD + "d) " + Color.RESET + "Datos personales" + Color.BLACK);
            System.out.println(Color.BLUE_BOLD + "h) " + Color.RESET + "Ayuda" + Color.BLACK);
            System.out.println(Color.BLUE_BOLD + "x) " + Color.RESET + "Salir" + Color.BLACK);
            System.out.print(Color.YELLOW_BACKGROUND + "Elige una opción: " + Color.RESET);

            String elecion = scanner.nextLine();
            if (elecion.equalsIgnoreCase("c")) {
            } else if (elecion.equalsIgnoreCase("v")) {
            } else if (elecion.equalsIgnoreCase("d")) {
                DatosPersonales.main(usuario);
            } else if (elecion.equalsIgnoreCase("h")){
            } else if (elecion.equalsIgnoreCase("x")) {
                return;
            }
        }
    }
}
