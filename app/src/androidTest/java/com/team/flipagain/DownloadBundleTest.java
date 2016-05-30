package com.team.flipagain;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.team.flipagain.gui.login.LoginActivity;
import com.team.flipagain.gui.mainScreen.cardCreator.CardCreatorActivity;
import com.team.flipagain.gui.mainScreen.cardCreator.SelectBundleActivity;
import com.team.flipagain.gui.mainScreen.cardGetter.CardGetterActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith (AndroidJUnit4.class)

@LargeTest
public class DownloadBundleTest {

    @Rule
    public ActivityTestRule<LoginActivity> LActivityRule = new ActivityTestRule<>(LoginActivity.class);
    private LoginActivity loginActivity;

    @Before
    public void setUpActivity() {
        //Start at LoginActivity
        loginActivity = LActivityRule.getActivity();
    }

    @Test
    public void testDownloadBundle(){

        onView(withResourceName("email")).perform(typeText("bueti@hsr.ch"));
        onView(withResourceName("password")).perform(typeText("123456"));
        onView(withResourceName("email_sign_in_button")).perform(click());
        //Navigation
        onView(withResourceName("mainScreen_btn_getNewCards")).perform(click());
        onView(withText("Informatik")).perform(click());
        onView(withText("Informationsicherheit")).perform(click());
        onView(withText("ServerTest one")).perform(click());
        //Download Bundle
        onView(withResourceName("cardGetter_btn_download")).perform(click());
        //Is Bundle available
        onView(withText("Lernen")).perform(click());
        onView(withText("Informatik")).perform(click());
        onView(withText("Informationsicherheit")).perform(click());
        onView(withText("ServerTest one")).perform(click());
        //Button Start
        onView(withId(2131492988)).perform(click());
        onView(withText("OK")).perform(click());

        //assert first question displayed
        onView(withResourceName("TextView_question")).check(matches(isDisplayed()));
    }
}
