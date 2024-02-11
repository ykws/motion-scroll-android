# MotionScrollApp for Android

[![CI](https://github.com/ykws/motion-scroll-android/actions/workflows/ci.yml/badge.svg)](https://github.com/ykws/motion-scroll-android/actions/workflows/ci.yml)

Demonstration scroll by [motion sensor](https://developer.android.com/guide/topics/sensors/sensors_motion
) for Android. Tap the sensor button at the bottom left, and tilt it forward to scroll down, or tilt it backward to scroll up.

https://github.com/ykws/sensor-scroll-android/assets/5770480/ca32d674-bc17-4da2-87b8-4d0002782277

## Tips
### Action while pressing

```kotlin
Box(modifier = Modifier.pointerInput(Unit) {
  detectTapGestures(
    onPress = {
      // isPressing

      tryAwaitRelease() // release
    }
  )
}
```

- 🔖 [Pointer input in Compose](https://developer.android.com/jetpack/compose/touch-input/pointer-input)
- 🔖 [PressGestureScope](https://developer.android.com/reference/kotlin/androidx/compose/foundation/gestures/PressGestureScope)
- 🔖 [Composeの様々なクリック処理](https://star-zero.medium.com/compose%E3%81%AE%E6%A7%98%E3%80%85%E3%81%AA%E3%82%AF%E3%83%AA%E3%83%83%E3%82%AF%E5%87%A6%E7%90%86-dd66f19992c5)
