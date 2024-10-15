package reto2XmlYJson.apartado4;



import java.util.ArrayList;


public class TradeElementos {
    private ArrayList<TradeElemento> arrayElementos;
    
    public TradeElementos() {
    	
    }
    
    public TradeElementos( ArrayList<TradeElemento> arrayElementos) {
    	this.arrayElementos=arrayElementos;
    }

    public ArrayList<TradeElemento> getArrayElementos() {
        return arrayElementos;
    }

    public void setArrayElementos(ArrayList<TradeElemento> TradeElemento) {
        this.arrayElementos = TradeElemento;
    }

    @Override
    public String toString() {
        return "TradeElementos{" +
                "TradeElemento=" + arrayElementos +
                '}';
    }
}
