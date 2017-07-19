package com.imeepwni.beatbox.model

import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.AudioManager
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
    val mSoundPool: SoundPool by lazy {
        SoundPool.Builder()
                .setMaxStreams(MAX_SOUNDS) // 同时播放的最大个数
                .setAudioAttributes(AudioAttributes.Builder()
                        .setLegacyStreamType(AudioManager.STREAM_MUSIC) // STREAM_MUSIC是音乐和游戏常用的音量控制常量
                        .build())
                .build()
    }

    fun loadSounds() {
        mSounds.forEach {
            try {
                it.soundId = mSoundPool.load(mAssets.openFd(SOUND_FOLDER + "/" + it.assetPath), 1)
            } catch (ioe: IOException) {
                Logger.t(TAG).e("loadSounds $it fail, throw ioe $ioe")
            }
        }

    }

    fun play(sound: Sound) {
        val soundId = sound.soundId
        if (soundId == null) {
            Logger.t(TAG).d("$sound id is null")
        } else {
            // loop: 0代表不循环,-1代表无限循环.
            mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, playRate)
        }
    }

    var playRate = 1.0f

    fun release() {
        mSoundPool.release()
    }
}