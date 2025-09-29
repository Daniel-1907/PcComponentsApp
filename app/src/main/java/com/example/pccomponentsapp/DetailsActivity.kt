package com.example.pccomponentsapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.pccomponentsapp.data.Repository
import com.example.pccomponentsapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repo = Repository(this)
        val id = intent.getIntExtra("id", -1)
        val component = repo.getComponentById(id) ?: return

        binding.tvName.text = component.name
        binding.tvLong.text = component.longDesc

        // Определяем имена картинок по названию
        val baseName = when (component.name.lowercase()) {
            "cpu" -> "ic_cpu"
            "gpu" -> "ic_gpu"
            "ram" -> "ic_ram"
            "ssd" -> "ic_ssd"
            "mother board", "motherboard" -> "ic_motherboard"
            "psu" -> "ic_psu"
            else -> null
        }

        baseName?.let {
            addImage(it)
            addImage("${it}_2")
        }
    }

    private fun addImage(name: String) {
        val resId = resources.getIdentifier(name, "drawable", packageName)
        if (resId != 0) {
            val image = ImageView(this).apply {
                layoutParams = android.widget.LinearLayout.LayoutParams(450, 450).apply {
                    setMargins(8, 8, 8, 8)
                }
                setImageResource(resId)
                scaleType = ImageView.ScaleType.CENTER_CROP
                setBackgroundColor(android.graphics.Color.WHITE)
            }
            binding.layoutImages.addView(image)
        }
    }
}