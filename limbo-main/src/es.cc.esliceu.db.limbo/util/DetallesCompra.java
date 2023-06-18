package es.cc.esliceu.db.limbo.util;

public class DetallesCompra {
    private int idCompra;
    private int idProducto;
    private double pvp;
    private int unidadesProducto;

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public int getUnidadesProducto() {
        return unidadesProducto;
    }

    public void setUnidadesProducto(int unidadesProducto) {
        this.unidadesProducto = unidadesProducto;
    }
}
