package mk.digital.kmpsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.defaultComponentContext
import mk.digital.kmpsample.ui.base.DefaultAppComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val componentContext = defaultComponentContext()
        val component = DefaultAppComponent(componentContext = componentContext.childContext("app"))

        setContent {
            MainView(component)
        }
    }
}

