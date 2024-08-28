package com.mandrykevich.testtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandrykevich.testtask.Adapter.Vacancy
import com.mandrykevich.testtask.Adapter.getVacancyData


class ViewModel : ViewModel() {
    val messageForFragment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageForActivity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    var selectedVacancy: Vacancy? = null

    val vacancies: MutableLiveData<List<Vacancy>> by lazy {
        MutableLiveData<List<Vacancy>>(getVacancyData())
    }

    fun toggleFavorite(vacancy: Vacancy) {
        val updatedVacancies = vacancies.value?.map { item ->
            if (item.id == vacancy.id) {
                item.copy(isFavorite = !item.isFavorite)
            } else {
                item
            }
        }
        updatedVacancies?.let {
            vacancies.postValue(it)
        }
    }

}
