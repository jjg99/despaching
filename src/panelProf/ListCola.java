package panelProf;

import javax.swing.JList;

/**Clase de tipo {@link JList}, que define como es la cola de alumnos */
public class ListCola extends JList implements Listable {

    /**Constructor de la clase, define el tamaño por defecto de la lista y pone los valores iniciales */
    ListCola(){
        this.setSize(30,30);    //se establece el tamaño
        this.actualizarValores();  //se cogen los valores más recientes de la cola
        this.setLayoutOrientation(JList.VERTICAL_WRAP);  //Hace que la lista se rellene de izquierda a derecha y de arriba a abajo
        this.setVisibleRowCount(10);
    }
    @Override 
    public void actualizarValores() {
        // TODO Auto-generated method stub
        
    }
}
