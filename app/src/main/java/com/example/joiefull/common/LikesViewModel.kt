package com.example.joiefull.common

import android.content.Context
import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel

class LikesViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    // Map to hold likes count for each item
    private val likesMap = mutableMapOf<Int, MutableState<Int>>()
    private val isLikedMap = mutableMapOf<Int, MutableState<Boolean>>()

    // Function to get the likes count for a specific item
    fun getLikesForItem(itemId: Int): MutableState<Int> {
        if (!likesMap.containsKey(itemId)) {
            likesMap[itemId] = mutableStateOf(loadLikesFromPreferences(itemId)) // Load from shared preferences
        }
        return likesMap[itemId]!!
    }


    // Function to check if an item is liked
    fun isLiked(itemId: Int): MutableState<Boolean> {
        if (!isLikedMap.containsKey(itemId)) {
            isLikedMap[itemId] = mutableStateOf(loadIsLikedFromPreferences(itemId)) // Load from shared preferences
        }
        return isLikedMap[itemId]!!
    }

    // Load likes count from shared preferences
    private fun loadLikesFromPreferences(itemId: Int): Int {
        return sharedPreferences.getInt("likes_count_$itemId", 0)
    }

    // Load liked status from shared preferences
    private fun loadIsLikedFromPreferences(itemId: Int): Boolean {
        return sharedPreferences.getBoolean("is_liked_$itemId", false)
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
        val currentLikes = getLikesForItem(itemId).value
        val newLikes = currentLikes + 1
        getLikesForItem(itemId).value = newLikes
        isLiked(itemId).value = true
        saveLikesToPreferences(itemId, newLikes)
        saveIsLikedToPreferences(itemId, true)
    }

    // Decrement likes for a specific item
    fun decrementLikes(itemId: Int) {
        val currentLikes = getLikesForItem(itemId).value
        if (currentLikes > 0) {
            val newLikes = currentLikes - 1
            getLikesForItem(itemId).value = newLikes
            isLiked(itemId).value = newLikes > 0
            saveLikesToPreferences(itemId, newLikes)
            saveIsLikedToPreferences(itemId, newLikes > 0)
        }
    }

    // Reset likes for a specific item
    fun resetLikes(itemId: Int) {
        getLikesForItem(itemId).value = 0
        isLiked(itemId).value = false
        saveLikesToPreferences(itemId, 0)
        saveIsLikedToPreferences(itemId, false)
    }
}
