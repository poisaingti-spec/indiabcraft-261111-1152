package com.crafted.indiabcraft.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crafted.indiabcraft.data.model.Content
import com.crafted.indiabcraft.data.repository.ContentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = ContentRepository()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadContent()
    }

    private fun loadContent() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            repository.getFeaturedContent()
                .catch { e -> _uiState.value = _uiState.value.copy(error = e.message) }
                .collect { featured ->
                    _uiState.value = _uiState.value.copy(
                        featuredContent = featured,
                        isLoading = false
                    )
                }
        }

        viewModelScope.launch {
            repository.getTrendingContent()
                .catch { e -> _uiState.value = _uiState.value.copy(error = e.message) }
                .collect { trending ->
                    _uiState.value = _uiState.value.copy(trendingContent = trending)
                }
        }

        viewModelScope.launch {
            repository.getContinueWatching()
                .catch { e -> _uiState.value = _uiState.value.copy(error = e.message) }
                .collect { continueWatching ->
                    _uiState.value = _uiState.value.copy(continueWatching = continueWatching)
                }
        }
    }

    fun refresh() {
        loadContent()
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val featuredContent: List<Content> = emptyList(),
    val trendingContent: List<Content> = emptyList(),
    val continueWatching: List<Content> = emptyList()
)