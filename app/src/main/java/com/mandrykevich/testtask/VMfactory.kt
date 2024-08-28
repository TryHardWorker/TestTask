package com.mandrykevich.testtask

import android.content.Context
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VMfactory(context: Context) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return com.mandrykevich.testtask.ViewModel(

        ) as T
    }


}