package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("سلام", appName)

    val buttonText = context.getString(R.string.button_salam)
    assertEquals("سلام", buttonText)

    val successText = context.getString(R.string.first_app_success)
    assertEquals("اولین اپ من کار کرد!", successText)
  }
}
