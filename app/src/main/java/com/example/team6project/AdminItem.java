package com.example.team6project;

public class AdminItem {
    private String itemName;
    private String address,phone;


    public AdminItem(String itemName, String itemDescription, String phone) {
        this.itemName = itemName;
        this.address = itemDescription;
        this.phone=phone;

    }

    public String getItemName() {
        return itemName;
    }

    public String getAddress() {
        return address;
    }

    public String getItemPhone(){return phone;}


}
