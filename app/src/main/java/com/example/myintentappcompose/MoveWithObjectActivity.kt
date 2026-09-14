package com.example.myintentappcompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.IntentCompat
import com.example.myintentappcompose.ui.components.DefaultTopAppBar
import com.example.myintentappcompose.ui.theme.MyIntentAppComposeTheme
import com.example.myintentappcompose.util.setDefaultContent
import com.example.myintentappcompose.util.startActivity

class MoveWithObjectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val person = IntentCompat.getParcelableExtra(
            intent,
            EXTRA_PERSON,
            Person::class.java,
        )

        setDefaultContent {
            MoveWithObjectScreen(person = person)
        }
    }

    companion object {
        private const val EXTRA_PERSON = "extra_person"

        fun start(context: Context, person: Person) {
            context.startActivity<MoveWithObjectActivity> {
                putExtra(EXTRA_PERSON, person)
            }
        }
    }
}

@Composable
fun MoveWithObjectScreen(person: Person?) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(titleRes = R.string.move_with_object)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PersonDetailRow(label = "Name", value = person?.name?.ifEmpty { "-" } ?: "-")
            PersonDetailRow(label = "Email", value = person?.email?.ifEmpty { "-" } ?: "-")
            PersonDetailRow(label = "Age", value = person?.age?.toString() ?: "-")
            PersonDetailRow(label = "Location", value = person?.city?.ifEmpty { "-" } ?: "-")
        }
    }
}

@Composable
fun PersonDetailRow(label: String, value: String) {
    Text(text = "$label: $value")
}

@Preview(showBackground = true)
@Composable
fun MoveWithObjectScreenPreview() {
    MyIntentAppComposeTheme {
        MoveWithObjectScreen(
            person = Person(
                name = "User",
                age = 25,
                email = "user@gmail.com",
                city = "Bekasi",
            ),
        )
    }
}
