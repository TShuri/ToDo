package com.example.todo

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FragmentProjectsTest {
    @Test
    fun testFragmentProjects_displayedInUi() {
        // Запускаем MainActivity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Проверяем, что TextView с заголовком отображается
        onView(withId(R.id.text_title_projects))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.projects)))

        // Проверяем, что кнопка создания проекта отображается
        onView(withId(R.id.button_create_project))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.create_project)))

        // Проверяем, что RecyclerView для списка проектов отображается
        onView(withId(R.id.list_projects))
            .check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun testButtonCreateProject_clickOpensDialog() {
        // Запускаем MainActivity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Нажимаем на кнопку создания проекта
        onView(withId(R.id.button_create_project))
            .perform(click())

        // Проверяем, что диалог отображается
        onView(withText(""))
            .check(matches(isDisplayed()))

        activityScenario.close()
    }
}
