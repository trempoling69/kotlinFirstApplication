package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.PostApi
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.api.model.PostResponse
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: PostResponse) : UiState()
        data class Error(val message: String) : UiState()
    }

    fun getPostById(postId: Int) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val post = RetrofitInstance.getInstance().create(PostApi::class.java).getPostById(postId)
                _uiState.value = UiState.Success(post)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Erreur lors du chargement : ${e.localizedMessage}")
            }
        }
    }
}
