package com.homework.lovedog.bean;


import com.homework.lovedog.dbmanager.DogInfoDbManager;
import com.homework.lovedog.utils.dbutils.db.annotation.Column;
import com.homework.lovedog.utils.dbutils.db.annotation.Table;

import java.util.List;
@Table(name = DogInfoDbManager.DOG_INFO_TABLE)
public class DogInfo {
   public static final String COLUMN_PET_ID="petID";
   public static final String COLUMN_NAME="name";
   public static final String COLUMN_ENG_NAME="engName";
   public static final String COLUMN_CHARACTER="character";
   public static final String COLUMN_EN_CHARACTER="enCharacter";
   public static final String COLUMN_NATION="nation";
   public static final String COLUMN_EN_NATION="enNation";
   public static final String COLUMN_EASY_OF_DISEASE="easyOfDisease";
   public static final String COLUMN_EN_EASY_OF_DISEASE="enEasyOfDisease";
   public static final String COLUMN_LIFE="life";
   public static final String COLUMN_EN_LIFE="enLife";
   public static final String COLUMN_PRICE="price";
   public static final String COLUMN_EN_PRICE="enPrice";
   public static final String COLUMN_DES="des";
   public static final String COLUMN_EN_DES="enDes";
   public static final String COLUMN_FEATURE="feature";
   public static final String COLUMN_EN_FEATURE="enFeature";
   public static final String COLUMN_CHARACTER_FEATURE="characterFeature";
   public static final String COLUMN_EN_CHARACTER_FEATURE="enCharacterFeature";
   public static final String COLUMN_CARE_KNOWLEDGE="careKnowledge";
   public static final String COLUMN_EN_CARE_KNOWLEDGE="enCareKnowledge";
   public static final String COLUMN_FEED_POINTS="feedPoints";
   public static final String COLUMN_EN_FEED_POINTS="enFeedPoints";
   public static final String COLUMN_IMAGE_URL_JSON="imageURLJson";

   @Column(name = COLUMN_PET_ID,autoGen = false,isId = true)
   private int petID;
   @Column(name = COLUMN_NAME)
   private String name;
   @Column(name = COLUMN_ENG_NAME)
   private String engName;
   @Column(name = COLUMN_CHARACTER)
   private String character;
   @Column(name = COLUMN_EN_CHARACTER)
   private String enCharacter;
   @Column(name = COLUMN_NATION)
   private String nation;
   @Column(name = COLUMN_EN_NATION)
   private String enNation;
   @Column(name = COLUMN_EASY_OF_DISEASE)
   private String easyOfDisease;
   @Column(name = COLUMN_EN_EASY_OF_DISEASE)
   private String enEasyOfDisease;
   @Column(name = COLUMN_LIFE)
   private String life;
   @Column(name = COLUMN_EN_LIFE)
   private String enLife;
   @Column(name = COLUMN_PRICE)
   private String price;
   @Column(name = COLUMN_EN_PRICE)
   private String enPrice;
   @Column(name = COLUMN_DES)
   private String des;
   @Column(name = COLUMN_EN_DES)
   private String enDes;
   @Column(name = COLUMN_FEATURE)
   private String feature;
   @Column(name = COLUMN_EN_FEATURE)
   private String enFeature;
   @Column(name = COLUMN_CHARACTER_FEATURE)
   private String characterFeature;
   @Column(name = COLUMN_EN_CHARACTER_FEATURE)
   private String enCharacterFeature;
   @Column(name = COLUMN_CARE_KNOWLEDGE)
   private String careKnowledge;
   @Column(name = COLUMN_EN_CARE_KNOWLEDGE)
   private String enCareKnowledge;
   @Column(name = COLUMN_FEED_POINTS)
   private String feedPoints;
   @Column(name = COLUMN_EN_FEED_POINTS)
   private String enFeedPoints;
   private List<String> imageURL;
   @Column(name = COLUMN_IMAGE_URL_JSON)
   private String imageURLJson;

   public int getPetID() {
      return petID;
   }

   public void setPetID(int petID) {
      this.petID = petID;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEngName() {
      return engName;
   }

   public void setEngName(String engName) {
      this.engName = engName;
   }

   public String getCharacter() {
      return character;
   }

   public void setCharacter(String character) {
      this.character = character;
   }

   public String getNation() {
      return nation;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   public String getEasyOfDisease() {
      return easyOfDisease;
   }

   public void setEasyOfDisease(String easyOfDisease) {
      this.easyOfDisease = easyOfDisease;
   }

   public String getLife() {
      return life;
   }

   public void setLife(String life) {
      this.life = life;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getDes() {
      return des;
   }

   public void setDes(String des) {
      this.des = des;
   }

   public String getFeature() {
      return feature;
   }

   public void setFeature(String feature) {
      this.feature = feature;
   }

   public String getCharacterFeature() {
      return characterFeature;
   }

   public void setCharacterFeature(String characterFeature) {
      this.characterFeature = characterFeature;
   }

   public String getCareKnowledge() {
      return careKnowledge;
   }

   public void setCareKnowledge(String careKnowledge) {
      this.careKnowledge = careKnowledge;
   }

   public String getFeedPoints() {
      return feedPoints;
   }

   public void setFeedPoints(String feedPoints) {
      this.feedPoints = feedPoints;
   }

   public List<String> getImageURL() {
      return imageURL;
   }

   public void setImageURL(List<String> imageURL) {
      this.imageURL = imageURL;
   }

   public String getEnCharacter() {
      return enCharacter;
   }

   public void setEnCharacter(String enCharacter) {
      this.enCharacter = enCharacter;
   }

   public String getEnNation() {
      return enNation;
   }

   public void setEnNation(String enNation) {
      this.enNation = enNation;
   }

   public String getEnEasyOfDisease() {
      return enEasyOfDisease;
   }

   public void setEnEasyOfDisease(String enEasyOfDisease) {
      this.enEasyOfDisease = enEasyOfDisease;
   }

   public String getEnLife() {
      return enLife;
   }

   public void setEnLife(String enLife) {
      this.enLife = enLife;
   }

   public String getEnPrice() {
      return enPrice;
   }

   public void setEnPrice(String enPrice) {
      this.enPrice = enPrice;
   }

   public String getEnDes() {
      return enDes;
   }

   public void setEnDes(String enDes) {
      this.enDes = enDes;
   }

   public String getEnFeature() {
      return enFeature;
   }

   public void setEnFeature(String enFeature) {
      this.enFeature = enFeature;
   }

   public String getEnCharacterFeature() {
      return enCharacterFeature;
   }

   public void setEnCharacterFeature(String enCharacterFeature) {
      this.enCharacterFeature = enCharacterFeature;
   }

   public String getEnCareKnowledge() {
      return enCareKnowledge;
   }

   public void setEnCareKnowledge(String enCareKnowledge) {
      this.enCareKnowledge = enCareKnowledge;
   }

   public String getEnFeedPoints() {
      return enFeedPoints;
   }

   public void setEnFeedPoints(String enFeedPoints) {
      this.enFeedPoints = enFeedPoints;
   }

   public String getImageURLJson() {
      return imageURLJson;
   }

   public void setImageURLJson(String imageURLJson) {
      this.imageURLJson = imageURLJson;
   }
}
