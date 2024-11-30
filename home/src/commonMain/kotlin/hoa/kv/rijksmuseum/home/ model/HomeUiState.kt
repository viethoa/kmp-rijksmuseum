package hoa.kv.rijksmuseum.home.model

import hoa.kv.rijksmuseum.repository.model.Art
import org.jetbrains.compose.resources.StringResource

sealed interface HomeUiState {
    data object Empty : HomeUiState
    data class Error(val message: StringResource) : HomeUiState
    data object Loading : HomeUiState
    data class Success(
        val arts: List<Art>,
        val filteredPlaces: List<String> = emptyList()
    ) : HomeUiState {

        val productionPlaces: List<String> = arts
            .flatMap { it.productionPlaces }
            .filterNot { it.startsWith("?") }
            .distinct()

        val filteredArts: List<Art> = arts.filter { art ->
            filteredPlaces.isEmpty() || art.productionPlaces.any { filteredPlaces.contains(it) }
        }

        fun getWebImage(position: Int): String = arts[position].webImage.url
    }
}