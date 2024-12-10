package com.example.recyclerview_franciscomarin
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Principal()
        }
    }
}

@Composable
fun Principal() {
    var pantalla by remember { mutableStateOf("LazyColum") }

    Scaffold(
        bottomBar = {
            NavigationBar(
                tonalElevation = 2.dp,
                containerColor = Color.Black
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.lista),
                            contentDescription = "Lista Tareas",
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = {
                        Text(
                            "Lista Tareas",
                            fontSize = 10.sp,
                            color = if (pantalla == "LazyColum") Color.White else Color.Gray
                        )
                    },
                    selected = pantalla == "LazyColum",
                    onClick = { pantalla = "LazyColum" }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.personajes),
                            contentDescription = "LazyVerticalGrid",
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = {
                        Text(
                            "Lista Personajes",
                            fontSize = 10.sp,
                            color = if (pantalla == "LazyVerticalGrid") Color.White else Color.Gray
                        )
                    },
                    selected = pantalla == "LazyVerticalGrid",
                    onClick = { pantalla = "LazyVerticalGrid" }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.contactos),
                            contentDescription = "StickyHeader",
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = {
                        Text(
                            "Lista de Contactos",
                            fontSize = 10.sp,
                            color = if (pantalla == "StickyHeader") Color.White else Color.Gray
                        )
                    },
                    selected = pantalla == "StickyHeader",
                    onClick = { pantalla = "StickyHeader" }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            when (pantalla) {
                "LazyColum" -> LazyColum()
                "LazyVerticalGrid" -> LazyVerticalGrid()
                "StickyHeader" -> StickyHeader()
            }
        }
    }
}
//**********************************************************************
data class Task(val title: String, var isCompleted: Boolean)

@Composable
fun LazyColum() {
    val tasks = remember {
        mutableStateListOf(
            Task("Ir al gimnasio", false),
            Task("Estudiar", false),
            Task("Cenar con mis amigos", false),
            Task("Ir a la aceituna", false),
            Task("Terminar proyecto programación", false),
            Task("Instalar ubuntu en el pc de mi amigo", false),
            Task("Borrar carpeta system32 en mi pc", false),
            Task("Destrozar el coche de mi vecino", false),
            Task("Robar en la pescaderia del mercadona", false),
            Task("Viajar a un pais tercermundista", false),
            Task("Adoptar un pinguino", false),
            Task("Comprar armas en la deep web", false),

            )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks.size) { index ->
            TareaItem(task = tasks[index]) { updatedTask ->
                tasks[index] = updatedTask
            }
        }
    }
}

@Composable
fun TareaItem(task: Task, onTaskUpdated: (Task) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(if (task.isCompleted) Color(0xFF2E7D32) else Color(0xFFFFA726))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = if (task.isCompleted) R.drawable.ic_completa else R.drawable.ic_pendiente),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = task.title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = Color.White
        )

        Button(
            onClick = {
                val updatedTask = task.copy(isCompleted = !task.isCompleted)
                onTaskUpdated(updatedTask)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (task.isCompleted) Color(0xFF66BB6A) else Color(0xFFFFCC80),
                contentColor = Color.White
            )
        ) {
            Text(text = if (task.isCompleted) "poner:PENDIENTE" else "poner:COMPLETA")
        }
    }
}

//*********************************************************************************************************
@Composable
fun LazyVerticalGrid() {
    // Lista de personajes unidos con su foto, he encontrado esta funcion en internet y me ha parecido
    //muy util e interesante para despues sacar el nombre y la foto y usarlos
    val personajes = listOf(
        Pair("Androide18", R.drawable.a18extra2),
        Pair("Androide 17", R.drawable.androide17),
        Pair("Boo", R.drawable.boo),
        Pair("Majin Boo", R.drawable.booextra2),
        Pair("Bulma", R.drawable.bulma),
        Pair("Celula", R.drawable.cellextra2),
        Pair("Chaoz", R.drawable.chaoz),
        Pair("Frezzer", R.drawable.frezzer),
        Pair("Gohan", R.drawable.gohanextra1),
        Pair("Goku", R.drawable.gokuextra1),
        Pair("Goten", R.drawable.gotenextra1),
        Pair("Gotenks", R.drawable.gotenksextra1),
        Pair("Krillin", R.drawable.krillin),
        Pair("Piccolo", R.drawable.piccoloextra1),
        Pair("Roshi", R.drawable.roshiextra2),
        Pair("Tenshinhan", R.drawable.tenshinhan),
        Pair("Trunks", R.drawable.trunksextra2),
        Pair("Vegeta", R.drawable.vegeta)
    )
    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(personajes) { personaje ->
            val nombre = personaje.first
            val imagen = personaje.second

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        Toast.makeText(
                            context,
                            "Se llama $nombre",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            ) {
                Image(
                    painter = painterResource(id = imagen),
                    contentDescription = nombre,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(4.dp)
                )
                Text(
                    text = nombre,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}



//*****************************************************************************************
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyHeader() {
    val contactos = listOf(
        "Jesús", "Efrén", "Juan",
        "Antonio", "Victor", "Andrés",
        "Carlos", "Cristian",
        "David", "Dani",
        "Edu", "Adri",
        "Fran", "Pepe","Mauro",
        "Gustavo","JD","Wanda"
    ).sorted()

    val contactosagrupados = contactos.groupBy { it.first().toString() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        contactosagrupados.forEach { (inicial, nombres) ->
            stickyHeader {
                Text(
                    text = inicial,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(8.dp)
                )
            }
            items(nombres) { nombre ->
                Text(
                    text = nombre,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color(0xFF4914BB))
                )
            }
        }
    }
}

