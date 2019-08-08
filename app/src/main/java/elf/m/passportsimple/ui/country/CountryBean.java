package elf.m.passportsimple.ui.country;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jerry on 5/8/18.
 */

public class CountryBean extends BaseComparatorBean implements Parcelable {
    private static final String TAG = CountryBean.class.getSimpleName();

    //区域的名字
    private String country_name;

    //区域代码
    private int country_code;


    private int flag;

    public ArrayList<CityBean> getCityBeans() {
        return cityBeans;
    }

    public void setCityBeans(ArrayList<CityBean> cityBeans) {
        this.cityBeans = cityBeans;
    }

    //下一级城市
    private ArrayList<CityBean> cityBeans;


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

    private static ArrayList<CountryBean> countries = null;

    public CountryBean(int code, String name, String locale, int flag) {
        super(code,name,locale,flag);

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
        dest.writeTypedList(this.cityBeans);
    }

    protected CountryBean(Parcel in) {
        this.country_name = in.readString();
        this.country_code = in.readInt();
        this.flag = in.readInt();
        this.cityBeans = in.createTypedArrayList(CityBean.CREATOR);
    }

    public static final Creator<CountryBean> CREATOR = new Creator<CountryBean>() {
        @Override
        public CountryBean createFromParcel(Parcel source) {
            return new CountryBean(source);
        }

        @Override
        public CountryBean[] newArray(int size) {
            return new CountryBean[size];
        }
    };
}
