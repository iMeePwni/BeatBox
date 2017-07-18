package com.imeepwni.beatbox.view

import android.support.v4.app.Fragment
import com.imeepwni.beatbox.app.SingleFragmentActivity

class BeatBoxActivity : SingleFragmentActivity() {
    override fun getFragment(): Fragment {
        return BeatBoxFragment.newInstance()
    }
}
