package ar.org.centro8.curso.java.entities;

import ar.org.centro8.curso.java.enumerados.Letra;
import java.time.LocalDate;

public class Factura {
    private int id;
    private Letra letra;
    private int numero;
    private LocalDate fecha;
    private float monto;
    private int idCliente;

    public Factura() {
    }

    public Factura(Letra letra, int numero, LocalDate fecha, float monto, int idCliente) {
        this.letra = letra;
        this.numero = numero;
        this.fecha = fecha;
        this.monto = monto;
        this.idCliente = idCliente;
    }

    public Factura(int id, Letra letra, int numero, LocalDate fecha, float monto, int idCliente) {
        this.id = id;
        this.letra = letra;
        this.numero = numero;
        this.fecha = fecha;
        this.monto = monto;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public Letra getLetra() {
        return letra;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public float getMonto() {
        return monto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLetra(Letra letra) {
        this.letra = letra;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Facturas{" + "id=" + id + ", letra=" + letra + ", numero=" + numero + ", fecha=" + fecha + ", monto=" + monto + ", idCliente=" + idCliente + '}';
    }
    
    
}
