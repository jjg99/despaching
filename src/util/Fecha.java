package util;

import java.util.Calendar;
import java.util.Locale;

/**Clase con metodos staticos para poder obtener el dia de la seman
 * De tipo abstract puesto que no se quiere que se instancie en el codigo.
*/
public abstract class Fecha {
    /** metodo para devolver el dia de la semana en forma de {@link String} */
    public static String fechaString(){
        /**Objeto de tipo {@link StringBuilder} para devolver posteriormente la fecha en formato {@link String} */
        StringBuilder fechaConvertida = new StringBuilder();       //se 
        /**Variable de tipo {@link Calendar} apra obtener un objeto de tipo calendario */
        Calendar calendario = Calendar.getInstance(new Locale("spanish","spain"));       // se coge una isntancia de calendario, es decir se coge un objeto calendario
        fechaConvertida.append(calendario.get(Calendar.DAY_OF_MONTH)+"/"+calendario.get(Calendar.MONTH)+"/"+calendario.get(Calendar.YEAR)); // se rellena el String builder con los datos de la fecha actual
        return fechaConvertida.toString();        
    }
}
