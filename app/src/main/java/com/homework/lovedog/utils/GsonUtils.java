package com.homework.lovedog.utils;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonUtils {
   private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

   public static  Gson getGson(){
      return  gson;
   }

   public static <T> T fromJson(String json,Class<T> clz){

      return  gson.fromJson(json,clz);
   }

   public static <T> T fromJson(String json, Type type){

      return  gson.fromJson(json,type);
   }

   public static String toJsonStr(Object object){
      if(object==null) {
          return null;
      }
      return gson.toJson(object);
   }

   public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz)

   {
      if(TextUtils.isEmpty(json)) {
          return null;
      }
      Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
      ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
      ArrayList<T> arrayList = new ArrayList<T>();
      for (JsonObject jsonObject : jsonObjects)
      {
         arrayList.add(new Gson().fromJson(jsonObject, clazz));
      }
      return arrayList;
   }

}
