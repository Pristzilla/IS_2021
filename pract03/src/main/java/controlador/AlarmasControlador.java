package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.Alarma;
import modelo.Alarmas;
import vista.MainWindow;

/**
 * El controlador coordina operaciones entre la vista y el modelo.
 * @author Zdravko y Sara
 *
 */
public class AlarmasControlador {
	
	private Alarmas modelo;
	private MainWindow vista;
	
	public AlarmasControlador(Alarmas m, MainWindow v) {
		modelo = m;
		vista = v;
		
		/*Indica que cuando se pulse el botón de añadir alarma,
		 * se ejecutará la operación desde la clase interna NuevaAlarmaListener.*/
		vista.addAnhadeAlarmaListener(new NuevaAlarmaListener());
		
	}
	
	public class NuevaAlarmaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String nombreAlarma  = "";
			nombreAlarma = vista.getNombreAlarma();
			
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
			Date fechaAlarma = null;
			try {
				fechaAlarma = sdf.parse("2020-04-3");
			} catch (ParseException e) {
				System.out.println("Se ha detectado una excepción.");
				e.printStackTrace();
			}
			
			Alarma alarma = new Alarma(nombreAlarma, fechaAlarma);
			vista.MuestraInformaciónAlarma(alarma);
			
			
			
		}
		
		
		
		
		}	
	}


