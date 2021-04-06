package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import modelo.Alarma;

import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;


public class MainWindow {

	// Atributos
	private JFrame frame;
	private JTextField txtNombreAlarma;
	private JLabel lblAlarmasActivas;
	private JLabel lblAlarmasDesact;
	private JSpinner spinnerHora;
	private JButton btnAnhadir;
	private JButton btnOn;
	private JButton btnOff;
	private JButton btnEliminar;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 672, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Panel principal
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// Seccion izquierda - crear nueva alarma
		JLabel lblNuevaAlarma = new JLabel("Nueva alarma");
		lblNuevaAlarma.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNuevaAlarma.setBounds(97, 32, 90, 14);
		panel.add(lblNuevaAlarma);
		
		JLabel lblNombreAlarma = new JLabel("Nombre");
		lblNombreAlarma.setBounds(45, 84, 46, 14);
		panel.add(lblNombreAlarma);
		
		txtNombreAlarma = new JTextField();
		txtNombreAlarma.setBounds(97, 81, 107, 20);
		panel.add(txtNombreAlarma);
		txtNombreAlarma.setColumns(10);
		
		JLabel lblHoraAlarma = new JLabel("Hora");
		lblHoraAlarma.setBounds(45, 129, 46, 14);
		panel.add(lblHoraAlarma);
		
		
		
		
		//LO NUEVO
		/*
		Calendar calendar = Calendar.getInstance();
		Date initDate = calendar.getTime();
		calendar.add(Calendar.MINUTE, -1);
		Date earliestDate = calendar.getTime();
		calendar.add(Calendar.YEAR, 200);
		Date latestDate = calendar.getTime();
		SpinnerModel dateModel = new SpinnerDateModel(initDate,
		                             earliestDate,
		                             latestDate,
		                             Calendar.YEAR);
		
		
		spinnerHora = new JSpinner (dateModel);
		*/
		
		// ESTO ES LO QUE HABÍA ANTES
		Calendar cal = Calendar.getInstance();
		spinnerHora = new JSpinner(new SpinnerDateModel(cal.getTime(),null,null,Calendar.MINUTE));
		JSpinner.DateEditor editor = new JSpinner.DateEditor (spinnerHora, "hh:mm a ");
		spinnerHora.setEditor(editor);
		//*/
		spinnerHora.setBounds(97, 126, 107, 20);
		panel.add(spinnerHora);
		
		btnAnhadir = new JButton("A\u00F1adir");
		btnAnhadir.setBounds(98, 216, 89, 23);
		panel.add(btnAnhadir);
		
		// Seccion derecha - modificar alarma existente
		JLabel lblModificarAlarma = new JLabel("Modificar alarma");
		lblModificarAlarma.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModificarAlarma.setBounds(379, 32, 110, 14);
		panel.add(lblModificarAlarma);
		
		lblAlarmasActivas = new JLabel("Alarmas activas");
		lblAlarmasActivas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlarmasActivas.setBounds(312, 81, 103, 14);
		panel.add(lblAlarmasActivas);
		
		JTextArea txtAreaAlarmasAct = new JTextArea();
		txtAreaAlarmasAct.setBounds(295, 106, 133, 99);
		panel.add(txtAreaAlarmasAct);
		
		btnOn = new JButton("ON");
		btnOn.setBounds(503, 216, 60, 23);
		panel.add(btnOn);
		
		lblAlarmasDesact = new JLabel("Alarmas desactivadas");
		lblAlarmasDesact.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlarmasDesact.setBounds(465, 81, 133, 14);
		panel.add(lblAlarmasDesact);
		
		JTextArea txtAreaAlarmasDesact = new JTextArea();
		txtAreaAlarmasDesact.setBounds(462, 106, 133, 99);
		panel.add(txtAreaAlarmasDesact);
		
		btnOff = new JButton("OFF");
		btnOff.setBounds(335, 216, 60, 23);
		panel.add(btnOff);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(401, 260, 89, 23);
		panel.add(btnEliminar);
		
	}
	
	/**
	 * Gestionador del boton "anhadir alarma"
	 * @param listenForNewAlarm
	 */
	public void addAnhadeAlarmaListener (ActionListener listenForNewAlarm) {
		btnAnhadir.addActionListener(listenForNewAlarm);
	}
	
	/**
	 * Gestionador del boton "elimina alarma"
	 * @param listenforDeletedAlarm
	 */
	public void addEliminaAlarmaListener (ActionListener listenforDeletedAlarm) {
		btnEliminar.addActionListener(listenforDeletedAlarm);
	}
	
	/**
	 * Gestionador del boton "activa alarma"
	 */
	public void addAlarmaONListener (ActionListener listenforAlarmON) {
		btnEliminar.addActionListener(listenforAlarmON);
	}
	
	/**
	 * Gestionador del boton "desactiva alarma
	 * @param listenforDeletedAlarm
	 */
	public void addAlarmaOFFListener (ActionListener listenforAlarmOFF) {
		btnEliminar.addActionListener(listenforAlarmOFF);
	}
	
	
	/**
	 * @return el nombre de la alarma
	 */
	public String getNombreAlarma() {
		return txtNombreAlarma.getText();
	}
	
	/**
	 * @return el nombre de la alarma activa seleccionada
	 * TODO: como se cual esta seleccionada??
	 */
	public String getAlarmaActivaSelected() {
		return lblAlarmasActivas.getText(); 
	}
	
	/**
	 * @return el nombre de la alarma desactivada seleccionada
	 * TODO: como se cual esta seleccionada??
	 */
	public String getAlarmaDesactivadaSelected() {
		return lblAlarmasDesact.getText(); 
	}
	
	/**
	 * Muestra la informacion de la nueva alarma anhadida
	 * @param alarma alarma anhadida
	 */
	public void MuestraInformacionAlarma (Alarma alarma) {
		System.out.println ("Nombre: "+ alarma.getId()+"; Hora programada: "+ alarma.getHora());
	}
	
	/**
	 * @return devuelve el spinner de la hora
	 */
	public JSpinner getSpinner() {
		return  spinnerHora;
	}
	
	/**
	 * Pone la vista a visible
	 */
	public void setVisible() {
		frame.setVisible(true);
	}
}
