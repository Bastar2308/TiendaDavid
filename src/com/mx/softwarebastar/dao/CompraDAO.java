/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.dao;

import com.mx.softwarebastar.pojo.Compra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bastar
 */
public class CompraDAO {

    private final String TABLE = "Compra";
    private final String SQL_INSERT
            = "Insert into " + TABLE + "(cantidadArticulos,total,fecha,Proveedor_idproveedor) values(?,?,?,?)";
    private final String SQL_DELETE = "Delete from " + TABLE + " where idCompra=?";
    private final String SQL_UPDATE = "Update " + TABLE
            + " set cantidadArticulos=?,total=?,fecha=?,Proveedor_idproveedor=? where idCompra=? ";
    private final String SQL_QUERY = "Select * from " + TABLE;

    public int insertar(Compra p) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Inicializo el ps
            ps = Conexion.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //Llenar la consulta
            ps.setInt(1, p.getCantidadArticulos());
            ps.setDouble(2, p.getTotal());
            ps.setTimestamp(3, p.getFecha());
            ps.setInt(4, p.getProveedor_idProveedor());
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

    public boolean actualizar(Compra p) {
        PreparedStatement ps = null;
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, p.getCantidadArticulos());
            ps.setDouble(2, p.getTotal());
            ps.setTimestamp(3, p.getFecha());
            ps.setInt(4, p.getProveedor_idProveedor());
            ps.setInt(5, p.getIdCompra());
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

    public Compra convertir(ResultSet rs) {
        Compra p = new Compra();
        try {
            int cantidadArticulos = rs.getInt("cantidadArticulos");
            double total = rs.getInt("total");
            Timestamp fecha = rs.getTimestamp("Fecha");
            int proveedor_IdProveedor = rs.getInt("proveedor_idproveedor");
            int idCompra = rs.getInt("idCompra");
            p.setCantidadArticulos(cantidadArticulos);
            p.setTotal(total);
            p.setFecha(fecha);
            p.setProveedor_idProveedor(proveedor_IdProveedor);
            p.setIdCompra(idCompra);
        } catch (Exception e) {
            System.out.println("Error al convertir " + e);
        }
        return p;
    }

    public List<Compra> obtenerTodos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Compra> productos = new ArrayList<>();
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

        String encabezados[] = {"ID", "Producto", "Compra", "Cantidad","Subtotal"};
        DefaultTableModel df = new DefaultTableModel();
        df.setColumnIdentifiers(encabezados);

        List<Compra> lista = obtenerTodos();
        for (int i = 0; i < lista.size(); i++) {
            Object ob[] = new Object[5];
            ob[0] = lista.get(i).getIdCompra();
            ob[1] = lista.get(i).getCantidadArticulos();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            ob[4] = lista.get(i).getProveedor_idProveedor();
            df.addRow(ob);
        }
        return df;
    }

}
