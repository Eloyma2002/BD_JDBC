package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.Limbo;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Direcciones;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DireccionesDAOImpl implements DireccionesDAO{
    @Override
    public int obtenIdCiudad(String s) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        PreparedStatement statement = con.prepareStatement("SELECT id FROM ciutat WHERE nom=?");
        statement.setString(1, s);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            Limbo.errada("Ha ocurrido un error");
            return 1;
        }
    }

    @Override
    public void insertarDireccion(Direcciones direccion) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        String insert = "INSERT INTO adreca(carrer, numero, ciutat_id, CP, client_id, pis, porta) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(insert);
        statement.setString(1, direccion.getCalle());
        statement.setString(2, direccion.getNumero());
        statement.setInt(3, direccion.getIdCiudad());
        statement.setString(4, direccion.getCP());
        statement.setInt(5, direccion.getIdCliente());
        statement.setInt(6, direccion.getPiso());
        statement.setString(7, direccion.getPuerta());
        statement.execute();

    }

    @Override
    public List<Direcciones> muestraDirecciones(Usuario usuario) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();
        List<Direcciones> list = new ArrayList<>();
        Direcciones direccion = new Direcciones();

        PreparedStatement statement = con.prepareStatement("SELECT * FROM adreca WHERE client_id=?");
        statement.setInt(1, usuario.getNumero_client());
        ResultSet rs = statement.executeQuery();

        System.out.println("********************");
        System.out.println("**  Direcciones   **");
        System.out.println("********************");

        while (rs.next()) {
            int cuenta = 0;
            String i = String.valueOf(cuenta);

            direccion.setIdCliente(usuario.getNumero_client());
            direccion.setPiso(rs.getInt("pis"));
            direccion.setCalle(rs.getString("carrer"));
            direccion.setPuerta(rs.getString("porta"));
            direccion.setCP(rs.getString("CP"));
            direccion.setIdCiudad(rs.getInt("ciutat_id"));
            direccion.setId(rs.getInt("id"));
            list.add(direccion);

            System.out.println(Color.YELLOW + i + "   " + Color.CYAN + direccion.getCalle() + " " + direccion.getNumero() + " " + direccion.getCP() + Color.RESET);
        }

        return list;
    }
}
