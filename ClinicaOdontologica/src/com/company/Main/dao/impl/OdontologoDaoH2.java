package com.company.Main.dao.impl;

import com.company.Main.dao.ConfiguracionJDBC;
import com.company.Main.dao.IDao;
import com.company.Main.entity.Odontologo;

import java.lang.module.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class OdontologoDaoH2 implements IDao <Odontologo> {

    private ConfiguracionJDBC configuracionJDBC;

    private Logger logger = Logger.getLogger(String.valueOf(OdontologoDaoH2.class));

    public OdontologoDaoH2(ConfiguracionJDBC configuracionJDBC) {
        this.configuracionJDBC = configuracionJDBC;
    }
///////////////////////////////////
    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("INSERT INTO odontologos VALUES('%s','%s','%s')", odontologo.getId(), odontologo.getNombre(), odontologo.getApellido(), odontologo);

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            logger.warning("No se ha guardado el odontologo");
            throw new RuntimeException(e);

        }

        logger.info("Se guardo el odontologo");
        return odontologo;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo buscar(String id) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        Odontologo odontologo = null;
        String query = String.format("select * FROM odontologos WHERE ID = '%s'",id);

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String idOdontologo = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                odontologo = new Odontologo(idOdontologo, nombre, apellido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return odontologo;


    }

    @Override
    public void eliminar(String id) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("DELETE FROM odontologos WHERE ID = '%s'",id);

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Odontologo> listarTodos() {

        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("SELECT * FROM odontologos");
        List<Odontologo> odontologos=new ArrayList<>();
        try {
            statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while (resultSet.next()){
                String idOdontologo= resultSet.getString("id");
                String nombre= resultSet.getString("nombre");
                String apellido= resultSet.getString("apellido");
                odontologos.add(new Odontologo(idOdontologo,nombre,apellido));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return odontologos;
    }


    }

