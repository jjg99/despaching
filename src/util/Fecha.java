package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**Clase con metodos staticos para poder obtener el dia de la seman
 * De tipo abstract puesto que no se quiere que se instancie en el codigo.
*/
public abstract class Fecha {
    /**
     * Metodo para obtener la fecha con el formato <code>dd/MM/yyyy<\code>
     * @return Una <code>String<\code> de la fecha
     */
    public static String fechaString(){
        Calendar fecha = Calendar.getInstance();    //Obtenemos una instancia de calendario
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  //Fijamos el formato que deseamos
		return sdf.format(fecha.getTime()).toString();     //aplicamos el formato y lo convertimos a String
    }
}
