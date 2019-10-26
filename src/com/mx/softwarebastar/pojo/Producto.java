package com.mx.softwarebastar.pojo;

import java.util.Objects;

public class Producto {

    private int idProducto;
    private String nombre;
    private String codigo;
    private double inventario;
    private double precio;
    private String descripcion;
    private int Marca_idMarca;
    private String unidadMedida;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String codigo, double inventario, double precio, String descripcion, int Marca_idMarca, String unidadMedida) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codigo = codigo;
        this.inventario = inventario;
        this.precio = precio;
        this.descripcion = descripcion;
        this.Marca_idMarca = Marca_idMarca;
        this.unidadMedida = unidadMedida;
    }

    public Producto(String nombre, String codigo, double inventario, double precio, String descripcion, int Marca_idMarca, String unidadMedida) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.inventario = inventario;
        this.precio = precio;
        this.descripcion = descripcion;
        this.Marca_idMarca = Marca_idMarca;
        this.unidadMedida = unidadMedida;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getInventario() {
        return inventario;
    }

    public void setInventario(double inventario) {
        this.inventario = inventario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMarca_idMarca() {
        return Marca_idMarca;
    }

    public void setMarca_idMarca(int Marca_idMarca) {
        this.Marca_idMarca = Marca_idMarca;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idProducto;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.codigo);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.inventario) ^ (Double.doubleToLongBits(this.inventario) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.descripcion);
        hash = 53 * hash + this.Marca_idMarca;
        hash = 53 * hash + Objects.hashCode(this.unidadMedida);
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
        final Producto other = (Producto) obj;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (Double.doubleToLongBits(this.inventario) != Double.doubleToLongBits(other.inventario)) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (this.Marca_idMarca != other.Marca_idMarca) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.unidadMedida, other.unidadMedida)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", codigo=" + codigo + ", inventario=" + inventario + ", precio=" + precio + ", descripcion=" + descripcion + ", Marca_idMarca=" + Marca_idMarca + ", unidadMedida=" + unidadMedida + '}';
    }
}
