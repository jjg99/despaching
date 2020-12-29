package paneluser;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import dominio.Usuario;
import server.Fachada;
import util.Colores;
import util.Fuente;

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

    /** Atributo que contendra el dia de la semana */
    private int dia;
    
    /** Constructor que llama al metodo {@link initComponents} */
    public PnlHorario(Usuario usuario,int dia){
        setUsuario(usuario);
        this.horario= Fachada.getHorario(this.usuario.getId()); // se consigue el horario de la base de datos
        clases = horario.split(";");  // se separa en dias de la semana
        this.dia=-1;    //Asignamos -1 para que la primera vez siempre cambie el dia
        initComponents(dia);
    }

    /** metodo para establecer el usuario
     * @param usuario contiene el usuario al que el horario pertenece
     */
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    private void initComponents(int dia){
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
        this.setHorario(dia);
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
    }
    
    /** metodo para establecer el horario del profesor de manera dinamica
     * @param btnHoras es una matriz de botones que seran de un color especifico y se les cambiara el color
     */
    public void setHorario(int dia){
        if(dia <= 4){   //Solo tratamos de lunes a viernes, los findes no hay clases
            if(this.dia != dia){    //Solo cambiamos el horario si se cambia de dia
                for(int i=0; i<7; i++){
                    for(int j=0; j<2; j++){
                        ocupacion[i][j]=0;
                        informacion[i][j]=null;
                    }
                }
                this.dia=dia;
                String[] horas = clases[dia].split(","); // se separa las clases del dia

                for (int i=1; i< horas.length ;i++){ // bucle que rrecore las clases del dia
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
                            sb.append(horaini + ":" + minutosini)
                              .append(" - ")
                              .append(horafin + ":" + minutosfin)
                              .append("\t Clase");
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
                            sb.append(horaini + ":" + minutosini)
                              .append(" - ")
                              .append(horafin + ":" + minutosfin)
                              .append("\t Clase");
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
                            sb.append(horaini + ":" + minutosini)
                              .append(" - ")
                              .append(horafin + ":" + minutosfin)
                              .append("\t Clase");
                            informacion[horaini -15][1]=sb.toString();
                            // Añadimos la ocupacion de la primera hora
                            ocupacion[horaini -15][1]=ocupacion[horaini -15][1]+(60-minutosini);

                            sb = new StringBuilder();
                            // Añadimos el mensaje en la segunda hora
                            if(informacion[horafin -15][1]!=null){
                                sb.append(informacion[horafin -15][1])
                                  .append("\n");
                            }
                            sb.append(horaini + ":" + minutosini)
                              .append(" - ")
                              .append(horafin + ":" + minutosfin)
                              .append("\t Clase");
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
                            sb.append(horaini + ":" + minutosini)
                              .append(" - ")
                              .append(horafin + ":" + minutosfin)
                              .append("\t Clase");
                            informacion[horaini -8][0]=sb.toString();
                            // Añadimos la ocupacion de la primera hora
                            ocupacion[horaini -8][0]=ocupacion[horaini -8][0]+(60-minutosini);

                            sb = new StringBuilder();
                            // Añadimos el mensaje de la segunda hora
                            if(informacion[horafin -8][0]!=null){
                                sb.append(informacion[horafin -8][0])
                                  .append("\n");
                            }
                            sb.append(horaini + ":" + minutosini)
                              .append(" - ")
                              .append(horafin + ":" + minutosfin)
                              .append("\t Clase");
                            informacion[horafin -8][0]=sb.toString();
                            // Añadimos la ocupacion de la primera hora
                            ocupacion[horafin -8][0]=ocupacion[horafin -8][0]+minutosfin;
                        }
                    }
                }
            }
            this.setBtnsColores();
        } else {
            this.setBtnsGreen();
        }
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
    }
}
