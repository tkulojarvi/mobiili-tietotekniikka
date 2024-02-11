package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter

/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        setContent {
            Text("Hello World!")
        }
        setContent {
            MessageCard(Message("Android", "Jetpack Compose"))
        }
        setContent {
            ComposeTutorialTheme {
                Conversation(SampleData.conversationSample)
            }
        }
        */

        setContent {
            Navigation()
        }
    }
}

/* HOMEWORK 1 STUFF */

data class Message(val body: String)

@Composable
fun MessageCard(msg: Message) {

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "",
        )

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = "Hello, I'm ${"TEMP"}",  // TEXT FROM MAINSCREEN HERE
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

/*
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)


@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Surface {
            MessageCard(
                msg = Message("Lexi", "Take a look at Jetpack Compose, it's great!")
            )
        }
    }
}
*/

@Composable
fun Conversation(messages: List<Message>, navController: NavController) {

    // Use the argument value in UI
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }

    // BACK TO MAIN SCREEN BUTTON
    Button(
        onClick = {
            navController.navigate(Screen.MainScreen.route)
        },
        //modifier = Modifier.align(Alignment.End)
    ) {
        Text(text = "MAIN SCREEN")
    }
}

/*
@Preview
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}
*/

/**
 * SampleData for Jetpack Compose Tutorial
 */
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Test...Test...Test..."
        ),
        Message(
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "It's available from API 21+ :)"
        ),
        Message(
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTutorialTheme {
        Greeting("Android")
    }
}































/* HOMEWORK 2 STUFF */

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            LoginScreen(navController = navController)
        }
        composable(route = Screen.ConversationScreen.route) {
            Conversation(SampleData.conversationSample, navController = navController)
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {

    // State variables to store user input
    val userName = remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
        ) {

        // Username input field
        OutlinedTextField(
            value = userName.value, onValueChange = {
            userName.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "icon")
            },
            label = {
                Text(text = "Username")
            },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                // Use the updated username object
                navController.navigate(Screen.ConversationScreen.route)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Conversation Screen")
        }
    }
}


sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object ConversationScreen : Screen("conversation_screen") // Added

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
*/


// HOMEWORK 3 STUFF

class LoginViewModel : ViewModel() {
    var username by mutableStateOf("")
    var url by mutableStateOf("")
}

@Composable
fun LoginPage(viewModel: LoginViewModel, onNextPage: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // USERNAME INPUT
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                viewModel.username = username
                onNextPage()
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        // URL INPUT
        TextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Image URL") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                viewModel.url = url
                onNextPage()
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PROFILE PHOTO
        LaunchedEffect(url) {
            viewModel.url = url
        }

        Image(
            painter = rememberAsyncImagePainter("${viewModel.url}"),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.username = username
                viewModel.url = url
                onNextPage()
            }
        ) {
            Text("Next")
        }
    }
}


@Composable
fun UserInfoPage(viewModel: LoginViewModel, onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // Handle the button click to navigate back to the login page
                onNavigateBack()
            }
        ) {
            Text("Back to Login")
        }

        Spacer(modifier = Modifier.height(2.dp))

        Conversation(SampleData.conversationSample, viewModel)

    }
}

@Composable
fun Conversation(messages: List<Message>, viewModel: LoginViewModel) {

    // Use the argument value in UI
    LazyColumn {
        items(messages) { message ->
            MessageCard(message, viewModel)
        }
    }
}

/**
 * SampleData for Jetpack Compose Tutorial
 */
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Test...Test...Test..."
        ),
        Message(
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "It's available from API 21+ :)"
        ),
        Message(
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}

data class Message(val body: String)

@Composable
fun MessageCard(msg: Message, viewModel: LoginViewModel) {

    Row(modifier = Modifier.padding(all = 8.dp)) {

        // PROFILE PHOTO
        Image(
            painter = rememberAsyncImagePainter("${viewModel.url}"),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "",
        )

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text =  "${viewModel.username}",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun TwoPageLoginScreen(viewModel: LoginViewModel) {
    var page by remember { mutableStateOf(1) }

    when (page) {
        1 -> LoginPage(viewModel) { page = 2 }
        2 -> UserInfoPage(viewModel) { page = 1 }
    }
}

@Composable
fun TwoPageLoginActivity() {
    val viewModel: LoginViewModel = viewModel()
    TwoPageLoginScreen(viewModel)
}

@Composable
fun JetpackComposeLoginScreen() {
    Surface(color = Color.White) {
        TwoPageLoginActivity()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLoginScreen()
        }
    }
}