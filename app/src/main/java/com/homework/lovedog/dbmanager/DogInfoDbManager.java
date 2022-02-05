package com.homework.lovedog.dbmanager;


import com.homework.lovedog.bean.DogInfo;
import com.homework.lovedog.utils.MMKVUtils;
import com.homework.lovedog.utils.dbutils.DBUtils;
import com.homework.lovedog.utils.dbutils.DbManager;

import java.io.File;
import java.io.IOException;

public class DogInfoDbManager {
   public static final String DOG_INFO_DB ="DogInfo.db";
   public static final String DOG_INFO_TABLE ="dog_info";

   public static String getResourceDbPath() {
      return MMKVUtils.INSTANCE.getDbPath()+ File.separator  + DOG_INFO_DB;
   }

   public static String getPwd() {
      return "";
   }

   public static DbManager getDogInfoDb() {
      return DBUtils.getManager(getResourceDbPath(), getPwd());
   }


   public static void saveOrUpdateDogInfo(DogInfo dogInfo){
      if(dogInfo!=null) {
         DbManager dogInfoDb = getDogInfoDb();
         if(dogInfoDb!=null) {
            try {
               dogInfoDb.saveOrUpdate(dogInfo);
            } catch (Exception e) {
               e.printStackTrace();
            }finally {
               try {
                  dogInfoDb.close();
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
         }
      }
   }

   public static DogInfo queryDogInfo(int pitId){
      DbManager dogInfoDb = getDogInfoDb();
      if(dogInfoDb!=null) {
         try {
           return dogInfoDb.selector(DogInfo.class)
                .where(DogInfo.COLUMN_PET_ID,"=",pitId)
                .findFirst();
         } catch (Exception e) {
            e.printStackTrace();
         }finally {
            try {
               dogInfoDb.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
      return null;
   }

}
