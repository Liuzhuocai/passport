package elf.m.passportsimple.ui.adapter

import android.content.Context
import android.util.Log
import elf.m.passportsimple.ui.http.data.result.RechargeListResultData

/**

 * author：liuzuo on 19-7-23 10:57

 *

 */
class AccountRechargeAdapter(context: Context) : AccountBaseAdapter<RechargeListResultData>(context) {
    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = mItems[position]

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
//        ViewCompat.setTransitionName(holder.img, position.toString() + "_image")
//        ViewCompat.setTransitionName(holder.tvTitle, position.toString() + "_tv")
        Log.d("liuzuo99", "onBindViewHolder=$position")
        holder.tvAmount.text = item.amount.toString()
        holder.tvDate.text = item.created_at
    }


}
