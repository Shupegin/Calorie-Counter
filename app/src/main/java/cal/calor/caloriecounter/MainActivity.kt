package cal.calor.caloriecounter


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cal.calor.caloriecounter.AddNewFoodScreen.AddFoodScreen
import cal.calor.caloriecounter.AddNewFoodScreen.AddFoodScreenViewModel
import cal.calor.caloriecounter.InternetScreen.CheckInternetScreen
import cal.calor.caloriecounter.LoginScreen.LoginScreen
import cal.calor.caloriecounter.LoginScreen.LoginViewModel
import cal.calor.caloriecounter.RegistrationScreen.RegistrationScreen
import cal.calor.caloriecounter.RegistrationScreen.RegistrationViewModel
import cal.calor.caloriecounter.internet.ConnectivityObserver
import cal.calor.caloriecounter.internet.NetworkConnectivityObserver
import cal.calor.caloriecounter.ui.theme.CalorieCounterTheme
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isFlexibleUpdateAllowed
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModelLogin: LoginViewModel
    private lateinit var viewModelRegistration: RegistrationViewModel
    private lateinit var viewModelAddFoodScreen: AddFoodScreenViewModel
    private lateinit var connectivityObserver: ConnectivityObserver

    private lateinit var appUpdateManager: AppUpdateManager
    private val updateType = AppUpdateType.FLEXIBLE
    private val AVAILABLE = "Available"
    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)

        if(true) {
            if (updateType == AppUpdateType.FLEXIBLE) {
                appUpdateManager.registerListener(installStateUpdateListener)
            }
            checkForAppUpdate()
        }
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModelLogin = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModelRegistration = ViewModelProvider(this)[RegistrationViewModel::class.java]
        viewModelAddFoodScreen = ViewModelProvider(this)[AddFoodScreenViewModel::class.java]

         mainViewModel.userListDAO.observe(this, Observer {
             mainViewModel.loadFirebaseData(it)
         })
        setContent {
            CalorieCounterTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Available
                )
                if(status.name == AVAILABLE){
                    LoginApplication(
                        viewModel = viewModelLogin,
                        viewModelRegistration = viewModelRegistration,
                        mainViewModel = mainViewModel,
                        viewModelAddFoodScreen = viewModelAddFoodScreen,
                        owner = this,
                        context = this
                    )
                }else{
                    CheckInternetScreen()
                }
            }
        }




    }

    override fun onResume() {
        super.onResume()
        if (updateType == AppUpdateType.IMMEDIATE){
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            if(info.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                appUpdateManager.startUpdateFlowForResult(
                    info,
                    updateType,
                    this,
                    123)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(updateType == AppUpdateType.FLEXIBLE){
            appUpdateManager.unregisterListener(installStateUpdateListener)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var intentResult : IntentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode, data)
        if(intentResult != null){
            var content = intentResult.contents
            if (content != null){
                mainViewModel.databaseEntryUser(intentResult.contents.toString())
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)

        }


    }

    private val installStateUpdateListener = InstallStateUpdatedListener { state->
        if(state.installStatus() == InstallStatus.DOWNLOADED){
            Toast.makeText(applicationContext,
                "DownLoad successful.Restarting app in 5 seconds",
                Toast.LENGTH_LONG
            ).show()
            lifecycleScope.launch {
                delay(5.seconds)
                appUpdateManager.completeUpdate()
            }
        }

    }



   private fun checkForAppUpdate(){
            appUpdateManager.appUpdateInfo.addOnSuccessListener { info->
                val isUpdateAvailable = info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                val isUpdateAllowed = when(updateType){
                    AppUpdateType.FLEXIBLE -> info.isFlexibleUpdateAllowed
                    AppUpdateType.IMMEDIATE -> info.isImmediateUpdateAllowed
                    else -> false
                }
                if (isUpdateAvailable && isUpdateAllowed){
                    appUpdateManager.startUpdateFlowForResult(
                        info,
                        updateType,
                        this,
                        123
                    )
                }
            }
    }
}

@Composable
fun LoginApplication(viewModel: LoginViewModel,
                     viewModelRegistration: RegistrationViewModel,
                     mainViewModel : MainViewModel,
                     viewModelAddFoodScreen : AddFoodScreenViewModel,
                     owner: LifecycleOwner,
                     context: Context){


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_page", builder ={
        composable("login_page", content = { LoginScreen(navController = navController,viewModel= viewModel, owner = owner, context = context)})
        composable("register_page", content = { RegistrationScreen(navController = navController, viewModel= viewModelRegistration,owner = owner, context = context)})
        composable("activity_main", content = { MainScreen(mainViewModel = mainViewModel, owner = owner, context = context,navController = navController) })
        composable("Add_food_screen", content = {AddFoodScreen(viewModel= viewModelAddFoodScreen)})
    })

}




