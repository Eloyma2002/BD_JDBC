package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ComprasDAOImpl implements ComprasDAO {
    @Override
    public void añadeCompra(Usuario usuario, Tarjetas tarjeta, Direcciones direccion, List<Productos> cesta) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        PreparedStatement statement = con.prepareStatement("INSERT INTO compra(targeta_id, adreca_id, client_id, id_transaccio, data) VALUES(?,?,?,?,?)");
        statement.setInt(1, tarjeta.getId());
        statement.setInt(2, direccion.getId());
        statement.setInt(3, usuario.getNumero_client());
        statement.setInt(4, 69);
        statement.setString(5, String.valueOf(LocalDate.now()));
        statement.execute();
        int idCompra = obtenIdCompra(usuario, tarjeta, direccion);
        //añadeDetallesCompra(usuario, tarjeta, direccion, cesta, idCompra);
    }

    private int obtenIdCompra(Usuario usuario, Tarjetas tarjeta, Direcciones direccion) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        PreparedStatement statement = con.prepareStatement("SELECT id FROM compra WHERE targeta_id=? AND adreca_id=? AND client_id=?");
        statement.setInt(1, tarjeta.getId());
        statement.setInt(2, direccion.getId());
        statement.setInt(3, usuario.getNumero_client());
        ResultSet rs = statement.executeQuery();

        int id = -1;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

/*    @Override
    public void añadeDetallesCompra(Usuario usuario, Tarjetas tarjeta, Direcciones direccion, List<Productos> cesta, int idCompra) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();

        for (Productos productos : cesta) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO detall_compra(compra_id, producte_id, pvp, unitats_producte) VALUES(?,?,?,?)");
            statement.setInt(1, idCompra);
            statement.setInt(2, productos.getId());
            statement.setDouble(3, productos.getPvp());
            statement.setInt(4, productos.getUnidades());
            statement.execute();
        }

    }*/
}
