package com.arjava.basiccodelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.ui.res.stringResource
import com.arjava.basiccodelabs.ui.theme.BasicCodeLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnBoarding) {
        OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = List(1000){"$it"}) {
        // A surface container using the 'background' color from the theme
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
               Greeting(name = name)
            }
//            for (name in names) {
//                Greeting(name = name)
//            }
        }
}

@Composable
fun Greeting(name: String) {
//    var expanded by rememberSaveable { mutableStateOf(false) }
//    val extraPadding by animateDpAsState(if (expanded) 48.dp else 0.dp, animationSpec = spring(
//        dampingRatio = Spring.DampingRatioMediumBouncy,
//        stiffness = Spring.StiffnessLow
//    ))
//    Surface(
//        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
//        color = MaterialTheme.colorScheme.primary
//    )
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    )
    {
        CardContent(name = name)
//        Row(modifier = Modifier.padding(24.dp)) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
//            ) {
//                Text(text = "Hello ", textAlign = TextAlign.Center)
//                Text(text = "$name!", textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold))
//            }
//            OutlinedButton(onClick = {
//                expanded = !expanded
//            }, colors = ButtonDefaults.buttonColors(Color(0xffffffff))) {
//                Text(
//                    text = if (expanded) "Show less" else "Show More",
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }
//        }
    }

}

@Composable
private fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }

            )
        }
    }
}

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {
    //TODO this state should be hoisted

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basic Code Labs!")
            Button(onClick = onContinueClicked, modifier = Modifier.padding(24.dp)) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(widthDp = 320, heightDp = 640)
@Composable
fun DefaultPreview() {
    Greetings()
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    BasicCodeLabsTheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}