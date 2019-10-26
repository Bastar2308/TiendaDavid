/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bastar
 */
public class Desglose_CompraDAO {

    private final String TABLE = "Desglose_Compra";
    private final String SQL_INSERT
            = "Insert into " + TABLE + "() values(?,?,?)";
    private final String SQL_DELETE = "Delete from " + TABLE + " where idProducto=?";
    private final String SQL_UPDATE = "Update " + TABLE
            + " set nombre=?,precio=?,descripcion=? where idProducto=? ";
    private final String SQL_QUERY = "Select * from " + TABLE;

    
    
    
    public int insertar(Producto p) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Inicializo el ps
            ps = Conexion.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //Llenar la consulta
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setString(3, p.getDescripcion());
            //Ejecuto la consulta y veo si funcionó
            if (ps.executeUpdate() == 0) {
                System.out.println("Error al insertar");
                return 0;
            }//Obtengo el cursor saliente
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                //Obtengo el primer valor(id)
                return rs.getInt(1);
            } else {
                System.out.println("Error al obtener id");
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Error al insertar " + e);
            return 0;
        } finally {
            Conexion.close(ps);
            Conexion.close(rs);
        }

    }

    public boolean eliminar(int id) {
        PreparedStatement ps = null;
        try {
            //Inicialice
            ps = Conexion.getConnection().prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            //ejecutarlo
            if (ps.executeUpdate() == 0) {
                System.out.println("Error al eliminar");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e);
            return false;
        } finally {
            Conexion.close(ps);
        }
        return true;
    }

    public void actualizar(Producto p) {
        PreparedStatement ps = null;
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setString(3, p.getDescripcion());
            ps.setInt(4, p.getIdProducto());
            if (ps.executeUpdate() == 0) {
                System.out.println("Error al actualizar");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar " + e);
        } finally {
            Conexion.close(ps);
        }
    }

    public Producto convertir(ResultSet rs) {
        Producto p = new Producto();
        try {
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            String descripcion = rs.getString("descripcion");
            int id = rs.getInt("idProducto");
            p.setIdProducto(id);
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);
        } catch (Exception e) {
            System.out.println("Error al convertir " + e);
        }
        return p;
    }

    public List<Producto> obtenerTodos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                productos.add(convertir(rs));
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        Conexion.close(ps);
        Conexion.close(rs);
        return productos;
    }

    public DefaultTableModel cargarTabla() {

        String encabezados[] = {"ID", "NOMBRE", "PRECIO", "DESCRIPiÓN"};
        DefaultTableModel df = new DefaultTableModel();
        df.setColumnIdentifiers(encabezados);

        List<Producto> lista = obtenerTodos();
        for (int i = 0; i < lista.size(); i++) {
            Object ob[] = new Object[4];
            ob[0] = lista.get(i).getIdProducto();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getPrecio();
            ob[3] = lista.get(i).getDescripcion();
            df.addRow(ob);
        }
        return df;
    }

}
