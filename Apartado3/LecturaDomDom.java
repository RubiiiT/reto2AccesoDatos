package xmlDom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LecturaDomDom {

    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
            File archivoXML = new File("alumnos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();

            // Crear una instancia de la clase Clase para reconstruir los datos
            Clase clase = new Clase();

            // Extraer todos los nodos "Alumno"
            NodeList nList = doc.getElementsByTagName("Alumno");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    // Acceder a los nodos hijos
                    String dni = eElement.getAttribute("dni"); // Obtiene el DNI directamente del atributo
                    String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String notaStr = eElement.getElementsByTagName("nota").item(0).getTextContent();
                    String nivelStr = eElement.getElementsByTagName("nivel").item(0).getTextContent();

                    // Validar que los nodos existan antes de acceder a su contenido
                    float nota = Float.parseFloat(notaStr);
                    Alumno.Nivel nivel = Alumno.Nivel.valueOf(nivelStr);

                    // Crear un nuevo objeto Alumno
                    Alumno alumno = new Alumno(dni, nombre, nota, nivel);

                    // Insertar el alumno en la clase
                    clase.insertar(alumno);
                }
            }

            // Mostrar la clase reconstruida
            System.out.println(clase);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
