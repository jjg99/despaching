package panelAlu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dominio.Alumno;
import dominio.Profesor;
import gui.JVentana;
import paneluser.PnlCalendario;
import paneluser.PnlEncabezado;
import paneluser.PnlHorario;
import util.Colores;
import util.Fecha;
import util.Fuente;


public class PnlReserva extends JPanel{
    private static final long serialVersionUID = 1L;

    private static Alumno alumno;
    private static Profesor profesor;

    private JPanel pnlIzquierda = new JPanel();
    public static PnlReserva pnlReserva;

    
    public PnlReserva (Alumno alumno, Profesor profesor){

        actualizarAlumno(alumno);
        actualizarProfesor(profesor);
        this.establecerVentana();
    }

    private void establecerVentana(){

        //Creamos el PnlHorario
        PnlHorario.pnlHorario = new PnlHorario(profesor, Fecha.getDiaSemana(), Fecha.getAnio(), Fecha.getMes(), Fecha.getDia());
        /** Panel que contendra el Horario del dia */
        setPnlHorario(Fecha.getDiaSemana(), Fecha.fechaString(), Fecha.getAnio(), Fecha.getMes(), Fecha.getDia());

        
        /** Panel que contendra la cola */
        JPanel pnlDerecha = new JPanel();
        pnlDerecha.setLayout(new BorderLayout());
        pnlDerecha.add(new PnlCalendario(alumno,profesor));

        /** Panel que contiene el boton de atras */
        JPanel pnlAbajo = new JPanel();
        pnlAbajo.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton btnAtras = new JButton("Atras");
        try{
            btnAtras.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/30/return.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnAtras.setHorizontalTextPosition(SwingConstants.CENTER); // ponemos el texto en el centro
        btnAtras.setVerticalTextPosition(SwingConstants.BOTTOM); // ponemos el texto abajo
        btnAtras.setOpaque(false);
        Colores.setBGTransparente(btnAtras);
        btnAtras.setBorder(null);
        pnlAbajo.add(btnAtras);

        btnAtras.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JVentana.cambiarPanel(PnlAlumno.PnlAlumno);
            }
        });
        this.setLayout(new BorderLayout()); //se establece el layout de tipo borderLayout
        this.add(new PnlEncabezado(alumno),BorderLayout.NORTH);   //se añade el panel de encabezado en la parte superior de la pantalla
        this.add(pnlIzquierda,BorderLayout.WEST);    //se añade el panel izquierdo para ser pintado
        this.add(pnlDerecha,BorderLayout.CENTER);     //se añade el panel derecho
        this.add(pnlAbajo, BorderLayout.SOUTH);
    }

    /**
     * Metodo que actualiza el rpofesor a ensenar en el panel
     * @param alu el alumno para hacer queries
     */
    public static void actualizarAlumno(Alumno alu)
    {
        PnlReserva.alumno = alu;
    }

    /**
     * Metodo que actualiza el rpofesor a ensenar en el panel
     * @param prof el profesor para hacer queries
     */
    public static void actualizarProfesor(Profesor prof)
    {
        PnlReserva.profesor = prof;
    }

    public void setPnlHorario(int diaSemana,String fecha, int anio, int mes, int dia){

        pnlIzquierda.removeAll();

        pnlIzquierda.setLayout(new BorderLayout());
        JLabel lblHorario = new JLabel(fecha);
        Fuente.setFuenteNegrita(lblHorario);

        /** Panel que contendra la etiqueta {@link lblHorario}*/
        JPanel pnlTxtHorario = new JPanel();
        pnlTxtHorario.setLayout(new FlowLayout());
        pnlTxtHorario.add(lblHorario);

        // Actualizamos el PnlHorario
        PnlHorario.pnlHorario.setHorario(diaSemana, anio, mes, dia);
        
        // Agregamos ambos componentes al panel
        pnlIzquierda.add(pnlTxtHorario,BorderLayout.NORTH);
        pnlIzquierda.add(PnlHorario.pnlHorario,BorderLayout.CENTER);
        pnlIzquierda.updateUI();
    }
}