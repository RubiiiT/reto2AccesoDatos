package xmlDom;

import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import xmlJson.Alumno;
import xmlJson.Clase;

public class EscrituraParaDom {

	public static void main(String[] args) {
		
		XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        
        Alumno a1 = new Alumno();
        a1.setDni("23847324J");
        a1.setNombre("Julio");
        
        Alumno a2 = new Alumno();
        a2.setDni("892348637L");
        a2.setNombre("Sans");
        
        Clase c = new Clase();
        c.insertar(a1);
        c.insertar(a2);
        
        // Serializar el objeto a XML
        String xml = xstream.toXML(c);
        System.out.println(xml);

     // Escribir el XML a un archivo
        try (FileWriter writer = new FileWriter("escrituraparaDom.xml")) {
            writer.write(xml);
            System.out.println("Objeto Clase escrito en escrituraparaDom.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
