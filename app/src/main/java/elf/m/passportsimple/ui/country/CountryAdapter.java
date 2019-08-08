package elf.m.passportsimple.ui.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import elf.m.passportsimple.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 5/8/18.
 */


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<CountryBean> mData;
    private Context mContext;

    public CountryAdapter(Context context, List<CountryBean> data, OnItemClickListener click) {
        mInflater = LayoutInflater.from(context);
        mData = data;
        this.mContext = context;
        this.mOnItemClickListener = click;
    }

    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CountryAdapter.ViewHolder(mInflater.inflate(R.layout.item_country_info, parent,false));
    }

    @Override
    public void onBindViewHolder(final CountryAdapter.ViewHolder holder, final int position) {


        holder.tvName.setText(this.mData.get(position).getCountry_name());
        ArrayList<CityBean> cityBeans = this.mData.get(position).getCityBeans();
        if(cityBeans!=null && cityBeans.size()>0){
            //holder.tvCode.setText("+" + this.mData.get(position).getCountry_code());
            holder.tvCode.setVisibility(View.VISIBLE);
        }else {
            holder.tvCode.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(holder.itemView, mData.get(position));
                }
                Toast.makeText(mContext, mData.get(position).getCountry_name(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    /*
    * item click callback
    * */

    public interface OnItemClickListener {
        void onItemClick(View view, CountryBean countryBean);
    }

    private CountryAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(CountryAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvCode;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCode = (TextView) itemView.findViewById(R.id.tvCode);
        }
    }

    /**
     * 提供给Activity刷新数据
     * @param list
     */
    public void updateList(List<CountryBean> list){
        this.mData = list;
        notifyDataSetChanged();
    }

    public Object getItem(int position) {
        return mData.get(position);
    }


    public void setListData(List<CountryBean> listData) {
        this.mData = listData;

    }


}
