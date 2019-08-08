package elf.m.passportsimple.ui.country;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jerry on 5/8/18.
 */

public class RegionBean extends BaseComparatorBean implements Parcelable {
    private static final String TAG = RegionBean.class.getSimpleName();

    //区域的名字
    private String country_name;

    //区域代码
    private long country_code;

    //区域的首字母
    private String first_letter;


    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public long getCountry_code() {
        return country_code;
    }

    public void setCountry_code(int country_code) {
        this.country_code = country_code;
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }



    private static ArrayList<RegionBean> countries = null;

    public RegionBean(long code, String name) {

        String pinyin = PinyinUtils.getPingYin(name);
        String sortString = pinyin.substring(0, 1).toUpperCase();

        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            setFirst_letter(sortString.toUpperCase());
        } else {
            setFirst_letter("#");
        }

        this.country_code = code;
        this.country_name = name;
    }

    @Override
    public String toString() {
        return "RegionBean{" +
                "country_name='" + country_name + '\'' +
                ", country_code=" + country_code +
                ", first_letter='" + first_letter + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.country_name);
        dest.writeLong(this.country_code);
        dest.writeString(this.first_letter);
    }

    protected RegionBean(Parcel in) {
        this.country_name = in.readString();
        this.country_code = in.readInt();
        this.first_letter = in.readString();
    }

    public static final Parcelable.Creator<RegionBean> CREATOR = new Parcelable.Creator<RegionBean>() {
        @Override
        public RegionBean createFromParcel(Parcel source) {
            return new RegionBean(source);
        }

        @Override
        public RegionBean[] newArray(int size) {
            return new RegionBean[size];
        }
    };
}
