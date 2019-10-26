/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.softwarebastar.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bastar
 */
public class Conexion {

    private static final String HOST = "localhost:3306";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DB = "abarrotes_david";
    private static final String URL = "jdbc:mysql://" + HOST + "/"+DB;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Driver controlador = null;

    
    public static synchronized Connection getConnection() throws SQLException {

        if (controlador == null) {
            try {
                Class jdbcDriver = Class.forName(DRIVER);
                controlador = (Driver) jdbcDriver.newInstance();
                DriverManager.registerDriver(controlador);

            } catch (Exception e) {
                System.out.println("Error al cargar driver");
            }
        }
        return DriverManager.getConnection(URL, USER, PASS);
    } 

    public static void close(Connection cn) {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión " + e);
        }
    }

    public static void close(ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar resultset " + e);
        }

    }

    public static void close(Statement st) {

        try {
            if (st != null) {
                st.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar statement " + e);
        }

    }

    public static void close(PreparedStatement ps) {

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar preparedStatement " + e);
        }

    }

}
