package es.cc.esliceu.db.limbo.dao;


import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.*;

public class UsuariosDAOImpl implements UsariosDAO {


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
    public void insertDeUsuario(Usuario usuario) throws SQLException {

        Connection con = ConexionJDBC.creaConexion();

        String insert = "INSERT INTO client(email, nom, cognom1, cognom2, username, contrasenya) VALUES(?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(insert);
        statement.setString(1, usuario.getEmail());
        statement.setString(2, usuario.getNombre());
        statement.setString(3, usuario.getPrimerApellido());
        statement.setString(4, usuario.getSegonApellido());
        statement.setString(5, usuario.getUsername());
        statement.setString(6, usuario.getPassword());
        statement.execute();
        usuario.setNumero_client(buscaIdCliente(usuario));

        if (usuario.getNumero_client() == -1) {
            System.out.println("Ha ocurrido un error en el proceso de la inserción del nuevo usuario");
            return;
        }

        System.out.println(Color.BLUE_BRIGHT + "El usuario " + usuario.getUsername() + " con id asignada " + usuario.getNumero_client() +
                                               " ha sido creado con éxito!" + Color.RESET);
        System.out.println();
    }

    @Override
    public Usuario asignarValoresUsuario(Usuario usuario) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        String select = "SELECT * FROM client WHERE username=? and contrasenya=?";
        PreparedStatement statement = con.prepareStatement(select);
        statement.setString(1, usuario.getUsername());
        statement.setString(2, usuario.getPassword());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            usuario.setNumero_client(rs.getInt("numero_client"));
            usuario.setPrimerApellido(rs.getString("cognom1"));
            usuario.setSegonApellido(rs.getString("cognom2"));
            usuario.setNombre(rs.getString("nom"));
            usuario.setEmail(rs.getString("email"));
        }
        return usuario;
    }

    private int buscaIdCliente(Usuario usuario) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        String select = "SELECT numero_client FROM client WHERE username=? and contrasenya=?";
        PreparedStatement statement = con.prepareStatement(select);
        statement.setString(1, usuario.getUsername());
        statement.setString(2, usuario.getPassword());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt("numero_client");
        }
        return -1;
    }
}
