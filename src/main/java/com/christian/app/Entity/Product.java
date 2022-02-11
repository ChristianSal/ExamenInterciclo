package com.christian.app.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Product implements Serializable {

    private static final long serialVersionUID = -8175823096608828847L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;
    @Column(length =100, nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private double precio;
    @Column(nullable = false)
    private int cantidad;
    private double calculo;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCalculo() {
        return calculo;
    }

    public void setCalculo(double calculo) {
        this.calculo = calculo;
    }
}
