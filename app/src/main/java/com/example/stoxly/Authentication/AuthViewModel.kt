import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)
    val isLoggedIn = mutableStateOf(false)
    val isRegistered = mutableStateOf(false)


    fun registerUser(email: String, password: String) {
        isLoading.value = true
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                isLoading.value = false
                if (it.isSuccessful) {
                  isRegistered.value = true
                } else {
                    error.value = it.exception?.message
                }
            }
    }

    fun loginUser(email: String, password: String) {
        isLoading.value = true
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                isLoading.value = false
                if (it.isSuccessful) {
                    isLoggedIn.value = true
                } else {
                    error.value = it.exception?.message
                }
            }
    }
}