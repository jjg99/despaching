package panelProf;

import javax.swing.JList;
/**Clase de tipo {@link JList}, que define como es una lista de reservas */
public class ListReservas extends JList implements Listable {
    /**Constructor de la clase, define el tamaño por defecto de la lista y pone los valores iniciales */
    ListReservas(){
        this.setSize(30,30);    //se establece el tamaño
        this.actualizarValores();  //se cogen los valores más recientes de reservas
        this.setLayoutOrientation(JList.VERTICAL_WRAP);  //Hace que la lista se rellene de izquierda a derecha y de arriba a abajo
        this.setVisibleRowCount(10);
    }
    @Override 
    public void actualizarValores() {
        // TODO Auto-generated method stub
        
    }
}
