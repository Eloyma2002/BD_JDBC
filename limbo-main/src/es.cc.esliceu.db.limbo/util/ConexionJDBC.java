package es.cc.esliceu.db.limbo.util;
import java.sql.DriverManager;
import java.sql.Connection;

public class ConexionJDBC {
    static String url = "jdbc:mysql://localhost:3307/biblioteca";
    static String username = "root";
    static String password = "";

    public static Connection creaConexion() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("No se ha podido hacer la con");
        }
        return con;
    }
}
