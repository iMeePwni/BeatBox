package com.imeepwni.beatbox.model

/**
 * Created by Guofeng on 2017/7/16.
 */
data class Sound(val assetPath: String) {
    val name = assetPath.split("/").last().replace(".wav", "")
}