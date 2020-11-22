package tst;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import dominio.Usuario;
import server.ConexionServer;

public class UsuarioTest {
  
    private static Usuario prof3,prof3bis;
    // private static Alumno alu1,alu2;

    @BeforeClass
    public static void testInit() {
        if(ConexionServer.startConnection()){
            
            // alu1 = new Alumno("testALU1@comillas.edu", "Alumno1", "Test", "testALU1" );
            // alu2 = new Alumno("testALU2@comillas.edu", "Alumno2", "Test", "testALU2" );
        }
    }

    @Test
    public void testGetCorreo() {
        prof3 = new Usuario("Profesor3", "Test", "testPROF3");
        prof3.setCorreo("testPROF3@comillas.edu");
        assertEquals("testPROF3@comillas.edu", prof3.getCorreo());
    }

    @Test
    public void testGetNombre() {
        prof3 = new Usuario("testPROF3@comillas.edu");
        prof3.setNombre("Profesor3");
        assertEquals("Profesor3", prof3.getNombre());
    }

    @Test
    public void testGetApellido() {
        prof3 = new Usuario("testPROF3@comillas.edu");
        prof3.setApellido("Test");
        assertEquals("Test", prof3.getApellido());
    }

    @Test
    public void testGetId() {
        prof3 = new Usuario("testPROF3@comillas.edu");
        prof3.setId("testPROF3");
        assertEquals("testPROF3", prof3.getId());
    }

    @Test
    public void testToString() {
        prof3 = new Usuario("Profesor3", "Test", "testPROF3");
        assertEquals("Profesor3 Test", prof3.toString());
    }

    @Test
    public void testEquals() {
        prof3 = new Usuario("Profesor3", "Test", "testPROF3");
        prof3bis = new Usuario("Profesor3", "Test", "testPROF3");
        assertEquals(true, prof3.equals(prof3bis));
        assertEquals(false, prof3.equals(new Usuario("Profesor3", "Test", "testPROF2")));
    }

}
