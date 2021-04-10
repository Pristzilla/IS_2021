package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;

public class Alarmas implements IModelo {
	
	private AlarmasState state;
	private final static int INTERVALO_SONANDO = 5000; // milisegundos
	private PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private ArrayList<Alarma> alarmasDesactivadas = new ArrayList<Alarma>();
	
	/**
	 * Constructor de la clase, inicializa el estado.
	 */
	public Alarmas() {
		state = AlarmasState.init(this);
	}

	public int getIntervaloSonando() {
		return INTERVALO_SONANDO;
	}
	
	public boolean anhadeAlarmaActiva(Alarma a) {
		if (existeAlarmaActiva(a)) {
			System.out.println ("Ya existe una alarma activada a esa hora.");
			return false;
		}
		alarmasActivas.add(a);
		return true;
	}

	public boolean eliminaAlarma(Alarma a) {
		if (existeAlarmaActiva(a)) {
			alarmasActivas.remove(a);
			return true;
		} else if (existeAlarmaDesactivada(a)) {
			alarmasDesactivadas.remove(a);
			return true;
		}
		return false;
	}
	
	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek(); 
	}
	
	public void desactivaAlarma(Alarma a) {
		if (existeAlarmaActiva(a)) {
			alarmasActivas.remove(a);
			alarmasDesactivadas.add(a);
		}
	}
	
	public void activaAlarma(Alarma a) {
		if (existeAlarmaDesactivada(a)) {
			alarmasDesactivadas.remove(a);
			alarmasActivas.add(a);
		}
	}
	
	public void activarMelodia() {
		System.out.println("ALARMA SONANDO");
	}

	public void desactivarMelodia() {
		System.out.println("SONIDO APAGADO");
	}

	public void setState(AlarmasState value) {
		this.state = value;
	}

	public void apagar() {
		state.apagar(this);
	}
	
	public void alarmaOff(String id) {
		state.alarmaOff(id, this);
	}
	
	public void alarmaOn(String id) {
		state.alarmaOn(id, this);
	}
	

	public void borraAlarma(String id) {
		state.borraAlarma(id, this);
	}
	
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(id, hora, this);
	}
	
	/**
	 * Los siguientes metodos los hemos separados para el metodo "eliminaAlarma",
	 * ya que podriamos 1)cazar la excepcion de la lista/cola en la que no estuviese la alarma,
	 * 2)hacer un metodo "existeAlarma" general, pero buscando en "eliminaAlarma" en que
	 * lista/cola se encontraba o 3)hacer metodos separados. Nos hemos decantado por la 
	 * 3 opcion, ya que es la que menos gestion conlleva y mejor rendimiento creemos que
	 * proporciona.
	 */
	
	public boolean existeAlarmaActiva(Alarma a) {
		// El metodo "equals" con "Dates" compara hasta los milisegundos
		Calendar cal1 = Calendar.getInstance(); 
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime (a.getHora());
		cal1.set(Calendar.MILLISECOND, 0);
		
		for (Alarma alar: alarmasActivas) {
			cal2.setTime(alar.getHora());
			cal2.set(Calendar.MILLISECOND, 0);
		
			if (cal1.equals(cal2)) {
				//System.out.println ("Ya existe una alarma activada a esa hora.");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Comprueba si una alarma existe y esta desactivada
	 * @param a alarma a buscar
	 * @return true si la encuentra o false en caso contrario
	 */
	private boolean existeAlarmaDesactivada(Alarma a) {
		for (Alarma alar: alarmasDesactivadas) {
			if (fechaEsIgual(a.getHora(), alar.getHora())) {
				return true;
			}
		}
		return false;
	}
	
	public Alarma buscaAlarmaByID(String id) {
		for (Alarma alar: alarmasActivas) {
			if (alar.getId().equals(id)) {
				return alar;
			}
		}
		for (Alarma alar: alarmasDesactivadas) {
			if (alar.getId().equals(id)) {
				return alar;
			}
		}
		return null;
	}
	
	public int getAlarmasActivasSize() {
		return alarmasActivas.size();		
	}	
	
	private boolean fechaEsIgual(Date fecha1, Date fecha2) {
		if (fecha1.toString().equals(fecha2.toString())) {
			return true;
		}
		return false;
	}
}