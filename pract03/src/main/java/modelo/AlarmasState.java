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
	
	public void apagar(Alarmas context) {}
	
	public void alarmaOff(String id, Alarmas context) {}
	
	public void alarmaOn(String id, Alarmas context) {}
	
	public void borraAlarma(String id, Alarmas context) {}
	
	public void nuevaAlarma(String id, Date hora, Alarmas context) {}
	
	public void entryAction(Alarmas context) {}
	
	public void doAction(Alarmas context) {}
	
	public void exitAction(Alarmas context) {}
	
	public static Desprogramado getEstadoDesprogramado() {
		return estadoDesprogramado;
	}
	
	public static Programado getEstadoProgramado() {
		return estadoProgramado;
	}
	
	public static Sonando getEstadoSonando() {
		return estadoSonando;
	}

}
