package paneluser;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


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

        JPanel pnlMes = new JPanel(new FlowLayout());
        JPanel pnlDias = new JPanel(new GridLayout(Fecha.getSemanasMes(Fecha.getMes(), Fecha.getAnio())+1,7));
        JLabel txtDias[] = new JLabel[7];
        JButton btnDias[] = new JButton[Fecha.getUltimoDiaMes(Fecha.getMes(), Fecha.getAnio())];

        txtDias[0] = new JLabel("L",SwingConstants.CENTER);
        txtDias[1] = new JLabel("M",SwingConstants.CENTER);
        txtDias[2] = new JLabel("X",SwingConstants.CENTER);
        txtDias[3] = new JLabel("J",SwingConstants.CENTER);
        txtDias[4] = new JLabel("V",SwingConstants.CENTER);
        txtDias[5] = new JLabel("S",SwingConstants.CENTER);
        txtDias[6] = new JLabel("D",SwingConstants.CENTER);

        for (int i=0;i<7;i++){
            txtDias[i].setOpaque(true);
            txtDias[i].setBorder(UIManager.getBorder("Button.border"));
            Colores.setBGAzul(txtDias[i]);
            pnlDias.add(txtDias[i]);
        }

        for (int i=1;i<Fecha.getUltimoDiaMes(Fecha.getMes(), Fecha.getAnio())+1;i++){
            btnDias[i-1] = new JButton(String.valueOf(i));
        }

        this.establecerMes(pnlDias,btnDias);
            
        pnlMes.add(pnlDias);
        this.add(pnlMes);
    }

    private void establecerMes(JPanel pnlDias,JButton btnDias[]){
        
        
        int primerDiaMes = Fecha.getPrimerDiaMes(Fecha.getMes(), Fecha.getAnio());
        int dia=1;
        for (int i= 0; i<Fecha.getSemanasMes(Fecha.getMes(), Fecha.getAnio()); i++)
            for (int j = 0; j<7; j++){
                if( i== 0 && j < primerDiaMes){
                    JLabel txtVacio = new JLabel();
                    txtVacio.setOpaque(true);
                    txtVacio.setBorder(UIManager.getBorder(btnDias[0].getBorder()));
                    Colores.setBGCarne(txtVacio);
                    pnlDias.add(txtVacio);
                }
                else{
                    if(dia <= Fecha.getUltimoDiaMes(Fecha.getMes(),Fecha.getAnio())){
                        Colores.setBGCarne(btnDias[dia-1]);
                        pnlDias.add(btnDias[dia-1]);
                        dia++;
                    }
                    else{
                        JLabel txtVacio = new JLabel();
                        txtVacio.setBorder(UIManager.getBorder(btnDias[0].getBorder()));
                        txtVacio.setOpaque(true);
                        Colores.setBGCarne(txtVacio);
                        pnlDias.add(txtVacio);
                    }
                    
                }
               
            }
    }
}
