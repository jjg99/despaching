package util;

import java.awt.Color;

import javax.swing.JComponent;


/** 
 * Esta clase se encarga de cambiar los colores de los diferentes componentes para mantener
 * consistencia en todo el programa.
*/
public class Colores {

    /**Fija el color del fonda para toda la app */
    public final static Color FONDO = new Color(255, 255, 255);    //Blanco
    /**Fija el color Verde */
    private final static Color VERDE = new Color(114, 202, 175);
    /**Fija el color Rojo */
    private final static Color ROJO = new Color(237, 120, 153);
    /**Fija el color Amarillo */
    private final static Color AMARILLO = new Color(249, 227, 174);
    /**Fija el color Azul */
    public final static Color AZUL = new Color(194, 205, 231); 
    /**Fija el color Azul Oscuro para el texto */
    private final static Color AZUL_OSC = new Color(9, 110, 226);
    /**Fija el color Azul Oscuro Translucido para el texto */
    private final static Color AZUL_OSC_TRANSLUX = new Color(9, 110, 226, 180);
    /**Fija el color Transparente */
    private final static Color TRANSPARENTE = new Color(0, 0, 0, 0);

   /**
     * Metodo para fijar el color del fonde para toda la app.
     * @param comp <code>JComponent</code> al que se le cambiara el color.
     */
    public static void setBGColor(JComponent comp){
        comp.setBackground(FONDO);
    }

    /**
     * Metodo para fijar el color del background en verde.
     * @param comp <code>JComponent</code> al que se le cambiara el color.
     */
    public static void setBGVerde(JComponent comp){
        comp.setBackground(VERDE);
    }

    /**
     * Metodo para fijar el color del background en rojo.
     * @param comp <code>JComponent</code> al que se le cambiara el color.
     */
    public static void setBGRojo(JComponent comp){
        comp.setBackground(ROJO);
    }

    /**
     * Metodo para fijar el color del background en amarillo.
     * @param comp <code>JComponent</code> al que se le cambiara el color.
     */
    public static void setBGAmarillo(JComponent comp){
        comp.setBackground(AMARILLO);
    }

    /**
     * Metodo para fijar el color del background en verde.
     * @param comp <code>JComponent</code> al que se le cambiara el color.
     */
    public static void setBGAzul(JComponent comp){
        comp.setBackground(AZUL);
    }

    /**
     * Metodo para fijar el color del foreground en verde.
     * @param comp <code>JComponent</code> al que se le cambiara el color.
     */
    public static void setFGAzulOsc(JComponent comp){
        comp.setForeground(AZUL_OSC);
    }

    /**
     * Metodo para fijar el color del foreground en azul oscuro transucido.
     * @param comp <code>JComponent</code> al que se le cambiara el color.
     */
    public static void setFGAzulOscTrans(JComponent comp){
        comp.setForeground(AZUL_OSC_TRANSLUX);
    }

    /**
     * Metodo para fijar el color del background en transparente.
     * @param comp <code>JComponent</code> al que se le cambiara el color.
     */
    public static void setBGTransparente(JComponent comp){
        comp.setBackground(TRANSPARENTE);
    }
}
