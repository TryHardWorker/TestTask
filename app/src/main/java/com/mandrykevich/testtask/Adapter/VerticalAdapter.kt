package com.mandrykevich.testtask.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mandrykevich.testtask.Adapter.Vacancy
import com.mandrykevich.testtask.R

class VerticalAdapter(
    private var items: List<Vacancy>,
    private val listener: OnVacancyClickListener,
    private val favoriteListener: OnFavoriteClickListener
) : RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    private val activeFavoriteIcon: Int = R.drawable.ic_favorite_active
    private val favoriteIcon: Int = R.drawable.ic_favorite

    fun updateData(newItems: List<Vacancy>) {
        items = newItems
        notifyDataSetChanged() // Уведомляем адаптер об изменениях
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vacancy, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvVacancyName.text = item.title
        holder.tvVacancyCity.text = item.address.town
        holder.tvVacancySalary.text = item.salary.short
        holder.tvVacancyPostdate.text = item.publishedDate
        holder.tvVacancyExp.text = item.experience.text
        holder.tvPeopleCount.text = "Сейчас просматривают ${item.lookingNumber} человек"

        holder.btnAbout.setOnClickListener {
            listener.onVacancyClick(item)
        }

        holder.isFavoriteIcon.setImageResource(if (item.isFavorite) activeFavoriteIcon else favoriteIcon)
        holder.isFavoriteIcon.setOnClickListener {
            favoriteListener.onFavoriteClick(item)
            item.isFavorite = !item.isFavorite

            holder.isFavoriteIcon.setImageResource(if (item.isFavorite) activeFavoriteIcon else favoriteIcon)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvVacancyName: TextView = view.findViewById(R.id.tv_vacancy_name)
        val tvVacancyCity: TextView = view.findViewById(R.id.tv_vacancy_city)
        val tvVacancySalary: TextView = view.findViewById(R.id.tv_vacancy_salary)
        val tvVacancyPostdate: TextView = view.findViewById(R.id.tv_vacancy_postdate)
        val tvVacancyExp: TextView = view.findViewById(R.id.tv_vacancy_exp)
        val tvPeopleCount: TextView = view.findViewById(R.id.tv_peoplecount)
        val btnAbout: View = view.findViewById(R.id.btn_view)
        val isFavoriteIcon: ImageView = view.findViewById(R.id.isFavorite)
    }
}
