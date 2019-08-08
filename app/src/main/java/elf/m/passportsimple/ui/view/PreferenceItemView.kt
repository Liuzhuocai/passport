package elf.m.passportsimple.ui.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import elf.m.passportsimple.R

/**

 * author：liuzuo on 19-8-1 15:09

 *

 */
class PreferenceItemView : RelativeLayout {


    private var imageTypeView: ImageView? = null
    private var textShowView: TextView? = null
    private val imagePointView: ImageView? = null
    private var textTipView: TextView? = null
    private var textExtView: TextView? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PreferenceItemView)
        val isCoexist = typedArray.getBoolean(R.styleable.PreferenceItemView_next_tip_coexist, false)
        if (isCoexist) {
            layoutInflater.inflate(R.layout.view_singleitem_show_one, this)
        } else {
            layoutInflater.inflate(R.layout.view_singleitem_show, this)
        }
        initTyped(typedArray)
    }


    /*
    * 设置布局属性
    * */
    private fun initTyped(typedArray: TypedArray) {

        textShowView = findViewById<View>(R.id.text_show) as TextView
        if (textShowView != null) {
            val textValue = typedArray.getString(R.styleable.PreferenceItemView_item_text)
            textShowView!!.text = textValue
        }

        textTipView = findViewById<View>(R.id.text_ext_tips) as TextView
        if (textTipView != null) {
            val textValue = typedArray.getString(R.styleable.PreferenceItemView_item_tip)
            val isCoexist = typedArray.getBoolean(R.styleable.PreferenceItemView_next_tip_coexist, false)

            if (textValue != null) {
                textTipView!!.text = textValue
                if (!isCoexist) {
                    findViewById<View>(R.id.image_icon_point).visibility = View.GONE
                }
            }

        }

        val textValue = typedArray.getString(R.styleable.PreferenceItemView_item_ext_info)
        if (textValue != null) {
            textExtView = findViewById<View>(R.id.text_ext_info) as TextView
            textExtView!!.visibility = View.VISIBLE
            textExtView!!.text = textValue

            findViewById<View>(R.id.image_icon_point).visibility = View.GONE
            findViewById<View>(R.id.image_icon_type).visibility = View.GONE

        }


        imageTypeView = findViewById<View>(R.id.image_icon_type) as ImageView
        if (imageTypeView != null) {
            val imageSrc = typedArray.getResourceId(R.styleable.PreferenceItemView_image_type_src, 0)
            imageTypeView!!.setImageResource(imageSrc)

            if (imageSrc == 0) {
                findViewById<View>(R.id.image_icon_type).visibility = View.GONE
            }

        }



        typedArray.recycle()

    }

    //设置图片资源
    private fun setImgResource(resId: Int) {
        imageTypeView!!.setImageResource(resId)
    }


    //设置控件背景图片
    private fun setBgImage(resId: Int) {
        imageTypeView!!.setImageResource(resId)
    }

    //设置图片的高度和宽度
    private fun setImageSize(width: Int, height: Int) {
        val layoutParams = RelativeLayout.LayoutParams(width, height)
        imageTypeView!!.layoutParams = layoutParams
    }

    //设置图片的不透明度
    private fun setImageAlpha(alpha: Float) {
        imageTypeView!!.alpha = alpha
    }

    //设置文字内容
    private fun setText(text: String) {
        textShowView!!.text = text
    }

    fun setTipText(text: String) {
        textTipView!!.text = text
    }

    //设置文字颜色
    private fun setTextColor(colorValue: Int) {
        textShowView!!.setTextColor(colorValue)
    }

    //设置文字大小
    private fun setTextSize(size: Int) {
        textShowView!!.textSize = size.toFloat()
    }

    interface MixTextImageListener {
        fun mClick()
    }


}
