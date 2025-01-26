package com.example.joiefull.common

import android.content.Context
import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.joiefull.features.domain.model.Clothes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LikesViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val likesMap = mutableMapOf<Int, MutableStateFlow<Int>>()
    private val isLikedMap = mutableMapOf<Int, MutableStateFlow<Boolean>>()

    // Function to get the likes count for a specific item
    fun getLikesForItem(itemId: Int): StateFlow<Int> {
        if (!likesMap.containsKey(itemId)) {
            likesMap[itemId] = MutableStateFlow(loadLikesFromPreferences(itemId))
        }
        return likesMap[itemId]!! as StateFlow<Int>
    }


    // Function to check if an item is liked
    fun isLiked(itemId: Int): StateFlow<Boolean> {
        if (!isLikedMap.containsKey(itemId)) {
            isLikedMap[itemId] = MutableStateFlow(loadIsLikedFromPreferences(itemId))
        }
        return isLikedMap[itemId]!!
    }

      // Load likes count from shared preferences
    private fun loadLikesFromPreferences(itemId: Int): Int {
        return sharedPreferences.getInt("likes_count_$itemId", 0) // Defaults to 0 if no value exists
    }

    // Load liked status from shared preferences
    private fun loadIsLikedFromPreferences(itemId: Int): Boolean {
        return sharedPreferences.getBoolean("is_liked_$itemId", false) // Defaults to false if no value exists
    }



    // Save likes to shared preferences
    private fun saveLikesToPreferences(itemId: Int, likes: Int) {
        sharedPreferences.edit().putInt("likes_count_$itemId", likes).apply()
    }

    // Save liked status to shared preferences
    private fun saveIsLikedToPreferences(itemId: Int, isLiked: Boolean) {
        sharedPreferences.edit().putBoolean("is_liked_$itemId", isLiked).apply()
    }

    // Increment likes for a specific item
    fun incrementLikes(itemId: Int) {
        val likesState = likesMap[itemId] ?: MutableStateFlow(0).also { likesMap[itemId] = it }
        likesState.value += 1
        saveLikesToPreferences(itemId, likesState.value)

        val isLikedState = isLikedMap[itemId] ?: MutableStateFlow(false).also { isLikedMap[itemId] = it }
        isLikedState.value = true
        saveIsLikedToPreferences(itemId, true)
    }

    // Decrement likes for a specific item
    fun decrementLikes(itemId: Int) {
        val likesState = likesMap[itemId] ?: return
        val currentLikes = likesState.value
        if (currentLikes > 0) {
            val newLikes = currentLikes - 1
            likesState.value = newLikes
            saveLikesToPreferences(itemId, newLikes)
            isLikedMap[itemId]?.value = false
            saveIsLikedToPreferences(itemId, false)
        }
    }


}
