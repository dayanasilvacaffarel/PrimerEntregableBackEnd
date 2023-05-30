package com.company.Main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionJDBC {
    private String jdbcDriver;
    private String url;
    private String usuario;
    private String password;
    private Connection connection;



    public ConfiguracionJDBC(String jdbcDriver, String url, String usuario, String password, Connection connection) {
        this.jdbcDriver = jdbcDriver;
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        this.connection = connection;
    }

    public ConfiguracionJDBC(){
        this.jdbcDriver = "org.h2.Driver";
        this.url = "jdbc:h2:./test;INIT=runscript FROM 'create.sql'";
        this.usuario = "sa";
        this.password = "sa";

    }
    public Connection conectarConBaseDeDatos(){

        if (connection==null){
            try {
                connection= DriverManager.getConnection(url, usuario, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
