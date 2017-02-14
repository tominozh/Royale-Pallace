package com.example.tomasaoibh.royaletabs;

/**
 * Created by Tomas & Aoibh on 22/04/2016.
 */
public class Order{

    private String name;
    private int amount;
    private Double price;
    private double total;
    private boolean isMainMeal;
    private boolean isFreeSide;

    public Order(){}
    public Order(String name,int amount,double price,boolean isMainMeal,boolean isFreeSide){
        this.setName(name);
        this.setPrice(price);
        this.setAmount(amount);
        this.setMainMeal(isMainMeal);
        this.setFreeSide(isFreeSide);
    }
    public Order(String name,int amount,double price,double total,boolean isMainMeal,boolean isFreeSide){
        this.setName(name);
        this.setPrice(price);
        this.setAmount(amount);
        this.setTotal(total);
        this.setMainMeal(isMainMeal);
        this.setFreeSide(isFreeSide);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
       String info="Meal "+this.getName() + "\n"
                  +"Price "+this.getPrice() +"\n"
                  +"Amount "+this.getAmount() +"\n"
                  +"Total for this meal " + this.getTotal() +"\n"
                 +"-----------------------------------------------\n";
        return info;
    }

    public boolean isMainMeal() {
        return isMainMeal;
    }

    public void setMainMeal(boolean mainMeal) {
        isMainMeal = mainMeal;
    }

    public boolean isFreeSide() {
        return isFreeSide;
    }

    public void setFreeSide(boolean freeSide) {
        isFreeSide = freeSide;
    }
}
