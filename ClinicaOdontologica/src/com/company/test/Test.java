package com.company.test;

import com.company.Main.dao.ConfiguracionJDBC;
import com.company.Main.dao.IDao;
import com.company.Main.dao.impl.OdontologoDaoH2;
import com.company.Main.entity.Odontologo;
import com.company.Main.service.OdontologoService;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.util.List;


public class Test {
    private static  IDao<Odontologo> odontologoIDao = new OdontologoDaoH2(new ConfiguracionJDBC());

    @BeforeClass

    public static void cargarOdontologos(){
        odontologoIDao.registrar(new Odontologo("1","Dayana", "Silva"));
        odontologoIDao.registrar(new Odontologo("2","Mary", "Caffarel"));
    }
    @org.junit.Test
    public void buscar(){
        Odontologo odontologo = odontologoIDao.buscar("1");
        Assert.assertNotNull(odontologo);
    }
    @org.junit.Test
    public void listarTodosLosOdontologos(){

        OdontologoService oService = new OdontologoService();
        Odontologo odontologo1 = new Odontologo("3","Michael", "Scott");
        Odontologo odontologo2 = new Odontologo("4","Homero", "Simpson");

        oService.guardarOdontologo(odontologo1);
        oService.guardarOdontologo(odontologo2);

        List<Odontologo> listaOdontologos = oService.listarOdontologos();

        Assert.assertTrue(listaOdontologos.size() > 0);
    }


}
