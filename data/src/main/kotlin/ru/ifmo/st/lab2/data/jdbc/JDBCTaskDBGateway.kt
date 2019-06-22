package ru.ifmo.st.lab2.data.jdbc

import ru.ifmo.st.lab2.core.Task
import ru.ifmo.st.lab2.gateway.TaskDBGateway

class JDBCTaskDBGateway(db: DB) : TaskDBGateway {
    private val connection = db.connection

    override fun addTask(task: Task) {

        if (task.id != null) {
            throw IllegalStateException()
        }
        val query = "SELECT add_task('${task.name}', '${task.description}', '${task.dueData}', ARRAY[${task.tags.toSQLList()}]);"
        connection.createStatement().use { it.execute(query) }
    }

    private fun List<String>.toSQLList() = joinToString(separator = ",") { "\"$it\"" }

    override fun fetchTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val query = "SELECT * FROM task_view;"
        connection.createStatement().use {
            val resultSet = it.executeQuery(query)
            while (resultSet.next()) {
                val id = resultSet.getLong("id")
                val name = resultSet.getString("name")
                val description = resultSet.getString("description")
                val dueDate = resultSet.getDate("due_date")
                val tags = resultSet.getArray("array_agg").array as Array<String>

                tasks.add(Task(name, description, dueDate, tags.toList(), id))
            }
        }

        return tasks
    }

    override fun clear() {
        val query = "DELETE FROM task; DELETE FROM tag;"
        connection.createStatement().use {
            it.execute(query)
        }
    }
}