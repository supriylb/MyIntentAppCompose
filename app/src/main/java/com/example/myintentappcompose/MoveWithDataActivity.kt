package com.example.myintentappcompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.myintentappcompose.ui.components.DefaultTopAppBar
import com.example.myintentappcompose.ui.theme.MyIntentAppComposeTheme
import com.example.myintentappcompose.util.setDefaultContent
import com.example.myintentappcompose.util.startActivity

class MoveWithDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra(EXTRA_NAME)
        val age = intent.getIntExtra(EXTRA_AGE, 0)

        setDefaultContent {
            MoveWithDataScreen(name = name, age = age)
        }
    }

    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_AGE = "extra_age"

        fun start(context: Context, name: String, age: Int) {
            context.startActivity<MoveWithDataActivity> {
                putExtra(EXTRA_NAME, name)
                putExtra(EXTRA_AGE, age)
            }
        }
    }
}

@Composable
fun MoveWithDataScreen(name: String?, age: Int) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(titleRes = R.string.move_with_data)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.format_user_data, name?.ifBlank { "-" } ?: "-", age),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoveWithDataScreenPreview() {
    MyIntentAppComposeTheme {
        MoveWithDataScreen(name = "User", age = 25)
    }
}
