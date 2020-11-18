package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**Clase con metodos staticos para poder obtener el dia de la seman
 * De tipo abstract puesto que no se quiere que se instancie en el codigo.
*/
public abstract class Fecha {
    /**
     * Metodo para obtener la fecha con el formato <code>dd/MM/yyyy</code>
     * @return Una <code>String</code> de la fecha
     */
    public static String fechaString(){
        Calendar fecha = Calendar.getInstance();    //Obtenemos una instancia de calendario
		SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy");  //Fijamos el formato que deseamos
		return sdf.format(fecha.getTime()).toString();     //aplicamos el formato y lo convertimos a String
    }

    /**
     * Metodo para obtener el dia de la semana indicado en un numero empezando en 0-> Lunes
     * @return un <code>int</code> del dia de la semana
     */
    public static int getDiaSemana(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("u");  //Fijamos el formato que deseamos
        return Integer.parseInt(sdf.format(cal.getTime()).toString()) -1;
    }
}
