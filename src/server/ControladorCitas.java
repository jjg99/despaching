package server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import dao.CitasDAO;
import dao.HorarioDAO;
import dao.UsuarioDAO;
import dominio.Alumno;
import dominio.Profesor;
import util.Fecha;

public class ControladorCitas {
    public static boolean crearTutoriaProfesor(Profesor prof, Date fechaIni, Date fechaFin) {
        String horario = HorarioDAO.getHorario(prof.getId());
        String[] clases = horario.split(";");  // se separa en dias de la semana
        boolean satisfactorio=true;
        if(Fecha.getDiaSemana(fechaIni)<5){
            String[] horas = clases[Fecha.getDiaSemana(fechaIni)].split(","); // se separa las clases del dia
            int citaTiempoIni=Fecha.getHora(fechaIni)*60+Fecha.getMinuto(fechaIni);
            int citaTiempoFin=Fecha.getHora(fechaFin)*60+Fecha.getMinuto(fechaFin);
            for (int i=1; i< horas.length ;i++){ // bucle que rrecore las clases del dia
                int horaini = Integer.valueOf(String.valueOf(horas[i].charAt(0)) + String.valueOf(horas[i].charAt(1))); // variable que almacena la hora de inicio de la clase
                int minutosini = Integer.valueOf(String.valueOf(horas[i].charAt(3)) + String.valueOf(horas[i].charAt(4))); // variable que almacena los minutos de inicio de la clase
                int horafin = Integer.valueOf(String.valueOf(horas[i].charAt(6)) + String.valueOf(horas[i].charAt(7))); // variable que almacena la hora de fin de la clase
                int minutosfin = Integer.valueOf(String.valueOf(horas[i].charAt(9)) + String.valueOf(horas[i].charAt(10))); // variable que almacena los minutos de fin de la clase

                int tiempoIni=horaini*60+minutosini;
                int timepoFin=horafin*60+minutosfin;

                if(tiempoIni>citaTiempoIni && tiempoIni>=citaTiempoFin){

                } else if(timepoFin<=citaTiempoIni){

                } else {
                    satisfactorio = false;
                }
            }
        }

        if(satisfactorio){
            ArrayList<Timestamp> citas = CitasDAO.getCitasProf(prof.getId());
            for (int i=0; i<citas.size();i=i+2) {
                if(citas.get(i).getTime() > fechaIni.getTime() && citas.get(i).getTime()>=fechaFin.getTime()){
                    
                } else if(citas.get(i+1).getTime()<=fechaIni.getTime()){

                } else{
                    satisfactorio = false;
                }
            }
        }
        
        if(satisfactorio){
            ArrayList<String> alumnos = UsuarioDAO.getAlumnosProf(prof.getId());
            for (String idAlu : alumnos) {
                CitasDAO.addCita(prof.getId(), idAlu, fechaIni, fechaFin);
            }
        }
        return satisfactorio;
    }

    public static boolean crearCita(Profesor prof, Alumno alu, Date fechaIni, Date fechaFin) {
        String horario = HorarioDAO.getHorario(prof.getId());
        String[] clases = horario.split(";");  // se separa en dias de la semana
        boolean satisfactorio=true;
        if(Fecha.getDiaSemana(fechaIni)<5){
            String[] horas = clases[Fecha.getDiaSemana(fechaIni)].split(","); // se separa las clases del dia
            int citaTiempoIni=Fecha.getHora(fechaIni)*60+Fecha.getMinuto(fechaIni);
            int citaTiempoFin=Fecha.getHora(fechaFin)*60+Fecha.getMinuto(fechaFin);
            for (int i=1; i< horas.length ;i++){ // bucle que rrecore las clases del dia
                int horaini = Integer.valueOf(String.valueOf(horas[i].charAt(0)) + String.valueOf(horas[i].charAt(1))); // variable que almacena la hora de inicio de la clase
                int minutosini = Integer.valueOf(String.valueOf(horas[i].charAt(3)) + String.valueOf(horas[i].charAt(4))); // variable que almacena los minutos de inicio de la clase
                int horafin = Integer.valueOf(String.valueOf(horas[i].charAt(6)) + String.valueOf(horas[i].charAt(7))); // variable que almacena la hora de fin de la clase
                int minutosfin = Integer.valueOf(String.valueOf(horas[i].charAt(9)) + String.valueOf(horas[i].charAt(10))); // variable que almacena los minutos de fin de la clase

                int tiempoIni=horaini*60+minutosini;
                int timepoFin=horafin*60+minutosfin;

                if(tiempoIni>citaTiempoIni && tiempoIni>=citaTiempoFin){

                } else if(timepoFin<=citaTiempoIni){

                } else {
                    satisfactorio = false;
                }
            }
        }

        if(satisfactorio){
            ArrayList<Timestamp> citas = CitasDAO.getCitasAlumno(alu.getId());
            for (int i=0; i<citas.size();i=i+2) {
                if(citas.get(i).getTime() > fechaIni.getTime() && citas.get(i).getTime()>=fechaFin.getTime()){
                    
                } else if(citas.get(i+1).getTime()<=fechaIni.getTime()){

                } else{
                    satisfactorio = false;
                }
            }
        }
        
        if(satisfactorio){
            System.out.println("\n\n"+prof.getId()+"           "+alu.getId()+"         "+ fechaIni+"         "+fechaFin);
            CitasDAO.addCita(prof.getId(), alu.getId(), fechaIni, fechaFin);
            
        }
        return satisfactorio;


    }

    public static boolean eliminarCitaProfesor(Profesor prof, Date fechaIni){
        boolean satisfactorio = false;
        ArrayList<Timestamp> citas = CitasDAO.getCitasProf(prof.getId());
        for (int i=0; i<citas.size();i=i+2) {
            if (fechaIni.equals(citas.get(i)))
                satisfactorio = true;
        }

        if (satisfactorio)
            CitasDAO.removeCitaProfesor(prof.getId(),fechaIni);

        return satisfactorio;
    }

    public static boolean eliminarCitaAlumno(Alumno alu, Date fechaIni){
        boolean satisfactorio = false;
        ArrayList<Timestamp> citas = CitasDAO.getCitasProf(alu.getId());
        for (int i=0; i<citas.size();i=i+2) {
            if (fechaIni.equals(citas.get(i)))
                satisfactorio = true;
        }

        if (satisfactorio)
            CitasDAO.removeCitaAlumno(alu.getId(),fechaIni);

        return satisfactorio;
    }
}
