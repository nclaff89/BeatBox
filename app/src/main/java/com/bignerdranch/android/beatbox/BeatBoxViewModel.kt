package com.bignerdranch.android.beatbox


import android.content.res.AssetManager
import androidx.lifecycle.ViewModel

/**
 * Add this entire class
 * for chapter 20 challenge 2
 */
class BeatBoxViewModel(assets: AssetManager): ViewModel() {
val bb = BeatBox(assets)

    /**
     * This is the important part, we are going to
     * release the Soundpool here, and remove this line from the main activity.
     */
    override fun onCleared() {
        super.onCleared()
        bb.release()
    }
}