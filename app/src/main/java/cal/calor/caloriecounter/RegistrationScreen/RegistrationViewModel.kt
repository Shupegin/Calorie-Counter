package cal.calor.caloriecounter.RegistrationScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationViewModel : ViewModel() {
    private var auth:  FirebaseAuth? = null
    private var firebaseDatabase : FirebaseDatabase? = null
    private var userReference : DatabaseReference? = null

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
        firebaseDatabase = FirebaseDatabase.getInstance()
        userReference = firebaseDatabase?.getReference("Users")
    }

    fun singUp(email: String, password: String, calories: Int){
        auth?.createUserWithEmailAndPassword(email,password)?.addOnSuccessListener {
            val fireBaseUser : FirebaseUser? = it.user

            val user = fireBaseUser?.let { it1 -> User(it1.uid,"Дима",calories) }

            user?.id?.let { it1 -> userReference?.child(it1)?.setValue(user) }

        }?.addOnFailureListener{
            error.value = it.message
        }
    }
}