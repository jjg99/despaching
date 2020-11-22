package tst;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dao.ColaDAO;

import org.junit.BeforeClass;

import dominio.Alumno;
import dominio.GestorCola;
import dominio.Profesor;
import server.ConexionServer;

/**Clase encargada de probar si la cola funciona correctamente */
public class GestorColaTest {
  
    private static Profesor profTest;
    private static Alumno alu1,alu2;

    @BeforeClass
    public static void testInit() {
        // lo primero que hace el crear un profesor y dos alumnos
        if(ConexionServer.startConnection()){
            profTest = new Profesor("profesorTest@comillas.edu", "ProfesorPrueba", "Test", "profTest");
            alu1 = new Alumno("testALU1@comillas.edu", "Alumno1", "Test", "testALU1" );
            alu2 = new Alumno("testALU2@comillas.edu", "Alumno2", "Test", "testALU2" );
        }
    }

    @Test
    public void testOpenCola() {
        // prueba que se pueda crear una cola nueva
        assertEquals(GestorCola.openCola(profTest.getId()), true);
    }

    @Test
    public void testAddAlu() {   // prueba de añadir un alumno a la cola
        ArrayList<Alumno> alumnosCola = new ArrayList<Alumno>();
        alumnosCola.add(alu1);
        //se añade un alumno a la cola de profesor
        GestorCola.addAlumnoCola(alu1, profTest);
        // se coge la cola del profesor y se compara
        assertEquals(alumnosCola, profTest.getColaAlu());
    }
    @Test
    public void testPosAlu() {   // prueba la posición en la cola de un alumno
        ArrayList<Alumno> alumnosCola = new ArrayList<Alumno>();
        alumnosCola.add(alu1);
        //se añade un alumno a la cola de profesor
        ColaDAO.addAlumnoCola(alu2, profTest);
        // se coge la cola del profesor y se compara
        assertEquals(2, GestorCola.getPosicionAlumno(alu2.getId(),profTest.getId()));
    }
    @Test
    public void testDelAlu() {   // prueba la posición en la cola de un alumno
        ArrayList<Alumno> alumnosCola = new ArrayList<Alumno>();
        alumnosCola.add(alu1);
        //se añade un alumno a la cola de profesor
        GestorCola.delAlumno(alu2, profTest);
        // se coge la cola del profesor y se compara
        assertEquals(alumnosCola, profTest.getColaAlu());
    }
    @Test
    public  void testColaAbierta() {
        // prueba que se pueda crear una cola nueva
        assertEquals(GestorCola.isColaAbierta(profTest.getId()), true);
    }
    @Test
    public  void closeCola() {
        // prueba que se pueda crear una cola nueva
        assertEquals(GestorCola.closeCola(profTest.getId(),true), true);
    }
}

