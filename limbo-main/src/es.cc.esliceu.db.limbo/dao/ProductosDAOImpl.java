package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Productos;

import javax.swing.plaf.synth.ColorType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAOImpl implements ProductosDAO{

    @Override
    public List<Productos> sugiereProductos() throws SQLException {
        List<Productos> list = new ArrayList<>();
        Connection con = ConexionJDBC.creaConexion();

        String select = "SELECT * FROM producte ORDER BY RAND() LIMIT 5";
        PreparedStatement statement = con.prepareStatement(select);

        ResultSet rs = statement.executeQuery();

        int i = 0;
        while (rs.next()) {
            Productos producto = new Productos();
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nom"));
            producto.setDescripcion(rs.getString("descripcio"));
            producto.setMarca(rs.getString("marca"));
            producto.setPvp(rs.getFloat("pvp"));
            producto.setIva(rs.getInt("iva"));
            producto.setCategoria(rs.getInt("categoria"));
            System.out.print(Color.YELLOW);
            System.out.print(i);
            System.out.print(Color.RESET + "   " + producto.getNombre() + "  " + producto.getDescripcion() + Color.RESET + Color.CYAN_BRIGHT
                    + "  " + producto.getMarca() + Color.BLUE_BOLD + "  "  + producto.getPvp() + "€" + Color.RESET);
            System.out.println();

            list.add(producto);
            i++;
        }
        return list;
    }
}
