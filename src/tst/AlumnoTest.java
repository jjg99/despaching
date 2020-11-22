package tst;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.BeforeClass;

import dominio.Alumno;
import dominio.Profesor;
import server.ConexionServer;
public class AlumnoTest {
    
    private static Alumno alu;
    private static Profesor prof1, prof3;

    @BeforeClass
    public static void testInit() {
        if(ConexionServer.startConnection()){
            alu = new Alumno("testALU1@comillas.edu", "Alumno1", "Test", "testALU1" );
            prof1 = new Profesor("testPROF1@comillas.edu", "Profesor1", "Test", "testPROF1");
            prof3 = new Profesor("testPROF3@comillas.edu", "Profesor3", "Test", "testPROF3");
        }
    }

    @Test
    public void testGetListaProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        profesores.add(prof1);
        profesores.add(prof3);
        assertEquals(profesores, alu.getListaProfesores());
    }

    @Test
    public void testGetListaColas() {   //En que colas esta el alumno
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        profesores.add(prof3);
        assertEquals(profesores, alu.getListaColas());
    }

}
