/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.dao;

import com.mx.softwarebastar.pojo.Proveedor;
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
public class ProveedorDAO {

    private final String TABLE = "Proveedor";
    private final String SQL_INSERT
            = "Insert into " + TABLE + "(nombre, telefono, correo, direccion, nombreContacto, correoContacto, telContacto) values(?,?,?,?,?,?,?)";
    private final String SQL_DELETE = "Delete from " + TABLE + " where idProveedor=?";
    private final String SQL_UPDATE = "Update " + TABLE
            + " set nombre=?, telefono=?, correo=?, direccion=?, nombreContacto=?, correoContacto=?, telContacto=? where idProveedor=?";
    private final String SQL_QUERY = "Select * from " + TABLE;

    public int insertar(Proveedor p) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //Inicializo el ps
            ps = Conexion.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //Llenar la consulta
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getNombreContacto());
            ps.setString(6, p.getCorreoContacto());
            ps.setString(7, p.getTelContacto());
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

    public boolean actualizar(Proveedor p) {
        PreparedStatement ps = null;
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getNombreContacto());
            ps.setString(6, p.getCorreoContacto());
            ps.setString(7, p.getTelContacto());
            ps.setInt(8, p.getIdProveedor());
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

    public Proveedor convertir(ResultSet rs) {
        Proveedor p = new Proveedor();
        try {
                        
            String nombre = rs.getString("nombre");
            String telefono = rs.getString("telefono");
            String correo = rs.getString("correo");
            String direccion = rs.getString("direccion");
            String nombreContacto = rs.getString("nombreContacto");
            String correoContacto = rs.getString("correoContacto");
            String telContacto = rs.getString("telContacto");
            
            p.setNombre(nombre);
            p.setTelefono(telefono);
            p.setCorreo(correo);
            p.setDireccion(direccion);
            p.setNombreContacto(nombreContacto);
            p.setCorreoContacto(correoContacto);
            p.setTelContacto(telContacto);
            
        } catch (Exception e) {
            System.out.println("Error al convertir " + e);
        }
        return p;
    }

    public List<Proveedor> obtenerTodos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proveedor> proveedor = new ArrayList<>();
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                proveedor.add(convertir(rs));
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        Conexion.close(ps);
        Conexion.close(rs);
        return proveedor;
    }

    public DefaultTableModel cargarTabla() {

        String encabezados[] = {"ID","Nombre", "Telefono", "Correo", "Direccion", "Nombre de Contacto", "Correo de Contacto", "Telefono de Contacto"};
        DefaultTableModel df = new DefaultTableModel();
        df.setColumnIdentifiers(encabezados);

        List<Proveedor> lista = obtenerTodos();
        for (int i = 0; i < lista.size(); i++) {
            Object ob[] = new Object[8];
            ob[0] = lista.get(i).getIdProveedor();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getTelefono();
            ob[3] = lista.get(i).getCorreo();
            ob[4] = lista.get(i).getDireccion();
            ob[5] = lista.get(i).getNombreContacto();
            ob[6] = lista.get(i).getCorreoContacto();
            ob[7] = lista.get(i).getTelContacto();
            df.addRow(ob);
        }
        return df;
    }

}
