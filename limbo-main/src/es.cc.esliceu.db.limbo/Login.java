package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.dao.UsariosDAO;
import es.cc.esliceu.db.limbo.dao.UsuariosDAOImpl;
import es.cc.esliceu.db.limbo.dao.UsariosDAO;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    public static void logearse() throws SQLException {
        Connection con = ConexionJDBC.creaConexion();
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario();
        UsariosDAO usuariosDAO = new UsuariosDAOImpl();

        System.out.println(Color.RESET);
        System.out.println(Color.YELLOW + "*****************************");
        System.out.println("**         Login           **");
        System.out.println("*****************************" + Color.RESET + Color.BLACK);

        String username;
        while (true){
            System.out.print(Color.BLACK);
            System.out.print(Color.YELLOW_BACKGROUND + "Username:" + Color.RESET);
            username = scanner.nextLine();
            if (!usuariosDAO.buscaMismoNombreYEmail(username, "username")) {
                Limbo.errada("Error: Nombre de usuario no es correcto");
                System.out.println();
                continue;
            }
            Limbo.info("Usuario encontrado en el sistema");
            System.out.println();
            break;
        }

        String password;
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                Limbo.errada("No se ha podido validar el usuario");
                System.out.println();
                break;
            }
            System.out.print(Color.BLACK);
            System.out.println();
            System.out.print(Color.YELLOW_BACKGROUND + "Password:" + Color.RESET);
            password = scanner.nextLine();
            if (!usuariosDAO.buscaMismoNombreYEmail(password, "contrasenya")) {
                continue;
            }
            Limbo.info("Usuario validado correctamente");
            System.out.println();
            PantallaPrincipal.main(username, password);
            break;
        }

    }
}