package com.company.Main.service;

import com.company.Main.dao.IDao;
import com.company.Main.entity.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao <Odontologo> odontologoIDao;

    public OdontologoService() {
    }
    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        odontologoIDao.registrar(odontologo);
        return odontologo;
    }
    public List<Odontologo> listarOdontologos(){
        return odontologoIDao.listarTodos();
    }
    public Odontologo buscar (String id){
       return odontologoIDao.buscar(id);

    }
    public void eliminar (String id){
        odontologoIDao.eliminar(id);
    }


}
