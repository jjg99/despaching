package dominio;


/**
 * Clase que define los atributos y metodos de todas las personas que usen el programa
 */
public class Usuario {

    /** String que contiene el correo con el que se inicia sesion */
    private String correo;
    /** String que contiene la contraseña con la que se inicia sesion */
    private String password;
    /** String que contine el nombre del usuario */
    private String nombre;
    /** String que contiene los apellidos del usuario */
    private String apellidos;
    /** String que contiene el id del usuario en la base de datos */
    private String id;
    
    /** constructor basico de un usuario, contiene el correo y la contraseña,
     *  lo minimo para poder iniciar sesion
     */
    public Usuario (String correo,String password){
        this.setCorreo(correo);
        this.setPassword(password);
    }

    /** constructor que inicia todos los atributos de un usuario */
    public Usuario (String correo, String password, String nombre, String apellidos, String id){
        this(correo, password);
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setId(id);
    }

    /** metodo para establecer el correo */
    public void setCorreo(String correo){
        this.correo = correo;
    }

    /** metodo recuperar el correo */
    public String getCorreo(){
        return this.correo;
    }
    
    /** metodo para establecer la contraseña */
    public void setPassword(String password){
        this.password = password;
    }

    /** metodo para recuperar la contraseña */
    public String getPassword(){
        return this.password;
    }

    /** metodo para establecer el nombre */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /** metodo para recuperar el nombre*/
    public String getNombre(){
        return this.nombre;
    }

    /** metodo para establecer los apellidos */
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    /** metodo para recuperar los apellidos */
    public String getApellidos(){
        return this.apellidos;
    }

    /** metodo para establecer el id */
    public void setId(String id){
        this.id = id;
    }

    /** metodo para recuperar el id */
    public String getId(){
        return this.id;
    }
}
