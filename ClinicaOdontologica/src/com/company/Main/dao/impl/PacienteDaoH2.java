package com.company.Main.dao.impl;

import com.company.Main.dao.ConfiguracionJDBC;
import com.company.Main.dao.IDao;
import com.company.Main.entity.Paciente;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PacienteDaoH2 implements IDao<Paciente> {



    private ConfiguracionJDBC configuracionJDBC;

    private Logger logger = Logger.getLogger(String.valueOf(PacienteDaoH2.class));

    public PacienteDaoH2(ConfiguracionJDBC configuracionJDBC) {
        this.configuracionJDBC = configuracionJDBC;
    }
    @Override
    public Paciente registrar(Paciente paciente) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("INSERT INTO pacientes VALUES('%s','%s','%s', '%s', '%s', '%s')",paciente.getId(), paciente.getNombre(), paciente.getApellido(), paciente.getDomicilio(), paciente.getDNI(), paciente.getFechaAlta());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e){
            logger.warning("No se ha guardado el paciente");
            throw new RuntimeException(e);
        }
        logger.info("Se guardo el paciente");
        return paciente;
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente buscar(String id) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        Paciente paciente = null;
        String query = String.format("SELECT * FROM pacientes WHERE ID ='%s'",id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String idPaciente = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String domicilio = resultSet.getString("domicilio");
                String DNI = resultSet.getString("DNI");
                String fechaAlta = resultSet.getString("fechaAlta");
                paciente = new Paciente(idPaciente, nombre, apellido, domicilio, DNI, fechaAlta);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return paciente;
    }

    @Override
    public void eliminar(String id) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("DELETE FROM pacientes WHERE ID='%s'", id);

        try {
            statement =connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("SELECT * FROM pacientes");
        List<Paciente> pacientes = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String idPaciente = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String domicilio = resultSet.getString("domicilio");
                String DNI = resultSet.getString("DNI");
                String fechaAlta = resultSet.getString("fechaAlta");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacientes;
    }
}
