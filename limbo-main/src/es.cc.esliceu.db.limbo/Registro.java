package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.util.Color;

import java.util.Scanner;

public class Registro {

    public static void registrarse() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Color.RESET);
        System.out.println(Color.YELLOW + "*****************************");
        System.out.println("**         Registre        **");
        System.out.println("*****************************" + Color.RESET);
        System.out.println();

        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Email enviado");
        System.out.println("Se ha enviado un correo electónico con una referencia la dirección: " + email);
        System.out.println("Referència:");
        String referencia = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        System.out.println("Nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Primer apellido");
        String primerApellido = scanner.nextLine();
        System.out.println("Segundo apellido:");
        String segundoApellido = scanner.nextLine();
        System.out.println(Color.BLUE_BOLD + "Usuario " + username + " creado correctamente con id: ");
    }
}
