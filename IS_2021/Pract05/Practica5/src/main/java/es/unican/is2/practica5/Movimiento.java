package es.unican.is2.practica5;

import java.time.LocalDateTime;

public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	public double getImporte() { // WMC +1 Ccog 0
		return mImporte;
	}

	public String getConcepto() { // WMC +1 Ccog 0
		return mConcepto;
	}

	public void setConcepto(String newMConcepto) { // WMC +1 Ccog 0
		mConcepto = newMConcepto;
	}

	public LocalDateTime getFecha() { // WMC +1 Ccog 0
		return mFecha;
	}

	public void setFecha(LocalDateTime newMFecha) { // WMC +1 Ccog 0
		mFecha = newMFecha;
	}

	public void setImporte(double newMImporte) { // WMC +1 Ccog 0
		mImporte = newMImporte;
	}
}