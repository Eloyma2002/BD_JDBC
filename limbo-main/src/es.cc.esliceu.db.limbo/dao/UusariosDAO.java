package es.cc.esliceu.db.limbo.dao;

import java.sql.SQLException;

public interface UusariosDAO {
    public boolean buscaMismoNombreYEmail(String insertCliente, String parametro) throws SQLException;
    public void insertDeUsuario(String username, String email, String referencia, String password,
                                String nombre, String primerApellido, String segundoApellido) throws SQLException;
}
