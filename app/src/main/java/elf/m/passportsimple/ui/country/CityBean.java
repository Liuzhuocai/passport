package elf.m.passportsimple.ui.country;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jerry on 5/8/18.
 */

public class CityBean implements Parcelable {
    private static final String TAG = CityBean.class.getSimpleName();

    //区域的名字
    private String country_name;

    //区域代码
    private int country_code;


    private int flag;

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getCountry_code() {
        return country_code;
    }

    public void setCountry_code(int country_code) {
        this.country_code = country_code;
    }



    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    private static ArrayList<CityBean> countries = null;

    public CityBean(int code, String name, String locale, int flag) {

        String pinyin = PinyinUtils.getPingYin(name);
        String sortString = pinyin.substring(0, 1).toUpperCase();

        this.country_code = code;
        this.country_name = name;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "RegionBean{" +
                "country_name='" + country_name + '\'' +
                ", country_code=" + country_code +
                ", flag=" + flag +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.country_name);
        dest.writeInt(this.country_code);
        dest.writeInt(this.flag);
    }

    protected CityBean(Parcel in) {
        this.country_name = in.readString();
        this.country_code = in.readInt();
        this.flag = in.readInt();
    }

    public static final Creator<CityBean> CREATOR = new Creator<CityBean>() {
        @Override
        public CityBean createFromParcel(Parcel source) {
            return new CityBean(source);
        }

        @Override
        public CityBean[] newArray(int size) {
            return new CityBean[size];
        }
    };
}
