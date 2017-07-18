package com.imeepwni.beatbox.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.imeepwni.beatbox.R

/**
 * Created by Guofeng on 2017/7/16.
 */
abstract class SingleFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)

        supportFragmentManager.run {
            if (findFragmentById(R.id.container) == null) {
                beginTransaction()
                        .add(R.id.container, getFragment())
                        .commit()
            }
        }
    }

    abstract fun getFragment(): Fragment
}