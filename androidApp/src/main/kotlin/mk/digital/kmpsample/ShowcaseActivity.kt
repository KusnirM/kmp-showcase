package mk.digital.kmpsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mk.digital.kmpsample.ui.components.ShowCase

class ShowcaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowCase()
        }
    }
}

@Preview
@Composable
fun AndroidShowPreview() {
    ShowCase()
}

