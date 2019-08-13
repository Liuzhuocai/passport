package elf.m.passportsimple.ui.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.adapter.HomeItemAdapter
import elf.m.passportsimple.ui.bean.HomeInfo
import elf.m.passportsimple.ui.event.ScanEvent
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment
import elf.m.passportsimple.ui.zxing.EasyCaptureActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.util.*

/**

 * author：liuzuo on 19-4-29 15:41

 *

 */
open class HomeFragment:BaseBackFragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()

        super.onActivityCreated(savedInstanceState)
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        EventBus.getDefault().register(this)

    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }
    private fun initView() {
        val arrayList = ArrayList<HomeInfo>()
        home_recycleview.layoutManager = GridLayoutManager(context,2)
        val array = resources.getStringArray(R.array.home_item)

        for (i in array.indices){
                when(i){
                    ACCOUNT_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],AccountFragment.newInstance(array[i]),false))
                    RECHARGE_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],RechargeFragment.newInstance(array[i]),false))
                    TRANSFER_ACCOUNTS_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],CreditTransferFragment.newInstance(array[i]),false))
                    AFTER_SALE_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],AccountFragment.newInstance(array[i]),false))
                    SCAN_QR_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],AccountFragment.newInstance(array[i]),true))
                    MY_QR_OPEN_TYPE ->  arrayList.add(HomeInfo(array[i],MyQRFragment.newInstance(array[i],get(Config.SP_PHONE,"")),false))
                }

        }
        home_recycleview.adapter = HomeItemAdapter(arrayList) {
            if(it.isActivity){
                checkCameraPermissions()
                //startActivity(Intent(_mActivity, EasyCaptureActivity::class.java))
            }else{
                start(it.type)
            }
        }
    }
    @AfterPermissionGranted(RC_CAMERA)
    private fun checkCameraPermissions() {
        if (EasyPermissions.hasPermissions(_mActivity, Manifest.permission.CAMERA)) {//有权限
            startActivity(Intent(_mActivity, EasyCaptureActivity::class.java))
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                this, getString(R.string.permission_camera),
                RC_CAMERA, Manifest.permission.CAMERA
            )
        }
    }
    companion object {

        fun newInstance(): HomeFragment {

            val args = Bundle()

            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
        const val RC_CAMERA = 0X01
        const val RC_READ_PHOTO = 0X02

        const val ACCOUNT_OPEN_TYPE :Int = 0
        const val RECHARGE_OPEN_TYPE :Int = 1
        const val TRANSFER_ACCOUNTS_OPEN_TYPE :Int = 2
        const val AFTER_SALE_OPEN_TYPE :Int = 3
        const val SCAN_QR_OPEN_TYPE :Int = 4
        const val MY_QR_OPEN_TYPE :Int = 5
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBusEvent(event: ScanEvent) {
        start(CreditTransferFragment.newInstance(getString(R.string.home_title_transfer_accounts),event))

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}