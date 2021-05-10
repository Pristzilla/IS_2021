package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private static final int LIMITE_DEBITO = 1000;
	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) { // WMC +1 Ccog 0
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = LIMITE_DEBITO;
	}

	/*
	 * public void ingresar(double cantidad) throws datoErroneoException { // WMC +2
	 * Ccog +1 if (cantidad <= 0) // WMC +1 Ccog +1 throw new
	 * datoErroneoException("No se puede ingresar una cantidad negativa");
	 * Movimiento m = new Movimiento(); LocalDateTime now = LocalDateTime.now();
	 * m.setF(now); m.setC("Ingreso en efectivo"); m.setI(cantidad);
	 * this.mMovimientos.add(m); }
	 */

	/*
	 * public void retirar(double cantidad) throws saldoInsuficienteException,
	 * datoErroneoException { // WMC +3 Ccog +2 if (cantidad <= 0) // WMC +1 Ccog +1
	 * throw new datoErroneoException("No se puede retirar una cantidad negativa");
	 * if (getSaldo() < cantidad) // WMC +1 Ccog +1 throw new
	 * saldoInsuficienteException("Saldo insuficiente"); Movimiento m = new
	 * Movimiento(); LocalDateTime now = LocalDateTime.now(); m.setF(now);
	 * m.setC("Retirada de efectivo"); m.setI(-cantidad); this.mMovimientos.add(m);
	 * }
	 */

	public void ingresar(String concepto, double cantidad) throws datoErroneoException { // WMC +3 Ccog +2
		if (cantidad <= 0) // WMC +1 Ccog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		if (concepto.equals("")) { // WMC +1 Ccog +1
			m.setConcepto("Ingreso en efectivo");
		} else {
			m.setConcepto(concepto);	
		}
		m.setImporte(cantidad);
		this.mMovimientos.add(m);
	}

	public void retirar(String concepto, double cantidad) throws saldoInsuficienteException, datoErroneoException { // WMC +4 Ccog +3
		if (getSaldo() < cantidad) // WMC +1 Ccog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (cantidad <= 0) // WMC +1 Ccog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		if (concepto.equals("")) { // WMC +1 Ccog +1
			m.setConcepto("Retirada de efectivo");
		} else {
			m.setConcepto(concepto);
		}
		m.setImporte(-cantidad);
		this.mMovimientos.add(m);
	}
	
	@Override
	public double getSaldo() { // WMC +2 Ccog +1
		double saldo = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) { // WMC +1 Ccog +1
			Movimiento m = mMovimientos.get(i);
			saldo += m.getImporte();
		}
		return saldo;
	}

	public void addMovimiento(Movimiento m) { // WMC +1 Ccog 0
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() { // WMC +1 Ccog 0
		return mMovimientos;
	}

	public LocalDate getCaducidadDebito() { // WMC +1 Ccog 0
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	public LocalDate getCaducidadCredito() { // WMC +1 Ccog 0
		return this.mFechaDeCaducidadTarjetaCredito;
	}

	public double getLimiteDebito() { // WMC +1 Ccog 0
		return limiteDebito;
	}

}