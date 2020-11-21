
package paneluser;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.JVentana;
import panelAlu.PnlAlumno;
import dominio.Alumno;
import dominio.GestorCola;
import dominio.Profesor;
import server.Fachada;
import util.Colores;
import util.Fuente;


/**
 * Clase que crea un panel que muestra la cola de un alumno y le da opciones para 
 * apuntarse y modificar su situacion en ella
 */
public class PnlCola extends JPanel {

    
    private static Alumno alumno;
    private static Profesor profesor;

    private static JButton btnAtras;
    private static JButton btnActualizar;
    private static JButton btnEntrarCola;
    private static JButton btnSalirCola;

    private static JLabel lblNumGenteEspera;
    private static JLabel lblNumPosCola;
    private static JLabel lblProf;

    //TODO: Revisar la instanciación de clases
    private static int intPosCola;
    private static int intNumCola;

    public static PnlCola pnlCola;



    /** Constructor que llama al metodo {@link initComponentes} y {@link crearComponentes} */
    public PnlCola(Alumno alu, Profesor prof)
    {
        actualizarAlumno(alu);
        actualizarProfesor(prof);
        getNombreProfesor(prof);
        actualizarDatos();
        this.crearComponentes();
        this.initComponentes(); 
    }


    /**
     * Método que genera los componentes del panel y los coloca en el orden adecuado
     */

    private void crearComponentes()
    {
        // Creacion del panel central/inferior que presenta todos los elementos
        JPanel pnlInfCola = new JPanel();
        
        // Ajustes de la disposicion de los elementos dentro del panel
        pnlInfCola.setLayout(new GridBagLayout());
        Colores.setBGColor(pnlInfCola);

        // Creacion de los contraints que determinarán la posicion y estilo de los objetos
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0.1;

        // Creacion de todos los componentes
        // ##################################
        
        // Creacion del boton atras
        btnAtras = new JButton("Volver");
        try{
            btnAtras.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/40/return.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        // Ajuste las caracteristicas del boton
        btnAtras.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAtras.setVerticalTextPosition(SwingConstants.CENTER);
        Colores.setBGTransparente(btnAtras);
        btnAtras.setBorder(null);
        Fuente.setFuente(btnAtras);

        // Ajuste del btnAtras en el panel
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        pnlInfCola.add(btnAtras, c);

        // Creacion del título
        JLabel lblCola = new JLabel("Cola");
        Fuente.setFuenteTituloNegrita(lblCola);

        // Ajuste de lblCola en el panel
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.anchor = GridBagConstraints.PAGE_START;

        pnlInfCola.add(lblCola, c);

        //Creacion de la etiqueta del profesor
        //lblProf = new JLabel("Profesor generico");
        //TODO: REvisar el nombre del profesor pasado por parametro
        getNombreProfesor(PnlCola.profesor);
        Fuente.setFuenteTitulo(lblProf);
        

        // Ajuste de lblProf en el panel
        c.gridy = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;

        pnlInfCola.add(lblProf, c);


        // Creacion de la etiqueta cola abierta
        //TODO: Añadir color a la etiqueta de Abierta
        //TODO: Cambiar la etiqueta para que la cola este cerrada
        JLabel lblAbierta = new JLabel("Abierta");
        Fuente.setFuente(lblAbierta);
        Colores.setBGVerde(lblAbierta);

        // Ajuste de lblAbierta en el panel
        c.gridy = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;

        pnlInfCola.add(lblAbierta, c);

        // Creacion del boton actualizar
        btnActualizar = new JButton("Actualizar");
        try{
            btnActualizar.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/30/000000/synchronize.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnActualizar.setHorizontalTextPosition(SwingConstants.RIGHT); // ponemos el texto en el centro
        btnActualizar.setVerticalTextPosition(SwingConstants.CENTER); // ponemos el texto abajo
        btnActualizar.setOpaque(false);
        Colores.setBGTransparente(btnActualizar);
        btnActualizar.setBorder(null);
        Fuente.setFuente(btnActualizar);

        // Ajuste de btnActualizar en el panel
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;

        pnlInfCola.add(btnActualizar, c);

        // Creacion de la etiqueta de gente a la espera
        JLabel lblGenteEspera = new JLabel("Gente en la cola: ");
        Fuente.setFuente(lblGenteEspera);

        // Ajuste de lblGenteEspera en el panel
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;

        pnlInfCola.add(lblGenteEspera, c);

        // Creacion de la etiqueta Numero Personas en la cola
        lblNumGenteEspera = new JLabel(String.valueOf(intNumCola));
        Fuente.setFuenteNegrita(lblNumGenteEspera);
        
        // Ajuste de lblNumGenteEspera en el panel
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;

        pnlInfCola.add(lblNumGenteEspera, c);

        // Creacion de la etiqueta de posicion en la cola
        JLabel lblPosCola = new JLabel("Su posicion en la cola: ");
        Fuente.setFuente(lblPosCola);

        // Ajuste de lblPosCola en el panel
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;

        pnlInfCola.add(lblPosCola, c);

        // Creacion de la etiqueta Numero Personas en la cola
        Fuente.setFuenteNegrita(lblNumPosCola);
        
        // Ajuste de lblNumPosCola en el panel
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;

        pnlInfCola.add(lblNumPosCola, c);

        // Creacion del boton salir de la cola
        btnSalirCola = new JButton("Salir de la cola");

        try{
            btnSalirCola.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/40/logout-rounded.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnSalirCola.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSalirCola.setVerticalTextPosition(SwingConstants.BOTTOM);
        Colores.setBGTransparente(btnSalirCola);
        btnSalirCola.setBorder(null);
        Fuente.setFuente(btnSalirCola);

        // Ajuste del btnSalirCola en el panel
        c.gridx = 0;
        c.gridy = 6;
        c.weighty = 0.3;
        c.anchor = GridBagConstraints.LINE_START;

        pnlInfCola.add(btnSalirCola, c);

        // Creacion del boton entrar a la cola
        btnEntrarCola = new JButton("Entrar a la cola");

        try{
            btnEntrarCola.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/40/login-rounded-right.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnEntrarCola.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEntrarCola.setVerticalTextPosition(SwingConstants.BOTTOM);
        Colores.setBGTransparente(btnEntrarCola);
        btnEntrarCola.setBorder(null);
        Fuente.setFuente(btnEntrarCola);

        // Ajuste del btnEntrarCola en el panel
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;

        pnlInfCola.add(btnEntrarCola, c);


        // Creacion del layout dentro de la ventana
        this.setLayout(new BorderLayout());
        this.add(new PnlEncabezado(PnlCola.alumno),BorderLayout.NORTH);
        //TODO: ajustar el encabezado dentro del panel e instanciarlo correctamente
        this.add(pnlInfCola, BorderLayout.CENTER);

    }

    /**
     * Metodo que genera todos los Action Listeners y asocia acciones a los elementos presentes en la ventana
     */

    private void initComponentes()
    {
        // CREACION DE LOS ACTION LISTENER

        /**
         * Metodo que implementa el action listener para volver al panel del alumno
         */
        btnAtras.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                volverPnlInicio();
                
            }
        });

        /**
         * Metodo que implementa el action listener para actualizar los datos en la pantalla
         */
        btnActualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                actualizarDatos();
                PnlCola.pnlCola.updateUI();

            }
        });

        /**
         * Metodo que implementa el action listener para entrar en la cola
         */
        btnEntrarCola.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                GestorCola.addAlumnoCola(alumno, profesor);
                actualizarDatos();
                PnlCola.pnlCola.updateUI();
                
            }
        });

        /**
         * Metodo que implementa el action listener para salir de la cola
         */
        btnSalirCola.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                //TODO: Añadir el metodo para salir de la cola
                
                actualizarDatos();
            }
        });
    }


    /**
     * Metodo que actualiza los datos enseñados en el panel
     */
    private static void actualizarDatos() 
    {

        PnlCola.intPosCola = GestorCola.getPosicionAlumno(PnlCola.alumno.getId(), PnlCola.profesor.getId());
        if (PnlCola.intPosCola == 0) {
            PnlCola.lblNumPosCola = new JLabel("-");
            Fuente.setFuenteNegrita(PnlCola.lblNumPosCola);
        } else {
            PnlCola.lblNumPosCola = new JLabel(String.valueOf(intPosCola));
            Fuente.setFuenteNegrita(PnlCola.lblNumPosCola);
        }
        //TODO: Checkear que no da error esto
        PnlCola.intNumCola = Fachada.getColaProfesor(PnlCola.profesor.getId()).size();
    }

    /**
     * Metodo que actualiza el alumno que tien que cargar el panel
     * @param alu recibe el alumno para anadir este al panel
     */

    public static void actualizarAlumno(Alumno alu)
    {
        PnlCola.alumno = alu;
    }

    /**
     * Metodo que actualiza el rpofesor a ensenar en el panel
     * @param prof String que indica el codigo de profesor para hacer queries
     */
    public static void actualizarProfesor(Profesor prof)
    {
        PnlCola.profesor = prof;
    }

    /**
     * Metodo que obtiene el nombre del profesor para enseñarlo por pantalla
     * @param prof String que contiene el identificador del porfesor
     */
    private static void getNombreProfesor(Profesor prof)
    {
        PnlCola.lblProf = new JLabel(prof.toString());
        Fuente.setFuenteTitulo(PnlCola.lblProf);
    }

    private static void volverPnlInicio()
    {
        JVentana.cambiarPanel(PnlAlumno.PnlAlumno);
    }

}
