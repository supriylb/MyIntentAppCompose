package com.example.myintentappcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myintentappcompose.ui.components.DefaultTopAppBar
import com.example.myintentappcompose.ui.theme.MyIntentAppComposeTheme
import com.example.myintentappcompose.util.setDefaultContent

class MoveWithResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultContent {
            MoveWithResultScreen { selectedValue ->
                val resultIntent = Intent().apply {
                    putExtra(EXTRA_SELECTED_VALUE, selectedValue)
                }
                setResult(RESULT_CODE, resultIntent)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = RESULT_OK

        fun createIntent(context: Context): Intent {
            return Intent(context, MoveWithResultActivity::class.java)
        }
    }
}

@Composable
fun MoveWithResultScreen(
    onResultSelected: (Int) -> Unit = {},
) {
    val options = remember { listOf(50, 100, 150, 200) }
    var selectedValue by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            DefaultTopAppBar(titleRes = R.string.move_with_result)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(text = stringResource(R.string.prompt_choose_number))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                options.forEach { value ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedValue = value },
                    ) {
                        RadioButton(
                            selected = (selectedValue == value),
                            onClick = { selectedValue = value },
                        )
                        Text(text = value.toString())
                    }
                }
            }

            Button(
                onClick = {
                    if (selectedValue > 0) {
                        onResultSelected(selectedValue)
                    }
                },
                enabled = selectedValue > 0,
                modifier = Modifier
                    .height(56.dp)
                    .widthIn(320.dp)
                    .align(Alignment.CenterHorizontally),
                shape = MaterialTheme.shapes.extraLarge,
            ) {
                Text(
                    text = stringResource(R.string.btn_choose),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoveWithResultScreenPreview() {
    MyIntentAppComposeTheme {
        MoveWithResultScreen()
    }
}
