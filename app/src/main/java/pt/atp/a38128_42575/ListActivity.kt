package pt.atp.a38128_42575


import android.os.Bundle

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import pt.atp.a38128_42575.data.CatsAPIClient
import pt.atp.a38128_42575.data.cb.DataRetriver
import pt.atp.a38128_42575.data.model.Breed



private const val TAG =  "ListActivity"

class ListActivity : AppCompatActivity(),DataRetriver{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        findViewById<RecyclerView>(R.id.rv_breeds).apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(applicationContext)
            adapter = BreedsAdapter()
        }

        CatsAPIClient.getListOfBreeds(this)
    }

    override fun onDataFetchedSucess(breeds: List<Breed>) {
        Log.d (TAG, "onDataFetchedSuccess")

        val adapter = findViewById<RecyclerView>(R.id.rv_breeds).adapter as BreedsAdapter
        adapter.submitList(breeds)
    }

    override fun onDataFetchedFailed() {
        Log.d (TAG, "onDataFetchedFailed")
    }
}