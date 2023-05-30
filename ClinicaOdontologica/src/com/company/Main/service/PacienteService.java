package com.company.Main.service;

import com.company.Main.dao.IDao;
import com.company.Main.entity.Odontologo;
import com.company.Main.entity.Paciente;

import java.util.List;

public class PacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService() {
    }

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public IDao<Paciente> getPacienteIDao() {
        return pacienteIDao;
    }

    public void setPacienteIDao(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardarPaciente(Paciente paciente){
        pacienteIDao.registrar(paciente);
        return paciente;
    }
    public List<Paciente> listarPaciente(){
        return pacienteIDao.listarTodos();
    }
    public Paciente buscar (String id){
        return pacienteIDao.buscar(id);

    }
    public void eliminar (String id){
        pacienteIDao.eliminar(id);
    }

}
