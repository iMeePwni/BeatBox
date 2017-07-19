package com.imeepwni.beatbox.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.imeepwni.beatbox.R
import com.imeepwni.beatbox.databinding.FragmentBeatBoxBinding
import com.imeepwni.beatbox.databinding.ListItemSoundBinding
import com.imeepwni.beatbox.model.BeatBox
import com.imeepwni.beatbox.model.Sound
import com.imeepwni.beatbox.viewmodel.BeatBoxViewModel
import com.imeepwni.beatbox.viewmodel.SoundViewModel

/**
 * Created by Guofeng on 2017/7/16.
 */
class BeatBoxFragment : Fragment() {

    companion object {
        fun newInstance(): BeatBoxFragment {
            return BeatBoxFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 已保留的Fragment不会随activity一起销毁并重建，而是一直保留在需要时，原封不动的转给新的activity
        retainInstance = true
        /*
        必须同时满足以下两个条件，Fragment才能进入保留状态：
        1）已调用的Fragment的setRetainInstance(true)方法；
        2）因设备配置改变（通常为设备旋转），托管的activity正在被销毁。

        Fragment只能保留非常短的时间，即从Fragment脱离旧activity到重新附加给快速新建的activity之间的一段时间。
         */
        /*
        是否保留Fragment
        1）相比不保留的Fragment，已保留的Fragment用起来更加复杂。一旦出现问题，问题排查非常耗时。既然它会让程序
        变得复杂，能不用就不用。
        2）Fragment在使用保存实例状态时也能够应对所有生命周期场景；但保留的Fragment只能应付Activity因设备旋转而销毁的
        情况。如果Activity是因系统回收内存而被销毁，则多有保留Fragment也会随之被销毁，数据也就更着丢啦。
         */
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil
                .inflate<FragmentBeatBoxBinding>(inflater, R.layout.fragment_beat_box, container, false)
        with(binding) {
            viewModel = BeatBoxViewModel()
            with(recyclerView) {
                layoutManager = GridLayoutManager(activity, 3)
                adapter = SoundAdapter()
            }
            with(seekBar) {
                max = 100
                progress = (BeatBox.playRate * 100).toInt()
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        viewModel?.onSeekBarProgressChanged((progress.toFloat() / max.toFloat()))
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {}
                    override fun onStopTrackingTouch(p0: SeekBar?) {}
                })
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        BeatBox.loadSounds()
    }

    override fun onDestroy() {
        super.onDestroy()
        BeatBox.release()
    }

    inner class SoundHolder(binding: ListItemSoundBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding

        fun bind(sound: Sound) {
            mBinding.run {
                viewModel!!.setSound(sound)
                executePendingBindings()
            }
        }
    }

    inner class SoundAdapter : RecyclerView.Adapter<SoundHolder>() {
        lateinit var binding: ListItemSoundBinding

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            holder.bind(BeatBox.mSounds[position])
        }

        override fun getItemCount(): Int {
            return BeatBox.mSounds.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SoundHolder {
            binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                    LayoutInflater.from(activity),
                    R.layout.list_item_sound,
                    parent,
                    false)
            with(binding) {
                viewModel = SoundViewModel(Sound("test"))
                root.setOnClickListener { (viewModel as SoundViewModel).ouButtonClicked() }
            }
            return SoundHolder(binding)
        }
    }
}