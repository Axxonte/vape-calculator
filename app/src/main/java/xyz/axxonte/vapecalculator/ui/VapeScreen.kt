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
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import xyz.axxonte.vapecalculator.R
import xyz.axxonte.vapecalculator.data.VapeViewModel
import xyz.axxonte.vapecalculator.ui.theme.VapeCalculatorTheme

@Composable
fun VapeScreen(
    viewModel: VapeViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = { AppTopBar() }
    ) { padding ->

        Column (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(padding)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val setQuantity = { float: Float ->
                    viewModel.setQuantity(float)
                }
                val setNicotine = { float: Float ->
                    viewModel.setNicotine(float)
                }
                QuantityCard(updateQuantity = setQuantity)
                NicotineCard(updateNicotine = setNicotine)

            }

            Divider(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color.Black
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ResultCard(
                    label = "Base à ajouter",
                    unit = "ml",
                    value = uiState.value.totalBase.toString()
                )

                ResultCard(
                    label = "Nicotine à ajouter",
                    unit = "ml",
                    value = uiState.value.totalNicotine.toString())

            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier) {
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
    updateQuantity: (Float) -> Unit
) {
    var quantity by remember { mutableStateOf(100f.toString()) }
    Card {
        Column(
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
                    onValueChange = {
                        quantity = it
                        if (quantity != "") {
                            updateQuantity(quantity.toFloat())
                        }
                    },
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
                Text(text = "ml")
            }
        }
    }
}

@Composable
fun NicotineCard(
    modifier: Modifier = Modifier,
    updateNicotine: (Float) -> Unit
) {
    var nicotine by remember { mutableStateOf(3f.toString()) }
    Card {
        Column(
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
                    value = nicotine,
                    onValueChange = {
                        nicotine = it
                        if (nicotine != "") {
                            updateNicotine(nicotine.toFloat())
                        }
                    },
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
                Text(text =  "ᵐᵍ⁄ₘₗ") // mg/ml in Unicode
            }
        }
    }
}

@Composable
fun ResultCard(modifier: Modifier = Modifier, label: String, unit: String, value: String) {
    Card {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = modifier
                        .padding(8.dp)
                        .height(64.dp)
                        .width(128.dp),
                    value = value,
                    onValueChange = { },
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
                    ),
                    readOnly = true
                )
                Text(text = unit) // mg/ml in Unicode
            }
        }
    }
}

@Preview
@Composable
fun VapeScreenPreview() {
    VapeCalculatorTheme {
        VapeScreen(VapeViewModel())
    }
}