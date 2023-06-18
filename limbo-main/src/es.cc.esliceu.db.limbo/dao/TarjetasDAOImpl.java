package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Tarjetas;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarjetasDAOImpl implements TarjetasDAO {
    @Override
    public List<Tarjetas> obtenTarjetas(Usuario usuario) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();
        Tarjetas tarjeta = new Tarjetas();
        List<Tarjetas> list = new ArrayList<>();

        PreparedStatement statement = con.prepareStatement("SELECT * FROM targeta WHERE client_id=?");
        statement.setInt(1, usuario.getNumero_client());

        ResultSet rs = statement.executeQuery();
        System.out.println("********************");
        System.out.println("**    Tarjetas    **");
        System.out.println("********************");

        while (rs.next()) {
            tarjeta.setId(rs.getInt("id"));
            tarjeta.setFecha(rs.getString("data_caducitat"));
            tarjeta.setTipo(rs.getString("tipus"));
            tarjeta.setNumero(rs.getString("numero"));
            tarjeta.setIdCliente(usuario.getNumero_client());
            tarjeta.setCodigoSeguridad(rs.getInt("codi_seguretat"));
            list.add(tarjeta);
        }
        return list;

    }

    @Override
    public void insertaTarjetas(Tarjetas tarjeta) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        String insert = "INSERT INTO targeta(tipus, numero, data_caducitat, codi_seguretat, client_id) VALUES(?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(insert);
        statement.setString(1, tarjeta.getTipo());
        statement.setString(2, tarjeta.getNumero());
        statement.setDate(3, java.sql.Date.valueOf(tarjeta.getFecha()));
        statement.setInt(4, tarjeta.getCodigoSeguridad());
        statement.setInt(5, tarjeta.getIdCliente());
        statement.execute();
    }
}
