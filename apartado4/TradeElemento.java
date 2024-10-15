package reto2XmlYJson.apartado4;

public class TradeElemento {
    private int tradeId;
    private String stockSymbol;
    private int quantity;
    private double purchasePrice;
    private double salePrice;
    private int tradeDate;

    // Constructor vacío
    public TradeElemento() {}

    // Constructor con parámetros
    public TradeElemento(int tradeId, String stockSymbol, int quantity, double purchasePrice, double salePrice, int tradeDate) {
        this.tradeId = tradeId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.tradeDate = tradeDate;
    }

    // Getters y Setters
    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(int tradeDate) {
        this.tradeDate = tradeDate;
    }

    // Método para mostrar los detalles del TradeElemento
    @Override
    public String toString() {
        return "TradeElemento{" +
                "tradeId=" + tradeId +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", tradeDate=" + tradeDate +
                '}';
    }
}
