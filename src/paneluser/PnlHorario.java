package paneluser;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.Colores;
import util.Fuente;

/** Panel generico el cual mostrata un horario de un profesor */
public class PnlHorario extends JPanel{
    
    /** Constructor que llama al metodo {@link initComponents} */
    public PnlHorario(){
        initComponents();
    }

    private void initComponents(){
        this.setLayout(new GridLayout(7,2,5,5));    //Matriz de 7*2 con separacion de 5 en ambos sentidos
        
        /** Matriz de botones que contendra el horario del profesor */
        JButton btnHoras[][] = new JButton[7][2];
        for(int i=0; i<7; i++){
            for(int j=0; j<2; j++){
                int hora = 8+i+(j*7);   //formula para obtener las distintas horas del horario en funcion de j e i
                btnHoras[i][j] = new JButton(hora +":00 - "+(hora + 1)+":00");
                Colores.setBGVerde(btnHoras[i][j]);
                Fuente.setFuente(btnHoras[i][j]);
                this.add(btnHoras[i][j]);
            }
        }
        //Fijamos algunos colores para ver como queda en distintos casos
        Colores.setBGAmarillo(btnHoras[4][0]);
        Colores.setBGAmarillo(btnHoras[5][0]);
        Colores.setBGAmarillo(btnHoras[5][1]);
        Colores.setBGRojo(btnHoras[0][0]);
        Colores.setBGRojo(btnHoras[1][0]);
        Colores.setBGRojo(btnHoras[3][1]);
        Colores.setBGRojo(btnHoras[3][0]);
        Colores.setBGRojo(btnHoras[6][0]);
        Colores.setBGRojo(btnHoras[0][1]);
    }
}
