import com.example.todo.models.Project
import org.junit.Assert.*
import org.junit.Test

class ProjectTest {
    @Test
    fun testGetName() {
        // Given
        val projectName = "Test Project"
        val project = Project(name = projectName)
        // When
        val name = project.getName()
        // Then
        assertEquals(projectName, name)
    }
    @Test
    fun testSetName() {
        // Given
        val project = Project(name = "Initial Name")
        val newName = "New Project Name"
        // When
        project.setName(newName)
        // Then
        assertEquals(newName, project.getName())
    }
    @Test
    fun testGetId() {
        // Given
        val project = Project(id = 123, name = "Test Project")
        // When
        val id = project.getId()
        // Then
        assertEquals(123, id)
    }
}
