package com.example.enjeela.finalassignment

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class LoginTestingInstrumented {
    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun checkLogin() {
        onView(withId(R.id.inputUsername))
            .perform(typeText("enjeela"))
        onView(withId(R.id.inputPassword))
            .perform(typeText("11"))
        closeSoftKeyboard()
        onView(withId(R.id.btnLogin))
            .perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.drawer_layout))
            .check(matches(isDisplayed()))
    }
}