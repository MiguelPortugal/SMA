package pt.atp.a38128_42575.data.cb

import pt.atp.a38128_42575.data.model.Breed

interface DataRetriver {

    fun onDataFetchedSucess(breeds: List<Breed>)

    fun onDataFetchedFailed()

}