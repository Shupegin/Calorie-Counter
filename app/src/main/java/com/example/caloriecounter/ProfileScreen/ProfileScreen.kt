package com.example.caloriecounter.ProfileScreen

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import coil.compose.rememberImagePainter
import com.example.caloriecounter.MainViewModel
import com.example.caloriecounter.ui.theme.Black900
import com.example.caloriecounter.ui.theme.Green100
import com.google.zxing.integration.android.IntentIntegrator


@Composable
fun ProfileScreen(viewModel: MainViewModel,
                  paddingValues: PaddingValues,
                  owner: LifecycleOwner,
                  context: Context
){
    var clientID = ""
     viewModel.client.observe(owner, Observer {
         clientID = it
         viewModel.generateQR(it)
     })
    var imageQR  =  Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888)


    viewModel.imageQR.observe(owner, Observer {
        imageQR = it
    })
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Профиль", color = Black900, fontSize = 50.sp )
            Text(text = "Колличество каллорий в день ", color = Black900, fontSize = 20.sp )
            OutlinedTextField(value = "", onValueChange ={})
            Text(text = "Отправить ссылку:", color = Black900, fontSize = 20.sp )
            Text(text = "https://shupegn-corp.ru:", color = Black900, fontSize = 20.sp )
            Text(text = "Для синхронизации счетчиков", color = Black900, fontSize = 20.sp )
            Text(text = "Ваш ID - $clientID ", color = Black900, fontSize = 20.sp )
            CoilImage(image = imageQR)
            Button(onClick = {
                var intentIntegrator = IntentIntegrator(context as Activity?)
                intentIntegrator.setOrientationLocked(true)
                intentIntegrator.setPrompt("Scan a QR code")
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                intentIntegrator.initiateScan()
            }) {
                Text(text = "QR scanner")
            }



        }
    }
}
@Composable
fun CoilImage(image :Bitmap){
    Box(modifier = Modifier
        .height(300.dp)
        .width(300.dp),
        contentAlignment = Alignment.Center
    ){
        val painter = rememberImagePainter(data = "https://yandex.ru/images/search?from=tabbar&img_url=https%3A%2F%2Fassets.publishing.service.gov.uk%2Fgovernment%2Fuploads%2Fsystem%2Fuploads%2Fimage_data%2Ffile%2F104841%2FQR_code_image.jpg&lr=55&pos=6&rpt=simage&text=qr%20code",
            builder = {

            }
        )
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = "some useful description",
        )
    }
}