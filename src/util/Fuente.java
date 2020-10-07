package util;

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

    public static void setFuente(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.PLAIN, TAM_CUERPO));
    }

    public static void setFuenteNegrita(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.BOLD, TAM_CUERPO));
    }

    public static void setFuenteTitulo(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.PLAIN, TAM_TITULO));
    }

    public static void setFuenteTituloNegrita(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.BOLD, TAM_TITULO));
    }
}
