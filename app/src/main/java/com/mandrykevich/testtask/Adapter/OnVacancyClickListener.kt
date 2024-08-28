package com.mandrykevich.testtask.Adapter

import com.mandrykevich.testtask.Adapter.Vacancy

interface OnVacancyClickListener {
    fun onVacancyClick(vacancy: Vacancy)
}

interface OnFavoriteClickListener{
    fun onFavoriteClick(vacancy: Vacancy)
}