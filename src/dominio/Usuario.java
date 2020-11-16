package dominio;


/**
 * Clase que define los atributos y metodos de todas las personas que usen el programa
 */
public class Usuario {

    /** String que contiene el correo con el que se inicia sesion */
    private String correo;
    /** String que contiene la contraseña con la que se inicia sesion */
    private String nombre;
    /** String que contiene los apellidos del usuario */
    private String apellidos;
    /** String que contiene el id del usuario en la base de datos */
    private String id;
    
    /** constructor basico de un usuario, contiene el correo y la contraseña,
     *  lo minimo para poder iniciar sesion
     *  @param correo String con el usuario
     */
    public Usuario (String correo){
        this.setCorreo(correo);
    }

    /** constructor que inicia todos los atributos de un usuario
     *  @param correo String con el correo
     *  @param nombre String con el nombre
     *  @param apellidos String con los apellidos
     *  @param id String con el id
     */
    public Usuario (String correo, String nombre, String apellidos, String id){
        this(correo);
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setId(id);
    }

    /** metodo para establecer el correo 
    *  @param correo que contiene el correo del usuario
    */
    public void setCorreo(String correo){
        this.correo = correo;
    }

    /**  metodo recuperar el correo 
     * @return String con el correo
    */
    public String getCorreo(){
        return this.correo;
    }

    /** metodo para establecer el nombre
     *  @param nombre contiene el nombre del usuario
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /** metodo para recuperar el nombre
     * @return String con el nombre
    */
    public String getNombre(){
        return this.nombre;
    }

    /** metodo para establecer los apellidos 
     *  @param apellidos contiene los apellidos del usuario
    */
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    /** metodo para recuperar los apellidos
     * @return String con los apellidos
     */
    public String getApellidos(){
        return this.apellidos;
    }

    /** metodo para establecer el id 
     *  @param id identificador del usuario
    */
    public void setId(String id){
        this.id = id;
    }

    /** metodo para recuperar el id 
     * @return String con el id
    */
    public String getId(){
        return this.id;
    }
}
