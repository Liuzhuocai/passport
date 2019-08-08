package elf.m.passportsimple.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import elf.m.passportsimple.R
import kotlinx.android.synthetic.main.toolbar_layout.view.*


/**
 * Created by jerry on 5/3/18.
 */

class ToolbarControl : Toolbar, View.OnClickListener {

    private var mNormalBackText: String? = null
    private var titleText: String? = null



    private var actionButtonClickListener: ActionButtonClickListener? = null

    var backText: String?
        get() = mNormalBackText
        set(titleText) {
            this.mNormalBackText = titleText
            if (toolbar_back_text != null) {
                toolbar_back_text!!.text = mNormalBackText
            }
        }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        setContentInsetsRelative(0, 0)
        val view = LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this)
        val a = getContext().obtainStyledAttributes(
            attrs, R.styleable.ToolbarControl, 0, 0
        )

        titleText = a.getString(R.styleable.ToolbarControl_titleText)
        toolbar_title.text = titleText

        mNormalBackText = a.getString(R.styleable.ToolbarControl_backText)
        toolbar_back_text.text = mNormalBackText

        val imageSrc = a.getResourceId(R.styleable.ToolbarControl_backimage, 0)
        toolbar_back_button!!.setImageResource(imageSrc)

        a.recycle()

        toolbar_left_text.setOnClickListener(this)
        toolbar_right_text.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === toolbar_left_text && actionButtonClickListener != null) {
            actionButtonClickListener!!.onLeftTextClick(v)
        } else if (v === toolbar_right_text && actionButtonClickListener != null) {
            actionButtonClickListener!!.onRightTextClick(v)
        }
    }

    fun setTitle(titleStr: String) {
        if (toolbar_title != null) {
            toolbar_title!!.text = titleStr
        }
    }

    fun setTitleByResourceId(rid: Int) {
        if (toolbar_title != null) {
            toolbar_title!!.setText(rid)
        }
    }

    fun hide() {
        this.visibility = View.GONE
    }

    fun setBackButtonOnClickListener(listener: () -> Boolean) {
        if (toolbar_back_button != null ) {
            toolbar_back_button!!.setOnClickListener{ listener.invoke() }
        }
        if (toolbar_back_text != null ) {
            toolbar_back_text!!.setOnClickListener{listener.invoke()}
        }
    }

    private fun setNormalBackImageResource(resId: Int) {
        toolbar_back_button!!.setImageResource(resId)
    }

    fun setLeftText(resId: Int) {
        toolbar_left_text!!.setText(resId)
    }

    fun setLeftText(text: String) {
        toolbar_left_text!!.text = text
    }

    fun setRightText(resId: Int) {
        toolbar_right_text!!.setText(resId)
    }

    fun setRightText(text: String) {
        toolbar_right_text!!.text = text
    }

    fun showBackButton(show: Boolean) {
        showView(show, toolbar_back_button)
    }

    fun showLeftText(show: Boolean) {
        showView(show, toolbar_left_text)
    }

    fun showRightText(show: Boolean) {
        showView(show, toolbar_right_text)
    }

    fun showBack(show: Boolean) {
        showView(show, toolbar_back_button)
        showView(show, toolbar_back_text)
    }

    fun showBottomLine(show: Boolean) {
        showView(show, view_line)
    }

    private fun showView(show: Boolean, view: View?) {
        if (view != null) {
            if (show) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }
    }

    fun setActionButtonClickListener(actionButtonClickListener: ActionButtonClickListener) {
        this.actionButtonClickListener = actionButtonClickListener
    }

    interface ActionButtonClickListener {
        fun onLeftTextClick(view: View)
        fun onRightTextClick(view: View)
    }

    companion object {

        private val TAG = ToolbarControl::class.java.simpleName
    }

}
