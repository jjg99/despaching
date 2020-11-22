package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Alumno;
import dominio.Profesor;
import dominio.Usuario;
import server.ConexionServer;

/** Clase que se encarga de realizar las sentencias SQL de la cola */
public class ColaDAO {

    /**
     * Metodo que añade al profesor en la tabla de colas, de manera que puede
     * aceptar alumnos en dicha cola
     * 
     * @param id identificador unico del profesor que va a abrir la cola
     * @return <code>true</code> si la sentencia se ha ejecutado correctamente y si
     *         falla algo devolvera <code>false</code>
     */
    public static boolean openCola(String id) {
        Statement stmt;
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "INSERT INTO \"Colas\"(\"clavePROFESOR\") VALUES ('" + id + "')";
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que borra al profesor de la tabla Colas, de esta manera queda cerrada
     * su cola, no pudiendo apuntarse nuevos alumnos
     * 
     * @param id identificador unico del profesor que va a cerrar la cola
     * @return <code>true</code> si la sentencia se ha ejecutado correctamente y si
     *         falla algo devolvera <code>false</code>
     */
    public static boolean closeCola(String id) {
        Statement stmt;
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "DELETE FROM \"Colas\" WHERE \"clavePROFESOR\"='" + id + "'";
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo encargado de acceder a la base de datos y de cargar las colas al que
     * se encuentra añadido un alumno
     * 
     * @param String id
     * @return @link ArrayList
     */

    public static ArrayList<Profesor> getColasAlumno(String idAlumno) {
        Statement stmt; // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        ArrayList<Profesor> resultadoColas = new ArrayList<Profesor>(); // objeto que va a contener el resultado que se
                                                                    // envia al alumno
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"nombre\",\"apellido\",\"clave\" FROM \"Colas\" FULL OUTER JOIN \"Usuarios\" ON  \"clavePROFESOR\" = \"clave\"  WHERE \"colas\" LIKE '%"+idAlumno+"%' ";
            resultadoConsulta = stmt.executeQuery(sql); // se ejecuta la solicitud sql
            // se lee la respuesta por parte de la base de datos
            while (resultadoConsulta.next()) {
                String nombre = resultadoConsulta.getString("nombre");
                String apellido = resultadoConsulta.getString("apellido");
                String id = resultadoConsulta.getString("clave"); 
                Usuario usuario = new Usuario(nombre,apellido,id);
                resultadoColas.add((Profesor)usuario);      // se hace un downcasting al objeto de usuario y se guarda en el arraylist

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoColas;
    }

    /**
     * Metodo que se encarga de devolver la posiciónk en la cola de un alumno
     * 
     * @param String idAlumno
     * @param String profesorCola
     * @return int posicion
     */
    public static int getPosicionColaAlumno(String idAlumno, String colaProfesor) {
        Statement stmt; // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        int resultadoPosicion = 0; // objeto que va a contener el resultado que se envia al alumno, en el caso de
                                   // que se produzca un error devolverá un 0
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"colas\" FROM \"Colas\" WHERE \"clavePROFESOR\" = '" + colaProfesor +
                         "' AND \"colas\" LIKE '%" + idAlumno + "%'";
        
            resultadoConsulta = stmt.executeQuery(sql); // se ejecuta la solicitud sql
            // se lee la respuesta por parte dela base de datos
            while (resultadoConsulta.next()) {
                String colaCompleta = resultadoConsulta.getString("colas");
                // ahora que se tiene toda la cola hay que fragmentarla
                String[] colaFragmentada = colaCompleta.split(",");
                // se recorre la cola para buscar el termino que se quiere
                int posicion = 0; // se utiliza la variable como un contador para saber en que posición se
                                  // encuentra el alumno
                for (String elemento : colaFragmentada) {
                    if (elemento.equals(",")) {
                        // en el caso de que se trate de un divisor no se aumenta el contador

                    } else if (elemento.equals(idAlumno)) {
                        resultadoPosicion = posicion + 1;

                    } else {
                        posicion = posicion + 1; // si se trata de un alumno se
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Se anade el + 1 para devolver la posicion y no los alumnos que existen anteriores
        return resultadoPosicion;

    }

    /**
     * Metodo encargado de conseguir la cola de alumnos de un profesor determinado
     * 
     * @param String idProfesor
     * @return {@link ArrayList}
     */
    public static ArrayList<Alumno> getColaProfesor(String idProfesor) {
        Statement stmt; // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null;
        ArrayList<Alumno> resultadoCola = new ArrayList<Alumno>();
        // ArrayList<String> resultadoCola = new ArrayList<String>(); // objeto que va a
        // contener el resultado que se envia al alumno
        // Ejecucion de la sentencia SQL
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "SELECT \"colas\" FROM \"Colas\" WHERE \"clavePROFESOR\" = '" + idProfesor + "'";
            resultadoConsulta = stmt.executeQuery(sql); // se ejecuta la solicitud sql
            // se lee la respuesta por parte de la base de datos
            while (resultadoConsulta.next()) {
                String colaCompleta = resultadoConsulta.getString("colas");
                // ahora que se tiene toda la cola hay que fragmentarla
                String[] colaFragmentada = colaCompleta.split(",");
                // se recorre la cola para buscar el termino que se quiere
                for (String elemento : colaFragmentada) {
                    if (elemento.equals(",")) {
                        // en el caso de que se trate de un divisor no se aumenta el contador

                    } else {
                        String nombreYapellido[] = UsuarioDAO.getNombreAlumno(elemento).split(" ");
                        StringBuilder nombreCompleto = new StringBuilder();
                        if (nombreYapellido.length >= 2) {
                            for (int i = 0; i < nombreYapellido.length - 1; i++) {
                                nombreCompleto.append(nombreYapellido[i]);
                                if (i < nombreYapellido.length - 2) {
                                    nombreCompleto.append(" ");
                                }
                            }
                        } else {
                            nombreCompleto.append(nombreYapellido[0]);
                        }
                        resultadoCola.add(new Alumno(nombreCompleto.toString(),
                                nombreYapellido[nombreYapellido.length - 1], elemento));
                        nombreCompleto = new StringBuilder();
                    }

                }
            }
        } catch (NullPointerException NPE) {
            return resultadoCola;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultadoCola;
    }

    /**
     * Metodo encargado de añadir un alumno a la una cola determinada, al final de
     * la cola
     * 
     * @param Alumno   alumno
     * @param Profesor profesor
     */
    public static void addAlumnoCola(Alumno alumno, Profesor profesor) {
        Statement stmt; // se crea el statement sobre el que trabajar
        StringBuilder stringCompuesto = new StringBuilder(); // objeto utilizado para formar un string con el nombre del
                                                             // alumno y su clave
        // Ejecucion de la sentencia SQL
        // primero se necesita conseguir la cola actual de alumno asociada al profesor
        // en concreto
        ArrayList<Alumno> colaProfesor = ColaDAO.getColaProfesor(profesor.getId());
        // se constuye un string con todos los alumnos de la cola
        for (Alumno alumnoEnCola : colaProfesor) {
            stringCompuesto.append(alumnoEnCola.getId());
            stringCompuesto.append(",");
        }
        // por ultimo se añade el alumno que se desea poner al final de la cola
        stringCompuesto.append(alumno.getId());
        // ahora se inserta el nuevo string en la base de datos
        try {
            stmt = ConexionServer.conexion.createStatement();
            String sql = "UPDATE \"Colas\" SET \"colas\"='" + stringCompuesto.toString() + "' WHERE \"clavePROFESOR\" ='"
                    + profesor.getId() + "'";
            stmt.executeUpdate(sql); // se ejecuta la solicitud sql
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo para borrar a un alumno de una cola determinada
     * 
     * @param Alumno   alumno
     * @param Profesor profesor
     */
    public static void delAlumnoCola(Usuario alumno, Profesor profesor) {
        Statement stmt; // se crea el statement sobre el que trabajar

        // para poder borrar a un alumno de una cola, primero se necesita cargar la cola
        // del profesor
        ArrayList<Alumno> colaProfesor = ColaDAO.getColaProfesor(profesor.getId());

        // busca en la cola en indice del alumno que se desea borrar
        int indiceAlumno = colaProfesor.indexOf(alumno);
        if (indiceAlumno < 0) { // indicaría que el alumno no pertenece a la col
            System.out.println("El alumno no pertenece a la cola");
        } else {
            StringBuilder newColaProfesor;
            try {
                // se borra el elemento siguiente, ya que después de cada alumno existe una coma
                // de separaciópn, se borra primero para evitar problemas de indices
                colaProfesor.remove(indiceAlumno);
            } catch (Error error) {
                // no se hace nada, se pone el try porque en el caso de que se borre el utlimo
                // elemento de la lista, va a saltar error al no tener ningun elemento en ese
                // indice
            }
            try {
                newColaProfesor = new StringBuilder();
                for (Alumno alu : colaProfesor) {
                    newColaProfesor.append(alu.getId()).append(",");
                }
                stmt = ConexionServer.conexion.createStatement();
                String sql = "UPDATE \"Colas\" SET \"colas\"='"
                        + newColaProfesor.substring(0, newColaProfesor.length() - 1) + "' WHERE \"clavePROFESOR\" ='"
                        + profesor.getId() + "'";
                stmt.executeUpdate(sql); // se ejecuta la solicitud sql
            } catch (StringIndexOutOfBoundsException se) {  //Si es el ultimo alumno, saltara esta excepcion
                try {
                    String sql = "UPDATE \"Colas\" SET \"colas\"= null WHERE \"clavePROFESOR\" ='" + profesor.getId() + "'";
                    stmt = ConexionServer.conexion.createStatement();
                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }catch (Exception e) {
                    e.printStackTrace();
            }
        }

    }

    /**
     * Metodo que comprueba si la cola de un profesor esta abierta o no
     * @param idProfesor String que indica el id de un profesor
     * @return Boolean que devuelve si la cola esta abierta
     */

    public static Boolean isColaAbierta(String idProfesor)
    {
        Statement stmt; // se crea el statement sobre el que trabajar
        ResultSet resultadoConsulta = null; // Se crea el resultset
        Boolean isAbierta = false; // Se crea la variable de return

        try {
            stmt = ConexionServer.conexion.createStatement();       //Creacion del statement
            String sql = "SELECT \"clavePROFESOR\" FROM \"Colas\" WHERE \"clavePROFESOR\" ='" + idProfesor + "'"; 
            // Sentencia SQL que solicita la devolucion del idPROFESOR para comprobar si existe dentro de la tabla

            resultadoConsulta = stmt.executeQuery(sql);     //Realizacion de la consulta SQL

            while (resultadoConsulta.next()) {
                String id = resultadoConsulta.getString("clavePROFESOR");       //Analisis de la consulta para determinar si esta abierta

                if (id.equals(idProfesor)) {
                    isAbierta = true;       // COnfirmacion de la apertura de la cola
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAbierta;
    }

}


