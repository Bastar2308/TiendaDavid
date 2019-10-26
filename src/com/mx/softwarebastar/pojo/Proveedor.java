/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.pojo;

import java.util.Objects;

/**
 *
 * @author bl4ck
 */
public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private String nombreContacto;
    private String correoContacto;
    private String telContacto;

    public Proveedor() {
    }

    public Proveedor(String nombre, String telefono, String correo, String direccion, String nombreContacto, String correoContacto, String telContacto) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.correoContacto = correoContacto;
        this.telContacto = telContacto;
    }

    public Proveedor(int idProveedor, String nombre, String telefono, String correo, String direccion, String nombreContacto, String correoContacto, String telContacto) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.correoContacto = correoContacto;
        this.telContacto = telContacto;
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
        final Proveedor other = (Proveedor) obj;
        if (this.idProveedor != other.idProveedor) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.nombreContacto, other.nombreContacto)) {
            return false;
        }
        if (!Objects.equals(this.correoContacto, other.correoContacto)) {
            return false;
        }
        if (!Objects.equals(this.telContacto, other.telContacto)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idProveedor;
        hash = 61 * hash + Objects.hashCode(this.nombre);
        hash = 61 * hash + Objects.hashCode(this.telefono);
        hash = 61 * hash + Objects.hashCode(this.correo);
        hash = 61 * hash + Objects.hashCode(this.direccion);
        hash = 61 * hash + Objects.hashCode(this.nombreContacto);
        hash = 61 * hash + Objects.hashCode(this.correoContacto);
        hash = 61 * hash + Objects.hashCode(this.telContacto);
        return hash;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "idProveedor=" + idProveedor + ", nombre=" + nombre + ", telefono=" + telefono + ", correo=" + correo + ", direccion=" + direccion + ", nombreContacto=" + nombreContacto + ", correoContacto=" + correoContacto + ", telContacto=" + telContacto + '}';
    }

    public String getTelContacto() {
        return telContacto;
    }

    public void setTelContacto(String telContacto) {
        this.telContacto = telContacto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }
    
    

}
