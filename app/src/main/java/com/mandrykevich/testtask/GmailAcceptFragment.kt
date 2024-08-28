package com.mandrykevich.testtask

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.mandrykevich.testtask.databinding.FragmentEntranceBinding
import com.mandrykevich.testtask.databinding.FragmentGmailAcceptBinding

class GmailAcceptFragment : Fragment() {
    lateinit var binding: FragmentGmailAcceptBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGmailAcceptBinding.inflate(inflater)
        with(binding){
            EnteringText(editNum1,editNum2,editNum3,editNum4)
        }


        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = GmailAcceptFragment()
    }

    fun EnteringText(textEdit1: EditText, textEdit2: EditText, textEdit3: EditText, textEdit4: EditText) {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                updateConfirmButtonState(textEdit1, textEdit2, textEdit3, textEdit4)
                // Переключение фокуса
                when (s.hashCode()) {
                    textEdit1.text.hashCode() -> {
                        if (s.length == 1) textEdit2.requestFocus()
                    }
                    textEdit2.text.hashCode() -> {
                        if (s.isEmpty()) textEdit1.requestFocus() else textEdit3.requestFocus()
                    }
                    textEdit3.text.hashCode() -> {
                        if (s.isEmpty()) textEdit2.requestFocus() else textEdit4.requestFocus()
                    }
                    textEdit4.text.hashCode() -> {
                        if (s.isEmpty()) textEdit3.requestFocus()
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }

        textEdit1.addTextChangedListener(textWatcher)
        textEdit2.addTextChangedListener(textWatcher)
        textEdit3.addTextChangedListener(textWatcher)
        textEdit4.addTextChangedListener(textWatcher)
    }

    private fun updateConfirmButtonState(textEdit1: EditText, textEdit2: EditText, textEdit3: EditText, textEdit4: EditText) {
        val allFieldsFilled = textEdit1.text.isNotEmpty() &&
                textEdit2.text.isNotEmpty() &&
                textEdit3.text.isNotEmpty() &&
                textEdit4.text.isNotEmpty()

        with(binding){
            if(allFieldsFilled == true){
                btnAccept.isEnabled = allFieldsFilled
                btnAccept.setBackgroundResource(R.drawable.buttonstyle_2)
                btnAccept.setTextColor(Color.WHITE)
                btnAccept.setOnClickListener {
                    viewModel.messageForActivity.value = "GmailAcceptFragment"
                }
            }
            if(allFieldsFilled == false){
                btnAccept.isEnabled = allFieldsFilled
                btnAccept.setBackgroundResource(R.drawable.buttonstyle_1)
                btnAccept.setTextColor(Color.GRAY)
            }

        }

    }
}