package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    public static String fechaHoraString(){
        Calendar fecha = Calendar.getInstance();    //Obtenemos una instancia de calendario
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");  //Fijamos el formato que deseamos
		return sdf.format(fecha.getTime()).toString();     //aplicamos el formato y lo convertimos a String
    }
    /**
     * Metodo para obtener la fecha con el formato <code>dd/MM/yyyy</code>
     * @return Una <code>String</code> de la fecha
     */
    public static String fechaString(int dia, int mes, int anio){
        Calendar fecha = Calendar.getInstance();    //Obtenemos una instancia de calendario
        SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy");  //Fijamos el formato que deseamos
        fecha.set(Calendar.YEAR,anio);
        fecha.set(Calendar.MONTH,mes);
        fecha.set(Calendar.DAY_OF_MONTH,dia);
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

    /**
     * Metodo para obtener el dia de la semana indicado en un numero empezando en 0-> Lunes
     * @return un <code>int</code> del dia de la semana
     */
    public static int getDiaSemana(int dia, int mes, int anio){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,anio);
        cal.set(Calendar.MONTH,mes);
        cal.set(Calendar.DAY_OF_MONTH,dia);
        SimpleDateFormat sdf = new SimpleDateFormat("u");  //Fijamos el formato que deseamos
        return Integer.parseInt(sdf.format(cal.getTime()).toString()) -1;
    }

   /**
    * metodo para obtener el año actual
    * @return <code>int</code> con el año actual
    */
    public static int getAnio(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return Integer.valueOf(sdf.format(cal.getTime()));
    }


    /**
    * metodo para obtener el mes actual siendo enero un 0
    * @return <code>int</code> con el mes de año
    */
    public static int getMes(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("M");
        return Integer.valueOf(sdf.format(cal.getTime())) -1;
    }

     /**
    * metodo para transformar el mes pasado de numero a letra
    * @return <code>String</code> con el mes de año
    */
    public static String getMesToString(int mes){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.MONTH,mes);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        return sdf.format(cal.getTime()).toString();
    }

    /**
    * metodo para obtener el dia actual
    * @return <code>int</code> con el dia del mes
    */
    public static int getDia(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        return Integer.valueOf(sdf.format(cal.getTime()));
    }

    /**
     * metodo para obtener el primer dia del mes de un año especifico
     * @param mes <code>int</code> con el valor del mes siendo enero un 0
     * @param anio <code>int</code> con el valor del año
     * @return <code>int</code> con el valor del dia del mes siendo lunes  un 0
     */
    public static int getPrimerDiaMes(int mes, int anio){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("u");
        cal.set(Calendar.YEAR,anio);
        cal.set(Calendar.MONTH,mes);
        cal.set(Calendar.DAY_OF_MONTH,1);
        return Integer.valueOf(sdf.format(cal.getTime())) -1;

    }

    /**
     * metodo para obtener el ultimo dia del mes
     * @param mes <code>int</code> con el valor del mes siendo enero un 0
     * @param anio <code>int</code> con el valor del año
     * @return <code>int</code> con el ultimo dia de un año
     */
    public static int getUltimoDiaMes(int mes, int anio){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,anio);
        cal.set(Calendar.MONTH,mes);
        cal.set(Calendar.DAY_OF_MONTH,1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
    * metodo para obtener el mes actual siendo enero un 0
    * @param mes <code>int</code> con el valor del mes siendo enero un 0
    * @param anio <code>int</code> con el valor del año
    * @return <code>int</code> con el mes de año
    */
    public static int getSemanasMes(int mes, int anio){
        Calendar cal = Calendar.getInstance();
        cal.setMinimalDaysInFirstWeek(1);
        cal.set(Calendar.MONTH,mes);
        cal.set(Calendar.YEAR,anio);
        cal.set(Calendar.DAY_OF_MONTH,1);
        return cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    /**
     * metodo para crear una Date en funcion de unos parametros
     * @param year anio que se desea asignar
     * @param month numero del mes - 1 que se desea asignar
     * @param day numero del dia que se desea asignar
     * @param hour hora a asignar
     * @param minute minutos a asignar
     * @return Date de los parametros asignados
     */
    public static java.util.Date setDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,day);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static int getDiaSemana(Date fecha){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("u");
        return Integer.valueOf(sdf.format(cal.getTime()))-1;
    }

    public static int getHora(Date fecha){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("H");
        return Integer.valueOf(sdf.format(cal.getTime()));
    }
    
    public static int getMinuto(Date fecha){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("m");
        return Integer.valueOf(sdf.format(cal.getTime()));
    }  
}
