package xmlDom;

import org.w3c.dom.*; // Para Document, Element, Attr, etc.
import javax.xml.parsers.*; // Para DocumentBuilder y DocumentBuilderFactory
import javax.xml.transform.*; // Para Transformer y TransformerFactory
import javax.xml.transform.dom.DOMSource; // Para DOMSource
import javax.xml.transform.stream.StreamResult; // Para StreamResult
import java.io.File; // Para manejar archivos

public class EscrituraDom {

    public static void main(String[] args) {
        // Crear una instancia de Clase y añadir algunos alumnos
        Clase clase = new Clase();
        clase.insertar(new Alumno("23846324J", "Paco", 7.5F, Alumno.Nivel.CFGS));
        clase.insertar(new Alumno("892357237L", "Mario", 8.0F, Alumno.Nivel.CFGM));
        
        // Generar el archivo XML
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            
            // Definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("clase");
            doc.appendChild(eRaiz);
            
            // Recorrer los alumnos y añadirlos al XML
            for (int i = 0; i < clase.size(); i++) {
                Alumno alumno = clase.getAlumno(i);
                
                // Definimos el nodo que contendrá los elementos
                Element eAlumno = doc.createElement("Alumno");
                
                // Atributo para el nodo alumno
                Attr attrDni = doc.createAttribute("dni");
                attrDni.setValue(alumno.getDni());
                eAlumno.setAttributeNode(attrDni);
                
                // Elemento nombre
                Element eNombre = doc.createElement("nombre");
                eNombre.appendChild(doc.createTextNode(alumno.getNombre()));
                eAlumno.appendChild(eNombre);
                
                // Elemento nota
                Element eNota = doc.createElement("nota");
                eNota.appendChild(doc.createTextNode(String.valueOf(alumno.getNota())));
                eAlumno.appendChild(eNota);
                
                // Elemento nivel
                Element eNivel = doc.createElement("nivel");
                eNivel.appendChild(doc.createTextNode(alumno.getNivel().name()));
                eAlumno.appendChild(eNivel);
                
                // Añadir el alumno al elemento raíz
                eRaiz.appendChild(eAlumno);
            }
            
            // Clases necesarias para finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("alumnos.xml"));
            transformer.transform(source, result);
            
            System.out.println("Archivo alumnos.xml generado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
