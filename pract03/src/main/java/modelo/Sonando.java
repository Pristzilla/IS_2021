package modelo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Sonando extends AlarmasState {
	
	protected Timer timer = new Timer();
	protected AlarmasTask alarmasTask;

	@Override
	public void apagar(Alarmas context) {
		
		alarmasTask.cancel();
		
		this.exitAction(context);
		context.setState(estadoProgramado);
		// Acciones asociadas a la transicion
		context.desactivaAlarma(context.alarmaMasProxima());
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}
	
	@Override
	public void entryAction(Alarmas context) {
		// Programa el timer
		alarmasTask = new AlarmasTask(context);
		timer.schedule(alarmasTask, context.getIntervaloSonando()); //ms
		context.activarMelodia();		
	}
	
	@Override
	public void exitAction(Alarmas context) {
		context.desactivarMelodia();
	}
	
	public class AlarmasTask extends TimerTask {
		
		private Alarmas context;
		public AlarmasTask(Alarmas a) {
			context = a;
		}
		
		@Override
		public void run() {
			estadoSonando.exitAction(context);
			context.setState(estadoProgramado);
			// Acciones asociadas a la transicion
			context.desactivaAlarma(context.alarmaMasProxima());
			estadoProgramado.entryAction(context);
			estadoProgramado.doAction(context);
		}
	}
}
