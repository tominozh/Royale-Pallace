package com.example.tomasaoibh.royaletabs;

/**
 * Created by HaladekT on 05/04/2016.
 */
public class Meal {
    private int _id;
    private String _mealName;
    private double _mealPrice;
    private double _total;
    private int _amount;
    private String sauce;


    public Meal() {
    }

    public Meal(int iD, String nm, double prc) {
        this.set_id(iD);
        this.set_mealName(nm);
        this.set_mealPrice(prc);
    }

    public Meal(int iD, String nm, Double prc, int amount,String sauce) {
        this._id = iD;
        this._mealName = nm;
        this._mealPrice = prc;
        this.set_amount(amount);
        this.setSauce(sauce);

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_mealName() {
        return _mealName;
    }

    public void set_mealName(String _mealName) {
        this._mealName = _mealName;
    }

    public double get_mealPrice() {
        return _mealPrice;
    }

    public void set_mealPrice(double _mealPrice) {
        this._mealPrice = _mealPrice;
    }

    public double get_total() {
        return this._mealPrice*this._amount;
    }

    public int get_amount() {
        return _amount;
    }

    public void set_amount(int _amount) {
        this._amount = _amount;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }
}
