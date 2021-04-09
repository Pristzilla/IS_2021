package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import modelo.Alarma;
import modelo.Alarmas;
import modelo.IModelo;
import vista.IVista;
import vista.MainWindow;

public class AlarmasControlador {
	
	private IModelo modelo;
	private IVista vista1;
	
	public AlarmasControlador(Alarmas m, MainWindow v1) {
		modelo = m;
		vista1 = v1;
		
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
			
			//GESTION DE ERRORES..
			if (alarma.getHora().before(new Date())) {
				System.out.println ("No se puede añadir una fecha anterior a la actual.");
			
			} else { //la alarma puede contar con fecha válida.
				
				if (!modelo.existeAlarmaActiva(alarma)) { 
					//la fecha de la alarma no coincide otra ya creada
					vista1.MuestraInformacionAlarmaActiva(alarma);
					modelo.nuevaAlarma(nombreAlarma, fechaAlarma);
					vista1.anhadeModelActivas(nombreAlarma);
			
				}
			}
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
			
			String nombreAlarma = vista1.getAlarmaDesactivadaSelected();
			vista1.anhadeModelActivas(nombreAlarma);
			vista1.eliminaModelDesactivadas(nombreAlarma);
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
