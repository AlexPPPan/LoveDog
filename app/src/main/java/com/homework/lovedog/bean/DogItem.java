package com.homework.lovedog.bean;

import com.homework.lovedog.dbmanager.DogInfoDbManager;
import com.homework.lovedog.utils.dbutils.DbManager;
import com.homework.lovedog.utils.dbutils.db.annotation.Column;
import com.homework.lovedog.utils.dbutils.db.annotation.Table;

@Table(name = DogInfoDbManager.DOG_ITEM_TABLE)
public class DogItem {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PET_ID = "petID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ENG_NAME = "engName";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_EN_PRICE = "enPrice";
    public static final String COLUMN_COVER_URL = "coverURL";

    @Column(name = COLUMN_ID, isId = true, autoGen = true)
    private int id;
    @Column(name = COLUMN_PET_ID)
    private int petID;
    @Column(name = COLUMN_NAME)
    private String name;
    @Column(name = COLUMN_ENG_NAME)
    private String engName;
    @Column(name = COLUMN_PRICE)
    private String price;
    @Column(name = COLUMN_EN_PRICE)
    private String enPrice;
    @Column(name = COLUMN_COVER_URL)
    private String coverURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getEngName() {
        return engName;
    }

    public void setEngName(java.lang.String engName) {
        this.engName = engName;
    }

    public java.lang.String getPrice() {
        return price;
    }

    public void setPrice(java.lang.String price) {
        this.price = price;
    }

    public java.lang.String getEnPrice() {
        return enPrice;
    }

    public void setEnPrice(java.lang.String enPrice) {
        this.enPrice = enPrice;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
}
