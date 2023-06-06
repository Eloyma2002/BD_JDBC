package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.dao.*;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class Registro {

    public static void registrarse() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UsuariosDAOImpl usuariosDAO = new UsuariosDAOImpl();
        Usuario usuario = new Usuario();

        System.out.println(Color.RESET);
        System.out.println(Color.YELLOW + "*****************************");
        System.out.println("**         Registre        **");
        System.out.println("*****************************" + Color.RESET);
        System.out.println();

        String username;
        while (true){
            System.out.println("Username: ");
            username = scanner.nextLine();
            if (usuariosDAO.buscaMismoNombreYEmail(username, "username")) {
                Limbo.errada("Este nombre de usuario ya está en uso, por favor introduzca uno diferente");
                System.out.println();
                continue;
            }
            usuario.setUsername(username);
            break;
        }

        String email;
        while (true) {
            System.out.println("Email: ");
            email = scanner.nextLine();
            if (usuariosDAO.buscaMismoNombreYEmail(email, "email")) {
                Limbo.errada( "Este email ya está en uso, por favor introduzca uno diferente");
                System.out.println();
                continue;
            }
            System.out.println("Email enviado");
            System.out.println("Se ha enviado un correo electrónico con una referencia a la dirección: " + email);
            System.out.println();
            usuario.setEmail(email);
            break;
        }

        System.out.println("Referencia:");
        String referencia = scanner.nextLine();
        usuario.setReferencia(referencia);
        System.out.println("Password:");
        String password = scanner.nextLine();
        usuario.setPassword(password);
        System.out.println("Nombre:");
        String nombre = scanner.nextLine();
        usuario.setNombre(nombre);
        System.out.println("Primer apellido");
        String primerApellido = scanner.nextLine();
        usuario.setPrimerApellido(primerApellido);
        System.out.println("Segundo apellido:");
        String segundoApellido = scanner.nextLine();
        usuario.setSegonApellido(segundoApellido);

        usuariosDAO.insertDeUsuario(usuario);
    }
}
