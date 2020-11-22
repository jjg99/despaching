package tst;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.BeforeClass;

import dominio.Alumno;
import dominio.Profesor;
import server.ConexionServer;

public class ProfesorTest {
  
    private static Profesor prof3;
    private static Alumno alu1,alu2;

    @BeforeClass
    public static void testInit() {
        if(ConexionServer.startConnection()){
            prof3 = new Profesor("testPROF3@comillas.edu", "Profesor3", "Test", "testPROF3");
            alu1 = new Alumno("testALU1@comillas.edu", "Alumno1", "Test", "testALU1" );
            alu2 = new Alumno("testALU2@comillas.edu", "Alumno2", "Test", "testALU2" );
        }
    }

    @Test
    public void testGetListaClases() {
        ArrayList<String> clases = new ArrayList<String>();
        clases.add("A");
        clases.add("B");
        assertEquals(clases, prof3.getListaClases());
    }

    @Test
    public void testGetColaAlu() {   //En que colas esta el alumno
        ArrayList<Alumno> colaAlu = new ArrayList<Alumno>();
        colaAlu.add(alu2);
        colaAlu.add(alu1);
        assertEquals(colaAlu, prof3.getColaAlu());
    }

}

