package reto2XmlYJson.apartado1;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Domicilio implements Serializable {

	
	private String calle;
	private int numero;
	
	public Domicilio() {
		
	}
	
	
	public Domicilio(String calle, int numero) {
		super();
		this.calle = calle;
		this.numero = numero;
	}


	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String toString() {
		return "Calle: "+calle+"\n Numero: "+numero;
	}
	

}
