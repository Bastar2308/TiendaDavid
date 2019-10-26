/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.pojo;

/**
 *
 * @author Laptop_Dell
 */
public class Desglose_venta {
    
    private int idDesglose;
    private int Producto_idProducto;
    private int Venta_idVenta;
    private double cantidad;
    private double subtotal;

    public Desglose_venta() {
    }

    public Desglose_venta(int idDesglose, int Producto_idProducto, int Venta_idVenta, double cantidad, double subtotal) {
        this.idDesglose = idDesglose;
        this.Producto_idProducto = Producto_idProducto;
        this.Venta_idVenta = Venta_idVenta;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    
    public Desglose_venta(int Producto_idProducto, int Venta_idVenta, double cantidad, double subtotal) {
        
        this.Producto_idProducto = Producto_idProducto;
        this.Venta_idVenta = Venta_idVenta;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getIdDesglose() {
        return idDesglose;
    }

    public void setIdDesglose(int idDesglose) {
        this.idDesglose = idDesglose;
    }

    public int getProducto_idProducto() {
        return Producto_idProducto;
    }

    public void setProducto_idProducto(int Producto_idProducto) {
        this.Producto_idProducto = Producto_idProducto;
    }

    public int getVenta_idVenta() {
        return Venta_idVenta;
    }

    public void setVenta_idVenta(int Venta_idVenta) {
        this.Venta_idVenta = Venta_idVenta;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Desglose_venta{" + "idDesglose=" + idDesglose + ", Producto_idProducto=" + Producto_idProducto + ", Venta_idVenta=" + Venta_idVenta + ", cantidad=" + cantidad + ", subtotal=" + subtotal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idDesglose;
        hash = 97 * hash + this.Producto_idProducto;
        hash = 97 * hash + this.Venta_idVenta;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.cantidad) ^ (Double.doubleToLongBits(this.cantidad) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.subtotal) ^ (Double.doubleToLongBits(this.subtotal) >>> 32));
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
        final Desglose_venta other = (Desglose_venta) obj;
        if (this.idDesglose != other.idDesglose) {
            return false;
        }
        if (this.Producto_idProducto != other.Producto_idProducto) {
            return false;
        }
        if (this.Venta_idVenta != other.Venta_idVenta) {
            return false;
        }
        if (Double.doubleToLongBits(this.cantidad) != Double.doubleToLongBits(other.cantidad)) {
            return false;
        }
        if (Double.doubleToLongBits(this.subtotal) != Double.doubleToLongBits(other.subtotal)) {
            return false;
        }
        return true;
    }
    
    
    
}
