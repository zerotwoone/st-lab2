package ru.ifmo.st.lab2.module

import ru.ifmo.st.lab2.domain.*
import ru.ifmo.st.lab2.sl.buildContainer
import ru.ifmo.st.lab2.sl.get

val domainModule = buildContainer {
    factory<AddNewTaskUseCase> { AddNewTaskUseCaseImpl(get()) }

    factory<FetchNActualTaskUseCase> { FetchNActualTaskUseCaseImpl(get()) }
    factory<FetchActualTaskUseCase> { FetchActualTaskUseCaseImpl(get()) }

    factory<FetchTaskUseCase> { FetchTaskUseCaseImpl(get()) }
    factory<FetchNTaskUseCase> { FetchNTaskUseCaseImpl(get()) }
}
