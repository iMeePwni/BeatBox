package com.imeepwni.beatbox.viewmodel

import com.imeepwni.beatbox.model.Sound
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Guofeng on 2017/7/18.
 */
class SoundViewModelTest {
    /*在本书其他地方声明SoundViewModel的时候，命名一般是mSoundViewModel。
    在这里用mSubject。这是一种习惯约定，原因有以下两点：
    1）很清楚就知道，mSubject是要测试的对象（与其他对象区别开来
    2）如果SoundViewModel里面有任何方法要移到其他类，如BeatBoxSoundViewModel,
    那么测试方法可以直接复制过去，省了mSoundViewModel到mBeatBoxSoundViewModel
    重命名的麻烦。
     */
    private lateinit var mSubject: SoundViewModel
    private lateinit var mSound: Sound

    @Before
    fun setUp() {
        mSound = Sound("assetPath")
        mSubject = SoundViewModel(mSound)
    }

    @Test
    fun exposesSoundNameAsTitle() {
        Assert.assertEquals(mSound.name, mSubject.getTitle())
    }

}