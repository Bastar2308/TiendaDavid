/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.dao;

import com.mx.softwarebastar.pojo.Desglose_compra;
import com.mx.softwarebastar.pojo.Marca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Davi_
 */
public class MarcaDAO {
     private final String TABLE = "Marca";
    private final String SQL_INSERT
            = "Insert into " + TABLE + "(nombre) values(?)";
    private final String SQL_DELETE = "Delete from " + TABLE + " where idMarca=?";
    private final String SQL_UPDATE = "Update " + TABLE
            + " set nombre=? where idMarca=? ";
    private final String SQL_QUERY = "Select * from " + TABLE;

    public int insertar(Marca p) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Inicializo el ps
            ps = Conexion.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //Llenar la consulta
            ps.setString(1, p.getNombre());
    
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

    public boolean actualizar(Marca p) {
        PreparedStatement ps = null;
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getIdMarca());
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

    public Marca convertir(ResultSet rs) {
        Marca p = new Marca();
        try {
            String nombre = rs.getString("nombre");
            int idMarca = rs.getInt("idMarca");
            
            p.setNombre(nombre);
            p.setIdMarca(idMarca);
           
        } catch (Exception e) {
            System.out.println("Error al convertir " + e);
        }
        return p;
    }

    public List<Marca> obtenerTodos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Marca> marca = new ArrayList<>();
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                marca.add(convertir(rs));
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        Conexion.close(ps);
        Conexion.close(rs);
        return marca;
    }

    public DefaultTableModel cargarTabla() {

        String encabezados[] = {"ID", "Nombre"};
        DefaultTableModel df = new DefaultTableModel();
        df.setColumnIdentifiers(encabezados);

        List<Marca> lista = obtenerTodos();
        for (int i = 0; i < lista.size(); i++) {
            Object ob[] = new Object[5];
            ob[0] = lista.get(i).getIdMarca();
            ob[1] = lista.get(i).getNombre();
           
            df.addRow(ob);
        }
        return df;
    }
}
