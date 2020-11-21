package panelAlu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;

import dominio.Alumno;
import dominio.Profesor;
import gui.JVentana;
import paneluser.PnlEncabezado;
import paneluser.PnlCola;
import util.Colores;
import util.Fuente;

/**
 * Clase destinada a crear el panel del alumno, con el que interactuara con el resto del programa
 */
public class PnlAlumno extends JPanel {
    public static PnlAlumno PnlAlumno;  // se crea la variable que se instanciará desde fuera

    /** Alumno que ha iniciado sesion */
    private static Alumno alumno;
    private static ArrayList<Profesor> listaProf;

    /** 
     * Constructor que llamara al metodo {@link initComponents} y al metodo{@link setAlumno}
     * @param alu alumno que a inicado sesion
     */
    public PnlAlumno(Alumno alu){
        setAlumno(alu);
        setListaProfesores();
        initComponents();
    }

    /**
     * Metodo que asigna el alumno al panel
     * @param alu alumno que a iniciado sesion
     */
    private void setAlumno(Alumno alu) {
        alumno = alu;
    }

    /**
     * Metodo que carga la lista de profesores que posee un alumno
     */

    private void setListaProfesores()
    {
        listaProf = alumno.getListaProfesores();
    }

    /** Metodo en el cual se instanciaran todos los componentes */
    private void initComponents(){
        /** Panel del alumno que contendra todos los componentes con los que interactuara */
        JPanel pnlInicioAlumno = new JPanel();
        pnlInicioAlumno.setLayout(new GridBagLayout());     //Establecemos el layout
        /** Restricciones  para ir colocando los diferentes elementos dentro del {@link Gridbaglayout} */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE; // para que los huecos no se rellenen

        /** Etiqueta que indica en que panel de uso se encuntra en el usuario */
        JLabel txtPantallaActual = new JLabel("Inicio");
        Fuente.setFuenteTituloNegrita(txtPantallaActual);   //Cambiamos el tipo de letra
        gbc.gridx = 1;      //Especificamos posicion de la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 1;  //Cuantas casillas de la matriz ocupa horizontalmete
        gbc.insets = (new Insets(0,40,0,0)); // ponemos margenes
        pnlInicioAlumno.add(txtPantallaActual, gbc);    //Lo añadimos al panel

        /** Etiqueta de Profesores */
        JLabel txtProfesor = new JLabel("Profesores");
        Fuente.setFuenteNegrita(txtProfesor);   //Cambiamos el tipo de letra
        gbc.gridx = 1;      //Especificamos posicion de la matriz
        gbc.gridy = 1;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 1;  //Cuantas casillas de la matriz ocupa horizontalmete
        gbc.insets = (new Insets(0,40,0,0)); // ponemos margenes
        pnlInicioAlumno.add(txtProfesor, gbc);    //Lo añadimos al panel

        DefaultListModel<Profesor> dlstProfesores = new DefaultListModel<Profesor>();   //Gestionara añadira y eliminara objetos de la lista
        /** Lista que en la que el alumno podra ver a sus profesores */
        JList<Profesor> lstProfesores = new JList<Profesor>(dlstProfesores);
        lstProfesores.setLayoutOrientation(JList.VERTICAL);  //Hace que la lista se rellene de arriba a abajo 
        addProfesores(dlstProfesores);      //Llamamos a el metodo para que rellene la lista
        Fuente.setFuente(lstProfesores);    //Fijamos la fuente
        lstProfesores.setFixedCellHeight(25);       //se fija la altura de cada objeto de la lista
        lstProfesores.setBorder(new EmptyBorder(5,5,5,5));        //se agrega un pequeño margen al en el interior de la lista
        JScrollPane pnlLstScroll = new JScrollPane(lstProfesores);   //Hacemos que podamos hacer scroll en la lista
        pnlLstScroll.setPreferredSize(new Dimension(350, 350));     //Fiajamos el tamaño del JScrollPane
        //Colocamos la lista en el panel de Inicio de alumno
        gbc.gridx = 0;      //Especificamos posicion de la matriz
        gbc.gridy = 2;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 3;  //Cuantas casillas de la matriz ocupa horizontalmete
        gbc.insets = (new Insets(20,0,0,0)); // ponemos margenes
        pnlInicioAlumno.add(pnlLstScroll, gbc);     //Agregamaos el panel de la lista
       

        /** Boton para poder accecer a la ventana de reservar */
        JButton btnReservar = new JButton("Reservar");
        try{
            btnReservar.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/64/000000/date-to.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnReservar.setHorizontalTextPosition(SwingConstants.CENTER); // ponemos el texto en el centro
        btnReservar.setVerticalTextPosition(SwingConstants.BOTTOM); // ponemos el texto abajo
        btnReservar.setOpaque(false);
        Colores.setBGTransparente(btnReservar);
        btnReservar.setBorder(null);
        Fuente.setFuente(btnReservar);
        //Colocamos la lista en el panel de Inicio de alumno
        gbc.gridx = 0;      //Especificamos posicion de la matriz
        gbc.gridy = 3;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 1;  //Cuantas casillas de la matriz ocupa horizontalmete
        gbc.insets = (new Insets(20,0,0,0)); // ponemos margenes
        pnlInicioAlumno.add(btnReservar, gbc);

        /** Boton para acceder a la cola */
        JButton btnCola = new JButton("Cola");
        try{
            btnCola.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/64/000000/queue.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnCola.setHorizontalTextPosition(SwingConstants.CENTER); // ponemos el texto en el centro
        btnCola.setVerticalTextPosition(SwingConstants.BOTTOM); // ponemos el texto abajo
        btnCola.setOpaque(false);
        Colores.setBGTransparente(btnCola);
        btnCola.setBorder(null);
        Fuente.setFuente(btnCola);
        //Colocamos la lista en el panel de Inicio de alumno
        gbc.gridx = 2;      //Especificamos posicion de la matriz
        gbc.gridy = 3;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 1;  //Cuantas casillas de la matriz ocupa horizontalmete
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = (new Insets(20,0,0,0)); // ponemos margenes
        pnlInicioAlumno.add(btnCola, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        /**
         * Metodo que implementa el action listener para entrar en el panel de la cola
         */
        btnCola.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                JVentana.cambiarPanel(PnlCola.pnlCola = new PnlCola(alumno, lstProfesores.getSelectedValue()));
                
            }
        });
        
        this.setLayout(new BorderLayout());
        this.add(new PnlEncabezado(alumno),BorderLayout.NORTH);
        this.add(pnlInicioAlumno, BorderLayout.CENTER);
    }

    /** Metodo estatico que elimina el alumno asociado al panel */
    public static void delAlumno(){
        alumno=null;
    }
    
    private void addProfesores(DefaultListModel<Profesor> dlstProfesores){
        
        for (Profesor prof : listaProf)
        {
            dlstProfesores.addElement(prof);
        }
        
    }
}
