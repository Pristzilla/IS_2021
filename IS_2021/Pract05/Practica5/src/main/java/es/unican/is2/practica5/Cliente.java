package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

 	public Cliente(String titular, String calle, String zip, String localidad, // WMC +1 Ccog 0
 			String telefono, String dni) {  
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) { // WMC +1 Ccog 0
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public double getSaldoTotal() { // WMC +2 Ccog +1
		double total = 0.0;
		for (Cuenta c: Cuentas) { // WMC +1 Ccog +1
			total += c.getSaldo();
		}
		/*
		 * for (Cuenta c: Cuentas) { // WMC +1 Ccog +1 if (c instanceof CuentaAhorro) {
		 * // WMC +1 Ccog +2 total += ((CuentaAhorro) c).getSaldo(); } else if (c
		 * instanceof CuentaValores) { // WMC +1 Ccog +2 total += ((CuentaValores)
		 * c).getSaldo(); } }
		 */
		return total;
	}
	
	public void anhadeCuenta(Cuenta c) { // WMC +1 Ccog 0
		Cuentas.add(c);
	}	
}