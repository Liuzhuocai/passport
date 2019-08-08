package elf.m.passportsimple.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import elf.m.passportsimple.R
import elf.m.passportsimple.ui.Config
import elf.m.passportsimple.ui.fragment.*
import elf.m.passportsimple.ui.fragment.base.BaseActivity
import elf.m.passportsimple.ui.fragment.base.BaseBackFragment
import elf.m.passportsimple.ui.http.RetrofitManager
import elf.m.passportsimple.ui.view.BottomBar
import elf.m.passportsimple.ui.view.BottomBarTab
import kotlinx.android.synthetic.main.activity_main.*
import me.yokeyword.fragmentation.SupportFragment
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : BaseActivity() ,BaseBackFragment.OnBackToFirstListener{
    override fun onBackToFirstFragment() {
        bottomBar!!.setCurrentItem(0)
    }

    private var fragments = arrayOfNulls<SupportFragment>(4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView (R.layout.activity_main)
        //titleText.text = getString(R.string.app_name)
        val newFirstFragment = findFragment<NewFirstFragment>(NewFirstFragment::class.java)
        if (newFirstFragment == null) {
            fragments[FIRST] = NewFirstFragment.newInstance()
            fragments[SECOND] = SecondFragment.newInstance()
            fragments[THIRD] = ThirdFragment.newInstance()
            fragments[FOURTH] = FourthFragment.newInstance()

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                fragments[FIRST],
                fragments[SECOND],
                fragments[THIRD],
                fragments[FOURTH])
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            fragments[FIRST] = newFirstFragment
            fragments[SECOND] = findFragment<SecondFragment>(SecondFragment::class.java)
            fragments[THIRD] = findFragment<ThirdFragment>(ThirdFragment::class.java)
            fragments[FOURTH] = findFragment<FourthFragment>(FourthFragment::class.java)
        }
        initBottomBar()
        checkJwttoken()
    }

    private fun checkJwttoken() {
        RetrofitManager.serverTime(get(Config.JWTTOKEN,""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("liuzuo99", "onNext=$it")

                //AccountManager.get(applicationContext).addAccountExplicitly(Account("passport","all"),get(Config.JWTTOKEN,""),Bundle())
                if (it.Success) {

                }else{
                    getJwttoken()
                }

            }, {
                Log.d("liuzuo99", "checkJwttoken onError=$it")
                //enterLoginActivity()
                getJwttoken()
            }, {
                Log.d("liuzuo99", "onCompleted")
            })
    }

    private fun enterLoginActivity() {
        put(Config.JWTTOKEN, "")
        put(Config.JWTREFRESHTOKEN, "")
        put(Config.USER_ID, "")
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun getJwttoken() {
        RetrofitManager.getToken(get(Config.JWTREFRESHTOKEN,""),get(Config.USER_ID,""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("liuzuo99", "onNext=$it")
                put(Config.JWTTOKEN, it.Result.jwttoken)
                put(Config.JWTREFRESHTOKEN, it.Result.jwtrefreshtoken)
                //AccountManager.get(applicationContext).addAccountExplicitly(Account("passport","all"),get(Config.JWTTOKEN,""),Bundle())
                if (it.Success) {
                    put(Config.JWTTOKEN, it.Result.jwttoken)
                    put(Config.JWTREFRESHTOKEN, it.Result.jwtrefreshtoken)
                    put(Config.USER_ID, it.Result.user_id)
                }else{
                    enterLoginActivity()
                }

            }, {
                Log.d("liuzuo99", "onError=$it")
                enterLoginActivity()
            }, {
                Log.d("liuzuo99", "onCompleted")

            })
    }
    private fun initBottomBar() {
        bottomBar.addItem( BottomBarTab(this, R.drawable.selector_bottom_bar_icon_apps, getString(R.string.bottom_bar_home)))
        .addItem( BottomBarTab(this, R.drawable.selector_bottom_bar_icon_search, getString(R.string.bottom_bar_cloud)))
        .addItem( BottomBarTab(this, R.drawable.selector_bottom_bar_icon_search, getString(R.string.bottom_bar_website)))
        .addItem( BottomBarTab(this, R.drawable.selector_bottom_bar_icon_mine, getString(R.string.bottom_bar_mine)))
        .setOnTabSelectedListener(object : BottomBar.OnTabSelectedListener {
                override fun onTabSelected(position: Int, prePosition: Int) {
                    showHideFragment(fragments[position], fragments[prePosition])
                }

                override fun onTabUnselected(position: Int) {

                }

                override fun onTabReselected(position: Int) {
                    val currentFragment = fragments[position]
                    val count = currentFragment?.childFragmentManager?.backStackEntryCount

                    // 如果不在该类别Fragment的主页,则回到主页;
                    if (count!! > 1) {
                        when (currentFragment) {
                            is NewFirstFragment -> currentFragment.popToChild(TestFragment::class.java, false)
                            is SecondFragment -> currentFragment.popToChild(HomeFragment::class.java, false)
                            is ThirdFragment -> currentFragment.popToChild(WebsiteFragment::class.java, false)
                            is FourthFragment -> currentFragment.popToChild(MineFragment::class.java, false)
                        }
                        return
                    }

                    // 这里推荐使用EventBus来实现 -> 解耦
                    //                if (count == 1) {
                    //                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    //                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    //                    EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));
                    //                }
                }
    })
    }
    companion object {

        const val FIRST = 0
        const val SECOND = 1
        const val THIRD = 2
        const val FOURTH = 3
        private val CODE_CHECK_PERMISSION = 1001
    }
}
