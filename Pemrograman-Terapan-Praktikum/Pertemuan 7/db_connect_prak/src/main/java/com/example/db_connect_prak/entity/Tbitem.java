package com.example.db_connect_prak.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tbitem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "itemId")
    private int itemId;
    @Basic
    @Column(name = "itemName")
    private String itemName;
    @Basic
    @Column(name = "itemPrice")
    private Double itemPrice;
    @Basic
    @Column(name = "itemDescription")
    private String itemDescription;
    @ManyToOne
    @JoinColumn(name = "fk_tbCategory_tbItem", referencedColumnName = "categoryId", nullable = false)
    private Tbcategory fkCategoryItem;

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

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tbitem tbitem = (Tbitem) o;
        return itemId == tbitem.itemId && Objects.equals(itemName, tbitem.itemName) && Objects.equals(itemPrice, tbitem.itemPrice) && Objects.equals(itemDescription, tbitem.itemDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, itemPrice, itemDescription);
    }

    public Tbcategory getFkCategoryItem() {
        return fkCategoryItem;
    }

    public void setFkCategoryItem(Tbcategory fkCategoryItem) {
        this.fkCategoryItem = fkCategoryItem;
    }
}
