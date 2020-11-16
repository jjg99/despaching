package paneluser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import server.ConexionServer;
import util.Colores;
import util.Fecha;
import util.Fuente;

/** panel generico que tienen todos los profesores y alumnos
 *  el panel tendra 1 entrada que sera el alumno o profesor en cuestion(la primera version puede no contar con ello)
 */
public class PnlEncabezado extends JPanel implements PnlInterface {
    
    /* *************************************************************
     * atributo que tiene la informacion del usuario
     *  Usuario usuario; 
     * 
     * constructor que indica que usuario se tiene
     * public PnlEncabezado(Usuario usuario){
     *      this.setUsuario(usuario);
     *      this.establecerVentana();
     * }
     * 
     * public void setUsuario(Usuario usuario){
     *      this.usuario = usuario;
     * }
     * 
     * public Usuario getUsuario(){
     *       return this.usuariuo;
     * }
     ************************************************************** */
    
    /** constructor del panel que no recibe ningun paramentro de entrada(solo para el sprint 1) */
    public PnlEncabezado(){
        this.establecerVentana();
    }

    @Override
    public void establecerVentana(){
        this.setLayout(new GridBagLayout()); //se establece el layout 
        /**Restricciones  para ir colocando los diferentes elementos dentro del {@link Gridbaglayout} */
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE; // para que no se rellenen los huecos de la matriz

        /** establece la imagen del usuario */
        JLabel lblImagenUsuario = null;
        try{
            lblImagenUsuario = new JLabel(new ImageIcon(new URL("https://img.icons8.com/dusk/64/000000/checked-user-male.png")));
        }catch (Exception e) {
            e.printStackTrace();
        }
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 2;	// altura
        gbc.gridwidth = 1;	// anchura
        gbc.insets = new Insets(0,20,0,0); // deja una separacion a la izquierda
        this.add(lblImagenUsuario,gbc);  //se añade el icono al panel

        /** nombre usuario */ 
        JLabel lblNombre = new JLabel("Usuario Generico"); 
        Fuente.setFuenteTituloNegrita(lblNombre);  //establece la fuente del nombre y la pone en negrita
        gbc.gridx = 1; // se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
		gbc.gridwidth = 4; // anchura
        this.add(lblNombre,gbc);  // se añade el nombre al panel

        /** rol  */
        JLabel lblRol = new JLabel("Rol");
        Fuente.setFuente(lblRol); // establece la fuente del rol
        gbc.gridx = 1;    // se establece la posicion en la matriz
        gbc.gridy = 1;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 2;	// anchura
        gbc.insets = new Insets(0,50,0,0); // deja una separacion a la izquierda
        this.add(lblRol,gbc);

        /**fecha */
        JLabel lblFecha = new JLabel(Fecha.fechaString());
        Fuente.setFuente(lblFecha); //establece la fuente de la fecha
        gbc.gridx = 3;    // se establece la posicion en la matriz
        gbc.gridy = 1;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 2;	// anchura
        this.add(lblFecha,gbc);

        /** boton cerrar sesion */
        JButton btnCerrarSesion = new JButton("Cerrar Sesion");
        try{
            btnCerrarSesion.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/40/000000/exit-sign.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnCerrarSesion.setHorizontalTextPosition(SwingConstants.CENTER); // ponemos el texto en el centro
        btnCerrarSesion.setVerticalTextPosition(SwingConstants.BOTTOM); // ponemos el texto abajo
        Fuente.setFuente(btnCerrarSesion);
        btnCerrarSesion.setOpaque(false);
        Colores.setBGTransparente(btnCerrarSesion);
        btnCerrarSesion.setBorder(null);
        gbc.gridx = 5;    // se establece la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 2;	// altura
        gbc.gridwidth = 1;	// anchura
        gbc.ipadx = 0;
        gbc.insets = new Insets(0,20,0,0); // deja una separacion a la izquierda
        this.add(btnCerrarSesion,gbc);

        /**Metodo que cerrara sesion al usuario, llevandole a la pantalla de inicio */
        btnCerrarSesion.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JVentana.cambiarPanel(PnlInicio.PnlInicio); // se establece el nuevo panel de la aplicación
                ConexionServer.endConnection();  
            }
        });
    }
}
