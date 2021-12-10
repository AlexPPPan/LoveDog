package com.homework.lovedog.base;


import android.os.Parcel;
import android.os.Parcelable;

import com.homework.lovedog.service.ResultCode;


public class BaseRsp implements Parcelable{
    public String result;
    public String message;

    public BaseRsp() {
    }

    protected BaseRsp(Parcel in) {
        result = in.readString();
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
        dest.writeString(message);
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess(){
         ResultCode resultCode=ResultCode.parse(result);
         return  resultCode==ResultCode.SUCCESS;
    }

    public String getResultCodeMessage(){
        ResultCode resultCode=ResultCode.parse(result);
        return resultCode!=null?resultCode.getMessage():"";
    }

    @Override
    public String toString() {
        return "BaseRsp{" +
            "result='" + result + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
