package xyz.axxonte.vapecalculator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.axxonte.vapecalculator.R
import xyz.axxonte.vapecalculator.ui.theme.VapeCalculatorTheme

@Composable
fun VapeScreen() {
    Scaffold(
        topBar = { AppTopBar() }
    ) { padding ->

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = padding)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            QuantityCard()
            NicotineCard()
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier){
    TopAppBar(
        title = { Text(text = "Calculateur de base") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
    )
}

@Composable
fun QuantityCard(
    modifier: Modifier = Modifier,
) {
    var quantity by remember { mutableStateOf(100f.toString()) }
    Card {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.quantity_text))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(64.dp)
                        .width(128.dp),
                    value = quantity,
                    onValueChange = { quantity = it },
                    textStyle = TextStyle.Default.copy(
                        textAlign = TextAlign.Center,
                        fontSize = TextUnit(25f, TextUnitType.Sp)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {

                        }
                    )
                )
                Text(text = "mL")
            }
        }
    }
}

@Composable
fun NicotineCard(
    modifier: Modifier = Modifier,
) {
    var quantity by remember { mutableStateOf(3f.toString()) }
    Card {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.nicotine_text))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(64.dp)
                        .width(128.dp),
                    value = quantity,
                    onValueChange = { quantity = it },
                    textStyle = TextStyle.Default.copy(
                        textAlign = TextAlign.Center,
                        fontSize = TextUnit(25f, TextUnitType.Sp)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {

                        }
                    )
                )
                Text(text = "ᵐᵍ⁄ₘₗ") // mg/ml in Unicode
            }
        }
    }
}

@Preview
@Composable
fun VapeScreenPreview(){
    VapeCalculatorTheme {
        VapeScreen()
    }
}