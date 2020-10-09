package panelAlu;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

import paneluser.PnlEncabezado;
import util.Fuente;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Clase destinada a crear el panel del alumno, con el que interactuara con el resto del programa
 */
public class PnlAlumno extends JPanel {
    public static PnlAlumno PnlAlumno = new PnlAlumno();

    /** Constructor que llamara al metodo {@link initComponents} */
    private PnlAlumno(){
        initComponents();
    }

    /** Metodo en el cual se instanciaran todos los compones */
    private void initComponents(){

    
        /** Panel del alumno que contendra todos los componentes con los que interactuara */
        JPanel pnlInicioAlumno = new JPanel();
        pnlInicioAlumno.setLayout(new GridBagLayout());     //Establecemos el layout
        /** Restricciones  para ir colocando los diferentes elementos dentro del {@link Gridbaglayout} */
        GridBagConstraints gbc = new GridBagConstraints();

        // gbc.fill = GridBagConstraints.CENTER; // para que no se rellenen los huecos de la matriz
        // gbc.anchor = GridBagConstraints.PAGE_START;

        /** Etiqueta que indica en que panel de uso se encuntra en el usuario */
        JLabel txtPantallaActual = new JLabel("Inicio");
        Fuente.setFuenteTituloNegrita(txtPantallaActual);   //Cambiamos el tipo de letra
        gbc.gridx = 1;      //Especificamos posicion de la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 1;  //Cuantas casillas de la matriz ocupa horizontalmete
        // // gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        // gbc.insets = new Insets(5,0,5,0);   //Añadimos margenes
        pnlInicioAlumno.add(txtPantallaActual, gbc);    //Lo añadimos al panel
        gbc.weighty = 0.0;

        /** Etiqueta de Profesores */
        JLabel txtProfesor = new JLabel("Profesores");
        Fuente.setFuenteNegrita(txtProfesor);   //Cambiamos el tipo de letra
        gbc.gridx = 1;      //Especificamos posicion de la matriz
        gbc.gridy = 1;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 1;  //Cuantas casillas de la matriz ocupa horizontalmete
        // // gbc.weightx = 0.5;
        // // gbc.weighty = 1.0;
        // gbc.insets = new Insets(50,0,0,0);   //Añadimos margenes
        pnlInicioAlumno.add(txtProfesor, gbc);    //Lo añadimos al panel

        DefaultListModel dlstProfesores = new DefaultListModel();   //Gestionara añadir y eliminar objetos de la lista
        /** Lista que en la que el alumno podra ver a sus profesores */
        JList lstProfesores = new JList(dlstProfesores);
        lstProfesores.setLayoutOrientation(JList.VERTICAL_WRAP);  //Hace que la lista se rellene de izquierda a derecha y de arriba a abajo
        lstProfesores.setVisibleRowCount(10);
        addProfesores(dlstProfesores);
        Fuente.setFuente(lstProfesores);
        JPanel pnlLista = new JPanel();
        pnlLista.setLayout(new BorderLayout());
        pnlLista.add(lstProfesores, BorderLayout.CENTER);
        //Colocamos la lista en el panel de Inicio de alumno
        gbc.gridx = 0;      //Especificamos posicion de la matriz
        gbc.gridy = 2;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 3;  //Cuantas casillas de la matriz ocupa horizontalmete
        // gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        // gbc.insets = new Insets(0,0,0,0);   //Quitamos margenes
        pnlInicioAlumno.add(pnlLista, gbc);
        // gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;

        /** Boton para poder accecer a la ventana de reservar */
        JButton btnReservar = new JButton("Reservar");
        Fuente.setFuente(btnReservar);
        //Colocamos la lista en el panel de Inicio de alumno
        gbc.gridx = 0;      //Especificamos posicion de la matriz
        gbc.gridy = 3;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 1;  //Cuantas casillas de la matriz ocupa horizontalmete
        pnlInicioAlumno.add(btnReservar, gbc);

        /** Boton para acceder a la cola */
	    JButton btnCola = new JButton("Cola");
        Fuente.setFuente(btnCola);
        //Colocamos la lista en el panel de Inicio de alumno
        gbc.gridx = 2;      //Especificamos posicion de la matriz
        gbc.gridy = 3;
        gbc.gridheight = 1; //Cuantas casillas de la matriz ocupa verticalmente
        gbc.gridwidth = 1;  //Cuantas casillas de la matriz ocupa horizontalmete
        gbc.anchor = GridBagConstraints.EAST;
        pnlInicioAlumno.add(btnCola, gbc);
        gbc.anchor = GridBagConstraints.CENTER;


        
        this.setLayout(new BorderLayout());
        this.add(new PnlEncabezado(),BorderLayout.NORTH);
        this.add(pnlInicioAlumno, BorderLayout.CENTER);
    }

    private void addProfesores(DefaultListModel dlstProfesores){
        dlstProfesores.addElement("Profesor Generico 1");
        dlstProfesores.addElement("Profesor Genreico 2");
        dlstProfesores.addElement("Profesor Genreico 3");
        dlstProfesores.addElement("Profesor Genreico 4");
        dlstProfesores.addElement("Profesor Genreico 5");
        dlstProfesores.addElement("Profesor Genreico 6");
        dlstProfesores.addElement("Profesor Genreico 7");
        dlstProfesores.addElement("Profesor Genreico 8");
        dlstProfesores.addElement("Profesor Genreico 9");
        dlstProfesores.addElement("Profesor Genreico 10");
        dlstProfesores.addElement("Profesor Genreico 11");
        dlstProfesores.addElement("Profesor Genreico 12");
        dlstProfesores.addElement("Profesor Genreico 13");
        dlstProfesores.addElement("Profesor Genreico 14");
        dlstProfesores.addElement("Profesor Genreico 15");
    }

}
