package com.example.pccomponentsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImagePagerAdapter(
    private val context: Context,
    private val fileNames: List<String>
) : RecyclerView.Adapter<ImagePagerAdapter.VH>() {

    inner class VH(v: View): RecyclerView.ViewHolder(v) {
        val iv: ImageView = v.findViewById(R.id.ivPager)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pager_image, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, pos: Int) {
        val name = fileNames[pos]
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        if (resId != 0) h.iv.setImageResource(resId)
    }

    override fun getItemCount() = fileNames.size
}
