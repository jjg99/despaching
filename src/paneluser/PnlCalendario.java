package paneluser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dominio.Usuario;
import panelProf.PnlProf;
import util.Colores;
import util.Fecha;
import util.Fuente;

public class PnlCalendario extends JPanel {

    private Usuario usuario;
    private static int diaActivo = Fecha.getDia();
    private static int mesActivo = Fecha.getMes();
    private static int anioActivo = Fecha.getAnio();
    private static int diaSemana = 0;
    private static int horaIni[] = new int[2];
    private static int horaFin[] = new int[2];
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor del panel el cual llamara al metodo {@link establecerVentana} y
     * al metodo{@link setProfesor}
     * 
     * @param usuario usuario que a iniciado sesion
     */
    public PnlCalendario(Usuario usuario) {
        setUsuario(usuario);
        this.establecerVentana();
    }

    /**
     * metodo que inicializa el atributo usuario asociado
     * 
     * @param usuario que puede ser un profesor o un alumno
     */
    private void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void establecerVentana() {

        // establecemos el dia actual como activos
        diaActivo = Fecha.getDia(); 
        mesActivo = Fecha.getMes();
        anioActivo = Fecha.getAnio();

        this.setLayout(new FlowLayout());
        // panel principal
        JPanel pnlPrincipal = new JPanel(new GridBagLayout()); // panel principal del calendario
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE; // para que no se rellenen los huecos de la matriz
        
        // panel que muestra las fechas
        JPanel pnlFecha = new JPanel(new GridBagLayout()); //panel que muestra la fecha

        // panel que sirve para cambiar de fecha
        JPanel pnlCambioFecha = new JPanel(new FlowLayout()); // panel para cambiar la fecha actual

        //boton para ir hacia atras en los meses
        JButton btnRetroceder = new JButton("<"); // creamos el boton retroceder
        Fuente.setFuente(btnRetroceder);    //  cambiamos la fuente
        btnRetroceder.setOpaque(false); //cambiamos la opacidad
        Colores.setBGTransparente(btnRetroceder);   // establecemos el color
        pnlCambioFecha.add(btnRetroceder);  // lo añadimos al panel

        //texto que muestra el año y la fecha actual
        JLabel lblMes = new JLabel(Fecha.getMesToString(mesActivo) + "  " + anioActivo); //creamos el label que tiene el mes
        Fuente.setFuente(btnRetroceder);    //  cambiamos la fuente
        pnlCambioFecha.add(lblMes);  // lo añadimos al panel 

        //boton que sirve para avanzar en los meses
        JButton btnAvanzar = new JButton(">"); // creamos el boton avanzar
        Fuente.setFuente(btnAvanzar);   // cambiamos la fuente
        btnAvanzar.setOpaque(false);    // cambiamos la opacidad
        Colores.setBGTransparente(btnAvanzar);  // establecemos el color
        pnlCambioFecha.add(btnAvanzar); //lo añadimos al panel
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 2;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,5,0); // deja una separacion a la izquierda 
        pnlFecha.add(pnlCambioFecha,gbc);   // lo añadimos al panel superior

        // panel que muestra la disposicion de los dias de un mes
        JPanel pnlMes = this.establecerMes();   // creamos el mes referido al mes y año activos
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 2;
        gbc.gridheight = 4;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,20,0); // deja una separacion a la izquierda
        pnlFecha.add(pnlMes,gbc);   // lo añadimos a un panel 
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 6;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,20,0); // deja una separacion a la izquierda
        pnlPrincipal.add(pnlFecha,gbc); // lo añadimos al panel principal

        // panel de botones
        JPanel pnlBotones = new JPanel(new GridLayout(4, 1));  // creamos el panel
        // boton crear tutoria
        JButton btnCrearTutoria = new JButton("Crear Tutoria"); // creamos el boton para crear tutorias
        Fuente.setFuente(btnCrearTutoria);  // cambiamos la fuente
        btnCrearTutoria.setOpaque(false);   // cambiamos la opacidad
        Colores.setBGTransparente(btnCrearTutoria); // establecemos el color
        pnlBotones.add(btnCrearTutoria);    // lo añadimos a un panel 

        //boton eliminar tutoria
        JButton btnEliminarTutoria = new JButton("Eliminar Tutoria"); // creamos el boton para eliminar tutorias
        Fuente.setFuente(btnEliminarTutoria);   // cambiamos la fuente
        btnEliminarTutoria.setOpaque(false);    // cambiamos la opacidad
        Colores.setBGTransparente(btnEliminarTutoria);  // establecemos el color
        pnlBotones.add(btnEliminarTutoria); // lo añadimos a un panel 

        //boton para cambiar el horario de un dia
        JButton btnCambiarHorario = new JButton("Cambiar Horario"); //  cremaos el boton para cambiar el horario
        Fuente.setFuente(btnCambiarHorario);    // cambiamos la fuente
        btnCambiarHorario.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnCambiarHorario);   //establecemos el color
        pnlBotones.add(btnCambiarHorario);      //& lo añadimos al panel 

        //boton para actualizar
        JButton btnActualizar = new JButton("Actualizar");  // creamos el boton actualizar
        Fuente.setFuente(btnActualizar);    // cambiamos la fuente
        btnActualizar.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnActualizar); // establecesmos el color
        pnlBotones.add(btnActualizar);  // lo añadimos a un panel 
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 6;
        gbc.gridheight = 4;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
        pnlPrincipal.add(pnlBotones,gbc);   // lo añadimos al panel principal
        this.add(pnlPrincipal);

        // panel para cambiar el horario
        JPanel pnlCambiarHorario = new JPanel(new GridLayout(7,1)); // creamos el panel para cambiar el horario

        //label titulo cambiar horario
        JPanel pnlTituloCambiarHorario = new JPanel (new GridBagLayout()); // creamos el panel que tiene el titulo
        

        JLabel lblCambiarHorario = new JLabel("Cambiar Horario"); // creamos el label cambiar horario
        Fuente.setFuenteNegrita(lblCambiarHorario); // cambiamos la fuente
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 3; // anchura
        gbc.insets = new Insets(0,0,0,100); // deja una separacion a la izquierda
        pnlTituloCambiarHorario.add(lblCambiarHorario,gbc); // lo añadimos al panel 

        JButton btnAtras = new JButton("Retroceder");
        try{
            btnAtras.setIcon(new ImageIcon(new URL("https://img.icons8.com/dusk/20/return.png"))); // se pone el icono al boton
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        btnAtras.setHorizontalTextPosition(SwingConstants.CENTER); // ponemos el texto en el centro
        btnAtras.setVerticalTextPosition(SwingConstants.BOTTOM); // ponemos el texto abajo
        btnAtras.setOpaque(false);
        Colores.setBGTransparente(btnAtras);
        btnAtras.setBorder(null);
        pnlTituloCambiarHorario.add(btnAtras);
        gbc.gridx = 3;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
        pnlCambiarHorario.add(pnlTituloCambiarHorario,gbc);

        // label dia
        JPanel pnlDia = new JPanel(new GridBagLayout());
        JLabel lblDia = new JLabel("Dia: ");
        Fuente.setFuente(lblDia);    //  cambiamos la fuente
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,0,50); // deja una separacion a la izquierda
        pnlDia.add(lblDia,gbc); // lo añadimos al panel 

        JPanel pnlDias = new JPanel(new GridLayout(1,5));
        JButton btnDiasSemana[] = new JButton[5];
        btnDiasSemana[0] = new JButton("L");
        btnDiasSemana[1] = new JButton("M");
        btnDiasSemana[2] = new JButton("X");
        btnDiasSemana[3] = new JButton("J");
        btnDiasSemana[4] = new JButton("V");
        
        Colores.setBGVerde(btnDiasSemana[0]);

        for (JButton i:btnDiasSemana)
            pnlDias.add(i);

        gbc.gridx = 1;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 3; // anchura
        gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
        pnlDia.add(pnlDias,gbc);
        
        
        // panel con la hora de inicio
        JPanel pnlHoraIni = new JPanel(new GridBagLayout());
        //label hora inicio
        JLabel lblHoraIni = new JLabel("Hora inicio: ");
        Fuente.setFuente(lblHoraIni);    //  cambiamos la fuente
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,0,30); // deja una separacion a la izquierda
        pnlHoraIni.add(lblHoraIni,gbc);
        //textfield con la hora de inicio
        JTextField txtHoraIni = new JTextField(10);
        gbc.gridx = 1;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 3; // anchura
        gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
        pnlHoraIni.add(txtHoraIni,gbc);
        
        // panel con la hora de fin
        JPanel pnlHoraFin = new JPanel(new GridBagLayout());
        //label hora inicio
        JLabel lblHoraFin = new JLabel("Hora fin: ");
        Fuente.setFuente(lblHoraFin);    //  cambiamos la fuente
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,0,55); // deja una separacion a la izquierda
        pnlHoraFin.add(lblHoraFin,gbc);
        //textfield con la hora de inicio
        JTextField txtHoraFin = new JTextField(10);
        gbc.gridx = 1;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 3; // anchura
        gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
        pnlHoraFin.add(txtHoraFin,gbc);
        

        //boton que sirve para agregar la hora al horario
        JButton btnAgregarHora = new JButton("Agregar Hora");  // creamos el boton actualizar
        Fuente.setFuente(btnAgregarHora);    // cambiamos la fuente
        btnAgregarHora.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnAgregarHora); // establecesmos el color
        btnAgregarHora.setBorder(null);
        
        //boton que sirve para eliminar la hora del horario
        JButton btnEliminarHora = new JButton("Eliminar Hora");  // creamos el boton actualizar
        Fuente.setFuente(btnEliminarHora);    // cambiamos la fuente
        btnEliminarHora.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnEliminarHora); // establecesmos el color
        btnEliminarHora.setBorder(null);
        
        //boton que sirve para guardar los cambios
        JButton btnGuardarCambiarHora = new JButton("Guardar Horario");  // creamos el boton actualizar
        Fuente.setFuente(btnGuardarCambiarHora);    // cambiamos la fuente
        btnGuardarCambiarHora.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnGuardarCambiarHora); // establecesmos el color
        btnGuardarCambiarHora.setBorder(null);
        
        
        // panel para crear una tutoria
        JPanel pnlCrearTutoria = new JPanel(new GridLayout(6,1)); // creamos el panel para cambiar el horario

        //label titulo crear tutoria
        JPanel pnlTituloCrearTutoria = new JPanel (new GridBagLayout()); // creamos el panel que tiene el titulo
        

        JLabel lblCrearTutoria = new JLabel("Crear Tutoria"); // creamos el label cambiar horario
        Fuente.setFuenteNegrita(lblCrearTutoria); // cambiamos la fuente
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 3; // anchura
        gbc.insets = new Insets(0,0,0,100); // deja una separacion a la izquierda
        pnlTituloCrearTutoria.add(lblCrearTutoria,gbc); // lo añadimos al panel 

        gbc.gridx = 3;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
        pnlCrearTutoria.add(pnlTituloCrearTutoria,gbc);

        // label informacion 
        JPanel pnlInformacion = new JPanel(new FlowLayout());
        JLabel lblInformacion= new JLabel("seleccionar arriba la fecha");
        Fuente.setFuente(lblInformacion);    //  cambiamos la fuente
        pnlInformacion.add(lblInformacion); // lo añadimos al panel 
        

        //boton que sirve para agregar la hora al horario
        JButton btnCrearCita = new JButton("Crear cita");  // creamos el boton actualizar
        Fuente.setFuente(btnCrearCita);    // cambiamos la fuente
        btnCrearCita.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnCrearCita); // establecesmos el color
        btnCrearCita.setBorder(null);

        //boton que sirve para agregar la hora al horario
        JButton btnGuardarCitas = new JButton("Guardar citas");  // creamos el boton actualizar
        Fuente.setFuente(btnGuardarCitas );    // cambiamos la fuente
        btnGuardarCitas .setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnGuardarCitas ); // establecesmos el color
        btnGuardarCitas .setBorder(null);
        
        // panel para crear una tutoria
        JPanel pnlEliminarTutoria = new JPanel(new GridLayout(6,1)); // creamos el panel para cambiar el horario

        //label titulo crear tutoria
        JPanel pnlTituloEliminarTutoria = new JPanel (new GridBagLayout()); // creamos el panel que tiene el titulo
        

        JLabel lblEliminarTutoria = new JLabel("Eliminar Tutoria"); // creamos el label cambiar horario
        Fuente.setFuenteNegrita(lblEliminarTutoria); // cambiamos la fuente
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 3; // anchura
        gbc.insets = new Insets(0,0,0,100); // deja una separacion a la izquierda
        pnlTituloEliminarTutoria.add(lblEliminarTutoria,gbc); // lo añadimos al panel 

        gbc.gridx = 3;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
        pnlEliminarTutoria.add(pnlTituloEliminarTutoria,gbc);

        //boton que sirbe para eliminar una cita
        JButton btnEliminarCita = new JButton("Eliminar cita");  // creamos el boton actualizar
        Fuente.setFuente(btnEliminarCita);    // cambiamos la fuente
        btnEliminarCita.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnEliminarCita); // establecesmos el color
        btnEliminarCita.setBorder(null);


        //Acciones de los botones
        
        btnRetroceder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (mesActivo == 0){
                    anioActivo--;
                    mesActivo = 11;
                }else{
                    mesActivo--;
                }
                diaActivo = 1;
                lblMes.setText(Fecha.getMesToString(mesActivo) + "  " + anioActivo);
                pnlFecha.removeAll();
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 0;
                gbc.gridheight = 2;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,5,0); // deja una separacion a la izquierda 
                pnlFecha.add(pnlCambioFecha,gbc);   // lo añadimos al panel superior
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 2;
                gbc.gridheight = 4;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,20,0); // deja una separacion a la izquierda
                pnlFecha.add(establecerMes(),gbc);
                pnlFecha.updateUI();
                int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo));
                updateUI();
            }
        });

        btnAvanzar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (mesActivo == 11){
                    anioActivo++;
                    mesActivo = 0;
                }else{
                    mesActivo++;
                }
                diaActivo = 1;
                lblMes.setText(Fecha.getMesToString(mesActivo) + "  " + anioActivo);
                pnlFecha.removeAll();
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 0;
                gbc.gridheight = 2;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,5,0); // deja una separacion a la izquierda 
                pnlFecha.add(pnlCambioFecha,gbc);   // lo añadimos al panel superior
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 2;
                gbc.gridheight = 4;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,20,0); // deja una separacion a la izquierda
                pnlFecha.add(establecerMes(),gbc);
                pnlFecha.updateUI();
                int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo));
                updateUI();
            }
        });

        btnAtras.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pnlPrincipal.removeAll();
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 0;
                gbc.gridheight = 6;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,10,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlFecha,gbc); // lo añadimos al panel principal
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 6;
                gbc.gridheight = 4;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlBotones,gbc);   // lo añadimos al panel principal
                pnlPrincipal.updateUI();
            }
        });

        btnCrearTutoria.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pnlPrincipal.removeAll();
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 0;
                gbc.gridheight = 6;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,10,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlFecha,gbc); // lo añadimos al panel principal
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 6;
                gbc.gridheight = 4;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlCrearTutoria,gbc);
                pnlTituloCrearTutoria.add(btnAtras);
                pnlCrearTutoria.add(pnlInformacion);
                pnlCrearTutoria.add(pnlHoraIni);
                pnlCrearTutoria.add(pnlHoraFin);
                pnlCrearTutoria.add(btnCrearCita);
                pnlCrearTutoria.add(btnGuardarCitas);
                pnlPrincipal.updateUI();
            }
        });

        btnEliminarTutoria.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pnlPrincipal.removeAll();
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 0;
                gbc.gridheight = 6;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,10,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlFecha,gbc); // lo añadimos al panel principal
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 6;
                gbc.gridheight = 4;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlEliminarTutoria,gbc);
                pnlTituloEliminarTutoria.add(btnAtras);
                pnlEliminarTutoria.add(pnlInformacion);
                pnlEliminarTutoria.add(pnlHoraIni);
                pnlEliminarTutoria.add(btnEliminarCita);
                pnlEliminarTutoria.add(btnGuardarCitas);
                pnlPrincipal.updateUI();
            }
        });

        btnCambiarHorario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pnlPrincipal.removeAll();
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 0;
                gbc.gridheight = 6;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,10,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlFecha,gbc); // lo añadimos al panel principal
                gbc.gridx = 0;       //se especifica la posicion en la matriz
                gbc.gridy = 6;
                gbc.gridheight = 4;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlCambiarHorario,gbc);
                pnlTituloCambiarHorario.add(btnAtras);
                pnlCambiarHorario.add(pnlDia);
                pnlCambiarHorario.add(pnlHoraIni);
                pnlCambiarHorario.add(pnlHoraFin);
                pnlCambiarHorario.add(btnAgregarHora);
                pnlCambiarHorario.add(btnEliminarHora);
                pnlCambiarHorario.add(btnGuardarCambiarHora);
                pnlPrincipal.updateUI();
            }
        });

        for (int i = 0; i< btnDiasSemana.length;i++){
            int dia = i;
            btnDiasSemana[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    Colores.setBGAzul(btnDiasSemana[diaSemana]);
                    diaSemana = dia;
                    Colores.setBGVerde(btnDiasSemana[diaSemana]);
                    updateUI();
                }
            });
        }
        
    }

    

    private JPanel establecerMes(){

        JPanel pnlMes = new JPanel(new FlowLayout());
        JPanel pnlDias = new JPanel(new GridLayout(Fecha.getSemanasMes(mesActivo, anioActivo) + 1, 7));
        JLabel txtDias[] = new JLabel[7];
        JButton btnDias[] = new JButton[Fecha.getUltimoDiaMes(mesActivo, anioActivo)];

        txtDias[0] = new JLabel("L", SwingConstants.CENTER);
        txtDias[1] = new JLabel("M", SwingConstants.CENTER);
        txtDias[2] = new JLabel("X", SwingConstants.CENTER);
        txtDias[3] = new JLabel("J", SwingConstants.CENTER);
        txtDias[4] = new JLabel("V", SwingConstants.CENTER);
        txtDias[5] = new JLabel("S", SwingConstants.CENTER);
        txtDias[6] = new JLabel("D", SwingConstants.CENTER);

        for (int i = 0; i < 7; i++) {
            txtDias[i].setOpaque(true);
            Colores.setBGAzul(txtDias[i]);
            pnlDias.add(txtDias[i]);
        }

        for (int i = 1; i < Fecha.getUltimoDiaMes(mesActivo, anioActivo) + 1; i++) {
            btnDias[i - 1] = new JButton(String.valueOf(i));
        }
        
        
        int primerDiaMes = Fecha.getPrimerDiaMes(mesActivo, anioActivo);
        int dia=1;
        for (int i= 0; i<Fecha.getSemanasMes(mesActivo, anioActivo); i++)
            for (int j = 0; j<7; j++){
                if( i== 0 && j < primerDiaMes){
                    JLabel txtVacio = new JLabel();
                    txtVacio.setOpaque(true);
                    Colores.setBGCarne(txtVacio);
                    pnlDias.add(txtVacio);
                }
                else{
                    if(dia <= Fecha.getUltimoDiaMes(mesActivo,anioActivo)){
                        if (diaActivo == dia){
                            Colores.setBGVerde(btnDias[dia-1]);
                            pnlDias.add(btnDias[dia-1]);
                            dia++;
                        }
                        else{
                            Colores.setBGCarne(btnDias[dia-1]);
                            pnlDias.add(btnDias[dia-1]);
                            dia++;
                        }
                    }
                    else{
                        JLabel txtVacio = new JLabel();
                        txtVacio.setOpaque(true);
                        Colores.setBGCarne(txtVacio);
                        pnlDias.add(txtVacio);
                    }
                    
                }
               
            }
        pnlMes.add(pnlDias);
        
        //acciones de los botones de los dias
        for (JButton btnDia: btnDias){
            btnDia.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    Colores.setBGCarne(btnDias[diaActivo-1]);
                    diaActivo = Integer.valueOf(btnDia.getText());
                    Colores.setBGVerde(btnDias[diaActivo-1]);
                    int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                    PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo));
                    updateUI();
                }
            });
        }
        return pnlMes;
    }
}
