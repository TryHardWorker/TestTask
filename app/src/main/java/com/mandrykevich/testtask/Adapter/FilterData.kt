package com.mandrykevich.testtask.Adapter.FilterData

data class FilterData(
    val id: String,
    val title: String,
    val buttonText: String? = null, // Nullable in case button is not present
    val link: String
)