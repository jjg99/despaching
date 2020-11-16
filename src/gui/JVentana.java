package gui;

import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import paneluser.PnlInicio;
import util.Colores;

/**
 * Esta clase contiene el main, y sera la que contendra todos los paneles de
 * todos los usuarios y la que se encargara de pasar entre ellas.
 */
public class JVentana extends JFrame {
    /** String con el nombre que tendra la ventanga generada */
    private final String Titulo = new String("Despaching");

    /**
     * Panel que contendra todos los componentes y ocupara la totalidad de la
     * <code>JVentana</code>
     */
    private static JPanel pnlVentana = new JPanel();

    /**
     * Metodo main crea una instancia de JVentanaApp
     */
    public static void main(String[] args) {
        new JVentana();
    }

    /**
     * Crea una nueva JVentanaApp, llamando al metodo {@link initComponents} para
     * instanciar el panel inicial e {@link initFrame} para configurar y mostrar la
     * ventana.
     */
    public JVentana() {
        //Fijamos unos colores por defecto
        UIManager.put("OptionPane.background", Colores.FONDO);
        UIManager.put("Panel.background", Colores.FONDO);
        UIManager.put("Button.background", Colores.AZUL);
        initComponents();
        initFrame();
        this.requestFocusInWindow(); // Hacemos focus en la JVentana para que no empiece en el Textfield de Usuario
    }

    /**
     * Metodo donde se inicializan los componentes iniciales de la aplicacion, es
     * decir, el {@link PnlInicio}
     */
    private void initComponents() {
        pnlVentana.setLayout(new BorderLayout()); // Fijamos el tipo de Layout
        pnlVentana.add(PnlInicio.PnlInicio, BorderLayout.CENTER); // Añadimos el panel de inicio

        this.setLayout(new BorderLayout()); // Fijamos el tipo de Layout
        this.add(pnlVentana, BorderLayout.CENTER); // Añadimos el panel de la ventana
    }

    /**
     * Metodo en el cual se configura el tamaño, se fija un {@linkplain Titulo} a la
     * ventana, se activa la visibilidad, se fija la accion al cerrarse, se fija la
     * localizacion y se hace que no se pueda cambiar el tamaño de la ventana.
     */
    private void initFrame() {
        try {
            this.setIconImage(
                    (new ImageIcon(new URL("https://img.icons8.com/dusk/32/000000/add-user-group-man-woman.png")))
                            .getImage());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        this.setSize(700,700);  //Fijamos el tamaño de la ventana a 600x600
        this.setTitle(Titulo);  //Ponemos el titulo a la ventana
        this.setVisible(true);  //Hacemos la ventana visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Cuando se cierre que termine el programa
        this.setLocationRelativeTo(null);   //Hacemos que la app se abra en el centro de la pantalla
        this.setResizable(false);   //Fijamos que no se modifique el tamaño de la ventana
        this.requestFocus();
    }

    /**
     * Metodo estatico que borra el contenido de {@link pnlVentana} y añade el <code>JPanel</code>
     * recibido en el centro.
     * @param pnlPoner panel a cargar en {@link pnlVentana}
     */
    public static void cambiarPanel(JPanel pnlPoner){
        pnlVentana.removeAll();
        pnlVentana.add(pnlPoner, BorderLayout.CENTER);
        pnlVentana.updateUI();
    }
}