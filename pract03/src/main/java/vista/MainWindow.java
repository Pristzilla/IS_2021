package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import modelo.Alarma;
import javax.swing.JList;
import java.awt.event.ActionEvent;

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
		private JButton btnApagarAlarma;
		private DefaultListModel<String> modelActivas = new DefaultListModel<String>();
		private DefaultListModel<String> modelDesactivadas = new DefaultListModel<String>();

		//listas de alarmas activas y desactivadas de las que podemos seleccionar
		JList<String> listAlarmasActivas = new JList<String>(modelActivas);
		JList<String> listAlarmasDesactivadas = new JList<String>(modelDesactivadas);
		
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
			txtNombreAlarma.setBounds(97, 81, 123, 20);
			panel.add(txtNombreAlarma);
			txtNombreAlarma.setColumns(10);
			
			JLabel lblHoraAlarma = new JLabel("Hora");
			lblHoraAlarma.setBounds(45, 129, 46, 14);
			panel.add(lblHoraAlarma);
			
			
	
			
			//Gestión del spinner
			spinnerHora = new JSpinner(new SpinnerDateModel(new Date(),null,null,Calendar.MINUTE));
			JSpinner.DateEditor editor = new JSpinner.DateEditor (spinnerHora, "hh:mm a");
			spinnerHora.setEditor(editor);
			spinnerHora.setBounds(97, 126, 123, 20);
			panel.add(spinnerHora);
			
			btnAnhadir = new JButton("A\u00F1adir");
			btnAnhadir.setBounds(113, 175, 89, 23);
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
			
			
			listAlarmasActivas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listAlarmasActivas.setBounds(295, 106, 133, 99);
			panel.add(listAlarmasActivas);
			
			btnOn = new JButton("ON");
			btnOn.setBounds(503, 216, 60, 23);
			panel.add(btnOn);
			
			lblAlarmasDesact = new JLabel("Alarmas desactivadas");
			lblAlarmasDesact.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblAlarmasDesact.setBounds(465, 81, 133, 14);
			panel.add(lblAlarmasDesact);
			
			
			
			
			listAlarmasDesactivadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listAlarmasDesactivadas.setBounds(462, 106, 133, 99);
			panel.add(listAlarmasDesactivadas);
			
			btnOff = new JButton("OFF");
			btnOff.setBounds(335, 216, 60, 23);
			panel.add(btnOff);
			
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBounds(401, 260, 89, 23);
			panel.add(btnEliminar);			
			
			btnApagarAlarma = new JButton("APAGAR!");
			btnApagarAlarma.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			btnApagarAlarma.setBounds(74, 239, 174, 64);
			panel.add(btnApagarAlarma);
		}
		
		/**
		 * Gestionador del boton "anhadir alarma"
		 * @param listenForNewAlarm
		 */
		public void addAnhadeAlarmaListener (ActionListener listenForNewAlarm) {
			btnAnhadir.addActionListener(listenForNewAlarm);
		}
		
		/**
		 * Gestionador del boton "apagar alarma"
		 * @param listenforStoppedAlarm
		 */
		public void addApagarAlarmaListener (ActionListener listenforStoppedAlarm) {
			btnApagarAlarma.addActionListener(listenforStoppedAlarm);
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
		public void addAlarmaOnListener (ActionListener listenforAlarmOn) {
			btnOn.addActionListener(listenforAlarmOn);
		}
		
		/**
		 * Gestionador del boton "desactiva alarma
		 * @param listenforDeletedAlarm
		 */
		public void addAlarmaOffListener (ActionListener listenforAlarmOff) {
			btnOff.addActionListener(listenforAlarmOff);
		}
		
		/**
		 * @return el nombre de la alarma creada
		 */
		public String getNombreAlarma() {
			return txtNombreAlarma.getText();
		}
		
		/**
		 * @return el nombre de la alarma activa seleccionada
		 * TODO: como se cual esta seleccionada??
		 */
		public String getAlarmaActivaSelected() {
			return listAlarmasActivas.getSelectedValue(); 
		}
		
		/**
		 * permite anhadir el nombre de una alarma a activa
		 * @param nombreAlarma
		 */
		public void anhadeModelActivas(String nombreAlarma) {
			modelActivas.addElement(nombreAlarma);
			
		}
		/**
		 * permite anhadir el nombre de una alarma a desactivadas
		 * @param nombreAlarma
		 */
		public void anhadeModelDesactivadas (String nombreAlarma) {
			modelDesactivadas.addElement(nombreAlarma);
		}
		
		/**
		 * permite eliminar el nombre de una alarma de activas
		 * @param nombreAlarma
		 */
		public void eliminaModelActivas (String nombreAlarma) {
			modelActivas.removeElement(nombreAlarma);
		}
		
		/**
		 * permite eliminar el nombre de una alarma de desactivadas
		 * @param nombreAlarma
		 */
		public void eliminaModelDesactivadas (String nombreAlarma) {
			modelDesactivadas.removeElement(nombreAlarma);
		}
		

		
		/**
		 * @return el nombre de la alarma desactivada seleccionada
		 */
		public String getAlarmaDesactivadaSelected() {
			return listAlarmasDesactivadas.getSelectedValue();
		}
		
		/**
		 * A la hora de eliminar una alarma, nos conviene saber donde hemos
		 * hecho la seleccion porque si comparamos con null, nos sale una excepcion.
		 */
		public Boolean SelectionAlarmasActivasEmpty() {
			return listAlarmasActivas.isSelectionEmpty();
		}
		
		
		
		/**
		 * Muestra la informacion de la nueva alarma anhadida
		 * @param alarma alarma anhadida
		 */
		public void MuestraInformacionAlarmaActiva (Alarma alarma) {
			System.out.println ("Nombre: "+ alarma.getId()+"; Hora programada: "+ alarma.getHora());
			//modelActivas.addElement(alarma.getId());
		}
		
		/**
		 * Muestra la informacion de la alarma desactivada
		 * @param alarma alarma anhadida
		 * ELIMINAR
		 */
		public void MuestraInformacionAlarmaDeactivada (Alarma alarma) { 
			//modelDesactivadas.addElement(alarma.getId());
		}
		
		/**
		 * @return 
		 */
		public Date getFechaHoraAlarma() {
			Date fecha = new Date();
			Date aux = (Date) spinnerHora.getValue();
			fecha.setHours(aux.getHours());
			fecha.setMinutes(aux.getMinutes());
			fecha.setSeconds(0);
			
			return fecha;
			
		}
		
		/**
		 * Pone la vista a visible
		 */
		public void setVisible() {
			frame.setVisible(true);
		}
}
