package com.imeepwni.beatbox.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imeepwni.beatbox.R
import com.imeepwni.beatbox.databinding.FragmentBeatBoxBinding
import com.imeepwni.beatbox.databinding.ListItemSoundBinding
import com.imeepwni.beatbox.model.BeatBox
import com.imeepwni.beatbox.model.Sound
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil
                .inflate<FragmentBeatBoxBinding>(inflater, R.layout.fragment_beat_box, container, false)
        with(binding.recyclerView) {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = SoundAdapter()
        }
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BeatBox.mSounds
    }

    inner class SoundHolder(binding: ListItemSoundBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding

        fun bind(sound: Sound) {
            mBinding.run {
                viewModel.setSound(sound)
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
            binding.viewModel = SoundViewModel(Sound("test"))
            return SoundHolder(binding)
        }
    }
}