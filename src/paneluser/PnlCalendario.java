package paneluser;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dominio.Usuario;
import panelProf.PnlProf;
import util.Colores;
import util.Fecha;
import util.Fuente;

public class PnlCalendario extends JPanel {

    private Usuario usuario;
    private static int diaActivo = Fecha.getDia();
    private static int mesActivo = Fecha.getMes();
    private static int anioActivo = Fecha.getAnio();
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor del panel el cual llamara al metodo {@link establecerVentana} y
     * al metodo{@link setProfesor}
     * 
     * @param usuario usuario que a iniciado sesion
     */
    public PnlCalendario(Usuario usuario) {
        setUsuario(usuario);
        this.establecerVentana();
    }

    /**
     * metodo que inicializa el atributo usuario asociado
     * 
     * @param usuario que puede ser un profesor o un alumno
     */
    private void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void establecerVentana() {

        // panel principal
        this.setLayout(new GridLayout(2,1)); //se establece el layout 

        // panel que muestra las fechas
        JPanel pnlFecha = new JPanel(new FlowLayout());

        // panel que sirve para cambiar de fecha
        JPanel pnlCambioFecha = new JPanel(new FlowLayout());

        JButton btnRetroceder = new JButton("<");
        Fuente.setFuente(btnRetroceder);
        btnRetroceder.setOpaque(false);
        Colores.setBGTransparente(btnRetroceder);
        pnlCambioFecha.add(btnRetroceder);

        JLabel lblMes = new JLabel(Fecha.getMesString() + "  " + Fecha.getAnio());
        pnlCambioFecha.add(lblMes);

        JButton btnAvanzar = new JButton(">");
        Fuente.setFuente(btnAvanzar);
        btnAvanzar.setOpaque(false);
        Colores.setBGTransparente(btnAvanzar);
        pnlCambioFecha.add(btnAvanzar);

        pnlFecha.add(pnlCambioFecha);

        // panel que muestra la disposiciond elos dias de un mes
        JPanel pnlMes = new JPanel(new FlowLayout());
        JPanel pnlDias = new JPanel(new GridLayout(Fecha.getSemanasMes(Fecha.getMes(), Fecha.getAnio()) + 1, 7));
        JLabel txtDias[] = new JLabel[7];
        JButton btnDias[] = new JButton[Fecha.getUltimoDiaMes(Fecha.getMes(), Fecha.getAnio())];

        txtDias[0] = new JLabel("L", SwingConstants.CENTER);
        txtDias[1] = new JLabel("M", SwingConstants.CENTER);
        txtDias[2] = new JLabel("X", SwingConstants.CENTER);
        txtDias[3] = new JLabel("J", SwingConstants.CENTER);
        txtDias[4] = new JLabel("V", SwingConstants.CENTER);
        txtDias[5] = new JLabel("S", SwingConstants.CENTER);
        txtDias[6] = new JLabel("D", SwingConstants.CENTER);

        for (int i = 0; i < 7; i++) {
            txtDias[i].setOpaque(true);
            Colores.setBGAzul(txtDias[i]);
            pnlDias.add(txtDias[i]);
        }

        for (int i = 1; i < Fecha.getUltimoDiaMes(Fecha.getMes(), Fecha.getAnio()) + 1; i++) {
            btnDias[i - 1] = new JButton(String.valueOf(i));
        }

        this.establecerMes(pnlDias, btnDias);

        pnlMes.add(pnlDias);
        pnlFecha.add(pnlMes);
        this.add(pnlFecha);

        // panel de botones
        JPanel pnlBotones = new JPanel(new GridLayout(4, 1, 0, 5));


        // boton crear tutoria
        JButton btnCrearTutoria = new JButton("Crear Tutoria");
        Fuente.setFuente(btnCrearTutoria);
        btnCrearTutoria.setOpaque(false);
        Colores.setBGTransparente(btnCrearTutoria);
        pnlBotones.add(btnCrearTutoria);

        //boton eliminar tutoria
        JButton btnEliminarTutoria = new JButton("Eliminar Tutoria");
        Fuente.setFuente(btnEliminarTutoria);
        btnEliminarTutoria.setOpaque(false);
        Colores.setBGTransparente(btnEliminarTutoria);
        pnlBotones.add(btnEliminarTutoria);

        //boton guardar
        JButton btnGuardar = new JButton("Guardar");
        Fuente.setFuente(btnGuardar);
        btnGuardar.setOpaque(false);
        Colores.setBGTransparente(btnGuardar);
        pnlBotones.add(btnGuardar);

        this.add(pnlBotones);

        //Acciones de los botones
        for (JButton btnDia: btnDias){
            btnDia.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    Colores.setBGCarne(btnDias[diaActivo-1]);
                    diaActivo = Integer.valueOf(btnDia.getText());
                    Colores.setBGVerde(btnDias[diaActivo-1]);
                    int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                    PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo));
                    updateUI();
                }
            });
        }
        

    }

    private void establecerMes(JPanel pnlDias,JButton btnDias[]){
        
        
        int primerDiaMes = Fecha.getPrimerDiaMes(Fecha.getMes(), Fecha.getAnio());
        int dia=1;
        for (int i= 0; i<Fecha.getSemanasMes(Fecha.getMes(), Fecha.getAnio()); i++)
            for (int j = 0; j<7; j++){
                if( i== 0 && j < primerDiaMes){
                    JLabel txtVacio = new JLabel();
                    txtVacio.setOpaque(true);
                    Colores.setBGCarne(txtVacio);
                    pnlDias.add(txtVacio);
                }
                else{
                    if(dia <= Fecha.getUltimoDiaMes(Fecha.getMes(),Fecha.getAnio())){
                        if (diaActivo == dia){
                            Colores.setBGVerde(btnDias[dia-1]);
                            pnlDias.add(btnDias[dia-1]);
                            dia++;
                        }
                        else{
                            Colores.setBGCarne(btnDias[dia-1]);
                            pnlDias.add(btnDias[dia-1]);
                            dia++;
                        }
                    }
                    else{
                        JLabel txtVacio = new JLabel();
                        txtVacio.setOpaque(true);
                        Colores.setBGCarne(txtVacio);
                        pnlDias.add(txtVacio);
                    }
                    
                }
               
            }
    }
}
