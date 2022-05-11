package pt.atp.a38128_42575.data

import android.nfc.Tag
import android.util.Log
import pt.atp.a38128_42575.data.cb.DataRetriver
import pt.atp.a38128_42575.data.model.Breed
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

private const val  TAG = "CatsAPIClient"

object CatsAPIClient {

    private val apiCat: CatsAPI by lazy {
        setup()
    }

    private fun setup():CatsAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    fun getListOfBreeds(listener: DataRetriver){
        apiCat.getBreedsList().enqueue(object :Callback<List<Breed>> {
            override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
               Log.d(TAG,  "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    listener.onDataFetchedSucess(response.body()!!)
                }

            }

            override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                Log.e(TAG, "onResponse: ${t.message}")
                listener.onDataFetchedFailed()
            }


        })
    }
}