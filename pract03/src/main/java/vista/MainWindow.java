package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import modelo.Alarma;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;

// TOOD: MOVER A CLASE A PARTE?
public interface SelectionListener {
	public void widgetSelected(SelectionEvent ev); // TODO error selectionEvent?
}

public abstract class Widget {
	public void addSelectionListener(SelectionListener sl) {}
}

public class MainWindow {

	private JFrame frame;
	private JTextField txtNombreAlarma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		JSpinner spinnerHora = new JSpinner();
		spinnerHora.setBounds(97, 126, 107, 20);
		panel.add(spinnerHora);
		
		JButton btnAnhadir = new JButton("A\u00F1adir");
		btnAnhadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombre = txtNombreAlarma.getText();
				//String hora = spinnerHora.toString();
				
			}
		});
		btnAnhadir.setBounds(98, 216, 89, 23);
		panel.add(btnAnhadir);
		
		// Seccion derecha - modificar alarma existente
		JLabel lblModificarAlarma = new JLabel("Modificar alarma");
		lblModificarAlarma.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModificarAlarma.setBounds(379, 32, 110, 14);
		panel.add(lblModificarAlarma);
		
		JLabel lblAlarmasActivas = new JLabel("Alarmas activas");
		lblAlarmasActivas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlarmasActivas.setBounds(312, 81, 103, 14);
		panel.add(lblAlarmasActivas);
		
		JTextArea txtAreaAlarmasAct = new JTextArea();
		txtAreaAlarmasAct.setBounds(295, 106, 133, 99);
		panel.add(txtAreaAlarmasAct);
		
		JButton btnOn = new JButton("ON");
		btnOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnOn.setBounds(503, 216, 60, 23);
		panel.add(btnOn);
		
		JLabel lblAlarmasDesact = new JLabel("Alarmas desactivadas");
		lblAlarmasDesact.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlarmasDesact.setBounds(465, 81, 133, 14);
		panel.add(lblAlarmasDesact);
		
		JTextArea txtAreaAlarmasDesact = new JTextArea();
		txtAreaAlarmasDesact.setBounds(462, 106, 133, 99);
		panel.add(txtAreaAlarmasDesact);
		
		JButton btnOff = new JButton("OFF");
		btnOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnOff.setBounds(335, 216, 60, 23);
		panel.add(btnOff);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnEliminar.setBounds(401, 260, 89, 23);
		panel.add(btnEliminar);
		
	}
}
