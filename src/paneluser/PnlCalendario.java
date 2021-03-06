package paneluser;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.text.DateFormatter;

import dominio.Alumno;
import dominio.Profesor;
import dominio.Usuario;
import gui.JVentana;
import panelAlu.PnlAlumno;
import panelAlu.PnlReserva;
import panelProf.PnlProf;
import server.Fachada;
import util.Colores;
import util.Fecha;
import util.Fuente;

public class PnlCalendario extends JPanel {

    private Usuario usuario;
    private Profesor profesor;
    private static int diaActivo = Fecha.getDia();
    private static int mesActivo = Fecha.getMes();
    private static int anioActivo = Fecha.getAnio();
    private static int diaSemana = 0;
    private static int horaIni[] = new int[2];
    private static int horaFin[] = new int[2];
    private static String horario = null;
    private SpinnerDateModel spnDMHIni = new SpinnerDateModel();
    private SpinnerDateModel spnDMHFin = new SpinnerDateModel();
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

    public PnlCalendario(Usuario usuario, Profesor profesor) {
        this(usuario);
        this.profesor = profesor;
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
        if (usuario instanceof Alumno)
            btnCrearTutoria.setText("Crear cita");
        Fuente.setFuente(btnCrearTutoria);  // cambiamos la fuente
        btnCrearTutoria.setOpaque(false);   // cambiamos la opacidad
        Colores.setBGTransparente(btnCrearTutoria); // establecemos el color
        pnlBotones.add(btnCrearTutoria);    // lo añadimos a un panel 

        //boton eliminar tutoria
        JButton btnEliminarTutoria = new JButton("Eliminar Tutoria"); // creamos el boton para eliminar tutorias
        if (usuario instanceof Alumno)
            btnEliminarTutoria.setText("Eliminar cita");
        Fuente.setFuente(btnEliminarTutoria);   // cambiamos la fuente
        btnEliminarTutoria.setOpaque(false);    // cambiamos la opacidad
        Colores.setBGTransparente(btnEliminarTutoria);  // establecemos el color
        pnlBotones.add(btnEliminarTutoria); // lo añadimos a un panel 

        //boton para cambiar el horario de un dia
        JButton btnCambiarHorario = new JButton("Cambiar Horario"); //  cremaos el boton para cambiar el horario
        Fuente.setFuente(btnCambiarHorario);    // cambiamos la fuente
        btnCambiarHorario.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnCambiarHorario);   //establecemos el color
        if (usuario instanceof Profesor)
            pnlBotones.add(btnCambiarHorario);      //& lo añadimos al panel 

        //boton para actualizar
        JButton btnActualizar = new JButton("Actualizar");  // creamos el boton actualizar
        Fuente.setFuente(btnActualizar);    // cambiamos la fuente
        btnActualizar.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnActualizar); // establecesmos el color
        pnlBotones.add(btnActualizar);  // lo añadimos a un panel 
        

        //boton para regresar siendo un alumno
        JButton btnAlumno = new JButton("Regresar");  // creamos el boton actualizar
        Fuente.setFuente(btnAlumno);    // cambiamos la fuente
        btnAlumno.setOpaque(false);     // cambiamos la opacidad
        Colores.setBGTransparente(btnAlumno); // establecesmos el color
        if (usuario instanceof Alumno)
            pnlBotones.add(btnAlumno);
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

        //JSpinner horaini
        JSpinner spnHoraIni = new JSpinner(spnDMHIni);
        JSpinner.DateEditor editorHIni = new JSpinner.DateEditor(spnHoraIni, "HH:mm");
        DateFormatter formatterHIni= (DateFormatter) editorHIni.getTextField().getFormatter();
        formatterHIni.setAllowsInvalid(false);
        formatterHIni.setOverwriteMode(true);
        spnHoraIni.setEditor(editorHIni);
        spnHoraIni.setOpaque(true);
        Fuente.setFuente(spnHoraIni);
        gbc.gridx = 1;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 3; // anchura
        gbc.insets = new Insets(0,0,0,0); // deja una separacion 
        pnlHoraIni.add(spnHoraIni,gbc);
        
        // panel con la hora de fin
        JPanel pnlHoraFin = new JPanel(new GridBagLayout());
        //label hora inicio
        JLabel lblHoraFin = new JLabel("Hora fin: ");
        Fuente.setFuente(lblHoraFin);    //  cambiamos la fuente
        gbc.gridx = 0;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 1; // anchura
        gbc.insets = new Insets(0,0,0,55); // deja una separacion 
        pnlHoraFin.add(lblHoraFin,gbc);

        //JSpinner horafin
        JSpinner spnHoraFin = new JSpinner(spnDMHFin);
        JSpinner.DateEditor editorHFin = new JSpinner.DateEditor(spnHoraFin, "HH:mm");
        DateFormatter formatterHFin= (DateFormatter) editorHFin.getTextField().getFormatter();
        formatterHFin.setAllowsInvalid(false);
        formatterHFin.setOverwriteMode(true);
        spnHoraFin.setEditor(editorHFin);
        spnHoraFin.setOpaque(true);
        Fuente.setFuente(spnHoraFin);
        gbc.gridx = 1;       //se especifica la posicion en la matriz
        gbc.gridy = 0;
        gbc.gridheight = 1;	// altura
        gbc.gridwidth = 3; // anchura
        gbc.insets = new Insets(0,0,0,0); // deja una separacion
        pnlHoraFin.add(spnHoraFin,gbc);
        

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
        if (usuario instanceof Alumno)
            lblCrearTutoria.setText("Crear Cita");
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
        
        // panel para crear una tutoria
        JPanel pnlEliminarTutoria = new JPanel(new GridLayout(6,1)); // creamos el panel para cambiar el horario

        //label titulo crear tutoria
        JPanel pnlTituloEliminarTutoria = new JPanel (new GridBagLayout()); // creamos el panel que tiene el titulo
        

        JLabel lblEliminarTutoria = new JLabel("Eliminar Tutoria"); // creamos el label cambiar horario
        if (usuario instanceof Alumno)
            lblEliminarTutoria.setText("Eliminar Cita");
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
                if (usuario instanceof Profesor)
                    PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                else{
                    PnlReserva.pnlReserva.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                }
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
                if (usuario instanceof Profesor)
                    PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                else{
                    PnlReserva.pnlReserva.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                }
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
                gbc.gridheight = 8;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlCrearTutoria,gbc);
                pnlTituloCrearTutoria.add(btnAtras);
                pnlCrearTutoria.add(pnlInformacion);
                pnlCrearTutoria.add(pnlHoraIni);
                pnlCrearTutoria.add(pnlHoraFin);
                pnlCrearTutoria.add(btnCrearCita);
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
                gbc.gridheight = 8;	// altura
                gbc.gridwidth = 1; // anchura
                gbc.insets = new Insets(0,0,0,0); // deja una separacion a la izquierda
                pnlPrincipal.add(pnlEliminarTutoria,gbc);
                pnlTituloEliminarTutoria.add(btnAtras);
                pnlEliminarTutoria.add(pnlInformacion);
                pnlEliminarTutoria.add(pnlHoraIni);
                pnlEliminarTutoria.add(btnEliminarCita);
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
                gbc.gridheight = 8;	// altura
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
                horario = Fachada.getHorario(usuario.getId());
            }
        });

        btnCrearCita.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                horaIni[0] = Fecha.getHora(spnDMHIni.getDate());
                horaIni[1] = Fecha.getMinuto(spnDMHIni.getDate());
                horaFin[0] = Fecha.getHora(spnDMHFin.getDate());
                horaFin[1] = Fecha.getMinuto(spnDMHFin.getDate());
                if (usuario instanceof Profesor){
                    Fachada.crearTutoria((Profesor)usuario,Fecha.setDate(anioActivo, mesActivo, diaActivo, horaIni[0], horaIni[1]),
                                            Fecha.setDate(anioActivo, mesActivo, diaActivo, horaFin[0], horaFin[1]));
                    //Actualizamos panel horario
                    int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                    PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                } else{
                    Fachada.crearCita(profesor,(Alumno)usuario,Fecha.setDate(anioActivo, mesActivo, diaActivo, horaIni[0], horaIni[1]),
                                            Fecha.setDate(anioActivo, mesActivo, diaActivo, horaFin[0], horaFin[1]));
                    //Actualizamos panel horario
                    int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                    PnlReserva.pnlReserva.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                }
                
            }
        });

        btnEliminarCita.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                horaIni[0] = Fecha.getHora(spnDMHIni.getDate());
                horaIni[1] = Fecha.getMinuto(spnDMHIni.getDate());
                horaFin[0] = Fecha.getHora(spnDMHFin.getDate());
                horaFin[1] = Fecha.getMinuto(spnDMHFin.getDate());
                
                if (usuario instanceof Profesor){
                    Fachada.eliminarCitaProfesor((Profesor)usuario,Fecha.setDate(anioActivo, mesActivo, diaActivo, horaIni[0], horaIni[1]));
                    //Actualizamos panel horario
                    int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                    PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                } else{
                    Fachada.eliminarCitaAlumno((Alumno)usuario,Fecha.setDate(anioActivo, mesActivo, diaActivo, horaIni[0], horaIni[1]));
                    //Actualizamos panel horario
                    int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                    PnlReserva.pnlReserva.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                }
            }
        });

        btnAgregarHora.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                boolean repetido = false;
                boolean satisfactorio = true;
                horaIni[0] = Fecha.getHora(spnDMHIni.getDate());
                horaIni[1] = Fecha.getMinuto(spnDMHIni.getDate());
                horaFin[0] = Fecha.getHora(spnDMHFin.getDate());
                horaFin[1] = Fecha.getMinuto(spnDMHFin.getDate());
                String dias[] = horario.split(";");
                String horas[] = dias[diaSemana].split(",");
                int horaini = horaIni[0]*60; 
                horaini = horaini + horaIni[1];
                for (int i=1;i<horas.length;i++){
                    String clases[] = horas[i].split("-");
                    int hora = Integer.valueOf(clases[0].split(":")[0])* 60 + Integer.valueOf(clases[0].split(":")[1]);
                    if (horaini == hora)
                        repetido = true;
                }

                if(diaSemana<5){
                    int citaTiempoIni=horaIni[0]*60+horaIni[1];
                    int citaTiempoFin=horaFin[0]*60+horaFin[1];
                    for (int i=1; i< horas.length ;i++){ // bucle que rrecore las clases del dia
                        int horainicio = Integer.valueOf(String.valueOf(horas[i].charAt(0)) + String.valueOf(horas[i].charAt(1))); // variable que almacena la hora de inicio de la clase
                        int minutosini = Integer.valueOf(String.valueOf(horas[i].charAt(3)) + String.valueOf(horas[i].charAt(4))); // variable que almacena los minutos de inicio de la clase
                        int horafin = Integer.valueOf(String.valueOf(horas[i].charAt(6)) + String.valueOf(horas[i].charAt(7))); // variable que almacena la hora de fin de la clase
                        int minutosfin = Integer.valueOf(String.valueOf(horas[i].charAt(9)) + String.valueOf(horas[i].charAt(10))); // variable que almacena los minutos de fin de la clase
        
                        int tiempoIni=horainicio*60+minutosini;
                        int timepoFin=horafin*60+minutosfin;
        
                        if(tiempoIni>citaTiempoIni && tiempoIni>=citaTiempoFin){
        
                        } else if(timepoFin<=citaTiempoIni){
        
                        } else {
                            satisfactorio = false;
                        }
                    }
                }

                if (!repetido && satisfactorio){
                    if (horaIni[0] < 10){
                        dias[diaSemana] = dias[diaSemana] + ",0" + horaIni[0];
                    } else {
                        dias[diaSemana] = dias[diaSemana] + "," + horaIni[0];
                    }
                    if (horaIni[1] < 10){
                        dias[diaSemana] = dias[diaSemana] + ":0" + horaIni[1];
                    } else {
                        dias[diaSemana] = dias[diaSemana] + ":" + horaIni[1];
                    }
                    dias[diaSemana] = dias[diaSemana] + "-";
                    if (horaFin[0] < 10){
                        dias[diaSemana] = dias[diaSemana] + "0" + horaFin[0];
                    } else {
                        dias[diaSemana] = dias[diaSemana] + horaFin[0];
                    }
                    if (horaFin[1] < 10){
                        dias[diaSemana] = dias[diaSemana] + ":0" + horaFin[1];
                    } else {
                        dias[diaSemana] = dias[diaSemana] + ":" + horaFin[1];
                    }
                }
                String realizar = dias[0];
                for (int i=1; i< dias.length; i++)
                    realizar = realizar + ";" + dias[i];
                
                horario = realizar;
                
            }
        });

        btnEliminarHora.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                horaIni[0] = Fecha.getHora(spnDMHIni.getDate());
                horaIni[1] = Fecha.getMinuto(spnDMHIni.getDate());
                horaFin[0] = Fecha.getHora(spnDMHFin.getDate());
                horaFin[1] = Fecha.getMinuto(spnDMHFin.getDate());
                String dias[] = horario.split(";");
                String horas[] = dias[diaSemana].split(",");
                int horaini = horaIni[0]*60; 
                horaini = horaini + horaIni[1];
                String cambio = horas[0];
                for (int i=1;i<horas.length;i++){
                    String clases[] = horas[i].split("-");
                    int hora = Integer.valueOf(clases[0].split(":")[0])* 60 + Integer.valueOf(clases[0].split(":")[1]);
                    if (horaini != hora)
                        cambio = cambio + "," +horas[i];
                }
                dias[diaSemana] = cambio;
                String realizar = dias[0];
                for (int i=1; i< dias.length; i++)
                    realizar = realizar + ";" + dias[i];
                horario = realizar;
            }
        });

        btnActualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                if (usuario instanceof Profesor){
                    //Actualizamos panel horario
                    PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                } else{
                    //Actualizamos panel horario
                    PnlReserva.pnlReserva.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                }
                updateUI();
            }
        });

        btnGuardarCambiarHora.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Fachada.setHorario(usuario.getId(), horario);
                System.out.println(horario);
                int dia= Fecha.getDiaSemana(diaActivo, mesActivo, anioActivo);
                PnlHorario.pnlHorario.actualizarHorarioCache();
                PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                updateUI();
            }
        });

        btnAlumno.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JVentana.cambiarPanel(PnlAlumno.PnlAlumno);     // LLamada a la funcion para la vuelta al panel anterior
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
                    if (usuario instanceof Profesor)
                        PnlProf.pnlProf.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                    else{
                        PnlReserva.pnlReserva.setPnlHorario(dia, Fecha.fechaString(diaActivo, mesActivo, anioActivo), anioActivo, mesActivo, diaActivo);
                    }
                    updateUI();
                }
            });
        }
        return pnlMes;
    }

    
}
