package com.example.moviedb.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.data.repository.UserRepositoryImpl
import com.example.moviedb.domain.repository.UserRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel: ViewModel() {

    private val _liveData = MutableLiveData<State>()
    val liveData: LiveData<State>
        get() = _liveData

    private val job = SupervisorJob()

    private val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val uiScope: CoroutineScope = CoroutineScope(coroutineContext)

    private val userRepository: UserRepository = UserRepositoryImpl()

    fun login(username: String, password: String) {
        uiScope.launch {
            _liveData.value = State.ShowLoading
            val result = withContext(Dispatchers.IO) {
                userRepository.createToken()
                userRepository.login(username, password)
            }
            val sessionId: String = withContext(Dispatchers.Default) {
                userRepository.createSession().body()!!.getAsJsonPrimitive("session_id").asString
            }
            val accountId: Int? = withContext(Dispatchers.IO) {
                userRepository.getAccountDetails(sessionId)?.id
            }
            _liveData.value = State.HideLoading
            _liveData.postValue(State.ApiResult(result, sessionId, accountId))
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    sealed class State() {
        object ShowLoading: State()
        object HideLoading: State()
        data class ApiResult(val success: Boolean, val session_id: String, val account_id: Int?): State()
        data class Error(val error: String): State()
    }
}



