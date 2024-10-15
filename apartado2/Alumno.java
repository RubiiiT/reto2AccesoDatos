package reto2XmlYJson.apartado2;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings({ "serial", "rawtypes" })
public class Alumno implements Comparable, Serializable {
	
	
	//ATRIBUTOS DE CLASE
	private static float notaCorte= 0.0F;
	
	//ATRIBUTOS DE INSTANCIA
	String dni;
	String nombre;
	float nota;
	Domicilio domicilio;

	
	

	public Alumno() {
		
	}
	
	public Alumno(String dni, String nombre, float nota, Domicilio domicilio) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.nota = nota;
		this.domicilio = domicilio;
	}
	
	
	public Alumno(String dni, String nombre) {
		
		this(dni, nombre, 3, null);
	}
	
	public String toString() {
				
		return "Nombre: "+nombre+"\nDni: "+dni+"\nNota: "+nota+"\nDomicilio:\n "+domicilio+"\n";
	}
	
	public int hashCode() {
		
		return dni.hashCode();		
		
	}
	
	public boolean equals(Object o) {		
		
		Alumno a= (Alumno) o;
		return this.dni.equals(a.getDni());
		
	}
	
	
	
	public static float getNotaCorte() {
		
		return Alumno.notaCorte;
	}
	
	public void setNotaCorte(float notaCorte) {
		
		Alumno.notaCorte= notaCorte;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public float getNota() {
		return nota;
	}


	public void setNota(float nota) {
		this.nota = nota;
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}


	@Override
	public int compareTo(Object o) {
		
		Alumno aux= (Alumno) o;
		
		//ordenar por nota
		return (int) (this.nota-aux.nota);
	}
	

}//class





