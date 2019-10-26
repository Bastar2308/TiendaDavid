/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.pojo;

/**
 *
 * @author Davi_
 */
public class Desglose_compra {
    
    private int idDesglose;
    private int producto_idProducto;
    private int compra_idCompra;
    private double cantidad;
    private double subtotal;

    public Desglose_compra() {
    }

    public Desglose_compra(int idDesglose, int producto_idProducto, int compra_idCompra, double cantidad, double subtotal) {
        this.idDesglose = idDesglose;
        this.producto_idProducto = producto_idProducto;
        this.compra_idCompra = compra_idCompra;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    public Desglose_compra(int producto_idProducto, int compra_idCompra, double cantidad, double subtotal) {
        this.producto_idProducto = producto_idProducto;
        this.compra_idCompra = compra_idCompra;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Desglose_compra{" + "idDesglose=" + idDesglose + ", producto_idProducto=" + producto_idProducto + ", compra_idCompra=" + compra_idCompra + ", cantidad=" + cantidad + ", subtotal=" + subtotal + '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public int getIdDesglose() {
        return idDesglose;
    }

    public void setIdDesglose(int idDesglose) {
        this.idDesglose = idDesglose;
    }

    public int getProducto_idProducto() {
        return producto_idProducto;
    }

    public void setProducto_idProducto(int producto_idProducto) {
        this.producto_idProducto = producto_idProducto;
    }

    public int getCompra_idCompra() {
        return compra_idCompra;
    }

    public void setCompra_idCompra(int compra_idCompra) {
        this.compra_idCompra = compra_idCompra;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
}
