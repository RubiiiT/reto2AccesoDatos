package reto2XmlYJson.apartado1;

import java.io.File;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.persistence.XmlArrayList;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;

public class XmlPersistenetLeer {
    
    protected static String fichero = "xmlPersistente";

    public static void main(String[] args) {
        
        // Configurar XStream con permisos de seguridad antes de la persistencia
        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);  // Permitir todas las clases (solo para desarrollo)

        // Asignar el XStream configurado a la estrategia de persistencia
        PersistenceStrategy strategy = new FilePersistenceStrategy(new File(fichero), xstream);
        XmlArrayList lista = new XmlArrayList(strategy);

        
      //Configuracion para escribir y leer alias 
        /*
         * SIN ESTO; EL PROGRAMA NO FUNCIONA
         */
        xstream.alias("Clase", Clase.class);
        xstream.alias("Alumno", Alumno.class);
        
        xstream.aliasField("NombreAlumno", Alumno.class, "nombre");
        xstream.aliasField("NotaCurso", Alumno.class, "nota");
        
        xstream.aliasAttribute(Alumno.class,"dni","DocumentoIdentidad");
        
        
        // Leer los objetos del archivo XML
        Clase[] arrayClase = new Clase[lista.size()];
        int contador = 0;

        for (Iterator it = lista.iterator(); it.hasNext(); ) {
        	Clase claseAux=(Clase) it.next();
            arrayClase[contador] = claseAux;
            contador++;
            System.out.println(claseAux.toString());
           
        }
        
        Clase c3 = arrayClase[0];
        Clase c4 = arrayClase[1];
       // System.out.println(c3.toString());
       // System.out.println(c4.toString());
    }
}
