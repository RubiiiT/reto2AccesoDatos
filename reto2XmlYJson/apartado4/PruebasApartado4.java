package reto2XmlYJson.apartado4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.BufferedReader;
import java.io.FileNotFoundException;


import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PruebasApartado4 {
	
	
    public static void main(String[] args) {
        // Configura XStream
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);

        // Configura alias para el XML
        xstream.alias("TradeElementos", TradeElementos.class);
        xstream.alias("TradeElemento", TradeElemento.class);
        xstream.addImplicitCollection(TradeElementos.class, "arrayElementos");
        
        xstream.aliasField("trade_id",TradeElemento.class,"tradeId");
        xstream.aliasField("stock_symbol",TradeElemento.class,"stockSymbol");
        xstream.aliasField("purchase_price",TradeElemento.class,"purchasePrice");
        xstream.aliasField("sale_price",TradeElemento.class,"salePrice");
        xstream.aliasField("trade_date",TradeElemento.class,"tradeDate");

        
        //Aqui lo recuperamos a partir del XML
      TradeElementos clase = null ;
        try {
			clase = (TradeElementos) xstream.fromXML(new FileReader("tradeElementos.xml"));
			System.out.println("CLASE DESDE XML(no la imprimo porque son muchos datos y no se imprime bien)\n\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        //Aqui lo pasamos a JSON y recuperamos 
        
        XStream xstream2 = new XStream(new JettisonMappedXmlDriver());
        xstream2.addPermission(AnyTypePermission.ANY);
        xstream2.alias("TradeElementos", TradeElementos.class);
        xstream2.alias("TradeElemento", TradeElemento.class);
        xstream2.addImplicitCollection(TradeElementos.class, "arrayElementos");
        
        xstream2.aliasField("trade_id",TradeElemento.class,"tradeId");
        xstream2.aliasField("stock_symbol",TradeElemento.class,"stockSymbol");
        xstream2.aliasField("purchase_price",TradeElemento.class,"purchasePrice");
        xstream2.aliasField("sale_price",TradeElemento.class,"salePrice");
        xstream2.aliasField("trade_date",TradeElemento.class,"tradeDate");
        
        String claseString = xstream2.toXML(clase);
        
        guardarEnArchivoTextoPlano(claseString,"TradeElementos.txt");
        
        claseString= recuperarDeArchivoTextoPlano("TradeElementos.txt");
        
        TradeElementos clase2 = (TradeElementos) xstream2.fromXML(claseString);
        
        System.out.println("\n\nCLASE DESDE JSON(no la imprimo porque son muchos datos y no se imprime bien)\n\n");
    }
    
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
