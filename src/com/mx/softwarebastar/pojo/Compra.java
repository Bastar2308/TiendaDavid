/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.pojo;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author edwin
 */
public class Compra {
    
    private int idCompra;
    private int cantidadArticulos;
    private double total;
    private Timestamp fecha;
    private int Proveedor_idProveedor;

    public Compra() {
    }

    public Compra(int cantidadArticulos, double total, Timestamp fecha, int Proveedor_idProveedor) {
        this.cantidadArticulos = cantidadArticulos;
        this.total = total;
        this.fecha = fecha;
        this.Proveedor_idProveedor = Proveedor_idProveedor;
    }

    public Compra(int idCompra, int cantidadArticulos, double total, Timestamp fecha, int Proveedor_idProveedor) {
        this.idCompra = idCompra;
        this.cantidadArticulos = cantidadArticulos;
        this.total = total;
        this.fecha = fecha;
        this.Proveedor_idProveedor = Proveedor_idProveedor;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.idCompra;
        hash = 73 * hash + this.cantidadArticulos;
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.fecha);
        hash = 73 * hash + this.Proveedor_idProveedor;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compra other = (Compra) obj;
        if (this.idCompra != other.idCompra) {
            return false;
        }
        if (this.cantidadArticulos != other.cantidadArticulos) {
            return false;
        }
        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
            return false;
        }
        if (this.Proveedor_idProveedor != other.Proveedor_idProveedor) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(int cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getProveedor_idProveedor() {
        return Proveedor_idProveedor;
    }

    public void setProveedor_idProveedor(int Proveedor_idProveedor) {
        this.Proveedor_idProveedor = Proveedor_idProveedor;
    }
    
    
}
