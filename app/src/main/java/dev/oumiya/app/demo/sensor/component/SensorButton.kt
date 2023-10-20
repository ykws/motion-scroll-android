package dev.oumiya.app.demo.sensor.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.oumiya.app.demo.sensor.ui.theme.SensorScrollDemoTheme

@Composable
fun SensorButton(
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier,
) {
    Icon(
        imageVector = Icons.Filled.Add,
        contentDescription = null,
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
            .padding(16.dp)
            .align(Alignment.Center)
            .size(32.dp),
        tint = MaterialTheme.colorScheme.onPrimary,
    )
}

@Preview(showBackground = true)
@Composable
fun SensorButtonPreview() {
    SensorScrollDemoTheme {
        SensorButton()
    }
}
