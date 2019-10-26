/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.dao;


import com.mx.softwarebastar.pojo.Desglose_venta;
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
public class Desglose_VentaDAO {

    private final String TABLE = "Desglose_Venta";
    private final String SQL_INSERT
            = "Insert into " + TABLE + "(producto_idproducto,venta_idventa,cantidad,subtotal) values(?,?,?,?)";
    private final String SQL_DELETE = "Delete from " + TABLE + " where idDesglose=?";
    private final String SQL_UPDATE = "Update " + TABLE
            + " set producto_idProducto=?,venta_idventa=?,cantidad=?,subtotal=? where idDesglose=? ";
    private final String SQL_QUERY = "Select * from " + TABLE;

    public int insertar(Desglose_venta p) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Inicializo el ps
            ps = Conexion.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //Llenar la consulta
            ps.setInt(1, p.getProducto_idProducto());
            ps.setInt(2, p.getVenta_idVenta());
            ps.setDouble(3, p.getCantidad());
            ps.setDouble(4, p.getSubtotal());
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

    public boolean actualizar(Desglose_venta p) {
        PreparedStatement ps = null;
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, p.getProducto_idProducto());
            ps.setInt(2, p.getVenta_idVenta());
            ps.setDouble(3, p.getCantidad());
            ps.setDouble(4, p.getSubtotal());
            ps.setInt(5, p.getIdDesglose());
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

    public Desglose_venta convertir(ResultSet rs) {
        Desglose_venta p = new Desglose_venta();
        try {
            int idProducto = rs.getInt("producto_idproducto");
            int idVenta = rs.getInt("venta_idventa");
            double cantidad = rs.getDouble("cantidad");
            double subtotal = rs.getDouble("subtotal");
            int idDesglose = rs.getInt("idDesglose");
            
            p.setProducto_idProducto(idProducto);
            p.setVenta_idVenta(idVenta);
            p.setIdDesglose(idDesglose);
            p.setSubtotal(subtotal);
            p.setCantidad(cantidad);
        } catch (Exception e) {
            System.out.println("Error al convertir " + e);
        }
        return p;
    }

    public List<Desglose_venta> obtenerTodos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Desglose_venta> desgloseVenta = new ArrayList<>();
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                desgloseVenta.add(convertir(rs));
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        Conexion.close(ps);
        Conexion.close(rs);
        return desgloseVenta;
    }

    public DefaultTableModel cargarTabla() {

        String encabezados[] = {"ID", "Producto", "Venta", "Cantidad","Subtotal"};
        DefaultTableModel df = new DefaultTableModel();
        df.setColumnIdentifiers(encabezados);

        List<Desglose_venta> lista = obtenerTodos();
        for (int i = 0; i < lista.size(); i++) {
            Object ob[] = new Object[5];
            ob[0] = lista.get(i).getIdDesglose();
            ob[1] = lista.get(i).getProducto_idProducto();
            ob[2] = lista.get(i).getVenta_idVenta();
            ob[3] = lista.get(i).getCantidad();
            ob[4] = lista.get(i).getSubtotal();
            df.addRow(ob);
        }
        return df;
    }

}
