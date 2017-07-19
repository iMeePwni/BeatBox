package com.imeepwni.beatbox.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.imeepwni.beatbox.model.BeatBox
import com.imeepwni.beatbox.model.Sound

/**
 * Created by Guofeng on 2017/7/16.
 */
class SoundViewModel(sound: Sound) : BaseObservable() {

    private var mSound = sound

    @Bindable
    fun getTitle() = mSound.name

    fun getSound() = mSound

    fun setSound(sound: Sound) {
        mSound = sound
        notifyChange()
    }

    fun ouButtonClicked() {
        BeatBox.play(mSound)
    }

}