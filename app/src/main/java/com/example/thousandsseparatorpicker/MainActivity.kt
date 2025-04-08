package com.example.thousandsseparatorpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thousandsseparatorpicker.ui.theme.Light_PrimaryContainer
import com.example.thousandsseparatorpicker.ui.theme.Schemes_OnPrimary
import com.example.thousandsseparatorpicker.ui.theme.Schemes_OnPrimary_Fixed
import com.example.thousandsseparatorpicker.ui.theme.Schemes_OnSurface
import com.example.thousandsseparatorpicker.ui.theme.ThousandsSeparatorPickerTheme
import com.example.thousandsseparatorpicker.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThousandsSeparatorPickerTheme {
                val thousandsSeparatorList: List<String> = listOf(
                    "1.000",
                    "1,000",
                    "1 000"
                )

                var thousandsSeparator by remember {
                    mutableStateOf("1.000")
                }

                Scaffold { _ ->
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp, vertical = 60.dp),
                    ) {
                        PreferencesItem(
                            text = "Thousands separator",
                            list = thousandsSeparatorList,
                            selectedItem = thousandsSeparator,
                            onClick = { newThousandsSeparator ->
                                thousandsSeparator = newThousandsSeparator
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun <T> PreferencesItem(
    text: String,
    list: List<T>,
    selectedItem: T,
    onClick: (T) -> Unit,
) {
    Text(
        text = text,
        style = Typography.labelSmall
    )

    Spacer(modifier = Modifier.height(4.dp))

    LazyVerticalGrid(
        columns = GridCells.Fixed(list.size),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Light_PrimaryContainer.copy(alpha = 0.08f)),
        horizontalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(list) { item ->
            val isSelected = item == selectedItem
            PreferencesTextButtons(
                textButton = when (item) {
                    true -> "-$10"
                    false -> "($10)"
                    else -> item.toString()
                },
                isTextButtonSelected = isSelected,
                onTextButtonClick = {
                    onClick(item)
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

}


@Composable
fun PreferencesTextButtons(
    textButton: String,
    isTextButtonSelected: Boolean,
    onTextButtonClick: () -> Unit,
) {
    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = if (isTextButtonSelected) Schemes_OnPrimary else Color.Transparent
            ),
        onClick = onTextButtonClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = textButton,
            style = Typography.titleLarge.copy(color = if (isTextButtonSelected) Schemes_OnSurface else Schemes_OnPrimary_Fixed),
            textAlign = TextAlign.Center
        )
    }
}