package Entities;

import java.util.HashMap;


public class Folio {

    private HashMap<String,Double> Products;
    private double Balance;
    public Folio (){
        Products = new HashMap<String,Double>();
    }
    public HashMap<String, Double> getProducts() {
        return Products;
    }
    public void setProducts(HashMap<String, Double> products) {
        Products = products;
    }
    public double getBalance() {
        return Balance;
    }
    public void setBalance(double balance) {
        Balance = balance;
    }

    @Override
    public String toString() {
        return "Folio{" +
                "Products=" + Products +
                ", Balance=" + Balance +
                '}';
    }
}
