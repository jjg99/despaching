package paneluser;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import dominio.Usuario;
import util.Colores;
import util.Fecha;

public class PnlCalendario extends JPanel{

    private Usuario usuario;
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    

    /**
     * Constructor del panel el cual llamara al metodo {@link establecerVentana} y al metodo{@link setProfesor}
     * @param usuario usuario que a iniciado sesion
     */
    public PnlCalendario (Usuario usuario){
        setUsuario(usuario);
        this.establecerVentana();
    }

    /**
     * metodo que inicializa el atributo usuario asociado
     * @param usuario que puede ser un profesor o un alumno
     */
    private void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    private void establecerVentana(){
        //JLabel txtProgress = new JLabel("Work in progress");
        //this.add(txtProgress);

        JPanel pnlMes = new JPanel(new FlowLayout());
        JPanel pnlDias = new JPanel(new GridLayout(Fecha.getSemanasMes(Fecha.getMes(), Fecha.getAnio())+1,7));
        JButton btnDias[][] = new JButton[Fecha.getSemanasMes(Fecha.getMes(), Fecha.getAnio())+1][7];


        this.establecerMes(btnDias);
        for (int i=0;i < Fecha.getSemanasMes(Fecha.getMes(), Fecha.getAnio())+1; i++)
            for (int j=0; j< 7;j++)
                pnlDias.add(btnDias[i][j]);
            
        pnlMes.add(pnlDias);
        this.add(pnlMes);
    }

    private void establecerMes(JButton btnDias[][]){
        
        btnDias[0][0] = new JButton("L");
        btnDias[0][1] = new JButton("M");
        btnDias[0][2] = new JButton("X");
        btnDias[0][3] = new JButton("J");
        btnDias[0][4] = new JButton("V");
        btnDias[0][5] = new JButton("S");
        btnDias[0][6] = new JButton("D");

        for (int j=0;j<7;j++){
            // btnDias[0][j]
        }

        int primerDiaMes = Fecha.getPrimerDiaMes(Fecha.getMes(), Fecha.getAnio());
        int dia=1;
        for (int i= 1; i<Fecha.getSemanasMes(Fecha.getMes(), Fecha.getAnio())+1; i++)
            for (int j = 0; j<7; j++){
                if( i== 1 && j < primerDiaMes){
                    btnDias[i][j] = new JButton();
                    Colores.setBGCarne(btnDias[i][j]);
                    btnDias[i][j].setOpaque(true);
                    
                }
                else{
                    if(dia <= Fecha.getUltimoDiaMes(Fecha.getMes(),Fecha.getAnio())){
                        btnDias[i][j] = new JButton(String.valueOf(dia));
                        Colores.setBGCarne(btnDias[i][j]);
                        dia++;
                    }
                    else{
                        btnDias[i][j] = new JButton();
                        Colores.setBGCarne(btnDias[i][j]);
                        btnDias[i][j].setOpaque(true);
                    }
                    
                }
               
            }
    }
}
