import com.example.todo.models.Task
import org.junit.Assert.*
import org.junit.Test

class TaskTest {
    @Test
    fun testChange() {
        // Given
        val originalTask = Task(name = "Original Task", description = "Description", mark = "Mark", date = "2024-05-23", priority = "High", project_id = 1)
        val changedTask = Task(name = "Changed Task", description = "Changed Description", mark = "Changed Mark", date = "2024-05-24", priority = "Low", project_id = 2)
        // When
        originalTask.change(changedTask)
        // Then
        assertEquals(changedTask.name, originalTask.name)
        assertEquals(changedTask.description, originalTask.description)
        assertEquals(changedTask.mark, originalTask.mark)
        assertEquals(changedTask.date, originalTask.date)
        assertEquals(changedTask.priority, originalTask.priority)
    }
    @Test
    fun testChangeStatus() {
        // Given
        val task = Task(name = "Test Task", description = "Description", mark = "Mark", date = "2024-05-23", priority = "High", project_id = 1)
        // When
        task.changeStatus()
        val statusAfterFirstChange = task.status
        task.changeStatus()
        val statusAfterSecondChange = task.status
        // Then
        assertTrue(statusAfterFirstChange!!)
        assertFalse(statusAfterSecondChange!!)
    }
}
