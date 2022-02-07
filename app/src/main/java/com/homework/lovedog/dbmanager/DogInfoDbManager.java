package com.homework.lovedog.dbmanager;


import com.homework.lovedog.bean.DogInfo;
import com.homework.lovedog.bean.ImageUrl;
import com.homework.lovedog.utils.MMKVUtils;
import com.homework.lovedog.utils.dbutils.DBUtils;
import com.homework.lovedog.utils.dbutils.DbManager;
import com.homework.lovedog.utils.dbutils.db.sqlite.WhereBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DogInfoDbManager {
    public static final String DOG_INFO_DB = "DogInfo.db";
    public static final String DOG_INFO_TABLE = "dog_info";
    public static final String IMAGE_URL_TABLE = "image_url";

    public static String getResourceDbPath() {
        return MMKVUtils.INSTANCE.getDbPath() + File.separator + DOG_INFO_DB;
    }

    public static String getPwd() {
        return "";
    }

    public static DbManager getDogInfoDb() {
        return DBUtils.getManager(getResourceDbPath(), getPwd());
    }


    public static void saveOrUpdateDogInfo(DogInfo dogInfo) {
        if (dogInfo != null) {
            DbManager dogInfoDb = getDogInfoDb();
            if (dogInfoDb != null) {
                try {
                    dogInfoDb.saveOrUpdate(dogInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        dogInfoDb.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static DogInfo queryDogInfo(int pitId) {
        DbManager dogInfoDb = getDogInfoDb();
        if (dogInfoDb != null) {
            try {
                return dogInfoDb.selector(DogInfo.class)
                    .where(DogInfo.COLUMN_PET_ID, "=", pitId)
                    .findFirst();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    dogInfoDb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void saveImageUrlList(int petId, List<String> imageUrlList) {
        if (imageUrlList != null && imageUrlList.size() > 0) {
            DbManager dogInfoDb = getDogInfoDb();
            if (dogInfoDb != null) {
                try {
                    dogInfoDb.delete(ImageUrl.class, WhereBuilder.b(ImageUrl.COLUMN_PET_ID, "=",
                        petId));
                    for (String url : imageUrlList) {
                        ImageUrl imageUrl = new ImageUrl();
                        imageUrl.setPetId(petId);
                        imageUrl.setUrl(url);
                        dogInfoDb.saveOrUpdate(imageUrl);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        dogInfoDb.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static List<String> queryImageUrlList(int petId) {
        List<String> imageUrlArr = new ArrayList<>();
        DbManager dogInfoDb = getDogInfoDb();
        if (dogInfoDb != null) {
            try {
                List<ImageUrl> imageUrlList = dogInfoDb.selector(ImageUrl.class)
                    .where(ImageUrl.COLUMN_PET_ID, "=", petId).findAll();
                if (imageUrlList != null && imageUrlList.size() > 0) {
                    for (ImageUrl imageUrl : imageUrlList) {
                        imageUrlArr.add(imageUrl.getUrl());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    dogInfoDb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return imageUrlArr;
    }

}
