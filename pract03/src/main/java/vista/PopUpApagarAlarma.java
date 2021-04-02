package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PopUpApagarAlarma extends JFrame {

	private JPanel contentPane;
	private JTextField txtAlarmaSonando;
	private JButton btnApagar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpApagarAlarma frame = new PopUpApagarAlarma();
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
	public PopUpApagarAlarma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtAlarmaSonando = new JTextField();
		txtAlarmaSonando.setBounds(101, 78, 227, 20);
		panel.add(txtAlarmaSonando);
		txtAlarmaSonando.setColumns(10);
		
		btnApagar = new JButton("APAGAR");
		btnApagar.setBounds(164, 136, 89, 23);
		panel.add(btnApagar);
	}
	
	/**
	 * Gestionador del boton "apagar alarma"
	 * @param listenForNewAlarm
	 */
	public void apagaAlarmaListener (ActionListener listenForNewAlarm) {
		btnApagar.addActionListener(listenForNewAlarm);
	}
}
