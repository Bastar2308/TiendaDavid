package com.mx.softwarebastar.dao;

import com.mx.softwarebastar.pojo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ProductoDAO {
    private final String TABLE = "producto";
    private final String SQL_INSERT
            = "INSERT INTO " + TABLE + "(nombre, codigo, inventario, precio, descripcion, Marca_idMarca, unidadMedida) VALUES(?,?,?,?,?,?,?)";
    private final String SQL_DELETE = "DELETE FROM " + TABLE + " WHERE idProducto=?";
    private final String SQL_UPDATE = "UPDATE " + TABLE
            + " SET nombre = ?, codigo = ?, inventario = ?, precio = ?, descripcion = ?, Marca_idMarca = ?, unidadMedida = ? WHERE idProducto = ?";
    private final String SQL_QUERY = "SELECT * FROM " + TABLE;
    
    public int insertar(Producto p) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCodigo());
            ps.setDouble(3, p.getInventario());
            ps.setDouble(4, p.getPrecio());
            ps.setString(5, p.getDescripcion());
            ps.setInt(6, p.getMarca_idMarca());
            ps.setString(7, p.getUnidadMedida());
            
            if (ps.executeUpdate() == 0) {
                System.out.println("Error al insertar");
                return 0;
            }
            
            rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
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
    
    public boolean actualizar(Producto p) {
        PreparedStatement ps = null;
        try {
            ps = Conexion.getConnection().prepareStatement(SQL_UPDATE);
            
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCodigo());
            ps.setDouble(3, p.getInventario());
            ps.setDouble(4, p.getPrecio());
            ps.setString(5, p.getDescripcion());
            ps.setInt(6, p.getMarca_idMarca());
            ps.setString(7, p.getUnidadMedida());
            
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
    
    public Producto convertir(ResultSet rs) {
        Producto p = new Producto();
        
        try {
            String nombre = rs.getString("nombre");
            String codigo = rs.getString("codigo");
            double inventario = rs.getDouble("inventario");
            double precio = rs.getDouble("precio");
            String descripcion = rs.getString("descripcion");
            int marca = rs.getInt("Marca_idMarca");
            String unidadMedida = rs.getString("unidadMedida");
            
            p.setNombre(nombre);
            p.setCodigo(codigo);
            p.setInventario(inventario);
            p.setPrecio(precio);
            p.setDescripcion(descripcion);
            p.setMarca_idMarca(marca);
            p.setUnidadMedida(unidadMedida);
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

        String encabezados[] = {"ID", "Nombre", "Codigo", "Inventario", "Precio", "Descripcion", "idMarca", "Unidad de Medida"};
        DefaultTableModel df = new DefaultTableModel();
        df.setColumnIdentifiers(encabezados);

        List<Producto> lista = obtenerTodos();
        for (int i = 0; i < lista.size(); i++) {
            Object ob[] = new Object[8];
            ob[0] = lista.get(i).getIdProducto();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getCodigo();
            ob[3] = lista.get(i).getInventario();
            ob[4] = lista.get(i).getPrecio();
            ob[5] = lista.get(i).getDescripcion();
            ob[6] = lista.get(i).getMarca_idMarca();
            ob[7] = lista.get(i).getUnidadMedida();
            df.addRow(ob);
        }
        return df;
    }
}
