package com.mandrykevich.testtask.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mandrykevich.testtask.R

class QuestionsAdapter(private val questions: List<String>) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.btn_question)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.questionTextView.text = questions[position]
    }

    override fun getItemCount(): Int {
        return questions.size
    }
}
