package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

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
		
		/*
		 * Indica que cuando se pusle el boton de apagar alarma, 
		 * se ejecutaa la operacion desde la clase interna ApagaAlarmaListener()*/
		vista1.addApagarAlarmaListener(new ApagaAlarmaListener());
		
		/*Cuando se pulse el boton de elimina Alarma, se
		 * ejecutara la operacion desde la clase interna EliminaAlarmaListener*/
		vista1.addEliminaAlarmaListener(new EliminaAlarmaListener());
		
		/*Cuandos se pulse el boton de activa Alarma, se 
		 * ejecutara al operacion desde la clase interna AlarmaOnListener*/
		vista1.addAlarmaOnListener(new AlarmaOnListener());
		
		/*Cuandos se pulse el boton de activa Alarma, se 
		 * ejecutara al operacion desde la clase interna AlarmaOnListener*/
		vista1.addAlarmaOffListener(new AlarmaOffListener());
		
		//vista2.addApagaAlarmaListener(new ApagaAlarmaListener()); NO LO USAMOS
	}
	
	/**
	 * Permite anhadir una nueva alarma al darle al boton correspondiente. Su nombre
	 * y hora apareceran en el recuadro de "alarmas activas".
	 */
	public class NuevaAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			//recoge los parametros de la alarma.
			String nombreAlarma = vista1.getNombreAlarma();
			Date fechaAlarma = vista1.getFechaHoraAlarma();
			
			//crea la nueva alarma y la añade
			Alarma alarma = new Alarma(nombreAlarma, fechaAlarma);
			
			/*1.	-Nos hace falta anhadeAlarma? Usando nuevaAlarma() ya se llama a este metodo, desde Programado o Desprogramado creo
			 *2.	-El sistema no debe permitir añadir dos alarmas con misma hora.
			 *	2.1.	-Cuando intentas anhadir dos alarmas con exactamente la misma fecha y hora, el sistema no es capaz de encontrar coincidencia 
			 usando el metodo "existeAlarmaActiva", dont know why. */
			
			//modelo.anhadeAlarmaActiva(alarma); 
			vista1.MuestraInformacionAlarmaActiva(alarma);
			modelo.nuevaAlarma(nombreAlarma, fechaAlarma);
			vista1.anhadeModelActivas(nombreAlarma);
			
			
				
			
		}
	}
	
	/**
	 * Permite eliminar la alarma seleccionada. Nota: su nombre no desaparece de la vista por simplicidad.
	 * @author Sara
	 *
	 */
	public class EliminaAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			String nombreAlarma = ""; //recogera el nombre de la alarma que deseamos eliminar
			
			if (vista1.SelectionAlarmasActivasEmpty()) {
				//la seleccion esta en desactivadas
				nombreAlarma = vista1.getAlarmaDesactivadaSelected();
				vista1.eliminaModelDesactivadas(nombreAlarma);
			
			} else if (!vista1.SelectionAlarmasActivasEmpty()) {
				//la seleccion esta en activas
				nombreAlarma = vista1.getAlarmaActivaSelected();
				vista1.eliminaModelActivas(nombreAlarma);
			
			} //el else que falta seria no haber pulsado nada pero no se gestiona
			
			Alarma alarma = modelo.buscaAlarmaByID(nombreAlarma);
			modelo.eliminaAlarma(alarma);
			modelo.borraAlarma(nombreAlarma); 
			
		/*
		 * NOTA: Con el codigo previo, si en la primera linea la seleccion fue en desactivadas,
		 * no progresa el codigo. Se estanca porque saltan muchas excepciones y no se elimina de desactivadas.
		 * Una solucion viable, es crear una estructura try-catch en la que se gestionen las interrupiciones mostrando un mensaje 
		 * por pantalla y ejecutando las lineas restantes de codigo que faltan. En lugar de esto, es mejor comprobar si acaso la lista
		 * de activas esta activada, siendo esto la implementacion de arriba.
		 * 
			try {
				
			
			String nombreAlarma = vista1.getAlarmaActivaSelected();
			if (nombreAlarma.equals(null)) { // Si no esta en activas, esta en desactivadas
				nombreAlarma = vista1.getAlarmaDesactivadaSelected();
				vista1.eliminaModelDesactivadas(nombreAlarma);
			} else {
				vista1.eliminaModelActivas(nombreAlarma);
			}
			Alarma alarma = modelo.buscaAlarmaByID(nombreAlarma);
			modelo.eliminaAlarma(alarma);
			modelo.borraAlarma(nombreAlarma); // TODO esta bien?
			
			}catch (Exception e) {
				System.out.println("No se ha podido eliminar la alarma porque has seleccionado en desactivadas."); 
				//eliminar alarma en desactivadas
			}
			*/
		}
	}
	
	/**
	 * Activa una alarma segun el nombre seleccionado.
	 *
	 */
	public class AlarmaOnListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			String nombreAlarma = vista1.getAlarmaDesactivadaSelected();
			vista1.anhadeModelActivas(nombreAlarma);
			vista1.eliminaModelDesactivadas(nombreAlarma);
			/*if (nombreAlarma.equals(null)) { // Si no esta en activas, esta en desactivadas
				nombreAlarma = vista1.getAlarmaDesactivadaSelected();
			}CREO QUE SOBRA*/
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
			vista1.anhadeModelDesactivadas(nombreAlarma);
			vista1.eliminaModelActivas(nombreAlarma);
			/*if (nombreAlarma.equals(null)) { // Si no esta en activas, esta en desactivadas
				nombreAlarma = vista1.getAlarmaDesactivadaSelected();
			}CREO QUE SOBRA*/
			modelo.alarmaOff(nombreAlarma);
		}
	}
	
	/**
	 * Apaga una alarma
	 *
	 */
	public class ApagaAlarmaListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			//vista2.dispose(); // No hacemos pop up 
			modelo.apagar(); // TODO bien hecho?
		}
	}
}
