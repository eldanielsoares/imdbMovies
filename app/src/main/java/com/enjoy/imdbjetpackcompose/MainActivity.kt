package com.enjoy.imdbjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.enjoy.imdbjetpackcompose.data.SeriesDetails
import com.enjoy.imdbjetpackcompose.data.seriesServices
import com.enjoy.imdbjetpackcompose.ui.theme.ImdbJetpackComposeTheme

class MainActivity : ComponentActivity() {
    private val viewmodel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(seriesService = seriesServices) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImdbJetpackComposeTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Header()
                    SeriesComponent(seriesData = viewmodel.seriesData)
                }
            }

        }
        viewmodel.getSeries()
    }
}

@Composable
fun Header(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .padding(24.dp, 0.dp)
        , verticalArrangement = Arrangement.Center) {
        Text(text = "Catálogo IMDB")
    }
}

@Composable
fun SeriesComponent(seriesData: LiveData<List<SeriesDetails>>) {
    val series by seriesData.observeAsState(emptyList())
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(series) { _, item ->
            SeriesItem(serie = item)
        }
    }
}

@Composable
fun SeriesItem(serie: SeriesDetails) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(24.dp, 8.dp)
            .background(Color(0xecf0f1FF)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter("https://image.tmdb.org/t/p/w342/${serie.poster_path}"),
            contentDescription = serie.title,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(20.dp)),
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = serie.title, style = TextStyle(color = Color.Black, fontSize = 22.sp))
            Text(text = "Nota: ${serie.vote_average}")
        }
    }
}
