package panelProf;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import paneluser.PnlEncabezado;
import paneluser.PnlHorario;
import util.Colores;
import util.Fuente;

/**Clase para el panel del profesor */
public class PnlProf extends JPanel{

    public static PnlProf pnlProf = new PnlProf(); // se crea la variable que se instanciará desde fuera

    /**
     * Constructor del panel el cual llamara al metodo {@link establecerVentana}
     */
    private PnlProf(){
        this.establecerVentana();
    }
    /** Metodo que inicializara e instanciara todos los componentes en la ventana */
    public void establecerVentana(){
        /** Panel que contendra el Horario del dia */
        JPanel pnlIzquierda = new JPanel();
        pnlIzquierda.setLayout(new BorderLayout());

        JLabel lblHorario = new JLabel("Horario:");
        Fuente.setFuenteNegrita(lblHorario);

        /** Panel que contendra la etiqueta {@link lblHorario}*/
        JPanel pnlTxtHorario = new JPanel();
        pnlTxtHorario.setLayout(new FlowLayout());
        pnlTxtHorario.add(lblHorario);
        
        // Agregamos ambos componentes al panel
        pnlIzquierda.add(pnlTxtHorario,BorderLayout.NORTH);
        pnlIzquierda.add(new PnlHorario(),BorderLayout.CENTER);

        /** Panel que contendra la cola */
        JPanel pnlDerecha = new JPanel();
        pnlDerecha.setLayout(new BorderLayout());

        //Etiqueta Cola
        JLabel lblCola = new JLabel("Cola:");
        Fuente.setFuenteNegrita(lblCola);

        //Panel para la etiqueta cola
        JPanel pnlTxtCola = new JPanel();
        pnlTxtCola.setLayout(new FlowLayout());
        pnlTxtCola.add(lblCola);

        //Boton para abrir la cola
        JButton btnAbrir = new JButton("Abrir");
        Fuente.setFuente(btnAbrir);
        Colores.setBGVerde(btnAbrir);
        btnAbrir.setOpaque(false);  
        
        //Boton para cerrar la cola
        JButton btnCerrar = new JButton("Cerrar");
        Fuente.setFuente(btnCerrar);
        Colores.setBGRojo(btnCerrar);

        /**Panel de botones que controlan la cola */
        JPanel pnlCola = new JPanel();
        pnlCola.setLayout(new FlowLayout());
        pnlCola.add(btnCerrar);
        pnlCola.add(btnAbrir);

        /**Panel con la etiqueta y los botones de abrir y cerrar la cola */
        JPanel pnlGestorCola = new JPanel();
        pnlGestorCola.setLayout(new GridLayout(2,1));
        pnlGestorCola.add(pnlTxtCola);
        pnlGestorCola.add(pnlCola);
        
        //Agregamos la lista
        DefaultListModel<String> dlstColaAlumnos = new DefaultListModel<String>();   //Gestionara añadira y eliminara objetos de la lista
        /** Lista que en la que el alumno podra ver a sus profesores */
        JList<String> lstCola = new JList<String>(dlstColaAlumnos);
        lstCola.setLayoutOrientation(JList.VERTICAL);  //Hace que la lista se rellene de  de arriba a abajo y de izquierda a derecha
        Fuente.setFuente(lstCola);
        lstCola.setFixedCellHeight(25);       //se fija la altura de cada objeto de la lista
        lstCola.setFixedCellWidth(225);       //se fija el ancho de cada objeto de la lista
        lstCola.setBorder(new EmptyBorder(5,5,5,5));        //se agrega un pequeño margen al en el interior de la lista
        JScrollPane lstScroll = new JScrollPane(lstCola);   //Hacemos que podamos hacer scroll en la lista

        /**Boton para eliminar alumno seleccionado */
        JButton btnEliminarAlumno = new JButton("Eliminar alumno");
        Fuente.setFuente(btnEliminarAlumno);
        Colores.setBGAzul(btnEliminarAlumno);
        JPanel pnlEliminarAlu = new JPanel();
        pnlEliminarAlu.setLayout(new FlowLayout());
        pnlEliminarAlu.add(btnEliminarAlumno);

        //Agregamos componentes al panel derecho
        pnlDerecha.add(pnlGestorCola,BorderLayout.NORTH);
        pnlDerecha.add(lstScroll,BorderLayout.CENTER);
        pnlDerecha.add(pnlEliminarAlu,BorderLayout.SOUTH);

        //Agregamos todo a PnlProf
        this.setLayout(new BorderLayout()); //se establece el layout de tipo borderLayout
        this.add(new PnlEncabezado(),BorderLayout.NORTH);   //se añade el panel de encabezado en la parte superior de la pantalla
        this.add(pnlIzquierda,BorderLayout.WEST);    //se añade el panel izquierdo para ser pintado
        this.add(pnlDerecha,BorderLayout.CENTER);     //se añade el panel derecho

        //Acciones de los botones
        btnAbrir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Se hace que el boton tenga color y se le quita al otro
                btnAbrir.setOpaque(true);
                btnCerrar.setOpaque(false);
                btnCerrar.updateUI();
                removeCola(dlstColaAlumnos);    //borramos la lista
                setCola(dlstColaAlumnos);       //obtenemos la lista
            }
        });
        btnCerrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Se hace que el boton tenga color y se le quita al otro
                btnCerrar.setOpaque(true);
                btnAbrir.setOpaque(false);
                btnAbrir.updateUI();
                removeCola(dlstColaAlumnos);
            }
        });
    }
    /**
     * Metodo que obtiene la Cola
     * @param dlstCola ListModel donde se cargara la cola
     */
    private void setCola(DefaultListModel<String> dlstCola){
        dlstCola.addElement("Alumno Generico 1");
        dlstCola.addElement("Alumno Generico 2");
        dlstCola.addElement("Alumno Generico 3");
        dlstCola.addElement("Alumno Generico 4");
        dlstCola.addElement("Alumno Generico 5");
        dlstCola.addElement("Alumno Generico 6");
        dlstCola.addElement("Alumno Generico 7");
        dlstCola.addElement("Alumno Generico 8");
        dlstCola.addElement("Alumno Generico 9");
        dlstCola.addElement("Alumno Generico 10");
        dlstCola.addElement("Alumno Generico 11");
        dlstCola.addElement("Alumno Generico 12");
        dlstCola.addElement("Alumno Generico 13");
        dlstCola.addElement("Alumno Generico 14");
        dlstCola.addElement("Alumno Generico 15");
        dlstCola.addElement("Alumno Generico 16");

    }

    /**
     * Metodo que elimina todo el contenido de la cola
     * @param dlstCola ListModel que contiene la cola a borrar
     */
    private void removeCola(DefaultListModel<String> dlstCola){
        dlstCola.removeAllElements();
    }
}
