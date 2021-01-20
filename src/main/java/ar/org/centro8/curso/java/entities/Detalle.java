package ar.org.centro8.curso.java.entities;
public class Detalle {
    private int idFactura;
    private int idArticulo;
    private int cantidad; 

    public Detalle() {
    }

    public Detalle(int idFactura, int idArticulo, int cantidad) {
        this.idFactura = idFactura;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Detalle{" + "idFactura=" + idFactura + ", idArticulo=" + idArticulo + ", cantidad=" + cantidad + '}';
    }
   
}