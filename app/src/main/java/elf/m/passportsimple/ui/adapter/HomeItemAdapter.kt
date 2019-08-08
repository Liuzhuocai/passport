package elf.m.passportsimple.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.bean.HomeInfo

/**

 * author：liuzuo on 19-6-17 11:34

 *

 */
class HomeItemAdapter(private val date :List<HomeInfo>,private var mItemListener: ((HomeInfo) -> Unit)? ) : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.homeItemTextView.text = date[position].title
        holder.homeItemTextView.setOnClickListener {
            mItemListener?.invoke(date[position])
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_card, parent, false))
    }


    override fun getItemCount(): Int {
        return date.size
    }
   /* fun setItemListener(itemListener: (homeInfo: HomeInfo) -> Unit){
        mItemListener = itemListener
    }*/
}
class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
     var homeItemTextView: TextView = itemView.findViewById(R.id.home_title)

    init {
        //homeItemTextView.setCompoundDrawablesWithIntrinsicBounds(SelectedDrawable(homeItemTextView.context), null, null, null)
    }

}

/*
interface OnItemClickListener {
    fun onItemClick(target:BaseBackFragment )
}*/
