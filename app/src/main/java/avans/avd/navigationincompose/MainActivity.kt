package avans.avd.navigationincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import avans.avd.navigationincompose.ui.theme.NavigationInComposeTheme
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data object ProfileRoute

@Serializable
data class InfoRoute(val name: String, val age: Int)

private val infoHenk = InfoRoute("Henk", 27)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationInComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute
                    ) {
                        composable<HomeRoute> {
                            HomeScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable<ProfileRoute> {
                            ProfileScreen(
                                onNavigateClick = {
                                    navController.navigate(
                                        infoHenk
                                    )
                                }
                            )
                        }

                        composable<InfoRoute> { backStackEntry ->
                            val info: InfoRoute = backStackEntry.toRoute()

                            InfoScreen(
                                info = info,
                                onNavigateClick = { navController.navigateUp() },
                            )
                        }

                    }
                }
            }
        }
    }
}


@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                "Home screen",
                style = TextStyle(color = Color.Black, fontSize = 36.sp),
                modifier = Modifier
                    .padding(24.dp)
            )

            Button(onClick = { navController.navigate(ProfileRoute) }) {
                Text("Show Pofile Screen")
            }

            Button(onClick = { navController.navigate(infoHenk) }) {
                Text("Show Info Screen")
            }
        }
    }
}

@Composable
fun ProfileScreen(
    onNavigateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                "Profile screen",
                style = TextStyle(color = Color.Black, fontSize = 36.sp),
                modifier = Modifier
                    .padding(24.dp)
            )

            Button(onClick = onNavigateClick, modifier = Modifier.padding(24.dp)) {
                Text("Show Info Screen")
            }
        }
    }
}

@Composable
fun InfoScreen(
    info: InfoRoute,
    onNavigateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                "Profile screen",
                style = TextStyle(color = Color.Black, fontSize = 36.sp),
                modifier = Modifier
                    .padding(24.dp)
            )

            Text(
                "Name: ${info.name}",
                style = TextStyle(color = Color.Black, fontSize = 24.sp),
                modifier = Modifier
                    .padding(24.dp)
            )

            Text(
                "Age: ${info.age}",
                style = TextStyle(color = Color.Black, fontSize = 24.sp),
                modifier = Modifier
                    .padding(24.dp)
            )

            Button(onClick = onNavigateClick, modifier = Modifier.padding(24.dp)) {
                Text("Go back")
            }
        }
    }
}
