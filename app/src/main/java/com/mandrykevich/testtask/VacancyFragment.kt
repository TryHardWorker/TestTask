    package com.mandrykevich.testtask

    import android.os.Bundle
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.activityViewModels
    import androidx.recyclerview.widget.LinearLayoutManager
    import com.mandrykevich.testtask.Adapter.OnFavoriteClickListener
    import com.mandrykevich.testtask.Adapter.OnVacancyClickListener
    import com.mandrykevich.testtask.Adapter.Vacancy
    import com.mandrykevich.testtask.Adapter.VerticalAdapter
    import com.mandrykevich.testtask.Adapter.getOffersData
    import com.mandrykevich.testtask.Adapter.getVacancyData
    import com.mandrykevich.testtask.databinding.FragmentVacancyBinding

    class VacancyFragment : Fragment(), OnVacancyClickListener, OnFavoriteClickListener {
        private lateinit var binding: FragmentVacancyBinding
        private lateinit var verticalAdapter: VerticalAdapter
        private val viewModel: ViewModel by activityViewModels()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentVacancyBinding.inflate(inflater, container, false)

            // Инициализируем адаптер
            verticalAdapter = VerticalAdapter(viewModel.vacancies.value ?: emptyList(), this, this)

            // Наблюдаем за изменениями во ViewModel
            viewModel.vacancies.observe(viewLifecycleOwner) { updatedVacancies ->
                verticalAdapter.updateData(updatedVacancies)
            }

            binding.rvVertical.layoutManager = LinearLayoutManager(context)
            binding.rvVertical.adapter = verticalAdapter

            val horizontalAdapter = OffersAdapter(getOffersData())
            binding.rvHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvHorizontal.adapter = horizontalAdapter

            // Установка текста кнопки "Показать все"
            val vacancyCount = viewModel.vacancies.value?.size ?: 0 - 3
            binding.btnShowall.text = getVacancyText(vacancyCount)

            binding.btnShowall.setOnClickListener {
                binding.btnShowall.visibility = View.GONE
                binding.rvHorizontal.visibility = View.GONE
                binding.ivSwapsearch.setImageResource(R.drawable.ic_left_arrow)
            }

            binding.ivSwapsearch.setOnClickListener {
                if (binding.ivSwapsearch.drawable.state == resources.getDrawable(R.drawable.ic_left_arrow, null).state) {
                    binding.btnShowall.visibility = View.VISIBLE
                    binding.rvHorizontal.visibility = View.VISIBLE
                    binding.ivSwapsearch.setImageResource(R.drawable.ic_search)
                }
            }

            return binding.root
        }

        override fun onVacancyClick(vacancy: Vacancy) {
            viewModel.selectedVacancy = vacancy
            viewModel.messageForActivity.value = "ShowVacancyFragment"
        }

        override fun onFavoriteClick(vacancy: Vacancy) {
            viewModel.toggleFavorite(vacancy) // Убираем локальное изменение, теперь оно происходит в ViewModel
        }

        companion object {
            @JvmStatic
            fun newInstance() = VacancyFragment()
        }

        private fun getVacancyText(count: Int): String {
            val vacancyText = when {
                count % 10 == 1 && count % 100 != 11 -> "вакансия"
                count % 10 in 2..4 && count % 100 !in 12..14 -> "вакансии"
                else -> "вакансий"
            }
            return "Показать ещё $count $vacancyText"
        }
    }

