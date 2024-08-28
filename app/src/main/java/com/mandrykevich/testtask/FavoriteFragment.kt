package com.mandrykevich.testtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandrykevich.testtask.Adapter.OnVacancyClickListener
import com.mandrykevich.testtask.Adapter.OnFavoriteClickListener
import com.mandrykevich.testtask.Adapter.VerticalAdapter
import com.mandrykevich.testtask.Adapter.Vacancy
import com.mandrykevich.testtask.Adapter.getVacancyData
import com.mandrykevich.testtask.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment(), OnVacancyClickListener, OnFavoriteClickListener {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var verticalAdapter: VerticalAdapter
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        verticalAdapter = VerticalAdapter(emptyList(), this, this) // начально пустой список
        binding.rvIsfavorite.adapter = verticalAdapter



        viewModel.vacancies.observe(viewLifecycleOwner) { allVacancies ->
            val favoriteVacancies = allVacancies.filter { it.isFavorite }
            verticalAdapter.updateData(favoriteVacancies)
        }
        updateFavoriteList()
        return binding.root
    }

    override fun onVacancyClick(vacancy: Vacancy) {
        viewModel.toggleFavorite(vacancy)
        updateFavoriteList()
    }

    override fun onFavoriteClick(vacancy: Vacancy) {
        viewModel.toggleFavorite(vacancy)
        updateFavoriteList()
    }

    private fun updateFavoriteList() {
        val favoriteVacancies = viewModel.vacancies.value?.filter { it.isFavorite } ?: emptyList()
        verticalAdapter.updateData(favoriteVacancies)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}


