package com.bignerdranch.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(private val beatBox: BeatBox): BaseObservable() {
    fun onButtonClicked() {
        sound?.let{
            beatBox.play(it)
        }

    }

    fun resume(){
        sound?.let{
            beatBox.resume(it)
        }
    }

    fun pause(){
        sound?.let{
            beatBox.pause(it)
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
}