package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import modelo.Alarma;
import modelo.Alarmas;
import modelo.IModelo;
import vista.IVista;
import vista.MainWindow;

public class AlarmasControlador {
	
	private IModelo modelo;
	private IVista vista;
	
	public AlarmasControlador(Alarmas m, MainWindow v) {
		modelo = m;
		vista = v;
		
		/*Indica que cuando se pulse el boton de anhadir alarma,
		 * se ejecutara la operacion desde la clase interna NuevaAlarmaListener.*/
		vista.addAnhadeAlarmaListener(new NuevaAlarmaListener());	
		
		/*
		 * Indica que cuando se pusle el boton de apagar alarma, 
		 * se ejecutaa la operacion desde la clase interna ApagaAlarmaListener()*/
		vista.addApagarAlarmaListener(new ApagaAlarmaListener());
		
		/*Cuando se pulse el boton de elimina Alarma, se
		 * ejecutara la operacion desde la clase interna EliminaAlarmaListener*/
		vista.addEliminaAlarmaListener(new EliminaAlarmaListener());
		
		/*Cuandos se pulse el boton de activa Alarma, se 
		 * ejecutara al operacion desde la clase interna AlarmaOnListener*/
		vista.addAlarmaOnListener(new AlarmaOnListener());
		
		/*Cuandos se pulse el boton de activa Alarma, se 
		 * ejecutara al operacion desde la clase interna AlarmaOnListener*/
		vista.addAlarmaOffListener(new AlarmaOffListener());
	}
	
	/**
	 * Permite anhadir una nueva alarma al darle al boton correspondiente. Su nombre
	 * y hora apareceran en el recuadro de "alarmas activas".
	 */
	public class NuevaAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			//recoge los parametros de la alarma.
			String nombreAlarma = vista.getNombreAlarma();
			Date fechaAlarma = vista.getFechaHoraAlarma();
			
			//crea la nueva alarma y la añade
			Alarma alarma = new Alarma(nombreAlarma, fechaAlarma);
			
			//GESTION DE ERRORES..
			if (alarma.getHora().before(new Date())) {
				System.out.println ("No se puede añadir una fecha anterior a la actual.");
			
			} else { //la alarma puede tener una fecha válida.
				
				if (!modelo.existeAlarmaActiva(alarma) && !modelo.existeAlarmaDesactivada(alarma)) { 
					//la fecha de la alarma no coincide otra ya creada
					vista.MuestraInformacionAlarmaActiva(alarma);
					modelo.nuevaAlarma(nombreAlarma, fechaAlarma);
					vista.anhadeModelActivas(nombreAlarma);
			
				}
			}
		}
	}
	
	/**
	 * Permite eliminar la alarma seleccionada. Nota: su nombre no desaparece de la vista por simplicidad.
	 *
	 */
	public class EliminaAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			String nombreAlarma = ""; //recogera el nombre de la alarma que deseamos eliminar
			
			if (vista.SelectionAlarmasActivasEmpty()) {
				//la seleccion esta en desactivadas
				nombreAlarma = vista.getAlarmaDesactivadaSelected();
				vista.eliminaModelDesactivadas(nombreAlarma);
			
			} else if (!vista.SelectionAlarmasActivasEmpty()) {
				//la seleccion esta en activas
				nombreAlarma = vista.getAlarmaActivaSelected();
				vista.eliminaModelActivas(nombreAlarma);
			
			} //el else que falta seria no haber seleccionado ninguna alarma, pero no se gestiona
			
			Alarma alarma = modelo.buscaAlarmaByID(nombreAlarma);
			modelo.eliminaAlarma(alarma);
			modelo.borraAlarma(nombreAlarma); 
		}
	}
	
	/**
	 * Activa una alarma segun el nombre seleccionado.
	 *
	 */
	public class AlarmaOnListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			String nombreAlarma = vista.getAlarmaDesactivadaSelected();
			vista.anhadeModelActivas(nombreAlarma);
			vista.eliminaModelDesactivadas(nombreAlarma);
			
			/**
			 * 1. Recoger la hora de la alarma
			 * 2. Revisar si la hora a la que estaba programada ya ha pasado
			 * 3. En ese caso, programarla para el día siguiente
			 */
			Alarma alarma = modelo.buscaAlarmaByID(nombreAlarma);
			Date fechaAlarma = alarma.getHora();
			Date fechaActual = new Date();
			if (fechaAlarma.before(fechaActual)) {
				Calendar nuevaFecha = Calendar.getInstance();
				nuevaFecha.setTime(fechaAlarma); 
				nuevaFecha.add(Calendar.DATE, 1);
				fechaAlarma = nuevaFecha.getTime();
				alarma.setHora(fechaAlarma);
				vista.MuestraInformacionAlarmaActiva(alarma);
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
	
			String nombreAlarma = vista.getAlarmaActivaSelected();
			vista.anhadeModelDesactivadas(nombreAlarma);
			vista.eliminaModelActivas(nombreAlarma);
			modelo.alarmaOff(nombreAlarma);
		}
	}
	
	/**
	 * Apaga una alarma
	 *
	 */
	public class ApagaAlarmaListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			modelo.apagar();
		}
	}
}
