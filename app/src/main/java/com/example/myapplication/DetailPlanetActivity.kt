package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityDetailPlanetBinding

class DetailPlanetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPlanetBinding

    companion object {
        const val EXTRA_PLANET = "extra_planet"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPlanetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarDetail.setNavigationOnClickListener { finish() }

        val planet = getPlanetExtra() ?: run {
            finish()
            return
        }

        // tampilkan data planet yang dipilih
        binding.imgTopPlanet.setImageResource(planet.photo)
        binding.tvPlanetName.text = planet.name
        binding.tvPlanetDescription.text = planet.description

        // tampilkan quick facts
        binding.tvFactType.text = planet.type
        binding.tvFactDistance.text = planet.distanceFromSun
        binding.tvFactDay.text = planet.dayLength
        binding.tvFactYear.text = planet.yearLength
        binding.tvFactTemp.text = planet.avgTemp

        // fun facts jadi bullet
        val bullets = planet.funFacts
            .split("\n")
            .filter { it.isNotBlank() }
            .joinToString("\n") { "• $it" }
        binding.tvFunfacts.text = bullets

        // tombol share
        binding.actionSharePlanet.setOnClickListener {
            val shareText = """
${planet.name}

${planet.description}

Quick Facts:
• Tipe: ${planet.type}
• Jarak: ${planet.distanceFromSun}
• Rotasi: ${planet.dayLength}
• Revolusi: ${planet.yearLength}
• Suhu: ${planet.avgTemp}

Fakta Menarik:
$bullets
            """.trim()

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, planet.name)
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan Planet"))
        }

        // ============================
        // Planet lainnya
        // ============================
        val otherPlanets = getListPlanets().filter { it.name != planet.name }

        binding.rvOtherPlanets.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val otherAdapter = OtherPlanetAdapter(otherPlanets)
        binding.rvOtherPlanets.adapter = otherAdapter

        otherAdapter.setOnItemClickListener(object : OtherPlanetAdapter.OnItemClickListener {
            override fun onClick(data: Planet) {
                val i = Intent(this@DetailPlanetActivity, DetailPlanetActivity::class.java)
                i.putExtra(EXTRA_PLANET, data)
                startActivity(i)
                finish()
            }
        })
    }

    private fun getPlanetExtra(): Planet? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_PLANET, Planet::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PLANET) as? Planet
        }
    }

    // ambil list planet dari resources (VERSI LENGKAP)
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
            listPlanet.add(
                Planet(
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
            )
        }

        dataPhoto.recycle()
        return listPlanet
    }
}
