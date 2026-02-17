package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Planet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hubungkan Toolbar sebagai ActionBar (agar menu muncul)
        setSupportActionBar(binding.toolbarMain)

        // RecyclerView setup
        binding.rvPlanets.setHasFixedSize(true)
        binding.rvPlanets.layoutManager = LinearLayoutManager(this)

        // Isi data
        list.clear()
        list.addAll(getListPlanets())

        // Tampilkan list + klik item ke detail
        showRecyclerList()
    }

    // Inflate menu_main.xml ke Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Handle klik item menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_profile -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListPlanets(): ArrayList<Planet> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val dataType = resources.getStringArray(R.array.data_type)
        val dataDistance = resources.getStringArray(R.array.data_distance)
        val dataDay = resources.getStringArray(R.array.data_day)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataTemp = resources.getStringArray(R.array.data_temp)
        val dataFunFacts = resources.getStringArray(R.array.data_funfacts)

        val listPlanet = ArrayList<Planet>()
        for (i in dataName.indices) {
            val planet = Planet(
                name = dataName[i],
                description = dataDescription[i],
                photo = dataPhoto.getResourceId(i, -1),
                type = dataType[i],
                distanceFromSun = dataDistance[i],
                dayLength = dataDay[i],
                yearLength = dataYear[i],
                avgTemp = dataTemp[i],
                funFacts = dataFunFacts[i]
            )
            listPlanet.add(planet)
        }

        dataPhoto.recycle()
        return listPlanet
    }

    private fun showRecyclerList() {
        val adapter = ListPlanetAdapter(list)
        binding.rvPlanets.adapter = adapter

        adapter.setOnItemClickListener(object : ListPlanetAdapter.OnItemClickListener {
            override fun onItemClicked(data: Planet) {
                Toast.makeText(this@MainActivity, "Klik: ${data.name}", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity, DetailPlanetActivity::class.java)
                intent.putExtra(DetailPlanetActivity.EXTRA_PLANET, data)
                startActivity(intent)
            }
        })
    }
}
