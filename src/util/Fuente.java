package util;

import java.awt.Font;

import javax.swing.JComponent;

/**
 * Esta clase se encarga de cambiar las propiedades del texto de los {@link JComponent} para
 * mantener consistencia a lo largo del programa.
 */
public class Fuente {
    private final static String FUENTE = Font.SANS_SERIF;

    private final static int TAM_TITULO = 30;

    private final static int TAM_CUERPO = 20;

    public static void SetFuente(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.PLAIN, TAM_CUERPO));
    }

    public static void SetFuenteNegrita(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.BOLD, TAM_CUERPO));
    }

    public static void SetFuenteTitulo(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.PLAIN, TAM_TITULO));
    }

    public static void SetFuenteTituloNegrita(JComponent comp){
        comp.setFont(new Font(FUENTE, Font.BOLD, TAM_TITULO));
    }
}
