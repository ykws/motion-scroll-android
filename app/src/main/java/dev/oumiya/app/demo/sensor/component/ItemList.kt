package dev.oumiya.app.demo.sensor.component

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.oumiya.app.demo.sensor.ui.theme.SensorScrollDemoTheme

@Composable
fun ItemList() {
    val context = LocalContext.current
    val sensorManager = context.getSystemService(SensorManager::class.java)

    ScrollableContent(sensorManager = sensorManager)
}

@Composable
private fun ScrollableContent(
    sensorManager: SensorManager? = null,
) {
    var scrollState = rememberScrollState()
    var scrollOffset by remember { mutableStateOf(0f) }
    var baseY: Float? by remember { mutableStateOf(null) }
    var isButtonPressing by remember { mutableStateOf(false) }

    val density = LocalDensity.current.density

    DisposableEffect(sensorManager) {
        if (sensorManager == null) return@DisposableEffect onDispose {}

        val listener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, sccuracy: Int) {}
            override fun onSensorChanged(event: SensorEvent) {
                if (isButtonPressing) {
                    if (baseY == null) baseY = event.values[1]
                    val y = event.values[1]
                    val delta = y - (baseY ?: 0f)
                    scrollOffset = delta * 100 * density
                }
            }
        }
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_GAME)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    LaunchedEffect(scrollOffset) {
        if (isButtonPressing) {
            scrollState.animateScrollBy(
                value = scrollOffset,
                animationSpec = tween(durationMillis = 200, easing = LinearEasing),
            )
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            repeat(100) {
                Text(text = "Item $it", modifier = Modifier.padding(16.dp))
            }
        }

        SensorButton(
            modifier = Modifier
                .size(72.dp)
                .align(Alignment.BottomStart)
                .padding(start = 8.dp, bottom = 8.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isButtonPressing = true
                            tryAwaitRelease()
                            baseY = null
                            isButtonPressing = false
                        },
                    )
                },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollableContentPreview() {
    SensorScrollDemoTheme {
        ScrollableContent()
    }
}
