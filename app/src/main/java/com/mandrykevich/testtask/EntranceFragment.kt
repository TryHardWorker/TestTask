package com.mandrykevich.testtask

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.mandrykevich.testtask.databinding.FragmentEntranceBinding


class EntranceFragment : Fragment() {
lateinit var binding: FragmentEntranceBinding
private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntranceBinding.inflate(inflater)
        textChecker(binding.editGmail)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = EntranceFragment()

    }


    fun textChecker(editText: EditText){
        binding.btnClear.setOnClickListener { editText.text = null }
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                with(binding) {
                    if (s.isNullOrEmpty()) {
                        // Строка пуста
                        imConvert.visibility = View.VISIBLE
                        btnClear.visibility = View.GONE
                        btnNext.isClickable = false
                        btnNext.setBackgroundResource(R.drawable.buttonstyle_1)
                        btnNext.setTextColor(Color.GRAY)
                        cardEmail.setBackgroundResource(R.drawable.card_border)
                        tvError.visibility = View.INVISIBLE
                    } else {
                        // Строка не пуста
                        imConvert.visibility = View.GONE
                        btnClear.visibility = View.VISIBLE
                        btnNext.isClickable = true
                        btnNext.setBackgroundResource(R.drawable.buttonstyle_2)
                        btnNext.setTextColor(Color.WHITE)
                        binding.cardEmail.setBackgroundResource(R.drawable.card_border)
                        tvError.visibility = View.INVISIBLE
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {nextFrag(binding.btnNext)}
        })
    }

    fun nextFrag(button: Button) {
        val email = binding.editGmail.text.toString()
        if (isEmail(email)) {
            // Обработка случая, когда email является корректным
            button.setOnClickListener {
                viewModel.messageForActivity.value = "ShowAcceptFragment"
            }
        } else {
            button.setOnClickListener {
                binding.cardEmail.setBackgroundResource(R.drawable.card_border_error)
                binding.tvError.visibility = View.VISIBLE
            }
        }
    }

    fun isEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}