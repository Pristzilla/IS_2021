package es.unican.is2.pract03.modelo;

import java.util.Date;

public interface IModelo {
	
	/**
	 * No ponemos el atributo publico por seguridad, para que no se pueda modificar.
	 * @return el intervalo que debe estar sonando la alarma en ms
	 */
	public int getIntervaloSonando();
	
	/**
	 * Anhade una nueva alarma activa
	 * @param a alarma nueva
	 * @return true si se ha anhadido o false si no
	 */
	public boolean anhadeAlarmaActiva(Alarma a);
	
	/**
	 * Elimina una alarma, tanto si esta activa como desactivada
	 * @param a alarma a anhadir
	 * @return true si se ha eliminado o false si no existia
	 */
	public boolean eliminaAlarma(Alarma a);
	
	/**
	 * Indica cual es la alarma con la hora mas proxima.
	 * @return alarma con la hora mas proxima
	 */
	public Alarma alarmaMasProxima();
	
	/**
	 * Pasa la alarma de activadas a desactivadas.
	 * @param a alarma a desactivar
	 */
	public void desactivaAlarma(Alarma a);
	
	/**
	 * Pasa la alarma de desactivadas a activadas.
	 * @param a alarma a activadas
	 */
	public void activaAlarma(Alarma a);
	
	/**
	 * Muestra un mensaje indicando que la alarma esta sonando.
	 */
	public void activarMelodia();
	
	/**
	 * Muestra un mensaje indicando que la alarma deja de sonar.
	 */
	public void desactivarMelodia();

	/**
	 * Cambia el estado
	 * @param value nuevo estado
	 */
	public void setState(AlarmasState value);
	
	/**
	 * Senhal de apagar la alarma
	 */
	public void apagar();
	
	/**
	 * Senhal de desactivar una alarma
	 * @param id identificador de la alarma a desactivar
	 */
	public void alarmaOff(String id);
	
	/**
	 * Senhal de activar una alarma
	 * @param id identificador de la alarma a activar
	 */
	public void alarmaOn(String id);
	
	/**
	 * Elimina una alarma del sistema
	 * @param id identificador de la alarma
	 */
	public void borraAlarma(String id);
	
	/**
	 * Anhade una nueva alarma al sistema ya programada para sonar 
	 * @param id identificador de la alarma
	 * @param hora hora a la que debe sonar
	 */
	public void nuevaAlarma(String id, Date hora);
	
	/**
	 * Comprueba si una alarma existe dado su ID
	 * @param id identificador de la alarma
	 * @return la alarma buscada o false si no la encuentra
	 */
	public Alarma buscaAlarmaByID(String id);
	
	/**
	 * @return el numero de alarmas activas
	 */
	public int getAlarmasActivasSize();
	
	/**
	 * Comprueba si una alarma existe y esta activa
	 * @param a alarma a buscar
	 * @return true si la encuentra o false en caso contrario
	 */
	public boolean existeAlarmaActiva(Alarma a);
	
	/**
	 * Comprueba si una alarma existe y esta desactivada
	 * @param a alarma a buscar
	 * @return true si la encuentra o false en caso contrario
	 */
	public boolean existeAlarmaDesactivada(Alarma a);
}
