package com.martin.basic.ui.router

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.os.Build
import android.transition.Fade
import com.martin.basic.BR
import com.martin.basic.R
import com.martin.basic.databinding.ActivityRouterBinding
import com.martin.basic.library.app.BaseActivity
import com.martin.basic.library.database.DatabaseProxy
import com.martin.basic.library.ex.routerTo
import com.martin.basic.library.log.LogX
import com.martin.basic.ui.login.LoginActivity

/**
 * Created by Martin on 2017/8/20.
 * Hello World
 */
class RouterActivity : BaseActivity<ActivityRouterBinding, RouterViewModel>(), RouterView {

    override fun beforeViewSet() {
        super.beforeViewSet()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.enterTransition = Fade()
        }
    }

    override fun getVmId(): Int = BR.vm

    override fun bindEvent() {
    }

    override fun bindData() {
    LogX.e(null)
        val proxy = Room.databaseBuilder(this, DatabaseProxy::class.java, "hh.db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                    }
                }).addMigrations(object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase?) {
            }
        }).allowMainThreadQueries().build()

        val users = proxy.userDao().getAll()
        showToast("${users.size}     ")

    }

    override fun openLoginActivity() {
        routerTo(LoginActivity::class.java)
    }

    override fun bindViewModel(): RouterViewModel = RouterViewModel(this)

    override fun bindLayoutId(): Int = R.layout.activity_router
}