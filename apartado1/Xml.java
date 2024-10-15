package reto2XmlYJson.apartado1;



import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Xml {
	
	protected static String fichero = "clase.xml";

    public static void main(String[] args) {
        
        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        
        xstream.alias("AlumnoGuay", Alumno.class);
        xstream.alias("PedazoClase", Clase.class);  

        // Definir alias para los campos
        xstream.aliasField("notaCurso", Alumno.class, "nota");
        xstream.aliasField("elNombre", Alumno.class, "nombre");
        
       xstream.aliasAttribute(Alumno.class, "dni", "documentoIdentidad");
             

        Alumno a1 = new Alumno();
        a1.setDni("23846324J");
        a1.setNombre("Paco");
        a1.setNota(2);
        
        Alumno a2 = new Alumno();
        a2.setDni("892357237L");
        a2.setNombre("Mario");
        a2.setNota(6);
        
        Clase c = new Clase();
        c.insertar(a1);
        c.insertar(a2);
        
        // Serializar el objeto a XML
        String xml = xstream.toXML(c);
        System.out.println(xml);

     // Escribir el XML a un archivo
        try (FileWriter writer = new FileWriter(fichero)) {
            writer.write(xml);
            System.out.println("Objeto Clase escrito en clase.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     // Deserializar el objeto desde el XML
        try (FileReader reader = new FileReader(fichero)) {
            Clase c2 = (Clase) xstream.fromXML(reader);
            System.out.println(c2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
