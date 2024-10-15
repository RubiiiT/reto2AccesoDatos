package reto2XmlYJson.apartado1;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;


public class PruebaPersonalizacion1_5 {

	public static void main(String[]args) {
		
		 guardarXML();
		 recuperarXML();
		
	}
	
	public static void guardarXML() {
		
		XStream xstream = new XStream(new StaxDriver());
		
		xstream.addPermission(AnyTypePermission.ANY);
		
		  
        /*Configuracion para escribir y leer alias 
         * SIN ESTO; EL PROGRAMA NO FUNCIONA
         */
        xstream.alias("IES", Clase.class);
        xstream.alias("AlumnoTIC", Alumno.class);
 
        
        xstream.aliasField("NombreAlumno", Alumno.class, "nombre");
        
        xstream.aliasAttribute(Alumno.class,"dni","DocumentoIdentidad");
        xstream.useAttributeFor(Clase.class, "cont");
        
        xstream.omitField(Alumno.class, "nota");
        
       
		
		//CREACION DE LAS CLASES PARA EL XML
		
		Clase clase1 = new Clase();
		//Aqui creamos un alumno con constructores por defecto y con setters
		
		Alumno alumno1 = new Alumno();
		alumno1.setDni("123123124T");
		alumno1.setNombre("Raul");
		alumno1.setNota(5);
		Domicilio domicilio = new Domicilio();
		domicilio.setCalle("Calle peplito");
		domicilio.setNumero(2);
		alumno1.setDomicilio(domicilio);
		
		
		//Aqui creamos un alumno con constructores con campos
		Alumno alumno2 = new Alumno("131312321F","berta",3,new Domicilio("Calle almendros",231));
		
		
		
		clase1.insertar(alumno1);
		clase1.insertar(alumno2);
		
		
		// Escribir el XML a un archivo
        try (FileWriter writer = new FileWriter("apartado1-5.xml")) {
        	String xml = xstream.toXML(clase1);
            writer.write(xml);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
	
	public static void recuperarXML() {
		XStream xstream = new XStream(new StaxDriver());
		
		/*
		 * Si no ponemos estos alias, da un error de CannotResolveClassException
		 * 
		 */
		
        xstream.alias("IES", Clase.class);
        xstream.alias("AlumnoTIC", Alumno.class);
        
        xstream.aliasField("NombreAlumno", Alumno.class, "nombre");
 
        
        xstream.omitField(Alumno.class, "nota");
        
        xstream.aliasAttribute(Alumno.class,"dni","DocumentoIdentidad");
        xstream.useAttributeFor(Clase.class, "cont");
		
      
		xstream.addPermission(AnyTypePermission.ANY);
		
		  try (FileReader reader = new FileReader("apartado1-5.xml")) {
	            Clase c2 = (Clase) xstream.fromXML(reader);
	            System.out.println(c2.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
}
