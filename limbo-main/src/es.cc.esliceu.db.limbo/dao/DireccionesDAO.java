package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.util.Direcciones;
import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface DireccionesDAO {
    public int obtenIdCiudad(String s) throws SQLException;
    public void insertarDireccion(Direcciones direccion) throws SQLException;
    public List<Direcciones> muestraDirecciones(Usuario usuario) throws SQLException;
}
