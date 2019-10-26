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
 * @author Bastar
 */
public class Venta {

    private int idVenta;
    private int cantidadArticulos;
    private double total;
    private Timestamp fecha;

    public Venta() {
    }

    public Venta(int idVenta, int cantidadArticulos, double total, Timestamp fecha) {
        this.idVenta = idVenta;
        this.cantidadArticulos = cantidadArticulos;
        this.total = total;
        this.fecha = fecha;
    }

    public Venta(int cantidadArticulos, double total, Timestamp fecha) {
        this.cantidadArticulos = cantidadArticulos;
        this.total = total;
        this.fecha = fecha;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.idVenta;
        hash = 19 * hash + this.cantidadArticulos;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        hash = 19 * hash + Objects.hashCode(this.fecha);
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
        final Venta other = (Venta) obj;
        if (this.idVenta != other.idVenta) {
            return false;
        }
        if (this.cantidadArticulos != other.cantidadArticulos) {
            return false;
        }
        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", cantidadArticulos=" + cantidadArticulos + ", total=" + total + ", fecha=" + fecha + '}';
    }

}
