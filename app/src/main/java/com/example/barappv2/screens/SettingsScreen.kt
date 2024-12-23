package com.example.barappv2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.barappv2.viewmodel.TableViewModel

@Composable
fun SettingsScreen(viewModel: TableViewModel = viewModel()) {
    var sectionCount by remember { mutableStateOf("") }
    var tableCount by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = sectionCount,
            onValueChange = { sectionCount = it },
            label = { Text("Number of Sections") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = tableCount,
            onValueChange = { tableCount = it },
            label = { Text("Number of Tables per Section") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val numSections = sectionCount.toIntOrNull() ?: 0
            val numTables = tableCount.toIntOrNull() ?: 0
            if (numSections > 0 && numTables > 0) {
                // Generate and add tables to the database
                repeat(numSections) { section ->
                    repeat(numTables) { table ->
                        val tableName = "Section ${section + 1} - Table ${table + 1}"
                        viewModel.addTable(tableName)
                    }
                }
            }
        }) {
            Text("Save")
        }
    }
}
