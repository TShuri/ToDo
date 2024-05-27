package com.example.todo

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.todo.adapters.ProjectsAdapter
import com.example.todo.screens.FragmentTasks
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.RecyclerViewActions


@RunWith(AndroidJUnit4::class)
@LargeTest
class FragmentTasksTest {

    @Test
    fun testFragmentTasks_displayedInUi() {
        // Запускаем MainActivity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Нажимаем на первый элемент списка проектов
        onView(withId(R.id.list_projects))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Проверяем, что заголовок фрагмента отображается
        onView(withId(R.id.text_title_tasks))
            .check(matches(isDisplayed()))

        // Проверяем, что RecyclerView для списка задач отображается
        onView(withId(R.id.list_tasks))
            .check(matches(isDisplayed()))

        // Проверяем, что кнопка создания задачи отображается
        onView(withId(R.id.button_create_task))
            .check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun testButtonCreateTask_clickNavigatesToFragmentTask() {
        // Запускаем MainActivity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Нажимаем на первый элемент списка проектов
        onView(withId(R.id.list_projects))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Нажимаем на кнопку создания задачи
        onView(withId(R.id.button_create_task))
            .perform(click())

        // Проверяем, что произошел переход на экран создания задачи
        onView(withId(R.id.fragment_task)) // Заменить на реальный идентификатор контейнера фрагмента
            .check(matches(isDisplayed()))

        activityScenario.close()
    }
}
