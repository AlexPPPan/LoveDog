package com.homework.lovedog.base;


import android.os.Parcel;
import android.os.Parcelable;

import com.homework.lovedog.service.ResultCode;


public class BaseRsp implements Parcelable{
    public String statusCode;
    public String desc;

    public BaseRsp() {
    }

    protected BaseRsp(Parcel in) {
        statusCode = in.readString();
        desc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(statusCode);
        dest.writeString(desc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseRsp> CREATOR = new Creator<BaseRsp>() {
        @Override
        public BaseRsp createFromParcel(Parcel in) {
            return new BaseRsp(in);
        }

        @Override
        public BaseRsp[] newArray(int size) {
            return new BaseRsp[size];
        }
    };

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSuccess(){
         ResultCode resultCode=ResultCode.parse(statusCode);
         return  resultCode==ResultCode.SUCCESS;
    }

    public String getResultCodeMessage(){
        ResultCode resultCode=ResultCode.parse(statusCode);
        return resultCode!=null?resultCode.getMessage():"";
    }

    @Override
    public String toString() {
        return "BaseRsp{" +
            "result='" + statusCode + '\'' +
            ", message='" + desc + '\'' +
            '}';
    }
}
