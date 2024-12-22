package com.example.barappv2.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class Table(val id: Int, val name: String, var value: Float = 0f)
data class Section(val name: String, val tables: List<Table>, var isExpanded: Boolean = false)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Placeholder section data
    val sections = remember {
        mutableStateListOf(
            Section("Section 1", listOf(
                Table(1, "Table 1"),
                Table(2, "Table 2"),
                Table(3, "Table 3"),
                Table(4, "Table 4")
            )),
            Section("Section 2", listOf(
                Table(5, "Table 5"),
                Table(6, "Table 6"),
                Table(7, "Table 7"),
                Table(8, "Table 8"),
                Table(9, "Table 9")
            ))
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Settings", style = MaterialTheme.typography.titleLarge)
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text("History", style = MaterialTheme.typography.titleLarge)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Bar Management") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    items(sections) { section ->
                        SectionItem(section)
                    }
                }
            }
        )
    }
}

@Composable
fun SectionItem(section: Section) {
    var isExpanded by remember { mutableStateOf(section.isExpanded) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                    section.isExpanded = isExpanded
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = section.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (isExpanded) "Collapse" else "Expand"
            )
        }
        if (isExpanded) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 500.dp) // Add height constraint to avoid infinite height
            ) {
                items(section.tables) { table ->
                    TableItem(table)
                }
            }
        }
    }
}

@Composable
fun TableItem(table: Table) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(120.dp),
        onClick = {
            // Placeholder: Add actions for table
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(table.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("€${table.value}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
