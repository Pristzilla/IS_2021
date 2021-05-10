package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	private static final double COMISION_RETIRADA = 0.05;
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) { // WMC +1 Ccog 0
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param cantidad Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double cantidad) throws saldoInsuficienteException, datoErroneoException { // WMC +3 Ccog +2
		if (cantidad<0) // WMC +1 Ccog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Retirada en cajero automático");
		cantidad += cantidad * COMISION_RETIRADA; // Añadimos una comisión de un 5%
		m.setImporte(-cantidad);
		
		if (getGastosAcumulados()+cantidad > mCredito) // WMC +1 Ccog +1
			throw new saldoInsuficienteException("Crédito insuficiente");
		else {
			mMovimientosMensuales.add(m);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double cantidad) throws saldoInsuficienteException, datoErroneoException { // WMC +3 Ccog +2
		if (cantidad<0) // WMC +1 Ccog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + cantidad > mCredito) // WMC +1 Ccog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Compra a crédito en: " + datos);
		m.setImporte(-cantidad);
		mMovimientosMensuales.add(m);
	}
	
    public double getGastosAcumulados() { // WMC +2 Ccog +1
		double total = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { // WMC +1 Ccog +1
			Movimiento m = mMovimientosMensuales.get(i);
			total += m.getImporte();
		}
		return total;
	}	
	
	public LocalDate getCaducidadCredito() { // WMC +1 Ccog 0
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() { // WMC +2 Ccog +1
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setFecha(now);
		liq.setConcepto("Liquidación de operaciones tarjeta crédito");
		double gastos = getGastosAcumulados();
		liq.setImporte(gastos);
	
		if (gastos != 0) // WMC +1 Ccog +1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() { // WMC +1 Ccog 0
		return mMovimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() { // WMC +1 Ccog 0
		return mCuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { // WMC +1 Ccog 0
		return mhistoricoMovimientos;
	}

}