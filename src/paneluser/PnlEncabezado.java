package paneluser;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.net.URL;
import java.awt.GridBagConstraints;

import util.Fuente;

/** panel generico que tienen todos los profesores y alumnos
 *  el panel tendra 1 entrada que sera el alumno o profesor en cuestion(la primera version puede no contar con ello)
 */
public class PnlEncabezado extends JPanel implements PnlInterface {
    
    /**************************************************************
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
     ***************************************************************/
    
    
    public PnlEncabezado(){
        /** constructor del panel que no recibe ningun paramentro de entrada(solo para el sprint 1) */
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
            lblImagenUsuario = new JLabel(new ImageIcon(new URL("https://img.icons8.com/dusk/64/000000/add-user-group-man-woman.png")));
        }catch (Exception e) {
            e.printStackTrace();
        }
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 2;	// altura
		gbc.gridwidth = 1;	// anchura
        this.add(lblImagenUsuario,gbc);  //se añade el icono al panel

        /** nombre usuario */ 
        JLabel lblNombre = new JLabel("Usuario Generico"); 
        Fuente.setFuenteTituloNegrita(lblNombre);  //establece la fuente del nombre y la pone en negrita
        gbc.gridx = 1; // se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
		gbc.gridwidth = 2; // anchura
        this.add(lblNombre,gbc);  // se añade el nombre al panel

        /** rol  */
        JLabel lblRol = new JLabel("Usuario");
        Fuente.setFuente(lblRol); // establece la fuente del rol
        gbc.gridx = 1;    // se establece la posicion en la matriz
        gbc.gridy = 1;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 1;	// anchura
        this.add(lblRol,gbc);

        /**fecha */
        JLabel lblFecha = new JLabel("12/10/20");
        Fuente.setFuente(lblFecha); //establece la fuente de la fecha
        gbc.gridx = 2;    // se establece la posicion en la matriz
        gbc.gridy = 1;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 1;	// anchura
        this.add(lblFecha,gbc);

        /** boton cerrar sesion */
        JButton btnCerrarSesion = new JButton("Cerrar Sesion"); // se cambiara por un icono mas adelante
        gbc.gridx = 3;    // se establece la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 2;	// altura
        gbc.gridwidth = 1;	// anchura
        this.add(btnCerrarSesion,gbc);
    }

    @Override
    public void eliminar(){

    }
}
