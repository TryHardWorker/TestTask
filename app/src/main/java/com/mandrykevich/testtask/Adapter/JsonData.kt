package com.mandrykevich.testtask.Adapter
import android.widget.TextView
import java.io.Serializable

data class ApiResponse(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)

data class Offer(
    val id: String,
    val title: String,
    val link: String,
    val button: String? = null
)

data class Button(
    val text: String
)

data class Vacancy(
    val id: String,
    val lookingNumber: Int? = null, // Nullable, может отсутствовать
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int? = null, // Nullable, может отсутствовать
    val description: String,
    val responsibilities: String,
    val questions: List<String>
) : Serializable

data class Address(
    val town: String,
    val street: String,
    val house: String
)

data class Experience(
    val previewText: String,
    val text: String
)

data class Salary(
    val short: String? = null, // Nullable, может отсутствовать
    val full: String
)


fun getOffersData(): List<Offer> {
    return listOf(
        Offer(
            id = "near_vacancies",
            title = "Вакансии рядом с вами",
            link = "https://hh.ru/",
            button = null
        ),
        Offer(
            id = "level_up_resume",
            title = "Поднять резюме в поиске",
            link = "https://hh.ru/mentors?from=footer_new&hhtmFromLabel=footer_new&hhtmFrom=main&purposeId=1",
            button = "Поднять"
        ),
        Offer(
            id = "temporary_job",
            title = "Временная работа или подработка",
            link = "https://hh.ru/",
            button = null
        ),
        Offer(
            id = "useful_articles",
            title = "Полезные статьи и советы",
            link = "https://hh.ru/articles?hhtmFrom=main",
            button = null
        )
    )
}


fun getVacancyData(): List<Vacancy> {
    return listOf(
        Vacancy(
            id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
            lookingNumber = 2,
            title = "UI/UX дизайнер",
            address = Address("Минск", "улица Бирюзова", "4/5"),
            company = "Мобирикс",
            experience = Experience("Опыт от 1 до 3 лет", "Опыт от 1 до 3 лет"),
            publishedDate = "2024-02-20",
            isFavorite = true,
            salary = Salary("Уровень дохода не указан", "Уровень дохода не указан"),
            schedules = listOf("полная занятость", "полный день"),
            appliedNumber = 147,
            description = "Мы ищем специалиста на позицию UX/UI Designer, который вместе с коллегами будет заниматься проектированием пользовательских интерфейсов внутренних и внешних продуктов компании.",
            responsibilities = "\"- проектирование пользовательских сценариев и создание прототипов;\\n- разработка интерфейсов для продуктов компании (Web+App);\\n- работа над созданием и улучшением Дизайн-системы;\\n- взаимодействие с командами frontend-разработки;\\n- контроль качества внедрения дизайна;\\n- ситуативно: создание презентаций и других материалов на основе фирменного стиля компании\"",
            questions = listOf(
                "Где располагается место работы?",
                "Какой график работы?",
                "Вакансия открыта?",
                "Какая оплата труда?"
            )
        ),
        Vacancy(
            id = "54a876a5-2205-48ba-9498-cfecff4baa6e",
            lookingNumber = 17,
            title = "UI/UX-дизайнер / Web-дизайнер / Дизайнер интерфейсов",
            address = Address(town = "Казань", street = "улица Чистопольская", house = "20/10"),
            company = "Шафигуллин Шакир",
            experience = Experience(previewText = "Опыт от 1 до 3 лет", text = "Опыт от 1 до 3 лет"),
            publishedDate = "2024-03-06",
            isFavorite = false,
            salary = Salary(short = "от 20 000 до 50 000 ₽ на руки", full = "от 20 000 до 50 000 ₽ на руки"),
            schedules = listOf("частичная занятость", "полный день"),
            appliedNumber = 0,
            description = "Мы разрабатываем мобильные приложения и web-приложения под ключ. Нам в команду нужен UX/UI Designer!",
            responsibilities = "Разработка дизайна Web+App (обязательно Figma); Работа над созданием и улучшением систем; Взаимодействие с командами frontend-разработки и backend-разработки",
            questions = listOf(
                "Где располагается место работы?",
                "Какой график работы?",
                "Как с вами связаться?"
            )
        ),
        Vacancy(
            id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3",
            lookingNumber = 0,
            title = "UX/UI Designer",
            address = Address(town = "Казань", street = "улица Пушкина", house = "2"),
            company = "Aston",
            experience = Experience(previewText = "Опыт от 3 до 6 лет", text = "Опыт от 3 до 6 лет"),
            publishedDate = "2024-02-28",
            isFavorite = false,
            salary = Salary(short = "Уровень дохода не указан", full = "Уровень дохода не указан"),
            schedules = listOf("полная занятость", "удаленная работа"),
            appliedNumber = 11,
            description = "Мы – аутсорсинговая IT-компания Aston, разрабатываем программное обеспечение на заказ и оказываем услуги IT-аутсорсинга. Среди наших клиентов – компании Тинькофф, Х5 Retail Group и другие.",
            responsibilities = "Определять бизнес-метрики, создавать прототипы, проводить тестирования и улучшать решения по результатам обратной связи.",
            questions = listOf(
                "Где располагается место работы?",
                "Какой график работы?",
                "Какая оплата труда?"
            )
        ),
        Vacancy(
            id = "2",
            lookingNumber = 2,
            title = "copypaster",
            address = Address(town = "Hrodno", street = "Broadway", house = "200"),
            company = "Innovate Inc.",
            experience = Experience(previewText = "17 years", text = "17 years"),
            publishedDate = "2024-08-10",
            isFavorite = false,
            salary = Salary(short = "$000,000", full = "$000,000"),
            schedules = listOf("Full-time"),
            appliedNumber = 30,
            description = "We need a product manager with experience.",
            responsibilities = "Oversee product development.",
            questions = listOf("How do you manage project timelines?")
        )
    )
}
