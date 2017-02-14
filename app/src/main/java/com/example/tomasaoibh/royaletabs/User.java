package com.example.tomasaoibh.royaletabs;

/**
 * Created by Tomas & Aoibh on 26/04/2016.
 */
public class User {
    private String name;
    private String address;
    private String town;
    private String phone;
    private String email;

    public User() {
    }

    public User(String nm, String add, String twn, String phn, String eml) {
        this.setName(nm);
        this.setAddress(add);
        this.setTown(twn);
        this.setPhone(phn);
        this.setEmail(eml);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n\nName " + this.getName() + "\n")
                .append("Address " + this.getAddress() + "\n")
                .append("Town " + this.getTown() + "\n")
                .append("Phone " + this.getPhone() + "\n")
                .append("Email " + this.getEmail() + "\n");
        return sb.toString();
    }
}
