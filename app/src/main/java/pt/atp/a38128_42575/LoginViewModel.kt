package pt.atp.a38128_42575

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResultLiveData = _loginResult

    fun credentialsValid(username:String, password:String){

       loginResultLiveData.postValue(username == password)


    }
}