package com.example.pccomponentsapp.data

import android.content.Context


data class Component(
    val id: Int,
    val name: String,
    val shortDesc: String,
    val longDesc: String
)


data class Image(
    val id: Int,
    val componentId: Int,
    val fileName: String
)


class Repository(context: Context) {

    private val db = DatabaseHelper(context).readableDatabase


    fun getAllComponents(): List<Component> {
        val list = mutableListOf<Component>()
        val cursor = db.rawQuery(
            "SELECT id, name, short_desc, long_desc FROM components ORDER BY id",
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                list.add(
                    Component(
                        it.getInt(0),
                        it.getString(1) ?: "",
                        it.getString(2) ?: "",
                        it.getString(3) ?: ""
                    )
                )
            }
        }
        return list
    }


    fun getComponentById(id: Int): Component? {
        val cursor = db.rawQuery(
            "SELECT id, name, short_desc, long_desc FROM components WHERE id = ?",
            arrayOf(id.toString())
        )
        cursor.use {
            if (it.moveToFirst()) {
                return Component(
                    it.getInt(0),
                    it.getString(1) ?: "",
                    it.getString(2) ?: "",
                    it.getString(3) ?: ""
                )
            }
        }
        return null
    }


    fun getImagesByComponentId(componentId: Int): List<Image> {
        val list = mutableListOf<Image>()
        val cursor = db.rawQuery(
            "SELECT id, component_id, file_name FROM images WHERE component_id = ? ORDER BY id",
            arrayOf(componentId.toString())
        )
        cursor.use {
            while (it.moveToNext()) {
                list.add(
                    Image(
                        it.getInt(0),
                        it.getInt(1),
                        it.getString(2) ?: ""
                    )
                )
            }
        }
        return list
    }
}
