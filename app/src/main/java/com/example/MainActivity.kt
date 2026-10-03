package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        SalamApp()
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalamApp() {
  // Use RTL layout direction for natural Persian text and alignment
  CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
    Scaffold(
      contentWindowInsets = WindowInsets.safeDrawing,
      topBar = {
        CenterAlignedTopAppBar(
          title = {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(
                imageVector = Icons.Default.WavingHand,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
              )
              Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
              )
            }
          },
          colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
          )
        )
      }
    ) { innerPadding ->
      SalamContent(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
      )
    }
  }
}

@Composable
fun SalamContent(modifier: Modifier = Modifier) {
  var isMessageVisible by remember { mutableStateOf(false) }
  var tapCount by remember { mutableIntStateOf(0) }

  // Subtle button bounce effect upon pressing
  val buttonScale by animateFloatAsState(
    targetValue = if (isMessageVisible) 1.03f else 1.0f,
    animationSpec = spring(
      dampingRatio = Spring.DampingRatioMediumBouncy,
      stiffness = Spring.StiffnessLow
    ),
    label = "button_scale"
  )

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.surfaceContainerLowest,
            MaterialTheme.colorScheme.surfaceContainerLow
          )
        )
      )
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 24.dp, vertical = 16.dp),
    contentAlignment = Alignment.Center
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 480.dp)
        .shadow(
          elevation = 8.dp,
          shape = RoundedCornerShape(28.dp),
          spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        ),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
      )
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        // Decorative waving hand icon avatar
        Surface(
          shape = CircleShape,
          color = MaterialTheme.colorScheme.primaryContainer,
          modifier = Modifier
            .size(72.dp)
            .shadow(4.dp, CircleShape)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(
              imageVector = Icons.Default.WavingHand,
              contentDescription = stringResource(R.string.button_salam),
              modifier = Modifier.size(36.dp),
              tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
          }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
          text = "برای شروع روی دکمه زیر بزنید",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Central Button "سلام"
        Button(
          onClick = {
            isMessageVisible = true
            tapCount++
          },
          modifier = Modifier
            .scale(buttonScale)
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .testTag("salam_button"),
          shape = RoundedCornerShape(28.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
          ),
          elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
          )
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
          ) {
            Icon(
              imageVector = Icons.Default.WavingHand,
              contentDescription = null,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
              text = stringResource(R.string.button_salam),
              style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
              )
            )
          }
        }

        // Animated reveal for "اولین اپ من کار کرد!"
        AnimatedVisibility(
          visible = isMessageVisible,
          enter = fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow)) +
              slideInVertically(
                initialOffsetY = { 40 },
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
              ) +
              scaleIn(initialScale = 0.9f),
          exit = fadeOut() + scaleOut()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Surface(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
              color = MaterialTheme.colorScheme.secondaryContainer,
              shape = RoundedCornerShape(20.dp),
              tonalElevation = 2.dp
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Celebration,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(26.dp)
                  )
                  // Requested prompt string
                  Text(
                    text = stringResource(R.string.first_app_success),
                    style = MaterialTheme.typography.titleLarge.copy(
                      fontWeight = FontWeight.ExtraBold,
                      fontSize = 22.sp
                    ),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag("result_message_text")
                  )
                }

                Text(
                  text = stringResource(R.string.celebration_subtitle),
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                  textAlign = TextAlign.Center
                )

                if (tapCount > 1) {
                  Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(top = 4.dp)
                  ) {
                    Text(
                      text = "تعداد کلیک: $tapCount",
                      style = MaterialTheme.typography.labelSmall,
                      color = MaterialTheme.colorScheme.primary,
                      modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                  }
                }
              }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Option to reset message
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center
            ) {
              FilledTonalIconButton(
                onClick = {
                  isMessageVisible = false
                  tapCount = 0
                },
                modifier = Modifier.size(48.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Refresh,
                  contentDescription = stringResource(R.string.reset_button)
                )
              }
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true, name = "Initial State")
@Composable
fun SalamAppPreview() {
  MyApplicationTheme {
    SalamApp()
  }
}
