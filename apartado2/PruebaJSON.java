package reto2XmlYJson.apartado2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import reto2XmlYJson.apartado1.Alumno;
import reto2XmlYJson.apartado1.Clase;
import reto2XmlYJson.apartado1.Domicilio;

import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 *  @descrition
 *	@author Laura
 *  @date 6/5/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class PruebaJSON {
	

	public static void main(String[] args) {
		//Construimos el objeto  a convertir después en XML
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
		Alumno alumno2 = new Alumno("131312321F","berta",3,null);
		
		
		
		clase1.insertar(alumno1);
		clase1.insertar(alumno2);
		
				
				
				//Primero pasamos el objeto a xml para despues recuperarlo con xstream , ObjectInputStream y ObjectOutputStream
				objetoAXml(clase1);
				Clase clase2 = xmlAObjeto();
				
				
				System.out.println("--------------------------------XSTREAM--------------------------------\n");
				jsonConXStream(clase2);
				
				System.out.println("\n--------------------------------GSON--------------------------------\n");
				jsonConGSON(clase2);
				
				System.out.println("\n--------------------------------STERARLY--------------------------------\n");
				jsonConSterarly(clase2);
       
	}
	/****************************************************************************************************************************************************
	 *********************************************** XML A OBJETO UTILIZANDO XSTREAM CON ObjectInputStream **********************************************
	 *******************************************************************************************************************************************/
	
	public static Clase xmlAObjeto() {
		Clase clase = new Clase();
		
		try(
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("clase.xml"));
				)
		{
			XStream xstream = new XStream(new StaxDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			clase = (Clase) xstream.fromXML(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clase;
		
	}
	
	/****************************************************************************************************************************************************
	 *********************************************** OBJETO a XML UTILIZANDO XSTREAM CON ObjectOutputStream **********************************************
	 *******************************************************************************************************************************************/
	
	//Al generar el xml, si lo abrimos no se ve nada porque objectOutputStream no esta diseñado para generar xml , sino para serializar objetos a binario
	public static void objetoAXml(Clase clase) {
		try(
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("clase.xml"));
				
				)
		{
			XStream xstream = new XStream(new StaxDriver());
			xstream.addPermission(AnyTypePermission.ANY);
			
			xstream.toXML(clase,out);
			
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Con sterarly es muy complicado y hay que saber a la perfeccion como esta diseñada la clase y con que atributos y objetos
	//Se puede hacer tambien pasandole el string con el JSON directamente pero lo tendriamos que escribir a mano
	
	/****************************************************************************************************************************************************
	 ***********************************************OBJETO A JSON y viceversa UTILIZANDO STERARLY (JSONObject) **********************************************
	 *******************************************************************************************************************************************/
	public static void jsonConSterarly(Clase clase) {
		
		JSONObject sterarlyObjetoClase = new JSONObject();
		
		JSONArray sterarlyArrayAlumnos = new JSONArray();
		JSONObject sterarlyObjetoAlumno = new JSONObject();
		JSONObject sterarlyObjetoAlumno2 = new JSONObject();
			
		JSONObject sterarlyObjetoDomicilioAlumno1 = new JSONObject();
		try {
			
			//Aqui metemos los atributos de domicilio a un jsonobject
			sterarlyObjetoDomicilioAlumno1.put("calle",clase.getAlumno(0).getDomicilio().getCalle());
			sterarlyObjetoDomicilioAlumno1.put("numero",clase.getAlumno(0).getDomicilio().getNumero());
			
			
			//Aqui metemos todos los datos ( incluidos el json object que contiene otro objeto) al jsonobject de alumno
			sterarlyObjetoAlumno.put("dni",clase.getAlumno(0).getDni());
			sterarlyObjetoAlumno.put("nombre",clase.getAlumno(0).getNombre());
			sterarlyObjetoAlumno.put("nota", clase.getAlumno(0).getNota());
			sterarlyObjetoAlumno.put("domicilio", sterarlyObjetoDomicilioAlumno1);
			
			//Aqui el alumno 2 
			sterarlyObjetoAlumno2.put("dni",clase.getAlumno(1).getDni());
			sterarlyObjetoAlumno2.put("nombre",clase.getAlumno(1).getNombre());
			sterarlyObjetoAlumno2.put("nota", clase.getAlumno(1).getNota());
			
			sterarlyArrayAlumnos.put(sterarlyObjetoAlumno);
			sterarlyArrayAlumnos.put(sterarlyObjetoAlumno2);
			
			sterarlyObjetoClase.put("clase",sterarlyArrayAlumnos);
			
			
			System.out.println(" String en sterarly json: "+sterarlyObjetoClase);
			
			guardarEnArchivoTextoPlano(sterarlyObjetoClase.toString(), "textoConSterarlyJson.txt");
			
			//Aqui recuperamos el JSONObject desde el archivo , creando un nuevo JSONObject
			JSONObject sterarlyObjetoClase2 = new JSONObject(recuperarDeArchivoTextoPlano("textoConSterarlyJson.txt"));
			
			//Tenemos que recuperar el array y luego recuperar el objeto con la posicion 0 del array ( ver syso para entender mejor)
			JSONArray sterarlyArrayAlumnos2 = sterarlyObjetoClase2.getJSONArray("clase");
			
			JSONObject sterarlyObjetoAlumno1_2 = sterarlyArrayAlumnos2.getJSONObject(0);
			JSONObject sterarlyObjetoAlumno2_2 = sterarlyArrayAlumnos2.getJSONObject(1);
			
			JSONObject sterarlyObjetoDomicilioAlumno1_2 = sterarlyObjetoAlumno1_2.getJSONObject("domicilio");
			
			//System.out.println(sterarlyObjetoTelefono2);
			
			//Nos creamos un nuevo Domicilio con el objeto sterarlyObjetoDomicilio
			Domicilio domicilioNuevo = new Domicilio();
			domicilioNuevo.setCalle(sterarlyObjetoDomicilioAlumno1_2.getString("calle"));
			domicilioNuevo.setNumero(Integer.parseInt (sterarlyObjetoDomicilioAlumno1_2.getString("numero")));
			
			
			//System.out.println(sterarlyObjetoPersona2);
			
			
			//Creamos una nueva persona con los datos
			Alumno alumno1 =new Alumno();
			
			alumno1.setDni(sterarlyObjetoAlumno1_2.getString("dni"));
			alumno1.setNombre(sterarlyObjetoAlumno1_2.getString("nombre"));
			alumno1.setNota(Float.parseFloat(sterarlyObjetoAlumno1_2.getString("nota")));
			alumno1.setDomicilio(domicilioNuevo);
			
			Alumno alumno2 = new Alumno();
			
			alumno2.setDni(sterarlyObjetoAlumno2_2.getString("dni"));
			alumno2.setNombre(sterarlyObjetoAlumno2_2.getString("nombre"));
			alumno2.setNota(Float.parseFloat(sterarlyObjetoAlumno2_2.getString("nota")));
			

			Clase clase2 = new Clase();
			
			clase2.insertar(alumno1);
			clase2.insertar(alumno2);
			
			
			System.out.println("Objeto persona devuelto de Sterarly JSON: "+clase2);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/****************************************************************************************************************************************************
	 *********************************************** OBJETO A JSON y viceversa utilizando GSON **********************************************
	 *******************************************************************************************************************************************/
	
	public static void jsonConGSON(Clase clase) {
		Gson objetoGson = new Gson();
		
		String gsonClase = objetoGson.toJson(clase);
		
		 System.out.println("String en gson: "+gsonClase+"\n");	
		
		guardarEnArchivoTextoPlano(gsonClase,"textoConGson.txt");
		
		Clase clase2 = objetoGson.fromJson(recuperarDeArchivoTextoPlano("textoConGson.txt"), Clase.class);
		
		System.out.println("Objeto persona devuelto de Gson: "+clase2);
		
	}
	/****************************************************************************************************************************************************
	 *********************************************** OBJETO A JSON y viceversa utilizando XSTREAM **********************************************
	 *******************************************************************************************************************************************/
	//ESTO ES CON EL JETTISONMAPPEDXML
	public static void jsonConXStream(Clase clase) {
		
		
		//El jettisonMappedXmlDriver cambia a JSON en vez de a XML
		
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("IES", Clase.class);
        xstream.alias("alumno", Alumno.class);

        String jsonClase = xstream.toXML(clase);
        System.out.println("String en xstream: "+jsonClase+"\n");		
        
        	
        guardarEnArchivoTextoPlano(jsonClase,"textoConXstream.txt");
      
        Clase clase2 = (Clase) xstream.fromXML(recuperarDeArchivoTextoPlano("textoConXstream.txt"));
        
        System.out.println("Objeto persona devuelto de Xstream: "+clase2);
        
	}
	
	/****************************************************************************************************************************************************
	 *********************************************** Guardar en archivo de texto plano **********************************************
	 *******************************************************************************************************************************************/
	
	public static void guardarEnArchivoTextoPlano(String jsonClase,String archivo) {
		
		try(
				PrintWriter escritor = new PrintWriter(archivo);
				) {
			
			escritor.println(jsonClase);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/****************************************************************************************************************************************************
	 *********************************************** recuperar de archivo de texto plano **********************************************
	 *******************************************************************************************************************************************/
	
	public static String recuperarDeArchivoTextoPlano(String archivo) {
		String clase = "";
		try (
				BufferedReader lector = new BufferedReader(new FileReader(archivo));
				){
			String linea;
			while((linea=lector.readLine())!=null) {
				clase+=linea;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clase;
		
	}

}


