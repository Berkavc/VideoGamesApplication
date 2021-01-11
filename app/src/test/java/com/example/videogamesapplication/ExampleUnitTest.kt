package com.example.videogamesapplication

import com.example.videogamesapplication.model.GameModels
import com.example.videogamesapplication.ui.di.modules.ActivityModule
import com.example.videogamesapplication.utils.Utils
import com.example.videogamesapplication.utils.toGameListModelItem
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun checkInvalidResponseCode() {
        assertEquals(Utils.controlResponseCode(401), false)
    }

    @Test
    fun checkValidResponseCode() {
        assertTrue(Utils.controlResponseCode(200))
    }

    @Test
    fun checkReflection() {
        assertEquals(
            GameModels.GameListModelItemDetails()
                .toGameListModelItem(GameModels.GameListModelItemDetails()),
            GameModels.GameListModelItem()
        )
    }

}