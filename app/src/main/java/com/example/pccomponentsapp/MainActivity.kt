package com.example.pccomponentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pccomponentsapp.data.Repository
import com.example.pccomponentsapp.data.Component
import com.example.pccomponentsapp.databinding.ActivityMainBinding
import com.example.pccomponentsapp.ui.ComponentAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        repo = Repository(this)
        val list: List<Component> = repo.getAllComponents()

        binding.rvComponents.layoutManager = GridLayoutManager(this, 2)
        binding.rvComponents.adapter = ComponentAdapter(this, list) { comp ->
            val i = Intent(this, DetailsActivity::class.java)
            i.putExtra("id", comp.id)
            startActivity(i)
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            startActivity(Intent(this, AboutActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
