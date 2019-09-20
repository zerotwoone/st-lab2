package ru.ifmo.st.lab2.program

import ru.ifmo.st.lab2.domain.ExportDBUseCase
import ru.ifmo.st.lab2.program.main.ArgumentCommandProgram
import ru.ifmo.st.lab2.program.main.CommandProgram

class ExportProgram(private val export: ExportDBUseCase) : ArgumentCommandProgram() {
    companion object {
        const val OK = "База данных была успешно импортирована"
    }

    override fun validateArgs(args: List<String>) = args.size == 1

    override fun afterStart() {
        export(args.first())
        showMessage(OK)
    }
}
