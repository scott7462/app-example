package com.example.pedroscott.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pedroscott.R
import com.example.pedroscott.features.UserUiModel
import com.example.pedroscott.features.main.viewmodel.MainViewModel
import com.example.pedroscott.features.main.viewmodel.MainViewSate
import com.example.pedroscott.ui.theme.CodeChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { MainList() }
            }
        }
    }
}

@Composable
fun MainList(mainViewModel: MainViewModel = viewModel()) {
    mainViewModel.loadData()
    when (val uiState = mainViewModel.uiState) {
        MainViewSate.Error -> ErrorScreen()
        MainViewSate.Loading -> LoadingPreview()
        is MainViewSate.Success -> UserList(uiState.items)
    }
}


@Composable
fun UserList(items: List<UserUiModel>) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items) { main -> UserProfileCard(main) }
    }
}

@Composable
fun UserProfileCard(user: UserUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(horizontal = 8.dp),
                    text = stringResource(
                        id = R.string.hello_user,
                        "${user.firstName} ${user.lastName}"
                    ),
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.job_description, user.jobDescription),
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}

@Composable
fun ErrorScreen() {
    Column(
        modifier =
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .clip(CircleShape)
                .padding(horizontal = 8.dp),
            text = stringResource(id = R.string.oh_no),
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            modifier = Modifier
                .clip(CircleShape)
                .padding(horizontal = 8.dp),
            text = stringResource(id = R.string.is_error),
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .testTag("Loading")
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CodeChallengeTheme {
        UserList(
            listOf(
                UserUiModel(
                    uid = "1",
                    avatarUrl = "",
                    firstName = "Pedro",
                    lastName = "Scott",
                    jobDescription = "Android Developer"
                ),
                UserUiModel(
                    uid = "2",
                    avatarUrl = "",
                    firstName = "Marsela",
                    lastName = "Sardi",
                    jobDescription = "IOs Developer"
                ),
                UserUiModel(
                    uid = "3",
                    avatarUrl = "",
                    firstName = "Patricia",
                    lastName = "Scott",
                    jobDescription = "Company Owner"
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    ErrorScreen()
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    LoadingScreen()
}
