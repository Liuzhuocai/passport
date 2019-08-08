package elf.m.passportsimple.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import elf.m.passportsimple.R

class BottomBarTab(context: Context, attrs: AttributeSet?, defStyleAttr: Int, icon: Int, text: String) :
    RelativeLayout(context, attrs, defStyleAttr) {
    private var mIcon: ImageView? = null
    private var mTextView: TextView? = null
    private var mContext: Context? = null
    var tabPosition = -1
        set(position) {
            field = position
            if (position == 0) {
                isSelected = true
            }
        }

    constructor(context: Context, @DrawableRes icon: Int, text: String) : this(context, null, icon, text) {}

    constructor(context: Context, attrs: AttributeSet?, icon: Int, text: String) : this(
        context,
        attrs,
        0,
        icon,
        text
    ) {
    }

    init {
        init(context, icon, text)
    }

    private fun init(context: Context, icon: Int, text: String) {
        mContext = context
        val typedArray = context.obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackgroundBorderless))
        val drawable = typedArray.getDrawable(0)
        setBackgroundDrawable(drawable)
        typedArray.recycle()

        LayoutInflater.from(context).inflate(R.layout.view_bottom_bar_tab, this)
        mIcon = findViewById(R.id.icon)
        mIcon!!.setImageResource(icon)

        mTextView = findViewById(R.id.text)
        mTextView!!.text = text
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        if (selected) {
            mIcon!!.isSelected = true
            mTextView!!.isSelected = true
        } else {
            mIcon!!.isSelected = false
            mTextView!!.isSelected = false
        }
    }
}
