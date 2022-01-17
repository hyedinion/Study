package com.example.my_first.presentation.scrap

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_first.domain.model.Scrap
import com.example.my_first.domain.use_case.DeleteScrap
import com.example.my_first.domain.use_case.ScrapUseCases
import com.example.my_first.domain.util.ScrapOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScrapViewModel @Inject constructor(
    private val scrapUseCases: ScrapUseCases
) : ViewModel(){

    private val _state = mutableStateOf(ScrapState())
    val state : State<ScrapState> = _state

    private var getScrapsJob : Job? = null


    fun onEvent(event: ScrapEvent){
        when(event){
            is ScrapEvent.Order -> {//state와 event가 다른객체이기 때문에 class가 같은지 비교, orderType은 object이기 때문에 같음
                if (state.value.scrapOrder::class == event.scrapOrder::class &&
                        state.value.scrapOrder.orderType ==event.scrapOrder.orderType){
                    return
                }
                getScraps(event.scrapOrder)


            }
            is ScrapEvent.DeleteScrap -> {
                viewModelScope.launch {
                    scrapUseCases.deleteScrap(event.scrap)//DeleteScrap의 파라미터로 넘어온 값
                }

            }
            is ScrapEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(//isOrderSection만 바꿔서 copy
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    //근데 getScrap을 호출할 때마다 새로운 flow를 받게 됨.
    //그래서 만약 여러번 호출을 하면 기존의 코루틴 job을 취소해줘야 성능이 좋아짐(getScrap은 여러번 호출할 수 있으니까)
    fun getScraps(scrapOrder: ScrapOrder){//getScrap은 비동기적으로 처리되야하니까 usecase에서 Flow로 넘겨줘야함
        getScrapsJob?.cancel()
        //Flow로 return 받음, flow는 database가 변경될 때마다 불러옴
        //만약 livedata로 return했으면 수정 불가능, suspend fun은 하나의 값만 return, flow는 list도 비동기적으로 return가능
        getScrapsJob = scrapUseCases.getScraps(scrapOrder)
            .onEach { scraps ->
                _state.value = state.value.copy(
                    scraps = scraps,
                    scrapOrder = scrapOrder //넘겨진 파라미터order로 scrap의 order를 변경
                )
            }
            .launchIn(viewModelScope)

    }

}