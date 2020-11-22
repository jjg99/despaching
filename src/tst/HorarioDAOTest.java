package tst;

import static org.junit.Assert.*;

import org.junit.Test;



import org.junit.BeforeClass;

import server.ConexionServer;
import dao.HorarioDAO;

public class HorarioDAOTest {

    private static String idProf;
    private static String horario;

    @BeforeClass
    public static void testInit() {
        if(ConexionServer.startConnection()){
            idProf = "testPROF1";
            horario = "L,08:00-08:50,16:00-16:50;M,09:00-09:50,11:15-12:05,16:00-16:30;X,09:00-09:50,12:30-13:30;J,17:00-17:50,18:00-18:50;V";
        }
    }

    @Test
    public void testGetHorario() {
        assertEquals(horario, HorarioDAO.getHorario(idProf));
    }

}
