package pt.atp.a38128_42575.data

import pt.atp.a38128_42575.data.model.Breed
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Headers

interface CatsAPI {

    @Headers(  "x-api-key: $API_KEY")
    @GET(BREEDS)
    fun getBreedsList(): Call<List<Breed>>
}