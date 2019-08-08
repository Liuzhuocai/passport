package elf.m.passportsimple.ui.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import elf.m.passportsimple.R;
import elf.m.passportsimple.ui.country.CountryAdapter;
import elf.m.passportsimple.ui.country.CountryBean;
import elf.m.passportsimple.ui.country.CountryPresenter;
import elf.m.passportsimple.ui.country.ICountryView;
import elf.m.passportsimple.ui.view.ToolbarControl;
import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by jerry on 5/8/18.
 */

public class CountrySelectActivity extends AppCompatActivity implements ICountryView, CountryAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private CountryPresenter regionPresenter;
    private CountryAdapter mCountryAdapter;
    private ToolbarControl mToolbarControl;
    private LinearLayoutManager linearLayoutManager;
    public  final static String PAR_KEY = "com.ddup.store.ui.activity.country";
    public final  static int REQUEST_CODE = 0x00;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {
            }
        }
        setContentView(R.layout.fragment_country_select);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        initViews();
        bindViews();
        loadData();

    }

    private void initViews(){

        recyclerView = (RecyclerView) findViewById(R.id.rv_coutry);
        mToolbarControl = (ToolbarControl)findViewById(R.id.id_toolbar);

        regionPresenter = new CountryPresenter(CountrySelectActivity.this, this);
        mCountryAdapter = new CountryAdapter(CountrySelectActivity.this, null, this);
        linearLayoutManager = new LinearLayoutManager(CountrySelectActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


    }

    private void bindViews(){


      /*  mToolbarControl.setBackButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/



        recyclerView.setAdapter(mCountryAdapter);

    }

    private void loadData(){

        regionPresenter.loadListRegion();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        regionPresenter.destroy();
    }

    @Override
    public void getListCountry(List<CountryBean> list) {
        //获取列表成功后就构造适配器

        recyclerView.setLayoutManager( linearLayoutManager);
        //recyclerView.addItemDecoration(new TitleItemDecoration(this,list));
        recyclerView.addItemDecoration(new DividerItemDecoration(CountrySelectActivity.this, DividerItemDecoration.VERTICAL));

        mCountryAdapter.setListData(list);
        mCountryAdapter.notifyDataSetChanged();

    }

    @Override
    public void updateListCountry(List<CountryBean> list) {
        mCountryAdapter.setListData(list);
        mCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLetter(String letter) {

    }

    @Override
    public void hideLetter() {

    }


    @Override
    public void onItemClick(View view, CountryBean region) {
        if ( region != null){

        /*    //数据是使用Intent返回
            Intent intent = new Intent();
            //把返回数据存入Intent
            Bundle bundle = new Bundle();
            //设置返回数据

            bundle.putParcelable(PAR_KEY, region);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);*/
            EventBus.getDefault().post(region);
            finish();
        }

    }
}
