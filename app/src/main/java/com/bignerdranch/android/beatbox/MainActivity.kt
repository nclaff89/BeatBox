package com.bignerdranch.android.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.beatbox.databinding.ActivityMainBinding
import com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    /**
     * Chapter 20 challenge 1
     */
    private var playbackSpeed = 1.0f //default playback speed if the user doesn't change seekbar
    private lateinit var seekbar: SeekBar
    private lateinit var seekTV: TextView
    private var previousPercentChange = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }

        /**
         * Chapter 20 challenge 1
         */
        seekTV = play_back_speed_tv
        seekbar = seek_bar
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekTV.text = "${seekbar.progress}%"
                val currentPercentChange = seekBar!!.progress
                Log.d("currentPercentage", "currentP = $currentPercentChange")
                if (previousPercentChange == 0) {
                    playbackSpeed += (currentPercentChange * 0.01).toFloat()
                }else{
                    val diff = Math.abs(currentPercentChange - previousPercentChange)
                    if(currentPercentChange < previousPercentChange){
                        playbackSpeed -= (diff * 0.01).toFloat()
                    }else {
                        playbackSpeed += (diff * 0.01).toFloat()
                    }
                }
                previousPercentChange = currentPercentChange
                Log.d("playbackSpeed", "playbackspeed = $playbackSpeed")
                updateUI()

            }
        })


    }

    override fun onDestroy(){
        super.onDestroy()
        beatBox.release()
    }

    /**
     * Chapter 20 challenge 1. This probably isn't the most efficient way
     * to go about it, however, we need to update every sound object once the
     * playback speed is updated any way....
     */
    private fun updateUI(){
        recycler_view.adapter = SoundAdapter(beatBox.sounds)
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding):
            RecyclerView.ViewHolder(binding.root){

        init {
            binding.viewModel = SoundViewModel(beatBox)
        }

        fun bind(sound: Sound){
            binding.apply {
                viewModel?.sound = sound
                /**
                 * Chapter 20 challenge 1
                 */
                viewModel?.speed = playbackSpeed
                executePendingBindings()
            }
        }

    }

    private inner class SoundAdapter(private val sounds: List<Sound>):
            RecyclerView.Adapter<SoundHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)

        }

        override fun getItemCount() = sounds.size
    }
}
