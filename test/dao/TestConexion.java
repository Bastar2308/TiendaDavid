/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mx.softwarebastar.dao.Conexion;
import java.sql.SQLException;

/**
 *
 * @author Bastar
 */
public class TestConexion {

    public static void main(String[] args) throws SQLException {
        
        System.out.println(Conexion.getConnection());
    
    }
}
