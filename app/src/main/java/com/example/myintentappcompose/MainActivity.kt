package com.example.myintentappcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.myintentappcompose.ui.components.DefaultTopAppBar
import com.example.myintentappcompose.ui.theme.MyIntentAppComposeTheme
import com.example.myintentappcompose.util.setDefaultContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var selectedValue by rememberSaveable { mutableStateOf<Int?>(null) }

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if ((result.resultCode == MoveWithResultActivity.RESULT_CODE) && (result.data != null)) {
            selectedValue = result.data?.getIntExtra(
                MoveWithResultActivity.EXTRA_SELECTED_VALUE,
                0,
            )
        }
    }

    val resultText = selectedValue?.let { value ->
        stringResource(R.string.result_activity_format, value)
    } ?: stringResource(R.string.result_activity_default)

    Scaffold(
        topBar = {
            DefaultTopAppBar(titleRes = R.string.app_name)
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            IntentButton(text = R.string.move_activity) {
                MoveActivity.start(context)
            }

            IntentButton(text = R.string.move_with_data) {
                MoveWithDataActivity.start(context, name = "User", age = 25)
            }

            IntentButton(text = R.string.move_with_object) {
                val person = Person("User", 25, "user@gmail.com", "Bekasi")
                MoveWithObjectActivity.start(context, person)
            }

            IntentButton(text = R.string.dial_number) {
                val phoneNumber = "081281966569"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, "tel:$phoneNumber".toUri())
                context.startActivity(dialPhoneIntent)
            }

            IntentButton(text = R.string.move_with_result) {
                resultLauncher.launch(MoveWithResultActivity.createIntent(context))
            }

            Text(text = resultText)
        }
    }
}

@Composable
fun IntentButton(
    @StringRes text: Int,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(56.dp)
            .widthIn(max = 320.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyIntentAppComposeTheme {
        MainScreen()
    }
}
