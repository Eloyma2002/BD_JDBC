package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.util.Direcciones;
import es.cc.esliceu.db.limbo.util.Productos;
import es.cc.esliceu.db.limbo.util.Tarjetas;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface ComprasDAO {

    public void añadeCompra(Usuario usuario, Tarjetas tarjeta, Direcciones direccion, List<Productos> cesta) throws SQLException;

   // public void añadeDetallesCompra(Usuario usuario, Tarjetas tarjeta, Direcciones direccion, List<Productos> cesta, int idCompra) throws SQLException;
}

