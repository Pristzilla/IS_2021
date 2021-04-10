package vista;

import java.awt.event.ActionListener;
import java.util.Date;

import modelo.Alarma;

public interface IVista {

	/**
	 * Gestionador del boton "anhadir alarma"
	 * @param listenForNewAlarm
	 */
	public void addAnhadeAlarmaListener (ActionListener listenForNewAlarm);
	
	/**
	 * Gestionador del boton "elimina alarma"
	 * @param listenforDeletedAlarm
	 */
	public void addEliminaAlarmaListener (ActionListener listenforDeletedAlarm);
	
	/**
	 * Gestionador del boton "apagar alarma"
	 * @param listenforStoppedAlarm
	 */
	public void addApagarAlarmaListener (ActionListener listenforStoppedAlarm);
	
	/**
	 * Gestionador del boton "activa alarma"
	 */
	public void addAlarmaOnListener (ActionListener listenforAlarmOn);
	
	/**
	 * Gestionador del boton "desactiva alarma
	 * @param listenforDeletedAlarm
	 */
	public void addAlarmaOffListener (ActionListener listenforAlarmOff);
	
	/**
	 * @return el nombre de la alarma
	 */
	public String getNombreAlarma();
	
	/**
	 * @return el nombre de la alarma activa seleccionada
	 */
	public String getAlarmaActivaSelected();
	
	/**
	 * @return el nombre de la alarma desactivada seleccionada
	 */
	public String getAlarmaDesactivadaSelected();
	
	/**
	 * permite anhadir el nombre de una alarma a activa
	 * @param nombreAlarma
	 */
	public void anhadeModelActivas(String nombreAlarma);
	/**
	 * permite anhadir el nombre de una alarma a desactivadas
	 * @param nombreAlarma
	 */
	public void anhadeModelDesactivadas (String nombreAlarma);
	
	/**
	 * permite eliminar el nombre de una alarma de activas
	 * @param nombreAlarma
	 */
	public void eliminaModelActivas (String nombreAlarma);
	
	/**
	 * permite eliminar el nombre de una alarma de desactivadas
	 * @param nombreAlarma
	 */
	public void eliminaModelDesactivadas (String nombreAlarma);
	
	/**
	 * A la hora de eliminar una alarma, nos conviene saber donde hemos
	 * hecho la seleccion porque si comparamos con null, nos sale una excepcion.
	 */
	public Boolean SelectionAlarmasActivasEmpty();
	
	/**
	 * Muestra la informacion de la nueva alarma anhadida
	 * @param alarma alarma anhadida
	 */
	public void MuestraInformacionAlarmaActiva (Alarma alarma);
	
	/**
	 * @return la fecha actual de la nueva alarma creada
	 */
	public Date getFechaHoraAlarma();
	
	/**
	 * Pone la vista a visible
	 */
	public void setVisible();
}
