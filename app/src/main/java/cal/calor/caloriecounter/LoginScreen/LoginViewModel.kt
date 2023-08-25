package cal.calor.caloriecounter.LoginScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {

    private var auth: FirebaseAuth? = null

    private val _error : MutableLiveData<String> = MutableLiveData()
    val error : MutableLiveData<String> =  _error
    private val _user : MutableLiveData<FirebaseUser> = MutableLiveData()
    val user : MutableLiveData<FirebaseUser> = _user

    init {
        auth = FirebaseAuth.getInstance()
        auth?.addAuthStateListener {firebaseAuth->
            if(firebaseAuth.currentUser != null ){
                _user.value = firebaseAuth.currentUser
            }
        }
    }


    fun login (email : String, password: String){
        auth?.signInWithEmailAndPassword(email,password)?.addOnSuccessListener { authResult->

        }?.addOnFailureListener{
            _error.value = it.message
        }
    }
}