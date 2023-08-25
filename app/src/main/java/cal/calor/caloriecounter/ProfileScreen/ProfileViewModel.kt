package cal.calor.caloriecounter.ProfileScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel() {
    private var auth:  FirebaseAuth? = null
    private val _clientID : MutableLiveData<String> = MutableLiveData()
    val client : MutableLiveData<String> =  _clientID

    init {
        auth?.addAuthStateListener{
            _clientID.value = it.uid
        }
    }
}