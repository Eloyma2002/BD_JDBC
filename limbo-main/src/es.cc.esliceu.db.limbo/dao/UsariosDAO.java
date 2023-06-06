package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.util.Usuario;

import java.sql.SQLException;

public interface UsariosDAO {
    public boolean buscaMismoNombreYEmail(String insertCliente, String parametro) throws SQLException;
    public void insertDeUsuario(Usuario usuario) throws SQLException;
    public Usuario asignarValoresUsuario(Usuario usuario) throws SQLException;

}
