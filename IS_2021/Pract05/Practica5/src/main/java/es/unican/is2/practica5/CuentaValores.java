package es.unican.is2.practica5;

import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta, List<Valor> valores) { // WMC +1 Ccog 0
		super(numCuenta);
		this.valores = valores;
	}
	
	public List<Valor> getValores() { // WMC +1 Ccog 0
		return valores;
	}
	
	public void anhadeValor(Valor v) { // WMC +1 Ccog 0
		valores.add(v);
	}
	
	@Override
	public double getSaldo() { // WMC +2 Ccog +1
		double total = 0;
		for (Valor v: ((CuentaValores) this).getValores()) { // WMC +1 Ccog +1
			total += v.getCotizacionActual()*v.getNumValores();
		}
		return total;
	}
}
