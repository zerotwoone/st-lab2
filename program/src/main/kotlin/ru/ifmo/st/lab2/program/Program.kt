package ru.ifmo.st.lab2.program


interface Program {
    fun create(context: Context)
    fun start()
    fun stop()

    fun process(input: String)

    val isWorking: Boolean
}