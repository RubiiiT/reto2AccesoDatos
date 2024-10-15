package xmlDom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import xmlJson.Alumno;
import xmlJson.Clase;

public class LecturaDom {

    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
            File archivoXML = new File("escrituraparaDom.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();

            // Crear una instancia de la clase Clase para reconstruir los datos
            Clase clase = new Clase();

            // Extraer todos los nodos "Alumno" dentro de la etiqueta xmlJson.Clase
            NodeList alumnosList = doc.getElementsByTagName("xmlJson.Alumno");

            for (int i = 0; i < alumnosList.getLength(); i++) {
                Node nNode = alumnosList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element alumnoElement = (Element) nNode;

                    // Acceder a los nodos hijos
                    String dni = alumnoElement.getElementsByTagName("dni").item(0).getTextContent();
                    String nombre = alumnoElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String notaStr = alumnoElement.getElementsByTagName("nota").item(0).getTextContent();
                    String nivelStr = alumnoElement.getElementsByTagName("nivel").item(0) != null
                            ? alumnoElement.getElementsByTagName("nivel").item(0).getTextContent()
                            : Alumno.Nivel.FPB.toString(); // Nivel por defecto si no está presente en el XML

                    // Convertir los valores a sus tipos correspondientes
                    float nota = Float.parseFloat(notaStr);
                    Alumno.Nivel nivel = Alumno.Nivel.valueOf(nivelStr);

                    // Crear un nuevo objeto Alumno
                    Alumno alumno = new Alumno(dni, nombre, nota, nivel);

                    // Insertar el alumno en la clase
                    clase.insertar(alumno);
                }
            }

            // Mostrar el número de alumnos y la lista de alumnos de la clase
            System.out.println("Número de alumnos: " + clase.size());
            System.out.println(clase);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



