package elf.m.passportsimple.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import elf.m.passportsimple.R
import kotlinx.android.synthetic.main.item_account_detail.view.*

/**

 * author：liuzuo on 19-7-23 10:57

 *

 */
abstract class AccountBaseAdapter<T>(context: Context) : RecyclerView.Adapter<AccountBaseAdapter<T>.VH>() {
    override fun getItemCount(): Int {
        Log.d("liuzuo99", "getItemCount="+mItems.size)
        return mItems.size
    }

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
     var mItems:ArrayList<T> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = mInflater.inflate(R.layout.item_account_detail, parent, false)
        /* holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val position = holder.adapterPosition
        })*/
        return VH(view)
    }

/*    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = mItems[position]

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
//        ViewCompat.setTransitionName(holder.img, position.toString() + "_image")
//        ViewCompat.setTransitionName(holder.tvTitle, position.toString() + "_tv")
        Log.d("liuzuo99", "onBindViewHolder=$position")
//        holder.tvAmount.text = item.amount.toString()
//        holder.tvDate.text = item.spent_at
    }*/

    open fun setData(items: ArrayList<T>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
        Log.d("liuzuo99", "setData="+mItems.size)
    }





    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvAmount: TextView = itemView.account_date as TextView
        var tvDate: TextView = itemView.account_text as TextView

    }
}
