package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.Limbo;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.ConexionJDBC;
import es.cc.esliceu.db.limbo.util.Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAOImpl implements ProductosDAO {

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
                    + "  " + producto.getMarca() + Color.BLUE_BOLD + "  " + producto.getPvp() + "€" + Color.RESET);
            System.out.println();

            list.add(producto);
            i++;
        }
        return list;
    }

    @Override
    public List<Productos> obtenTodosLosProductosOPorCategoria(int categoriaProducto) throws SQLException {
        List<Productos> list = new ArrayList<>();
        Connection con = ConexionJDBC.creaConexion();
        String select = "";
        PreparedStatement statement;

        if (categoriaProducto == 0) {
            select = "SELECT * FROM producte";
            statement = con.prepareStatement(select);
        } else if (categoriaProducto > 0 && categoriaProducto < 9) {
            select = "SELECT * FROM producte WHERE categoria=?";
            statement = con.prepareStatement(select);
            statement.setInt(1, categoriaProducto);
        } else {
            Limbo.errada("Ha habido un error");
            return null;
        }

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
            list.add(producto);
            i++;
        }
        imprimeProductos(i, list);
        return list;
    }

    @Override
    public List<Productos> buscarProductos(String nombre, String descripcion, String marca) throws SQLException {
        Connection con = ConexionJDBC.creaConexion();
        List<Productos> productosEncontrados = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM producte WHERE 1=1");

        boolean estaEscrito = false;
        if (!nombre.equals("")) {
            queryBuilder.append(" AND nom LIKE ?");
            estaEscrito = true;
        }
        if (!descripcion.equals("") && estaEscrito) {
            queryBuilder.append(" AND descripcio LIKE ?");
        } else if (!descripcion.equals("")) {
            queryBuilder.append(" AND descripcio LIKE ?");
        }
        if (!marca.equals("") && estaEscrito) {
            queryBuilder.append(" AND marca LIKE ?");
        } else if (!marca.equals("")) {
            queryBuilder.append(" AND marca LIKE ?");
        }

        PreparedStatement statement = con.prepareStatement(queryBuilder.toString());

        int parameterIndex = 1;
        if (!nombre.equals("")) {
            statement.setString(parameterIndex++, "%" + nombre + "%");
        }
        if (!descripcion.equals("")) {
            statement.setString(parameterIndex++, "%" + descripcion + "%");
        }
        if (!marca.equals("")) {
            statement.setString(parameterIndex++, "%" + marca + "%");
        }

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
            productosEncontrados.add(producto);
            i++;
        }
        imprimeProductos(i, productosEncontrados);
        return productosEncontrados;
    }



    private void imprimeProductos(int i, List<Productos> list) {

        Limbo.info("Se han encontrado " + i + " productos");
        for (int j = 0; j < list.size(); j++) {
            System.out.print(Color.YELLOW);
            System.out.print(j);
            System.out.print(Color.RESET + "   " + list.get(j).getNombre() + "  " + list.get(j).getDescripcion() + Color.RESET +
                    Color.CYAN_BRIGHT + "  " + list.get(j).getMarca() + Color.BLUE_BOLD + "  " + list.get(j).getPvp() + "€" + Color.RESET);
            System.out.println();
        }
    }

}
