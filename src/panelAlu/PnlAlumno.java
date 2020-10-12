package panelAlu;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import java.net.MalformedURLException;
import java.net.URL;

import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import paneluser.PnlEncabezado;
import util.Colores;
import util.Fuente;

/**
 * Clase destinada a crear el panel del alumno, con el que interactuara con el resto del programa
 */
public class PnlAlumno extends JPanel {
    public static PnlAlumno PnlAlumno = new PnlAlumno();

    /** Constructor que llamara al metodo {@link initComponents} */
    private PnlAlumno(){
        initComponents();
    }

    /** Metodo en el cual se instanciaran todos los componentes */
    private void initComponents(){

        /** Panel del alumno que contendra todos los componentes con los que interactuara */
        JPanel pnlInicioAlumno = new JPanel();
        pnlInicioAlumno.setLayout(new GridBagLayout());     //Establecemos el layout
        Colores.setBGColor(pnlInicioAlumno);   //Fijamos el color del fondo
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
        gbc.insets = (new Insets(20,100,0,0)); // ponemos margenes
        pnlInicioAlumno.add(txtPantallaActual, gbc);    //Lo añadimos al panel

        /** Etiqueta de Profesores */
        JLabel txtProfesor = new JLabel("Profesores");
        Fuente.setFuenteNegrita(txtProfesor);   //Cambiamos el tipo de letra
        gbc.gridx = 1;      //Especificamos posicion de la matriz
        gbc.gridy = 1;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 1;  //Cuantas casillas de la matriz ocupa horizontalmete
        gbc.insets = (new Insets(20,100,0,0)); // ponemos margenes
        pnlInicioAlumno.add(txtProfesor, gbc);    //Lo añadimos al panel

        DefaultListModel<String> dlstProfesores = new DefaultListModel<String>();   //Gestionara añadira y eliminara objetos de la lista
        /** Lista que en la que el alumno podra ver a sus profesores */
        JList<String> lstProfesores = new JList<String>(dlstProfesores);
        lstProfesores.setLayoutOrientation(JList.VERTICAL_WRAP);  //Hace que la lista se rellene de  de arriba a abajo y de izquierda a derecha
        addProfesores(dlstProfesores);      //Llamamos a el metodo para que rellene la lista
        lstProfesores.setVisibleRowCount(Integer.valueOf(Math.round(Float.valueOf(dlstProfesores.getSize())/2))); // establecemos el numero de filas de la lista
        Fuente.setFuente(lstProfesores);    //Fijamos la fuente
        lstProfesores.setFixedCellHeight(25);       //se fija la altura de cada objeto de la lista
        lstProfesores.setFixedCellWidth(225);       //se fija el ancho de cada objeto de la lista
        lstProfesores.setBorder(new EmptyBorder(5,5,5,5));        //se agrega un pequeño margen al en el interior de la lista
        JScrollPane pnlLstScroll = new JScrollPane(lstProfesores);   //Hacemos que podamos hacer scroll en la lista
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
        
        this.setLayout(new BorderLayout());
        Colores.setBGColor(this);   //Fijamos el color del fondo
        this.add(new PnlEncabezado(),BorderLayout.NORTH);
        this.add(pnlInicioAlumno, BorderLayout.CENTER);
    }
    
    private void addProfesores(DefaultListModel<String> dlstProfesores){
        dlstProfesores.addElement("Profesor Generico 1");
        dlstProfesores.addElement("Profesor Generico 2");
        dlstProfesores.addElement("Profesor Generico 3");
        dlstProfesores.addElement("Profesor Generico 4");
        dlstProfesores.addElement("Profesor Generico 5");
        dlstProfesores.addElement("Profesor Genreico 6");
        dlstProfesores.addElement("Profesor Generico 7");
        dlstProfesores.addElement("Profesor Generico 8");
        dlstProfesores.addElement("Profesor Generico 9");
        dlstProfesores.addElement("Profesor Generico 10");
        dlstProfesores.addElement("Profesor Generico 11");
        dlstProfesores.addElement("Profesor Generico 12");
        dlstProfesores.addElement("Profesor Generico 13");
        dlstProfesores.addElement("Profesor Genreico 14");
        dlstProfesores.addElement("Profesor Generico 15");
        
        
    }

}
