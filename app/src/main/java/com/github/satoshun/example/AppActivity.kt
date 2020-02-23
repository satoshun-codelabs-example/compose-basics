package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.Checkbox
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.OutlinedButton
import androidx.ui.material.lightColorPalette
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class AppActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MyApp {
        MyScreenContent()
      }
    }
  }
}

@Preview("Text preview")
@Composable
fun DefaultPreview() {
  MyApp {
    MyScreenContent()
  }
}

@Composable
fun Greeting(name: String) {
  Text(
    text = "Hello $name!",
    modifier = LayoutPadding(24.dp)
//    style = MaterialTheme.typography().body1
  )
}

@Composable
fun MyApp(children: @Composable() () -> Unit) {
  MyAppTheme {
    Surface(color = Color.Yellow) {
      children()
    }
  }
}

@Composable
fun MyScreenContent(
  names: List<String> = listOf("Android", "there"),
  counterState: CounterState = CounterState()
) {
  Column(modifier = LayoutHeight.Fill) {
    Column(modifier = LayoutFlexible(1.0f)) {
      for (name in names) {
        Greeting(name = name)
        Divider(color = Color.Black)
      }
    }

    Divider(color = Color.Transparent, height = 32.dp)
    Counter(counterState)

    Divider(color = Color.Transparent, height = 32.dp)
    Form(formState = FormState(optionChecked = true))
  }
}

@Model
class CounterState(var count: Int = 0)

@Composable
fun Counter(state: CounterState) {
  OutlinedButton(
    onClick = {
      state.count++
    },
    backgroundColor = if (state.count > 5) Color.Green else Color.White
  ) {
    Text(text = "I've been clicked ${state.count} times")
  }
}

@Model
class FormState(var optionChecked: Boolean)

@Composable
fun Form(formState: FormState) {
  Checkbox(
    checked = formState.optionChecked,
    onCheckedChange = { newState -> formState.optionChecked = newState })
}

val green = Color(0xFF1EB980)
private val themeColors = lightColorPalette(
  primary = green,
  surface = Color.DarkGray,
  onSurface = green
)

@Composable
fun MyAppTheme(children: @Composable() () -> Unit) {
  MaterialTheme(colors = themeColors) {
    children()
  }
}
