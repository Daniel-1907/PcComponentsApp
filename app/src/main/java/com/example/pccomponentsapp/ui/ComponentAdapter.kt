package com.example.pccomponentsapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pccomponentsapp.R
import com.example.pccomponentsapp.data.Component

class ComponentAdapter(
    private val context: Context,
    private val items: List<Component>,
    private val onClick: (Component) -> Unit
) : RecyclerView.Adapter<ComponentAdapter.VH>() {

    // ViewHolder принимает View, а не Component
    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val icon: ImageView = v.findViewById(R.id.icon)
        val title: TextView = v.findViewById(R.id.title)
        val subtitle: TextView = v.findViewById(R.id.subtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_component, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, pos: Int) {
        val component = items[pos]

        h.title.text = component.name
        h.subtitle.text = component.shortDesc

        val iconName = when (component.name.lowercase()) {
            "cpu" -> "ic_cpu"
            "gpu" -> "ic_gpu"
            "ram" -> "ic_ram"
            "ssd" -> "ic_ssd"
            "motherboard" -> "ic_motherboard"
            "psu" -> "ic_psu"
            else -> "ic_cpu"
        }

        val resId = context.resources.getIdentifier(iconName, "drawable", context.packageName)
        if (resId != 0) h.icon.setImageResource(resId)

        h.itemView.setOnClickListener { onClick(component) }
    }

    override fun getItemCount(): Int = items.size
}
