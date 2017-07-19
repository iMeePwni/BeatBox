package com.imeepwni.beatbox.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.imeepwni.beatbox.R
import com.imeepwni.beatbox.app.App
import com.imeepwni.beatbox.model.BeatBox

/**
 * Created by Guofeng on 2017/7/18.
 */
class BeatBoxViewModel : BaseObservable() {

    @Bindable
    fun getPlayRate(): String? {
        val playRate = (BeatBox.playRate * 100).toInt()
        if (playRate == 0) {
            return App.getInstance().resources.getString(R.string.play_speed_0)
        } else {
            return App.getInstance().resources.getString(R.string.play_speed_other, playRate)
        }
    }

    fun onSeekBarProgressChanged(progress: Float) {
        BeatBox.playRate = progress
        notifyChange()
    }
}