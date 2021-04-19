package com.example.riya.finalassignment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
class SignUpInstrumentedTesting {

        @get:Rule
        val testRule = ActivityScenarioRule(LoginActivity::class.java)

        @Test
        fun checkSignup() {
            onView(withId(R.id.fullname))
                .perform(typeText("enjeela"))

            onView(withId(R.id.username))
                .perform(typeText("enjeela"))

            onView(withId(R.id.address))
                .perform(typeText("Balaju"))
            closeSoftKeyboard()

            onView(withId(R.id.contact))
                .perform(typeText("888765"))

            onView(withId(R.id.email))
                .perform(typeText("balaju"))

            onView(withId(R.id.password))
                .perform(typeText("balaju"))

            onView(withId(R.id.btnRegister))
                .perform(click())
            Thread.sleep(6000)

            onView(withId(R.id.linearLayout))
                .check(matches(isDisplayed()))
        }
}