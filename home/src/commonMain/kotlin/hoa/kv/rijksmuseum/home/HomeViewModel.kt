package hoa.kv.rijksmuseum.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hoa.kv.rijksmuseum.designsystem.resources.Res
import hoa.kv.rijksmuseum.designsystem.resources.no_network_connection
import hoa.kv.rijksmuseum.designsystem.resources.something_when_wrong
import hoa.kv.rijksmuseum.home.model.HomeUiState
import hoa.kv.rijksmuseum.repository.ApiResponse
import hoa.kv.rijksmuseum.repository.ApiResponse.NoNetworkException
import hoa.kv.rijksmuseum.repository.art.ArtRepository
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

class HomeViewModel(
    private val artRepository: ArtRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading
        )

    init {
        viewModelScope.launch {
            val response = artRepository.getCollection(Random.nextInt(1, 10))
            _uiState.update {
                when (response) {
                    is ApiResponse.Success -> {
                        if (response.data.isEmpty()) {
                            HomeUiState.Empty
                        } else {
                            HomeUiState.Success(response.data)
                        }
                    }
                    is ApiResponse.Error -> HomeUiState.Error(response.getErrorMessage())
                }
            }
        }
    }

    private fun ApiResponse.Error.getErrorMessage(): StringResource {
        return when (this.throwable) {
            is NoNetworkException -> Res.string.no_network_connection
            else -> Res.string.something_when_wrong
        }
    }
}