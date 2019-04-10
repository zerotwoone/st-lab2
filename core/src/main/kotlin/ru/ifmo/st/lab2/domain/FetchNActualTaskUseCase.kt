package ru.ifmo.st.lab2.domain

import ru.ifmo.st.lab2.core.Task

interface FetchNActualTaskUseCase {
    fun fetch(limit: Int): List<Task>
}