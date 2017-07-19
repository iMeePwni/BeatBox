package com.imeepwni.beatbox.model

/**
 * Created by Guofeng on 2017/7/16.
 */
data class Sound(val assetPath: String,
                 var soundId: Int? = null) {
    val name = assetPath.split("/").last().replace(".wav", "")
}