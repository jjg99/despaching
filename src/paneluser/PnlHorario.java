package paneluser;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import dominio.Alumno;
import dominio.Usuario;
import server.Fachada;
import util.Colores;
import util.Fecha;
import util.Fuente;
import util.GestionMensajes;

/** Panel generico el cual mostrata un horario de un profesor */
public class PnlHorario extends JPanel{
    /**
     */
    private static final long serialVersionUID = 1L;

    public static PnlHorario pnlHorario; // se crea la variable que se instanciará desde fuera
    
    /** atributo que contiene el usuario al que pertenece el horario */
    private Usuario usuario;

    /** Atributo que contendrá el horario del usuario */
    private String horario;

    /** Atributo que contendra las clases de cada dia */
    private String[] clases;

    /** Matriz de botones que contendra el horario del profesor */
    private JButton btnHoras[][];

    /** Matriz con la informacion de las horas */
    private String[][] informacion = new String[7][2];

    /** Matriz con la ocupacion en tanto por 60 de cada hora */
    private int[][] ocupacion = new int[7][2];

    /** Constructor que llama al metodo {@link initComponents} */
    public PnlHorario(Usuario usuario,int diaSemana, int anio, int mes, int dia){
        setUsuario(usuario);
        this.horario= Fachada.getHorario(this.usuario.getId()); // se consigue el horario de la base de datos
        clases = horario.split(";");  // se separa en diaSemanas de la semana
        initComponents(diaSemana, anio, mes, dia);
    }

    /** metodo para establecer el usuario
     * @param usuario contiene el usuario al que el horario pertenece
     */
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    private void initComponents(int diaSemana, int anio, int mes, int dia){
        this.setLayout(new GridLayout(7,2,5,5));    //Matriz de 7*2 con separacion de 5 en ambos sentidos
        
        btnHoras = new JButton[7][2];
        for(int i=0; i<7; i++){
            for(int j=0; j<2; j++){
                int hora = 8+i+(j*7);   //formula para obtener las distintas horas del horario en funcion de j e i
                btnHoras[i][j] = new JButton(hora +":00 - "+(hora + 1)+":00");
                Colores.setBGVerde(btnHoras[i][j]);
                Fuente.setFuente(btnHoras[i][j]);
                this.add(btnHoras[i][j]);
            }
        }
        //hacemos que el horario sea dinámico
        this.setHorario(diaSemana, anio, mes, dia);
        this.setAccionesBtn();
    }

    /**
     * Metodo para colorear todos los botones, de una matriz, de color verde
     * @param btns Matriz de botones a colorear
     */
    private void setBtnsGreen(){
        for(int i=0; i<7; i++){
            for(int j=0; j<2; j++){
                Colores.setBGVerde(btnHoras[i][j]);
            }
        }
        this.updateUI();
    }

    public void actualizarHorarioCache() {
        this.horario= Fachada.getHorario(this.usuario.getId()); // se consigue el horario de la base de datos
        clases = horario.split(";");  // se separa en diaSemanas de la semana
    }
    
    /** metodo para establecer el horario del profesor de manera dinamica
     * @param btnHoras es una matriz de botones que seran de un color especifico y se les cambiara el color
     */
    public void setHorario(int diaSemana, int anio, int mes, int dia){
        if(diaSemana <= 4){   //Solo tratamos de lunes a viernes, los findes no hay clases
            for(int i=0; i<7; i++){
                for(int j=0; j<2; j++){
                    ocupacion[i][j]=0;
                    informacion[i][j]=null;
                }
            }
            String[] horas = clases[diaSemana].split(","); // se separa las clases del diaSemana

            for (int i=1; i< horas.length ;i++){ // bucle que rrecore las clases del diaSemana
                int horaini = Integer.valueOf(String.valueOf(horas[i].charAt(0)) + String.valueOf(horas[i].charAt(1))); // variable que almacena la hora de inicio de la clase
                int minutosini = Integer.valueOf(String.valueOf(horas[i].charAt(3)) + String.valueOf(horas[i].charAt(4))); // variable que almacena los minutos de inicio de la clase
                int horafin = Integer.valueOf(String.valueOf(horas[i].charAt(6)) + String.valueOf(horas[i].charAt(7))); // variable que almacena la hora de fin de la clase
                int minutosfin = Integer.valueOf(String.valueOf(horas[i].charAt(9)) + String.valueOf(horas[i].charAt(10))); // variable que almacena los minutos de fin de la clase

                if(horaini==horafin){   //Estan en la misma hora
                    if (horaini-7 > 7 ){
                        StringBuilder sb = new StringBuilder();
                        // Añadimos el mensaje en la hora en concreto
                        if(informacion[horaini -15][1]!=null){
                            sb.append(informacion[horaini -15][1])
                                .append("\n");
                        }
                        sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                        sb.append("   Clase");
                        informacion[horaini -15][1]=sb.toString();
                        // Añadimos la ocupacion
                        ocupacion[horaini -15][1]=ocupacion[horaini -15][1]+(minutosfin-minutosini);
                    }else{
                        StringBuilder sb = new StringBuilder();
                        // Añadimos el mensaje en la hora en concreto
                        if(informacion[horaini -8][0]!=null){
                            sb.append(informacion[horaini -8][0])
                                .append("\n");
                        }
                        sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                        sb.append("   Clase");
                        informacion[horaini -8][0]=sb.toString();
                        // Añadimos la ocupacion
                        ocupacion[horaini -8][0]=ocupacion[horaini -8][0]+(minutosfin-minutosini);
                    }
                } else {
                    if (horaini-7 > 7 ){
                        StringBuilder sb = new StringBuilder();
                        // Añadimos el mensaje en la primera hora
                        if(informacion[horaini -15][1]!=null){
                            sb.append(informacion[horaini -15][1])
                                .append("\n");
                        }
                        sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                        sb.append("   Clase");
                        informacion[horaini -15][1]=sb.toString();
                        // Añadimos la ocupacion de la primera hora
                        ocupacion[horaini -15][1]=ocupacion[horaini -15][1]+(60-minutosini);

                        sb = new StringBuilder();
                        // Añadimos el mensaje en la segunda hora
                        if(informacion[horafin -15][1]!=null){
                            sb.append(informacion[horafin -15][1])
                                .append("\n");
                        }
                        sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                        sb.append("   Clase");
                        informacion[horafin -15][1]=sb.toString();
                        // Añadimos la ocupacion de la segunda hora
                        ocupacion[horafin -15][1]=ocupacion[horafin -15][1]+minutosfin;
                    }else{
                        StringBuilder sb = new StringBuilder();
                        // Añadimos el mensaje de la primera hora
                        if(informacion[horaini -8][0]!=null){
                            sb.append(informacion[horaini -8][0])
                                .append("\n");
                        }
                        sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                        sb.append("   Clase");
                        informacion[horaini -8][0]=sb.toString();
                        // Añadimos la ocupacion de la primera hora
                        ocupacion[horaini -8][0]=ocupacion[horaini -8][0]+(60-minutosini);

                        sb = new StringBuilder();
                        // Añadimos el mensaje de la segunda hora
                        if(informacion[horafin -8][0]!=null){
                            sb.append(informacion[horafin -8][0])
                                .append("\n");
                        }
                        sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                        sb.append("   Clase");
                        informacion[horafin -8][0]=sb.toString();
                        // Añadimos la ocupacion de la primera hora
                        ocupacion[horafin -8][0]=ocupacion[horafin -8][0]+minutosfin;
                    }
                }
                
            }
            this.setBtnsColores();
        } else {
            for(int i=0; i<7; i++){
                for(int j=0; j<2; j++){
                    ocupacion[i][j]=0;
                    informacion[i][j]=null;
                }
            }
            this.setBtnsGreen();
        }
        ArrayList<Timestamp> citas;
        if(usuario instanceof Alumno){
            //TODO
            citas=Fachada.getCitasAlumno(usuario.getId());
        } else {
            citas=Fachada.getCitasProfesor(usuario.getId());
        }
        ArrayList<Integer> tiempoIni = new ArrayList<Integer>();
        ArrayList<Integer> tutorias = new ArrayList<Integer>();
        ArrayList<Integer> tutoriasPos = new ArrayList<Integer>();
        for (int i=0; i<citas.size();i=i+2){
            if(Fecha.getAnio(citas.get(i))==anio && Fecha.getMes(citas.get(i))==mes && Fecha.getDia(citas.get(i))==dia){
                int horainicio = Fecha.getHora(citas.get(i))*60+Fecha.getMinuto(citas.get(i));
                if(tiempoIni.contains(horainicio)){
                    tutoriasPos.add(i);
                    tutorias.add(horainicio);
                } else {
                    tiempoIni.add(horainicio);
                }
            }
        }
        for (int i=0; i<citas.size();i=i+2) {
            if(Fecha.getAnio(citas.get(i))==anio && Fecha.getMes(citas.get(i))==mes && Fecha.getDia(citas.get(i))==dia && !tutoriasPos.contains(i)){
                int horaini=Fecha.getHora(citas.get(i));
                int minutosini=Fecha.getMinuto(citas.get(i));
                int horafin=Fecha.getHora(citas.get(i+1));
                int minutosfin=Fecha.getMinuto(citas.get(i+1));
                if(horafin>=22){
                    horafin=21;
                    minutosfin=59;
                }

                // if(tutorias.contains(horaini*60+minutosini)){
                    for(int j=0; j<=horafin-horaini; j++){
                        if (horaini+j-7 > 7 ){
                            StringBuilder sb = new StringBuilder();
                            // Añadimos el mensaje en la hora en concreto
                            if(informacion[horaini+j -15][1]!=null){
                                sb.append(informacion[horaini+j -15][1])
                                .append("\n");
                            }
                            sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                            if(tutorias.contains(horaini*60+minutosini)){
                                sb.append("   Tutoria");
                            } else {
                                sb.append("   Cita");
                            }
                            informacion[horaini+j -15][1]=sb.toString();
                            if(horafin-horaini==0){
                                ocupacion[horaini+j -15][1]=ocupacion[horaini+j -15][1]+(minutosfin-minutosini);
                            } else if(horaini+j==horafin){
                                ocupacion[horaini+j -15][1]=ocupacion[horaini+j -15][1]+(minutosfin);
                            } else if(j==0){
                                ocupacion[horaini+j -15][1]=ocupacion[horaini+j -15][1]+(60-minutosini);
                            } else {
                                ocupacion[horaini+j -15][1]=ocupacion[horaini+j -15][1]+60;
                            }
                        } else {
                            StringBuilder sb = new StringBuilder();
                            // Añadimos el mensaje en la hora en concreto
                            if(informacion[horaini+j -8][0]!=null){
                                sb.append(informacion[horaini+j -8][0])
                                .append("\n");
                            }
                            sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                            if(tutorias.contains(horaini*60+minutosini)){
                                sb.append("   Tutoria");
                            } else {
                                sb.append("   Cita");
                            };
                            informacion[horaini+j -8][0]=sb.toString();
                            if(horafin-horaini==0){
                                ocupacion[horaini+j -8][0]=ocupacion[horaini+j -8][0]+(minutosfin-minutosini);
                            } else if(horaini+j==horafin){
                                ocupacion[horaini+j -8][0]=ocupacion[horaini+j -8][0]+(minutosfin);
                            } else if(j==0){
                                ocupacion[horaini+j -8][0]=ocupacion[horaini+j -8][0]+(60-minutosini);
                            } else {
                                ocupacion[horaini+j -8][0]=ocupacion[horaini+j -8][0]+(60);
                            }
                        }
                    }

                // } else {

                // if(horaini==horafin){   //Estan en la misma hora
                //     if (horaini-7 > 7 ){
                //         StringBuilder sb = new StringBuilder();
                //         // Añadimos el mensaje en la hora en concreto
                //         if(informacion[horaini -15][1]!=null){
                //             sb.append(informacion[horaini -15][1])
                //             .append("\n");
                //         }
                //         sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                //         if(tutorias.contains(horaini*60+minutosini)){
                //             sb.append("   Tutoria");
                //         } else {
                //             sb.append("   Cita");
                //         }
                //         informacion[horaini -15][1]=sb.toString();
                //         // Añadimos la ocupacion
                //         ocupacion[horaini -15][1]=ocupacion[horaini -15][1]+(minutosfin-minutosini);
                //     }else{
                //         StringBuilder sb = new StringBuilder();
                //         // Añadimos el mensaje en la hora en concreto
                //         if(informacion[horaini -8][0]!=null){
                //             sb.append(informacion[horaini -8][0])
                //             .append("\n");
                //         }
                //         sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                //         if(tutorias.contains(horaini*60+minutosini)){
                //             sb.append("   Tutoria");
                //         } else {
                //             sb.append("   Cita");
                //         }
                //         informacion[horaini -8][0]=sb.toString();
                //         // Añadimos la ocupacion
                //         ocupacion[horaini -8][0]=ocupacion[horaini -8][0]+(minutosfin-minutosini);
                //     }
                // } else {
                //     if (horaini-7 > 7 ){
                //         StringBuilder sb = new StringBuilder();
                //         // Añadimos el mensaje en la primera hora
                //         if(informacion[horaini -15][1]!=null){
                //             sb.append(informacion[horaini -15][1])
                //             .append("\n");
                //         }
                //         sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                //         if(tutorias.contains(horaini*60+minutosini)){
                //             sb.append("   Tutoria");
                //         } else {
                //             sb.append("   Cita");
                //         }
                //         informacion[horaini -15][1]=sb.toString();
                //         // Añadimos la ocupacion de la primera hora
                //         ocupacion[horaini -15][1]=ocupacion[horaini -15][1]+(60-minutosini);

                //         sb = new StringBuilder();
                //         // Añadimos el mensaje en la segunda hora
                //         if(informacion[horafin -15][1]!=null){
                //             sb.append(informacion[horafin -15][1])
                //             .append("\n");
                //         }
                //         sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                //         if(tutorias.contains(horaini*60+minutosini)){
                //             sb.append("   Tutoria");
                //         } else {
                //             sb.append("   Cita");
                //         }
                //         informacion[horafin -15][1]=sb.toString();
                //         // Añadimos la ocupacion de la segunda hora
                //         ocupacion[horafin -15][1]=ocupacion[horafin -15][1]+minutosfin;
                //     }else{
                //         StringBuilder sb = new StringBuilder();
                //         // Añadimos el mensaje de la primera hora
                //         if(informacion[horaini -8][0]!=null){
                //             sb.append(informacion[horaini -8][0])
                //             .append("\n");
                //         }
                //         sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                //         if(tutorias.contains(horaini*60+minutosini)){
                //             sb.append("   Tutoria");
                //         } else {
                //             sb.append("   Cita");
                //         }
                //         informacion[horaini -8][0]=sb.toString();
                //         // Añadimos la ocupacion de la primera hora
                //         ocupacion[horaini -8][0]=ocupacion[horaini -8][0]+(60-minutosini);

                //         sb = new StringBuilder();
                //         // Añadimos el mensaje de la segunda hora
                //         if(informacion[horafin -8][0]!=null){
                //             sb.append(informacion[horafin -8][0])
                //             .append("\n");
                //         }
                //         sb.append(this.horaMensaje(horaini, minutosini, horafin, minutosfin));
                //         if(tutorias.contains(horaini*60+minutosini)){
                //             sb.append("   Tutoria");
                //         } else {
                //             sb.append("   Cita");
                //         }
                //         informacion[horafin -8][0]=sb.toString();
                //         // Añadimos la ocupacion de la primera hora
                //         ocupacion[horafin -8][0]=ocupacion[horafin -8][0]+minutosfin;
                //     }
                // }
            }
        }
        this.setBtnsColores();
    }

    private String horaMensaje(int horaini, int minutosini, int horafin, int minutosfin){
        StringBuilder sb = new StringBuilder();
        sb.append(horaini + ":");
        if(minutosini<10){
            sb.append("0" + minutosini);
        } else {
                sb.append(minutosini);
            }
            sb.append(" - ")
                .append(horafin + ":");
            if(minutosfin<10){
                sb.append("0" + minutosfin);
            } else {
                sb.append(minutosfin);
            }
        return sb.toString();
    }

    /**
     * Metodo que colorea los botones en funcion de su ocuopacion
     */
    private void setBtnsColores(){
        this.setBtnsGreen();
        for(int i=0; i<7; i++){
            for(int j=0; j<2; j++){
                if(ocupacion[i][j]>=50){
                    Colores.setBGRojo(btnHoras[i][j]);
                } else if(ocupacion[i][j]>0){
                    Colores.setBGAmarillo(btnHoras[i][j]);
                }
            }
        }
        this.updateUI();
    }

    private void setAccionesBtn() {
        for(int i=0; i<7; i++){
            for(int j=0; j<2; j++){
                JButton boton = btnHoras[i][j];
                boton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(!Colores.VERDE.equals(boton.getBackground())){
                            String titulo = boton.getText();
                            int horaini = Integer.parseInt((titulo.split(":", 0))[0]);
                            if(horaini-7 > 7){
                                GestionMensajes.msgMostarInfo(informacion[horaini -15][1], titulo);
                            } else {
                                GestionMensajes.msgMostarInfo(informacion[horaini -8][0], titulo);
                            }
                        }
                    }
                });
            }
        }
    }

}
