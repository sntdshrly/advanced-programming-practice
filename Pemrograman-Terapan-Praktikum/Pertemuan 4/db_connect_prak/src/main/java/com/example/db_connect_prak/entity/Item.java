package com.example.db_connect_prak.entity;

public class Item {
    private int itemId;
    private String itemName;
    private float itemPrice;
    private String itemDescription;
    private Category fkCategoryItem;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Category getFkCategoryItem() {
        return fkCategoryItem;
    }

    public void setFkCategoryItem(Category fkCategoryItem) {
        this.fkCategoryItem = fkCategoryItem;
    }
}
