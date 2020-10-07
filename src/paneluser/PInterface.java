package paneluser;
/**
 * Interfaz que define los metodos que tienen que implementar todos los paneles de la aplicación
 */
public interface PInterface {
    /**
     * Metodo que hace que se elimine el Panel y deje paso al siguiente
    */
    public void eliminar(); 
    /**
    * Metodo para pintar todos lo componentes del panel
    */
    public void establecerVentana();    
}
