package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.util.Productos;

import java.sql.SQLException;
import java.util.List;

public interface ProductosDAO {

    List <Productos> sugiereProductos() throws SQLException;
    List<Productos> obtenTodosLosProductosOPorCategoria(int categoriaProducto) throws SQLException;
    public List<Productos> buscarProductos(String nombre, String descripcion, String marca) throws SQLException;
}

