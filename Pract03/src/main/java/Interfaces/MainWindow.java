package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class MainWindow {

	private JFrame frame;

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
		frame.setBounds(100, 100, 530, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Panel principal
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNuevaAlarma = new JLabel("Nueva alarma");
		lblNuevaAlarma.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNuevaAlarma.setBounds(71, 32, 90, 14);
		panel.add(lblNuevaAlarma);
		
		JLabel lblModificarAlarma = new JLabel("Modificar alarma");
		lblModificarAlarma.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModificarAlarma.setBounds(325, 32, 110, 14);
		panel.add(lblModificarAlarma);
		
		
	}
}
