package com.martin.basic.ui.main;

import android.content.Context
import android.media.MediaPlayer
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.ImageView
import com.martin.basic.BR
import com.martin.basic.R
import com.martin.basic.databinding.ActivityMainBinding
import com.martin.basic.library.app.BaseActivity
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoaderInterface

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainView {
    override fun getVmId(): Int {
        return BR.vm
    }

    private lateinit var player: MediaPlayer

    override fun bindView() {
        super.bindView()
        binding.banner.setImageLoader(object : ImageLoaderInterface<ImageView> {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                Log.d("TAG", path as String?)
            }

            override fun createImageView(context: Context?): ImageView {
                return ImageView(context)
            }
        })
        binding.banner.setBannerAnimation(obtainRandomTransformer())
        val list = listOf("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502731111514&di=43d6c40a5ed660cdf2fbc77ba6a09791&imgtype=0&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201601%2F2016011405.jpg"
                , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502731133190&di=96bcd1d130da71062b81623bcc87009e&imgtype=0&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201410%2F2014102406.jpg"
                , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502731147727&di=0a1663db89b6edc9b8e30135ca28ffa2&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2015%2F131%2F29%2FGB4BEV4492EO.jpg"
                , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502731164167&di=a56633ef30994fe175781ea2487b5c8a&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2015%2F131%2F33%2FD472XQ25C7H2.jpg"
                , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502731178299&di=1a37510a54e4d67ce71c5e710ab9018b&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F160821%2F9-160R1150R1.jpg"
                , "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1374886373,2289223337&fm=26&gp=0.jpg")
        binding.banner.setBannerStyle(BannerConfig.NUM_INDICATOR)
        binding.banner.setImages(list)
        binding.banner.start()


        player = MediaPlayer()
        player.isLooping = true
        player.setDataSource("http://so1.111ttt.com:8282/2017/1/05m/09/298092041338.m4a?tflag=1502723646&pin=aa7ece678b72abbc357a1f40e28b4918&ip=58.251.250.193#.mp3")
        //MediaPlayer.create(this, Uri.parse("http://so1.111ttt.com:8282/2017/1/05m/09/298092041338.m4a?tflag=1502723646&pin=aa7ece678b72abbc357a1f40e28b4918&ip=58.251.250.193#.mp3"))
        player.setOnPreparedListener { it.start() }
        player.prepareAsync()

        val dialog = showLoading()


    }

    private fun obtainRandomTransformer(): Class<out ViewPager.PageTransformer> {
        val n = (Math.random() * 5).toInt()
        return when (n) {
            0 -> {
                Transformer.Accordion
            }
            1 -> {
                Transformer.BackgroundToForeground
            }
            2 -> {
                Transformer.DepthPage
            }
            3 -> {
                Transformer.Tablet
            }
            4 -> {
                Transformer.FlipHorizontal
            }
            5 -> {
                Transformer.ZoomOutSlide
            }
            else -> Transformer.RotateDown
        }
    }

    override fun bindEvent() {
    }

    override fun bindData() {
        binding.vm = vm
    }

    override fun bindViewModel(): MainViewModel {
        return MainViewModel(this)
    }

    override fun bindLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        player.let { player.release() }
        super.onDestroy()

    }
}
