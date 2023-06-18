package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.util.Tarjetas;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface TarjetasDAO {
    public List<Tarjetas> obtenTarjetas(Usuario usuario) throws SQLException;
    public void insertaTarjetas(Tarjetas tarjeta) throws SQLException;
}
