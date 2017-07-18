package com.imeepwni.beatbox.model

import android.content.res.AssetManager
import android.media.SoundPool
import com.imeepwni.beatbox.app.App
import com.orhanobut.logger.Logger
import java.io.IOException

/**
 * Created by Guofeng on 2017/7/16.
 */
object BeatBox {
    val TAG = javaClass.simpleName!!
    val mAssets: AssetManager by lazy {
        App.getInstance().assets
    }
    private val SOUND_FOLDER = "sample_sounds"
    val mSounds by lazy {
        arrayListOf<Sound>().apply {
            try {
                val soundNames = mAssets.list(SOUND_FOLDER)
                Logger.t(TAG).i("Found ${soundNames.size} sounds")
                soundNames.forEach {
                    this.add(Sound(it))
                }
            } catch (ioe: IOException) {
                Logger.t(TAG).e("Could not list assets", ioe)
            }
        }
    }
    private val MAX_SOUNDS = 5
    val mSoundPool by lazy {
        SoundPool.Builder()
                .setMaxStreams(MAX_SOUNDS) // 同时播放的最大个数
    }
}