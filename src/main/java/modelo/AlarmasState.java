package modelo;

import java.util.Date;

public abstract class AlarmasState {
	
	protected static Desprogramado estadoDesprogramado = new Desprogramado();
	protected static Programado estadoProgramado = new Programado();
	protected static Sonando estadoSonando = new Sonando();
	
	/**
	 * Constructor de la clase, inicializa el estado a desactivado.
	 * @param context conjunto de alarmas del sistema
	 * @return el estado inicial
	 */
	public static AlarmasState init(Alarmas context) {
		estadoDesprogramado.entryAction(context);
		return estadoDesprogramado;
	}
	
	/**
	 * Apaga la melodia de una alarma y cambia el estado a "programado".
	 */
	public void apagar(Alarmas context) {}
	
	/**
	 * Pasa una alarma de "activa" a "desactivada".
	 * @param id nombre de la alarma
	 */
	public void alarmaOff(String id, Alarmas context) {}
	
	/**
	 * Pasa una alarma de "desactivada" a "activa".
	 * @param id nombre de la alarma
	 */
	public void alarmaOn(String id, Alarmas context) {}
	
	/**
	 * Elimina una alarma del sistema.
	 * @param id nombre de la alarma
	 */
	public void borraAlarma(String id, Alarmas context) {}
	
	/**
	 * Crea una nueva alarma activa segun los parametros indicados.
	 * @param id nombre de la alarma.
	 * @param hora hora a la que esta programada la alarma.
	 */
	public void nuevaAlarma(String id, Date hora, Alarmas context) {}
	
	/**
	 * Funcion que se ejecuta en la entrada del estado.
	 */
	public void entryAction(Alarmas context) {}
	
	/**
	 * Funcion que se ejecuta durante el estado.
	 */
	public void doAction(Alarmas context) {}
	
	/**
	 * Funcion que se ejecuta a la salida del estado.
	 */
	public void exitAction(Alarmas context) {}
	
	/**
	 * @return estado Desprogramado
	 */
	public static Desprogramado getEstadoDesprogramado() {
		return estadoDesprogramado;
	}
	
	/**
	 * @return estado Programado
	 */
	public static Programado getEstadoProgramado() {
		return estadoProgramado;
	}
	
	/**
	 * @return estado Sonando
	 */
	public static Sonando getEstadoSonando() {
		return estadoSonando;
	}

}
