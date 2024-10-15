package xmlDom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import xmlJson.Alumno;
import xmlJson.Clase;

public class LecturaDesdeDom {

    public static void main(String[] args) {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        
        // Definir alias para las clases
        xstream.alias("Alumno", Alumno.class);
        xstream.alias("clase", Clase.class);
        
        xstream.aliasAttribute(Alumno.class, "dni", "dni");
        
        // Indicar a XStream que el campo "clase" en la clase "Clase" es un array de "Alumno"
        xstream.addImplicitArray(Clase.class, "clase");

        try (FileReader reader = new FileReader("alumnos.xml")) {

            Clase c = (Clase) xstream.fromXML(reader);
            System.out.println(c.toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

