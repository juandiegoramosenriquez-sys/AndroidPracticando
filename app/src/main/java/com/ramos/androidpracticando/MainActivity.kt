package com.ramos.androidpracticando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramos.androidpracticando.ui.theme.AndroidPracticandoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPracticandoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListaDeTareas(
                        nombre = "Juan Diego",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListaDeTareas(nombre: String, modifier: Modifier = Modifier) {
    // Estado: el texto que el usuario está escribiendo ahora mismo
    var textoNuevo by remember { mutableStateOf("") }

    // Estado: la lista de tareas (empieza con 2 de ejemplo)
    val tareas = remember {
        mutableStateListOf("Aprender Kotlin", "Terminar el laboratorio 01")
    }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "Hola, soy $nombre",
            fontSize = 24.sp
        )
        Text(
            text = "Curso: Programación en Móviles",
            fontSize = 14.sp
        )

        Text(
            text = "Mi lista de tareas (${tareas.size})",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        // Fila con el campo de texto + botón de agregar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = textoNuevo,
                onValueChange = { textoNuevo = it },
                label = { Text("Nueva tarea") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (textoNuevo.isNotBlank()) {
                    tareas.add(textoNuevo)
                    textoNuevo = ""
                }
            }) {
                Text("Agregar")
            }
        }

        // Lista desplazable de tareas
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(tareas) { tarea ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = tarea)
                        Button(onClick = { tareas.remove(tarea) }) {
                            Text("Listo")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaDeTareasPreview() {
    AndroidPracticandoTheme {
        ListaDeTareas(nombre = "Juan Diego")
    }
}