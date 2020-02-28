package com.bignerdranch.android.beatbox

import android.app.Activity
import android.content.res.AssetManager
import androidx.lifecycle.ViewModel

class BeatBoxViewModel(assets: AssetManager): ViewModel() {
val bb = BeatBox(assets)
//    val assets = assets
// private val _beatbox = MutableLiveData<BeatBox>()
//    val beatBox: LiveData<BeatBox>
//        get() = _beatbox
//
//
//    private fun createBeatBox(){
//        _beatbox.postValue(BeatBox(assets))
//    }
//
//    fun getBB(): LiveData<BeatBox>{
//        createBeatBox()
//        return beatBox
//    }
}