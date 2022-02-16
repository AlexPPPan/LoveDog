package com.homework.lovedog.bean;

import com.homework.lovedog.dbmanager.DogInfoDbManager;
import com.homework.lovedog.utils.dbutils.db.annotation.Column;
import com.homework.lovedog.utils.dbutils.db.annotation.Table;

@Table(name = DogInfoDbManager.IMAGE_URL_TABLE)
public class ImageUrl {

    public static final String COLUMN_ID ="id";
    public static final String COLUMN_PET_ID ="patId";
    public static final String COLUMN_URL ="url";

    @Column(name = COLUMN_ID,isId = true,autoGen = true)
    private int id;
    @Column(name = COLUMN_PET_ID)
    private int petId;
    @Column(name = COLUMN_URL)
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

