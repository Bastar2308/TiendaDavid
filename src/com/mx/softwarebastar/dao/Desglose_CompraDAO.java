/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.dao;

import com.mx.softwarebastar.pojo.Desglose_compra;
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
            = "Insert into " + TABLE + "(producto_idproducto,compra_idcompra,cantidad,subtotal) values(?,?,?,?)";
    private final String SQL_DELETE = "Delete from " + TABLE + " where idDesglose=?";
    private final String SQL_UPDATE = "Update " + TABLE
            + " set producto_idProducto=?,compra_idCompra=?,cantidad=?,subtotal=? where idDesglose=? ";
    private final String SQL_QUERY = "Select * from " + TABLE;

    public int insertar(Desglose_compra p) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Inicializo el ps
            ps = Conexion.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //Llenar la consulta
            ps.setInt(1, p.getProducto_idProducto());
            ps.setInt(2, p.getCompra_idCompra());
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

    public boolean actualizar(Desglose_compra p) {
        PreparedStatement ps = null;
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, p.getProducto_idProducto());
            ps.setInt(2, p.getCompra_idCompra());
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

    public Desglose_compra convertir(ResultSet rs) {
        Desglose_compra p = new Desglose_compra();
        try {
            int idProducto = rs.getInt("producto_idproducto");
            int idCompra = rs.getInt("compra_idcompra");
            double cantidad = rs.getDouble("cantidad");
            double subtotal = rs.getDouble("subtotal");
            int idDesglose = rs.getInt("idDesglose");
            int id = rs.getInt("idDesglose");
            p.setProducto_idProducto(idProducto);
            p.setCompra_idCompra(idCompra);
            p.setIdDesglose(idDesglose);
            p.setSubtotal(subtotal);
            p.setCantidad(cantidad);
        } catch (Exception e) {
            System.out.println("Error al convertir " + e);
        }
        return p;
    }

    public List<Desglose_compra> obtenerTodos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Desglose_compra> productos = new ArrayList<>();
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

        List<Desglose_compra> lista = obtenerTodos();
        for (int i = 0; i < lista.size(); i++) {
            Object ob[] = new Object[5];
            ob[0] = lista.get(i).getIdDesglose();
            ob[1] = lista.get(i).getProducto_idProducto();
            ob[2] = lista.get(i).getCompra_idCompra();
            ob[3] = lista.get(i).getCantidad();
            ob[4] = lista.get(i).getSubtotal();
            df.addRow(ob);
        }
        return df;
    }

}
