package com.mandrykevich.testtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandrykevich.testtask.Adapter.Address
import com.mandrykevich.testtask.Adapter.Button
import com.mandrykevich.testtask.Adapter.Experience
import com.mandrykevich.testtask.Adapter.QuestionsAdapter
import com.mandrykevich.testtask.Adapter.Salary
import com.mandrykevich.testtask.Adapter.Vacancy
import com.mandrykevich.testtask.databinding.FragmentAboutVacancyBinding

class AboutVacancyFragment : Fragment() {
    private lateinit var vacancy: Vacancy
    private val viewModel: ViewModel by activityViewModels()
    lateinit var binding: FragmentAboutVacancyBinding
    private lateinit var questionsAdapter: QuestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutVacancyBinding.inflate(inflater, container, false)

        vacancy = viewModel.selectedVacancy ?: run {
            binding.tvAboutname.text = "No vacancy details available."
            return binding.root
        }

        binding.tvAboutname.text = vacancy.title
        binding.tvAboutsalary.text = vacancy.salary.full
        binding.tvAboutexp.text = vacancy.experience.text
        binding.tvAboutschedules.text = vacancy.schedules.joinToString(", ")
        binding.tvAboutcompany.text = vacancy.company
        binding.tvAboutaddress.text = "${vacancy.address.town}, ${vacancy.address.street} ${vacancy.address.house}"
        binding.tvAboutdescription.text = vacancy.description
        binding.tvAboutresponsibilities.text = vacancy.responsibilities
        binding.tvIsLooking.text = "${vacancy.lookingNumber.toString()} человека сейчас смотрят"
        binding.tvIsClicked.text = "${vacancy.appliedNumber.toString()} человек уже откликнулись"

        if (vacancy.questions.isNotEmpty()) {
            questionsAdapter = QuestionsAdapter(vacancy.questions)
            binding.recyclerViewQuestions.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewQuestions.adapter = questionsAdapter
        } else {
            binding.recyclerViewQuestions.visibility = View.GONE
        }

        binding.imToPrev.setOnClickListener { toMain() }
        return binding.root
    }
    fun toMain(){
        viewModel.messageForActivity.value = "GmailAcceptFragment"
    }


    companion object {
        @JvmStatic
        fun newInstance(vacancy: Vacancy): AboutVacancyFragment {
            val fragment = AboutVacancyFragment()
            val args = Bundle()
            args.putSerializable("vacancy", vacancy)
            fragment.arguments = args
            return fragment
        }
    }
}


