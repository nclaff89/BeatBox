package com.bignerdranch.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(private val beatBox: BeatBox): BaseObservable() {


    fun onButtonClicked() {
        sound?.let{
           beatBox.play(it, speed!!)
        }

    }

    var sound: Sound? = null
        set(sound){
            field = sound
            notifyChange()

        }
    @get: Bindable
    val title: String?
        get() = sound?.name

    /**
     * Chapter 20 challenge 1
     */
    var speed: Float? = 1.0f
        set(speed){
            field = speed
            notifyChange()
        }
//    @get: Bindable
//    val playbackSpeed: Float?
//        get() = sound?.speed
}