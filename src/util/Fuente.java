package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;

/**
 * Esta clase se encarga de cambiar las propiedades del texto de los {@link JComponent} para
 * mantener consistencia a lo largo del programa.
 */
public class Fuente {
    /**Fija el tipo de letra a <code>SANS_SERIF</code>*/
    private final static String FUENTE = Font.SANS_SERIF;

    /**Fija el tamaño de la letra de los titulos*/
    private final static int TAM_TITULO = 30;

    /**Fija el tamaño de la letra del cuerpo*/
    private final static int TAM_CUERPO = 20;

    /**Fija el color de la letra verda */
    private final static Color VERDE = new Color(114, 202, 175);

    /**
     * Metodo para fijar la letra del cuerpo.
     * @param comp <code>JComponent</code> al que se le cambiara la letra.
     */
    public static void setFuente(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.PLAIN, TAM_CUERPO));
    }

    /**
     * Metodo para fijar la letra del cuerpo en negrita.
     * @param comp <code>JComponent</code> al que se le cambiara la letra.
     */
    public static void setFuenteNegrita(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.BOLD, TAM_CUERPO));
    }

    /**
     * Metodo para fijar la letra del titulo.
     * @param comp <code>JComponent</code> al que se le cambiara la letra.
     */
    public static void setFuenteTitulo(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.PLAIN, TAM_TITULO));
    }

    /**
     * Metodo para fijar la letra del titulo en negrita.
     * @param comp <code>JComponent</code> al que se le cambiara la letra.
     */
    public static void setFuenteTituloNegrita(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.BOLD, TAM_TITULO));
    }

    
}
