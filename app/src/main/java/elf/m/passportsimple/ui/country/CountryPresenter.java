package elf.m.passportsimple.ui.country;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import elf.m.passportsimple.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jerry on 5/8/18.
 */

public class CountryPresenter {
    private static final String TAG = CountryPresenter.class.getSimpleName();
    private Context context;
    private ICountryView regionView;
    private PinyinComparator mComparator;
    List<CountryBean> fullData ;

    public CountryPresenter(Context context, ICountryView view) {
        this.context = context;
        this.regionView = view;
        mComparator = new PinyinComparator();
    }

    public void loadListRegion() {

        fullData = getAll(context);
        Collections.sort(fullData, mComparator);
        regionView.getListCountry(fullData);
    }

    public void filterListData( String key){

       List<CountryBean> filterData = new ArrayList<>();

       if ( TextUtils.isEmpty(key)){

           filterData = getAll(context);

       }else {

           for (CountryBean sortModel : fullData) {

               String name = sortModel.getCountry_name();

               if (name.indexOf(key.toString()) != -1 ||
                       PinyinUtils.getFirstSpell(name).startsWith(key.toString())
                       //不区分大小写
                       || PinyinUtils.getFirstSpell(name).toLowerCase().startsWith(key.toString())
                       || PinyinUtils.getFirstSpell(name).toUpperCase().startsWith(key.toString())
                       ) {
                   filterData.add(sortModel);
               }
           }
       }
        Collections.sort(filterData, mComparator);
        fullData.clear();
        fullData.addAll(filterData);

        regionView.updateListCountry(fullData);

    }

    public ArrayList<CountryBean> getAll(@NonNull Context ctx) {
        ArrayList<CountryBean> mDataList = new ArrayList<>();
        boolean inChina = inChina(ctx);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(ctx.getResources().getAssets().open("code.json")));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null)
                sb.append(line);
            br.close();
            JSONArray ja = new JSONArray(sb.toString());
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                int flag = 0;
                String locale = jo.getString("code");



                if(!TextUtils.isEmpty(locale)) {
                    flag = ctx.getResources().getIdentifier("flag_" + locale.toLowerCase(), "drawable", ctx.getPackageName());
                }
                mDataList.add(new CountryBean(jo.getInt("code"), jo.getString(inChina? "zh" : "en"), locale, flag));
            }

            Log.i(TAG, mDataList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mDataList;
    }

    public  static RegionBean qurey(@NonNull Context ctx, int code) {


        boolean inChina = inChina(ctx);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(ctx.getResources().getAssets().open("code.json")));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null)
                sb.append(line);
            br.close();
            JSONArray ja = new JSONArray(sb.toString());
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                int flag = 0;
                String locale = jo.getString("locale");

                if( code == jo.getInt("code")){
                    return new RegionBean(jo.getInt("code"), jo.getString(inChina? "zh" : "en"));

                }


                if(!TextUtils.isEmpty(locale)) {
                    flag = ctx.getResources().getIdentifier("flag_" + locale.toLowerCase(), "drawable", ctx.getPackageName());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public  void destroy(){ fullData = null; }

    private static boolean inChina(Context ctx) {
        return false/*"CN".equalsIgnoreCase(ctx.getResources().getConfiguration().locale.getCountry())*/;
    }

    public static RegionBean getDefaultRegion(Context context){
        boolean isInChina = inChina(context);

        return new RegionBean(86, context.getString(R.string.default_region));
    }
}
