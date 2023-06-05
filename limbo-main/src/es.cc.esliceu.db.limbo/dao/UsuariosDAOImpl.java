package es.cc.esliceu.db.limbo.dao;


import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;

import java.sql.*;

public class UsuariosDAOImpl implements UusariosDAO {


    @Override
    public boolean buscaMismoNombreYEmail(String insertCliente, String parametro) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        String select = "SELECT username FROM client WHERE " + parametro + "=?";
        PreparedStatement statement = con.prepareStatement(select);
        statement.setString(1, insertCliente);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    @Override
    public void insertDeUsuario(String username, String email, String referencia, String password, String nombre,
                                String primerApellido, String segundoApellido) throws SQLException {

        Connection con = ConexionJDBC.creaConexion();

        String insert = "INSERT INTO client(email, nom, cognom1, cognom2, username, contrasenya) VALUES(?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(insert);
        statement.setString(1, email);
        statement.setString(2, nombre);
        statement.setString(3, primerApellido);
        statement.setString(4, segundoApellido);
        statement.setString(5, username);
        statement.setString(6, password);
        statement.execute();
        int id = buscaIdCliente(username, password);

        if (id == -1) {
            System.out.println("Ha ocurrido un error en el proceso de la inserción del nuevo usuario");
            return;
        }

        System.out.println(Color.BLUE_BRIGHT + "El usuario " + username + " con id asignada " + id +
                " ha sido creado con éxito!" + Color.RESET);
        System.out.println();
    }

    private int buscaIdCliente(String username, String password) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        String select = "SELECT numero_client FROM client WHERE username=? and contrasenya=?";
        PreparedStatement statement = con.prepareStatement(select);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt("numero_client");
        }
        return -1;
    }
}
