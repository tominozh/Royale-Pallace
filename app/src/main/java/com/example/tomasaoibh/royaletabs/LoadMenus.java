package com.example.tomasaoibh.royaletabs;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HaladekT on 05/04/2016.
 */
public class LoadMenus {

    private static ArrayList<Meal> mainMeal;
    private static ArrayList<Meal> myAppetizers;
    private static ArrayList<Meal> mySides;
    private static ArrayList<Meal> myVegetables;
    private static ArrayList<Meal> myExtras;
    private static ArrayList<Meal> myOffers;
    private static ArrayList<Order> cart;
    private static ArrayList<Meal> mySoup;
    private static Context context;

    public LoadMenus(Context cntx) {
        this.context = cntx;
    }

    public static ArrayList<Meal> getMainMeal() {
        int mealCounter = 1;
        if (mainMeal == null) {
            mainMeal = new ArrayList<Meal>();
            mainMeal.add(new Meal(mealCounter++, "Crispy Shredded Beef", 8.50));
            mainMeal.add(new Meal(mealCounter++, "Crispy Shredded Chicken", 8.50));
            mainMeal.add(new Meal(mealCounter++, "Brest of Chicken - diced", 7.60));
            mainMeal.add(new Meal(mealCounter++, "Brest of Chicken - whole", 7.60));
            mainMeal.add(new Meal(mealCounter++, "Crispy Chicken - Hong Kong Style", 8.00));
            mainMeal.add(new Meal(mealCounter++, "Crispy Tiger Prawn", 8.50));
            mainMeal.add(new Meal(mealCounter++, "Roast Duck Dishes", 8.50));
            mainMeal.add(new Meal(mealCounter++, "King Prawn Dishes", 8.50));
            mainMeal.add(new Meal(mealCounter++, "House Special Dishes: Mixture of Tiger Prawn, Chicken,Beef and Roast Pork", 8.50));
            mainMeal.add(new Meal(mealCounter++, "Fillet of Beef Dishes", 9.50));
            mainMeal.add(new Meal(mealCounter++, "Chicken Dishes", 7.20));
            mainMeal.add(new Meal(mealCounter, "Beef Dishes", 7.20));

        }
        return mainMeal;
    }

    public static ArrayList<Meal> getAppetizers() {
        int starterCounter = 1;
        if (myAppetizers == null) {
            myAppetizers = new ArrayList<Meal>();
            myAppetizers.add(new Meal(starterCounter++, "House Combination Platter for 2", 8.80));
            myAppetizers.add(new Meal(starterCounter++, "Yuk Sung for 2", 10.00));
            myAppetizers.add(new Meal(starterCounter++, "Crispy aromatic duck 1/4", 8.50));
            myAppetizers.add(new Meal(starterCounter++, "Thai Spicy Chicken Roll", 3.80));
            myAppetizers.add(new Meal(starterCounter++, "Chicken Cheese Roll", 3.80));
            myAppetizers.add(new Meal(starterCounter++, "Spring Roll", 2.80));
            myAppetizers.add(new Meal(starterCounter++, "Vegetable Spring Roll", 2.80));
            myAppetizers.add(new Meal(starterCounter++, "Spare Ribs", 5.80));
            myAppetizers.add(new Meal(starterCounter++, "Crispy Chicken Wings", 4.00));
            myAppetizers.add(new Meal(starterCounter++, "Deep Fried Shredded Chicken with Salt & Pepper", 5.00));
            myAppetizers.add(new Meal(starterCounter++, "Baked Prawns with Salt & Chilli", 5.50));
            myAppetizers.add(new Meal(starterCounter++, "Skewered Chicken Satay Sauce", 4.50));
            myAppetizers.add(new Meal(starterCounter++, "Crispy Won Ton served with Sweet&Sour Sauce", 4.50));
            myAppetizers.add(new Meal(starterCounter++, "Sesame Prawn Toast", 4.50));
            myAppetizers.add(new Meal(starterCounter++, "Crispy Curry Puff ", 3.80));
            myAppetizers.add(new Meal(starterCounter++, "Chicken Ball - half portion (5)", 2.80));
            myAppetizers.add(new Meal(starterCounter, "Chicken Ball - full portion (10)", 5.20));
        }
        return myAppetizers;
    }

    public static ArrayList<Meal> getSoup() {
        int mealCounter = 1;
        if (mySoup == null) {
            mySoup = new ArrayList<Meal>();
            mySoup.add(new Meal(mealCounter++, "Chicken Sweet Corn Soup", 2.80));
            mySoup.add(new Meal(mealCounter++, "Crab Meat Sweet Corn Soup", 3.20));
            mySoup.add(new Meal(mealCounter++, "Chicken Mushroom Soup", 3.20));
            mySoup.add(new Meal(mealCounter++, "Chicken Noodles Soup", 3.20));
            mySoup.add(new Meal(mealCounter++, "Hot and Sour Soup", 3.20));
            mySoup.add(new Meal(mealCounter++, "Won Ton Soup", 3.20));
            mySoup.add(new Meal(mealCounter, "Yom Yam Soup", 4.00));
        }
        return mySoup;
    }

    public static ArrayList<Meal> getSides() {
        int mealCounter = 1;
        if (mySides == null) {
            mySides = new ArrayList<Meal>();
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Boiled Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Noodles", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Chips", 3.88));
            mySides.add(new Meal(mealCounter++, "Egg Rice", 3.99));
            mySides.add(new Meal(mealCounter++, "Fried Rice", 3.99));

        }
        return mySides;
    }

    public static ArrayList<Meal> getVegetables() {
        int mealCounter = 1;
        if (myVegetables == null) {
            myVegetables = new ArrayList<Meal>();
            myVegetables.add(new Meal(mealCounter++, "Soft Drink 300 ml - Can", 0.99));
            myVegetables.add(new Meal(mealCounter++, "Soft Drink 500 ml", 1.99));
            myVegetables.add(new Meal(mealCounter++, "Juice", 2.33));

        }
        return myVegetables;
    }

    public static ArrayList<Meal> getExtras() {
        int mealCounter = 1;
        if (myExtras == null) {
            myExtras = new ArrayList<Meal>();
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
            myExtras.add(new Meal(mealCounter++, "Add Delivery", 0.99));
            myExtras.add(new Meal(mealCounter++, "Extra", 3.99));
            myExtras.add(new Meal(mealCounter++, "Something", 4.88));
        }
        return myExtras;
    }

    public static ArrayList<Meal> getOffers() {
        int offerCount = 1;
        if (myOffers == null) {
            myOffers = new ArrayList<Meal>();
            myOffers.add(new Meal(offerCount++, "Familly Deal", 14.99));
            myOffers.add(new Meal(offerCount++, "2+2 Deal", 14.99));

        }
        return myOffers;
    }

    public static ArrayList<Order> getCart() {
        if (cart == null) {
            cart = new ArrayList<Order>();
        }
        return cart;
    }

    public static void addToCart(Order order) {
        if (cart == null) {
            cart = new ArrayList<Order>();
        }
        cart.add(order);


    }

    public static boolean wipeCart() {
        if (cart != null) {
            int sizeCart = cart.size();
            for (int i = 0; i < sizeCart; i++) {
                cart.remove(0);
            }
            return true;
        } else {
            Toast.makeText(context, "Cart empty", Toast.LENGTH_LONG).show();
        }
        return false;
    }
    public static void deleteOrder(int num){
        cart.remove(num);
    }
   }
