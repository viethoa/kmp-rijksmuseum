package hoa.kv.githubadmin.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import githubadmin.userdetails.generated.resources.Res
import githubadmin.userdetails.generated.resources.no_network_connection
import githubadmin.userdetails.generated.resources.something_when_wrong
import hoa.kv.githubadmin.repository.ApiResponse
import hoa.kv.githubadmin.repository.ApiResponse.NoNetworkException
import hoa.kv.githubadmin.repository.model.User
import hoa.kv.githubadmin.repository.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

class UserDetailViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserDetailUiState>(UserDetailUiState.Loading)
    val uiState: StateFlow<UserDetailUiState> = _uiState

    fun getUser(loginName: String) {
        viewModelScope.launch {
            _uiState.value = UserDetailUiState.Loading
            when (val response = userRepository.getUser(loginName)) {
                is ApiResponse.Success -> {
                    _uiState.value = UserDetailUiState.GetUserSuccess(response.data)
                }
                is ApiResponse.Error -> {
                    _uiState.value = UserDetailUiState.GetUserError(response.getErrorMessage())
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

sealed class UserDetailUiState {
    data object Loading : UserDetailUiState()
    data class GetUserSuccess(val user: User) : UserDetailUiState()
    data class GetUserError(val errorMessage: StringResource) : UserDetailUiState()
}