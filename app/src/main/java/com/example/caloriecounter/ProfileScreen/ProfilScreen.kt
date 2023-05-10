package com.example.caloriecounter.ProfileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import coil.compose.rememberImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.caloriecounter.MainViewModel
import com.example.caloriecounter.R
import com.example.caloriecounter.ui.theme.Black900
import com.example.caloriecounter.ui.theme.Gray500
import com.example.caloriecounter.ui.theme.Green100

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreen(viewModel: MainViewModel,
                  paddingValues: PaddingValues,
                  owner: LifecycleOwner
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Green100),
        contentAlignment = Alignment.TopCenter
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Профиль", color = Black900, fontSize = 50.sp )
            Text(text = "Колличество каллорий в день ", color = Black900, fontSize = 20.sp )
            OutlinedTextField(value = "", onValueChange ={})
            Text(text = "Отправить ссылку:", color = Black900, fontSize = 20.sp )
            Text(text = "https://shupegn-corp.ru:", color = Black900, fontSize = 20.sp )
            Text(text = "Для синхронизации счетчиков", color = Black900, fontSize = 20.sp )
            CoilImage()


        }
    }
}
@Composable
fun CoilImage(){
    Box(modifier = Modifier
        .height(300.dp)
        .width(300.dp),
        contentAlignment = Alignment.Center
    ){
        val painter = rememberImagePainter(data = "https://yandex.ru/images/search?from=tabbar&img_url=https%3A%2F%2Fassets.publishing.service.gov.uk%2Fgovernment%2Fuploads%2Fsystem%2Fuploads%2Fimage_data%2Ffile%2F104841%2FQR_code_image.jpg&lr=55&pos=6&rpt=simage&text=qr%20code",
            builder = {

            }
        )
        Image(painterResource(id = R.drawable.qr), contentDescription = "QR-code")
    }
}