package xyz.axxonte.vapecalculator.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VapeViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(UiState(100f, 3f, null, null))
    val uiState = _uiState.asStateFlow()

    fun calculateTotalNicotineAndBase() {
        // Calcul quantité nicotine = taux de nicotine voulue * quantité voulue / 20
        val tNicotine =
            uiState.value.quantity * uiState.value.nicotine / 20
        val tBase = uiState.value.quantity - tNicotine

        _uiState.update {
            UiState(
                quantity = uiState.value.quantity,
                nicotine = uiState.value.nicotine,
                totalBase = tBase,
                totalNicotine = tNicotine
            )
        }
    }

    fun setQuantity(quantity: Float) {
        _uiState.update {
            UiState(
                quantity = quantity,
                nicotine = uiState.value.nicotine,
                totalBase = uiState.value.totalBase,
                totalNicotine = uiState.value.totalNicotine
            )
        }
    }

    fun setNicotine(nicotine: Float) {
        _uiState.update {
            UiState(
                quantity = uiState.value.quantity,
                nicotine = nicotine,
                totalBase = uiState.value.totalBase,
                totalNicotine = uiState.value.totalNicotine
            )
        }
    }

}

data class UiState
    (
    val quantity: Float,
    val nicotine: Float,
    val totalBase: Float?,
    val totalNicotine: Float?
)