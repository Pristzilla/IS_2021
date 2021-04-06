package modelo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Programado extends AlarmasState {
	
	protected Timer timer = new Timer();
	protected AlarmasTask alarmasTask;

	@Override
	public void alarmaOff(String id, Alarmas context) {
		
		alarmasTask.cancel();
		
		this.exitAction(context);
		context.setState(estadoProgramado);
		// Acciones asociadas a la transicion
		context.desactivaAlarma(context.buscaAlarmaByID(id));
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);		
	}

	@Override
	public void alarmaOn(String id, Alarmas context) {
		
		alarmasTask.cancel();
		
		this.exitAction(context);
		context.setState(estadoProgramado);
		// Acciones asociadas a la transicion
		context.activaAlarma(context.buscaAlarmaByID(id));
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);		
	}

	@Override
	public void borraAlarma(String id, Alarmas context) {
		
		alarmasTask.cancel();
		
		this.exitAction(context);
		context.setState(estadoProgramado);
		// Acciones asociadas a la transicion
		context.eliminaAlarma(context.buscaAlarmaByID(id));
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);		
	}

	@Override
	public void nuevaAlarma(String id, Date hora, Alarmas context) {
		
		alarmasTask.cancel();
		
		this.exitAction(context);
		context.setState(estadoProgramado);
		// Acciones asociadas a la transicion
		context.anhadeAlarma(new Alarma(id,hora));
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);				
	}

	@Override
	public void entryAction(Alarmas context) {
		if (context.getAlarmasActivasSize() == 0) {
			estadoProgramado.exitAction(context);
			context.setState(estadoDesprogramado);
			estadoDesprogramado.entryAction(context);
			estadoDesprogramado.doAction(context);
		} else {
			alarmasTask = new AlarmasTask(context);
			timer.schedule(alarmasTask, context.alarmaMasProxima().getHora());
		}
	}
	
	public class AlarmasTask extends TimerTask {
		
		private Alarmas context;
		public AlarmasTask(Alarmas a) {
			context = a;
		}
		
		@Override
		public void run() {
			estadoProgramado.exitAction(context);
			context.setState(estadoSonando);
			estadoSonando.entryAction(context);
			estadoSonando.doAction(context);
		}
	}

}
