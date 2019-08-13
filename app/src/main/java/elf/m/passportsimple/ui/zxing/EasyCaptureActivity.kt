package elf.m.passportsimple.ui.zxing

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.king.zxing.CaptureActivity
import com.king.zxing.DecodeFormatManager
import com.king.zxing.Intents
import com.king.zxing.util.CodeUtils
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config.REQUEST_CODE_PHOTO
import elf.m.passportsimple.ui.Config.REQUEST_CODE_SCAN
import elf.m.passportsimple.ui.event.ScanEvent
import elf.m.passportsimple.ui.utils.StatusBarUtils
import elf.m.passportsimple.ui.utils.toast
import kotlinx.android.synthetic.main.toolbar_capture.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.greenrobot.eventbus.EventBus
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**

 * author：liuzuo on 19-8-12 10:15

 *

 */
class EasyCaptureActivity : CaptureActivity() {
    val KEY_TITLE = "key_title"
    val KEY_IS_QR_CODE = "key_code"
    val KEY_IS_CONTINUOUS = "key_continuous_scan"

    override fun getLayoutId(): Int {
        return R.layout.easy_capture_activity
    }

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        toolbar_right_image.visibility = View.VISIBLE
        StatusBarUtils.immersiveStatusBar(this, toolbar, 0.2f)
        /*   TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(getIntent().getStringExtra(KEY_TITLE));*/
        captureHelper
            .decodeFormats(DecodeFormatManager.QR_CODE_FORMATS)//设置只识别二维码会提升速度
            .playBeep(true)
            .vibrate(true)
        toolbar.setBackButtonOnClickListener {
            finish()
            true
        }
        toolbar_right_image.setOnClickListener{
            val pickIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(pickIntent, REQUEST_CODE_PHOTO)

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_CODE_SCAN -> {
                    val result = data.getStringExtra(Intents.Scan.RESULT)
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                }
                REQUEST_CODE_PHOTO -> parsePhoto(data)
            }

        }
    }
    fun getGalleryCodeObservable(path:String): Observable<String> {
        return Observable.create {

            it.onNext(CodeUtils.parseCode(path))
            it.onCompleted()
        }
    }
    private fun parsePhoto(data: Intent) {
        val path = UriUtils.INSTANCE.getImagePath(this, data)
        Log.d("Jenly", "path:$path")
        if (TextUtils.isEmpty(path)) {
            return
        }
        getGalleryCodeObservable(path)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                EventBus.getDefault().post(ScanEvent(it))
                toast(R.string.scan_complete)
                finish()
            },{

            },{

            })
    }
    fun onClick(v: View) {
        when (v.getId()) {

        }/* case R.id.ivLeft:
                onBackPressed();
                break;*/
    }

    override fun onResultCallback(result: String?): Boolean {
        Log.d("liuzuo99", "result=" + result!!)
        EventBus.getDefault().post(ScanEvent(result))
        toast(R.string.scan_complete)
        finish()
        return super.onResultCallback(result)
    }
}