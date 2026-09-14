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
import androidx.compose.ui.tooling.preview.Preview
import com.example.myintentappcompose.ui.components.DefaultTopAppBar
import com.example.myintentappcompose.ui.theme.MyIntentAppComposeTheme
import com.example.myintentappcompose.util.setDefaultContent
import com.example.myintentappcompose.util.startActivity

class MoveActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultContent {
            MoveScreen()
        }
    }

    companion object {
        fun start(context: Context) = context.startActivity<MoveActivity>()
    }
}

@Composable
fun MoveScreen() {
    Scaffold(
        topBar = {
            DefaultTopAppBar(titleRes = R.string.move_activity)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = stringResource(R.string.this_is_move_activity))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoveScreenPreview() {
    MyIntentAppComposeTheme {
        MoveScreen()
    }
}
