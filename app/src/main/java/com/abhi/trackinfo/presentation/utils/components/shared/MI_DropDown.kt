package com.abhi.trackinfo.presentation.utils.components.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MI_DropDown(
    dropDownExpanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onSelectedType: (String) -> Unit
) {

    val investmentType = listOf("Mutual Funds", "Stocks", "ETF")
    var selectedText by remember { mutableStateOf(investmentType[0]) }

    ExposedDropdownMenuBox(
        expanded = dropDownExpanded,
        onExpandedChange = { onExpandChange(!dropDownExpanded) }
    ) {

        TextField(
            readOnly = true,
            value = selectedText,
            onValueChange = {},
            label = { Text("invest type") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = dropDownExpanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    type = MenuAnchorType.PrimaryEditable,
                    enabled = true
                )
        )

        ExposedDropdownMenu(
            expanded = dropDownExpanded,
            onDismissRequest = { onExpandChange(false) }
        ) {
            investmentType.forEach { selectedType ->
                DropdownMenuItem(
                    text = { Text(text = selectedType) },
                    onClick = {
                        selectedText = selectedType
                        onSelectedType(selectedText)
                        onExpandChange(!dropDownExpanded)
                    }
                )
            }
        }
    }
}

