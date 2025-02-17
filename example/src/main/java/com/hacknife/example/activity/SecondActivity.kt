package com.hacknife.example.activity

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import com.hacknife.appfloat.AppFloat
import com.hacknife.appfloat.anim.DefaultAnimator
import com.hacknife.appfloat.enums.SidePattern
import com.hacknife.appfloat.interfaces.OnFloatAnimator
import com.hacknife.appfloat.interfaces.OnInvokeView
import com.hacknife.example.R
import com.hacknife.example.startActivity
import kotlinx.android.synthetic.main.activity_second.*
import kotlin.random.Random

/**
 * @author: liuzhenfeng
 * @function:
 * @date: 2019-06-28  16:10
 */
class SecondActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvShow.setOnClickListener {
            AppFloat.with(this)
                .setLayout(R.layout.float_top_dialog,
                    OnInvokeView {
                        it.postDelayed({ AppFloat.dismiss(this, it.tag.toString()) }, 2500)
                    })
                .setMatchParent(true)
                .setSidePattern(SidePattern.TOP)
                .setDragEnable(false)
                .setTag(Random.nextDouble().toString())
                .setAnimator(object : DefaultAnimator() {
                    override fun enterAnim(
                        view: View, parentView: ViewGroup, sidePattern: SidePattern
                    ): Animator? = super.enterAnim(view, parentView, sidePattern)?.apply {
                        interpolator = BounceInterpolator()
                    }

                    override fun exitAnim(
                        view: View, parentView: ViewGroup, sidePattern: SidePattern
                    ): Animator? = super.exitAnim(view, parentView, sidePattern)?.setDuration(300)
                })
                .show()
        }

        floatingView.apply {
            config.floatAnimator = object : OnFloatAnimator {
                override fun enterAnim(
                    view: View, parentView: ViewGroup, sidePattern: SidePattern
                ): Animator = AnimatorSet().apply {
                    play(ObjectAnimator.ofFloat(view, "alpha", 0f, 0.3f, 1f))
                        .with(ObjectAnimator.ofFloat(view, "scaleX", 0f, 2f, 1f))
                        .with(ObjectAnimator.ofFloat(view, "scaleY", 0f, 2f, 1f))
                        .before(ObjectAnimator.ofFloat(view, "rotation", 0f, 360f, 0f))
                    duration = 1000
                }
            }
        }

        openThird.setOnClickListener { startActivity<ThirdActivity>(this) }
    }

}