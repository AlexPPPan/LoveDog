package com.homework.lovedog.bean;


import java.util.List;

public class TranslateResult {

   /**
    * from : zh
    * to : en
    * trans_result : [{"src":"非常聪明友好、喜欢吠叫","dst":"Very smart, friendly and barking"}]
    */

   private String from;
   private String to;
   /**
    * src : 非常聪明友好、喜欢吠叫
    * dst : Very smart, friendly and barking
    */

   private List<TransResultBean> trans_result;

   public String getFrom() {
      return from;
   }

   public void setFrom(String from) {
      this.from = from;
   }

   public String getTo() {
      return to;
   }

   public void setTo(String to) {
      this.to = to;
   }

   public List<TransResultBean> getTrans_result() {
      return trans_result;
   }

   public void setTrans_result(List<TransResultBean> trans_result) {
      this.trans_result = trans_result;
   }

   public static class TransResultBean {
      private String src;
      private String dst;

      public String getSrc() {
         return src;
      }

      public void setSrc(String src) {
         this.src = src;
      }

      public String getDst() {
         return dst;
      }

      public void setDst(String dst) {
         this.dst = dst;
      }
   }
}
