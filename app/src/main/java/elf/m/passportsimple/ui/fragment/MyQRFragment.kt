package elf.m.passportsimple.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.king.zxing.util.CodeUtils
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.fragment.base.BaseActionBarFragment
import kotlinx.android.synthetic.main.fragment_my_qr.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class MyQRFragment: BaseActionBarFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_my_qr, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString("phone")!!.let {

            initQR(it)
        }
    }

    private fun initQR(it: String) {
        getMyQRBitmapObservable(it)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                my_qr_iv.setImageBitmap(it)
            },{

            },{

            })
    }
    fun getMyQRBitmapObservable(phone:String): Observable<Bitmap> {
        return Observable.create {

            it.onNext(CodeUtils.createQRCode(phone, 600, null))
            it.onCompleted()
        }
    }

    companion object {

        fun newInstance(title:String,phone:String): MyQRFragment {

            val args = Bundle()

            val fragment = MyQRFragment()
            args.putString("phone",phone)
            args.putString("title",title)
            fragment.arguments = args
            return fragment
        }
    }

}