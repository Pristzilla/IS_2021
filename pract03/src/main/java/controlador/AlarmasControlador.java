package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.Alarma;
import modelo.Alarmas;
import vista.MainWindow;
import vista.PopUpApagarAlarma;

public class AlarmasControlador {
	
	private Alarmas modelo;
	private MainWindow vista1;
	private PopUpApagarAlarma vista2;
	
	public AlarmasControlador(Alarmas m, MainWindow v1, PopUpApagarAlarma v2) {
		modelo = m;
		vista1 = v1;
		vista2 = v2;
		
		/*Indica que cuando se pulse el boton de anhadir alarma,
		 * se ejecutara la operacion desde la clase interna NuevaAlarmaListener.*/
		vista1.addAnhadeAlarmaListener(new NuevaAlarmaListener());	
		
		/*Cuando se pulse el boton de elimina Alarma, se
		 * ejecutara¡ la operacion desde la clase interna EliminaAlarmaListener*/
		vista1.addEliminaAlarmaListener(new EliminaAlarmaListener());
		
		/*Cuandos se pulse el boton de activa Alarma, se 
		 * ejecutara al operacion desde la clase interna AlarmaOnListener*/
		vista1.addAlarmaOnListener(new AlarmaOnListener());
		
		/*Cuandos se pulse el boton de activa Alarma, se 
		 * ejecutara al operacion desde la clase interna AlarmaOnListener*/
		vista1.addAlarmaOffListener(new AlarmaOffListener());
		
		vista2.addApagaAlarmaListener(new ApagaAlarmaListener());
	}
	
	/**
	 * Permite anhadir una nueva alarma al darle al boton correspondiente. Su nombre
	 * y hora apareceran en el recuadro de "alarmas activas".
	 */
	public class NuevaAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			String nombreAlarma = vista1.getNombreAlarma();
			
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
			Date fechaAlarma = null;
			try {
				fechaAlarma = sdf.parse("2020-04-3");
			} catch (ParseException e) {
				System.out.println("Error en formato de fecha.");
				e.printStackTrace();
			}
			
			Alarma alarma = new Alarma(nombreAlarma, (Date) vista1.getSpinner().getValue());
			vista1.MuestraInformacionAlarmaActiva(alarma);
			modelo.anhadeAlarma(alarma);
			modelo.nuevaAlarma(nombreAlarma, fechaAlarma);
		}
	}
	
	/**
	 * Permite eliminar la alarma seleccionada. Nota: su nombre no desaparece de la vista por simplicidad.
	 * @author Sara
	 *
	 */
	public class EliminaAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
		
			String nombreAlarma = vista1.getAlarmaActivaSelected();
			if (nombreAlarma.equals(null)) { // Si no esta en activas, esta en desactivadas
				nombreAlarma = vista1.getAlarmaDesactivadaSelected();
			}
			Alarma alarma = modelo.buscaAlarmaByID(nombreAlarma);
			modelo.eliminaAlarma(alarma);
			modelo.borraAlarma(nombreAlarma); // TODO esta bien?
		}
	}
	
	/**
	 * Activa una alarma segun el nombre seleccionado.
	 *
	 */
	public class AlarmaOnListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			String nombreAlarma = vista1.getAlarmaActivaSelected();
			if (nombreAlarma.equals(null)) { // Si no esta en activas, esta en desactivadas
				nombreAlarma = vista1.getAlarmaDesactivadaSelected();
			}
			modelo.alarmaOn(nombreAlarma);
		}
	}
	
	/**
	 * Desctiva una alarma segun el nombre seleccionado.
	 *
	 */
	public class AlarmaOffListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			String nombreAlarma = vista1.getAlarmaActivaSelected();
			if (nombreAlarma.equals(null)) { // Si no esta en activas, esta en desactivadas
				nombreAlarma = vista1.getAlarmaDesactivadaSelected();
			}
			modelo.alarmaOff(nombreAlarma);
		}
	}
	
	/**
	 * Apaga una alarma
	 *
	 */
	public class ApagaAlarmaListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			vista2.dispose(); // Cierro el PopUp
			modelo.apagar(); // TODO bien hecho?
		}
	}
}
