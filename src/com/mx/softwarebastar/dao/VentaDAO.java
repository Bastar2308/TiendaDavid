/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.dao;

import com.mx.softwarebastar.pojo.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Davi_
 */
public class VentaDAO {
     private final String TABLE = "Venta";
    private final String SQL_INSERT
            = "Insert into " + TABLE + "(cantidadArticulos,total,fecha) values(?,?,?)";
    private final String SQL_DELETE = "Delete from " + TABLE + " where idVenta=?";
    private final String SQL_UPDATE = "Update " + TABLE
            + " set cantidadArticulos=?,total=?,fecha=? where idVenta=? ";
    private final String SQL_QUERY = "Select * from " + TABLE;

    public int insertar(Venta p) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Inicializo el ps
            ps = Conexion.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //Llenar la consulta
            ps.setInt(1, p.getCantidadArticulos());
            ps.setDouble(2, p.getTotal());
            ps.setTimestamp(3, p.getFecha());
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

    public boolean actualizar(Venta p) {
        PreparedStatement ps = null;
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, p.getCantidadArticulos());
            ps.setDouble(2, p.getTotal());
            ps.setTimestamp(3, p.getFecha());
            ps.setInt(4, p.getIdVenta());
            if (ps.executeUpdate() == 0) {
                System.out.println("Error al actualizar");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar " + e);
            return false;
        } finally {
            Conexion.close(ps);
        }
        return true;
    }

    public Venta convertir(ResultSet rs) {
        Venta p = new Venta();
        try {
            int cantidadArticulos = rs.getInt("cantidad_articulos");
            double total = rs.getDouble("total");
            Timestamp fecha = rs.getTimestamp("fecha");
            int id = rs.getInt("idVenta");
            p.setCantidadArticulos(cantidadArticulos);
            p.setTotal(total);
            p.setFecha(fecha);
            p.setIdVenta(id);
        } catch (Exception e) {
            System.out.println("Error al convertir " + e);
        }
        return p;
    }

    public List<Venta> obtenerTodos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Venta> venta = new ArrayList<>();
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                venta.add(convertir(rs));
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        Conexion.close(ps);
        Conexion.close(rs);
        return venta;
    }

    public DefaultTableModel cargarTabla() {

        String encabezados[] = {"ID", "Cantidad Articulos", "Total", "Fecha"};
        DefaultTableModel df = new DefaultTableModel();
        df.setColumnIdentifiers(encabezados);

        List<Venta> lista = obtenerTodos();
        for (int i = 0; i < lista.size(); i++) {
            Object ob[] = new Object[4];
            ob[0] = lista.get(i).getIdVenta();
            ob[1] = lista.get(i).getCantidadArticulos();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            df.addRow(ob);
        }
        return df;
    }
}
