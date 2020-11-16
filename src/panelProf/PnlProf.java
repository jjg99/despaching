package panelProf;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

import dominio.GestorCola;
import dominio.Profesor;
import paneluser.PnlEncabezado;
import paneluser.PnlHorario;
import util.Colores;
import util.Fuente;

/**Clase para el panel del profesor */
public class PnlProf extends JPanel{

    public static PnlProf pnlProf = new PnlProf(); // se crea la variable que se instanciará desde fuera

    /** Profesor que ha iniciado sesion */
    private Profesor profesor;

    /**
     * Constructor del panel el cual llamara al metodo {@link establecerVentana}
     */
    private PnlProf(){
        this.establecerVentana();
    }

    /**
     * Constructor del panel el cual llamara al metodo {@link establecerVentana} y asigna el profesor asociado al panel
     * @param prof profesor que inicia sesion.
     */
    private PnlProf(Profesor prof){
        this.profesor = prof;
        this.establecerVentana();
    }

    /** Metodo que inicializara e instanciara todos los componentes en la ventana */
    public void establecerVentana(){
        /** Panel que contendra el Horario del dia */
        JPanel pnlIzquierda = new JPanel();
        pnlIzquierda.setLayout(new BorderLayout());
        Colores.setBGColor(pnlIzquierda);   //Fijamos el color del fondo

        JLabel lblHorario = new JLabel("Horario:");
        Fuente.setFuenteNegrita(lblHorario);

        /** Panel que contendra la etiqueta {@link lblHorario}*/
        JPanel pnlTxtHorario = new JPanel();
        pnlTxtHorario.setLayout(new FlowLayout());
        Colores.setBGColor(pnlTxtHorario);   //Fijamos el color del fondo
        pnlTxtHorario.add(lblHorario);
        
        // Agregamos ambos componentes al panel
        pnlIzquierda.add(pnlTxtHorario,BorderLayout.NORTH);
        pnlIzquierda.add(new PnlHorario(),BorderLayout.CENTER);

        /** Panel que contendra la cola */
        JPanel pnlDerecha = new JPanel();
        pnlDerecha.setLayout(new BorderLayout());
        Colores.setBGColor(pnlDerecha);   //Fijamos el color del fondo

        //Etiqueta Cola
        JLabel lblCola = new JLabel("Cola:");
        Fuente.setFuenteNegrita(lblCola);

        //Panel para la etiqueta cola
        JPanel pnlTxtCola = new JPanel();
        pnlTxtCola.setLayout(new FlowLayout());
        Colores.setBGColor(pnlTxtCola);   //Fijamos el color del fondo
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

        /**Panel de botones que controlan el estado de la cola */
        JPanel pnlCola = new JPanel();
        pnlCola.setLayout(new FlowLayout());
        Colores.setBGColor(pnlCola);   //Fijamos el color del fondo
        pnlCola.add(btnCerrar);
        pnlCola.add(btnAbrir);

        /**Panel con la etiqueta y los botones de abrir y cerrar la cola */
        JPanel pnlGestorCola = new JPanel();
        pnlGestorCola.setLayout(new GridLayout(2,1));
        Colores.setBGColor(pnlGestorCola);   //Fijamos el color del fondo
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
        try{
            btnEliminarAlumno.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/30/000000/delete-sign.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnEliminarAlumno.setHorizontalTextPosition(SwingConstants.CENTER); // ponemos el texto en el centro
        btnEliminarAlumno.setVerticalTextPosition(SwingConstants.BOTTOM); // ponemos el texto abajo
        btnEliminarAlumno.setOpaque(false);
        Colores.setBGTransparente(btnEliminarAlumno);
        btnEliminarAlumno.setBorder(null);
        Fuente.setFuente(btnEliminarAlumno);
        
        /**Boton para actualizar la cola */
        JButton btnActualizar = new JButton("Actualizar");
        try{
            btnActualizar.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/30/000000/synchronize.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnActualizar.setHorizontalTextPosition(SwingConstants.CENTER); // ponemos el texto en el centro
        btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM); // ponemos el texto abajo
        btnActualizar.setOpaque(false);
        Colores.setBGTransparente(btnActualizar);
        btnActualizar.setBorder(null);
        Fuente.setFuente(btnActualizar);

        /** Panel que contiene el boton de actualizar y el de eliminar */
        JPanel pnlBotonesLista = new JPanel();
        pnlBotonesLista.setLayout(new FlowLayout(FlowLayout.CENTER, 20,5));
        Colores.setBGColor(pnlBotonesLista);   //Fijamos el color del fondo
        pnlBotonesLista.add(btnActualizar);
        pnlBotonesLista.add(btnEliminarAlumno);
        pnlBotonesLista.setVisible(false);  

        //Agregamos componentes al panel derecho
        pnlDerecha.add(pnlGestorCola,BorderLayout.NORTH);
        pnlDerecha.add(lstScroll,BorderLayout.CENTER);
        pnlDerecha.add(pnlBotonesLista,BorderLayout.SOUTH);

        //Agregamos todo a PnlProf
        this.setLayout(new BorderLayout()); //se establece el layout de tipo borderLayout
        this.add(new PnlEncabezado(),BorderLayout.NORTH);   //se añade el panel de encabezado en la parte superior de la pantalla
        this.add(pnlIzquierda,BorderLayout.WEST);    //se añade el panel izquierdo para ser pintado
        this.add(pnlDerecha,BorderLayout.CENTER);     //se añade el panel derecho

        //Acciones de los botones
        btnAbrir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Añadir el profesor a la tabla de la Cola
                // if(GestorCola.openCola(profesor.getId())){
                if(GestorCola.openCola("Atilano")){
                    //Se hace que el boton tenga color y se le quita al otro
                    btnAbrir.setOpaque(true);
                    btnCerrar.setOpaque(false);
                    btnCerrar.updateUI();
                    removeCola(dlstColaAlumnos);    //borramos la lista
                    setCola(dlstColaAlumnos);       //obtenemos la lista

                    //Se muestran los botones de Actualizar y eliminar
                    pnlBotonesLista.setVisible(true);
                    PnlProf.pnlProf.updateUI();
                }
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

                //Se ocultan los botones de Actualizar y eliminar
                pnlBotonesLista.setVisible(false);
                PnlProf.pnlProf.updateUI();
            }
        });
    }
    /**
     * Metodo que obtiene la Cola
     * @param dlstCola <code>ListModel</code> donde se cargara la cola
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
