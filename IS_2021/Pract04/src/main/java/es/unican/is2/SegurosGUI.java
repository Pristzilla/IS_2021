package es.unican.is2;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.Calendar;
import java.util.Date;

import es.unican.is2.Cliente;
import es.unican.is2.Seguro;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class SegurosGUI extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnMinusvalia;
	private JTextField txtFechaUltimoSiniestro;
	private JButton btnCalcular;
	private JComboBox comboBoxCobertura;
	private JTextField textFieldPrecio;
	private JEditorPane editorPanePotencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SegurosGUI frame = new SegurosGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SegurosGUI() {
		setTitle("Calcular precio seguro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSueldo = new JLabel("PRECIO");
		lblSueldo.setBounds(85, 257, 81, 17);
		contentPane.add(lblSueldo);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setName("textFieldPrecio");//nombre
		textFieldPrecio.setBounds(180, 252, 235, 26);
		contentPane.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);

		txtFechaUltimoSiniestro = new JTextField();
		txtFechaUltimoSiniestro.setText("dd/mm/yyyy");
		txtFechaUltimoSiniestro.setName("txtFechaUltimoSiniestro");
		txtFechaUltimoSiniestro.setBounds(124, 8, 86, 20);
		contentPane.add(txtFechaUltimoSiniestro);
		txtFechaUltimoSiniestro.setColumns(10);
		
		btnCalcular = new JButton("CALCULAR");
		btnCalcular.setName("btnCalcular"); //nombre boton
		btnCalcular.setToolTipText("");
		btnCalcular.addActionListener(new ActionListener() {
			//accion que toma lugar al pulsar el boton
			public void actionPerformed(ActionEvent arg0) {
				
				//recogemos la fecha insertada en la interfaz
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								
				//recogemos el tipo de cobertura
				Seguro.Cobertura cobertura;
				String c = (String) comboBoxCobertura.getSelectedItem();
				if (c.equals("TODO_RIESGO")) {
					cobertura = Seguro.Cobertura.TODORIESGO;
					
				} else if (c.equals("TERCEROS_LUNAS")){
					cobertura = Seguro.Cobertura.TERCEROSLUNAS;
					
				} else {
					cobertura = Seguro.Cobertura.TERCEROS;
				}					
				System.out.println ("La cobertura seleccionada es: "+ c); //comprobamos en consola cobertura

				/*recogemos potencia:
				 * Opcion 1: try-catch aqui y para intentar convertirlo a int
				 * Opcion 2: convertirlo a int luego en el try-catch ultimo
				 * */
				int potencia = -1;
				try {
					potencia = Integer.parseInt(editorPanePotencia.getText());
				
				} catch (Exception e) {}
								
				// recogemos minusvalia
				Boolean minusvalia = rdbtnMinusvalia.isSelected();
				
				//Intentamos mostrar el precio en la interfaz
				try {
					LocalDate fechaUltimoSiniestro = LocalDate.parse(txtFechaUltimoSiniestro.getText(), formatter);
					Cliente cliente = new Cliente("nombreTest", "123456789", minusvalia);
					Seguro seguro = new Seguro(potencia, cliente, cobertura);
					seguro.setFechaUltimoSiniestro(fechaUltimoSiniestro);
					Double precioSeguro = seguro.precio();
					textFieldPrecio.setText(Double.toString(precioSeguro)); //escribe el precio por pantalla
					
				} catch (Seguro.DatoIncorrectoException e) {
					textFieldPrecio.setText("¡Dato de entrada incorrecto!");
					
				} catch (DateTimeParseException e) {
					textFieldPrecio.setText("¡Formato de fecha no valido!");
				}
			}
		});
		btnCalcular.setBounds(228, 200, 141, 40);
		contentPane.add(btnCalcular);
		
		rdbtnMinusvalia = new JRadioButton("Minusvalia");
		rdbtnMinusvalia.setName("rdbtnMinusvalia"); //nombre radio button
		rdbtnMinusvalia.setBounds(346, 144, 141, 23);
		contentPane.add(rdbtnMinusvalia);

		
		comboBoxCobertura = new JComboBox();
		comboBoxCobertura.setName("comboBoxCobertura"); //nombre
		comboBoxCobertura.setModel(new DefaultComboBoxModel(new String[] {"TODO_RIESGO", "TERCEROS_LUNAS", "TERCEROS_SIMPLE"}));
		comboBoxCobertura.setBounds(157, 79, 141, 27);
		contentPane.add(comboBoxCobertura);
		
		JLabel lblCobertura = new JLabel("Cobertura");
		lblCobertura.setBounds(31, 84, 114, 14);
		contentPane.add(lblCobertura);
		
		JLabel lblPotencia = new JLabel("Potencia");
		lblPotencia.setBounds(31, 148, 114, 17);
		contentPane.add(lblPotencia);
		
		editorPanePotencia = new JEditorPane();
		editorPanePotencia.setName("editorPanePotencia"); //nombre
		editorPanePotencia.setToolTipText("Inserta la potencia del vehiculo");
		editorPanePotencia.setBounds(157, 144, 136, 16);
		contentPane.add(editorPanePotencia);
		
		JLabel lblUltimoSiniestro = new JLabel("Ultimo Siniestro");
		lblUltimoSiniestro.setBounds(22, 29, 114, 17);
		contentPane.add(lblUltimoSiniestro);
		
	}
}