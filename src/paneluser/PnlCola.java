
package paneluser;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import util.Colores;
import util.Fuente;
import dominio.Usuario;
import dominio.Alumno;
import dominio.GestorCola;

/**
 * Clase que crea un panel que muestra la cola de un alumno y le da opciones para 
 * apuntarse y modificar su situacion en ella
 */
public class PnlCola extends JPanel {
    public static PnlCola pnlCola = new PnlCola();

    /** Constructor que llama al metodo {@link initComponentes} */
    private PnlCola()
    {
        crearComponentes();
        
    }


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
        JButton btnAtras = new JButton("Volver");
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

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.anchor = GridBagConstraints.PAGE_START;

        pnlInfCola.add(lblCola, c);

        //Creacion de la etiqueta del profesor
        JLabel lblProf = new JLabel("Profesor generico");
        //TODO: Añadir el nombre del profesor pasado por el constructor
        Fuente.setFuenteTitulo(lblProf);

        // Ajuste de lblProf en el panel
        c.gridy = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;

        pnlInfCola.add(lblProf, c);


        // Creacion de la etiqueta cola abierta
        //TODO: Añadir color a la etiqueta de 
        JLabel lblAbierta = new JLabel("Abierta");
        Fuente.setFuente(lblAbierta);
        Colores.setBGVerde(lblAbierta);

        // Ajuste de lblAbierta en el panel
        c.gridy = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;

        pnlInfCola.add(lblAbierta, c);

        // Creacion del boton actualizar
        JButton btnActualizar = new JButton("Actualizar");
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

        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;

        pnlInfCola.add(lblGenteEspera, c);

        // Creacion de la etiqueta Numero Personas en la cola
        JLabel lblNumGenteEspera = new JLabel("4");
        Fuente.setFuenteNegrita(lblNumGenteEspera);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;

        pnlInfCola.add(lblNumGenteEspera, c);

        // Creacion de la etiqueta de posicion en la cola
        JLabel lblPosCola = new JLabel("Su posicion en la cola: ");
        Fuente.setFuente(lblPosCola);

        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;

        pnlInfCola.add(lblPosCola, c);

        // Creacion de la etiqueta Numero Personas en la cola
        JLabel lblNumPosCola = new JLabel("4");
        Fuente.setFuenteNegrita(lblNumPosCola);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;

        pnlInfCola.add(lblNumPosCola, c);

        // Creacion del boton salir de la cola
        JButton btnSalirCola = new JButton("Salir de la cola");

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

        c.gridx = 0;
        c.gridy = 6;
        c.weighty = 0.3;
        c.anchor = GridBagConstraints.LINE_START;

        pnlInfCola.add(btnSalirCola, c);

        // Creacion del boton entrar a la cola
        JButton btnEntrarCola = new JButton("Entrar a la cola");

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

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;

        pnlInfCola.add(btnEntrarCola, c);

        this.setLayout(new BorderLayout());
        //this.add(new PnlEncabezado(user),BorderLayout.NORTH);
        this.add(pnlInfCola, BorderLayout.CENTER);




        // CREACION DE LOS ACTION LISTENER
        btnAtras.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                //TODO: Implementar la vuelta al panel original 
            }
        });

        btnActualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                String lista
            }
        });



    }

   
}
