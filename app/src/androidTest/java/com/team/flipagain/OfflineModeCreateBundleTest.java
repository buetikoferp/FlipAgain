package com.team.flipagain;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.team.flipagain.gui.mainScreen.cardCreator.CardCreatorActivity;

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
public class OfflineModeCreateBundleTest {

    @Rule
    public ActivityTestRule<CardCreatorActivity> CRActivityRule = new ActivityTestRule<>(CardCreatorActivity.class);
    private CardCreatorActivity cardCreatorActivity;

    @Before
    public void setUpActivity(){
        //Start at CardCreatorActivity
        cardCreatorActivity = CRActivityRule.getActivity();
        onView(withText("Bundle erstellen")).perform(click());
    }


    @Test
    public void createBundleTest(){
        //Navigation
        onView(withText("Informatik")).perform(click());
        onView(withText("prog1")).perform(click());

        //Create Bundle
        onView(withResourceName("creatorBundle_txtF_bundlename")).perform(typeText("BundleIntegrationTest"));
        onView(withText("Bundle hinzufügen")).perform(click());

        //Create Cards for Bundle
        onView(withResourceName("creatorCards_txtf_question")).perform(typeText("IntegrationTestQuestion1"));
        onView(withResourceName("creatorCards_txtf_solution")).perform(typeText("IntegrationTestSolution1"));
        onView(withText("Speichern")).perform(click());
        onView(withResourceName("creatorCards_txtf_question")).perform(typeText("IntegrationTestQuestion2"));
        onView(withResourceName("creatorCards_txtf_solution")).perform(typeText("IntegrationTestSolution2"));
        onView(withText("Speichern")).perform(click());
        onView(withResourceName("creatorCards_txtf_question")).perform(typeText("IntegrationTestQuestion2"));
        onView(withResourceName("creatorCards_txtf_solution")).perform(typeText("IntegrationTestSolution2"));
        onView(withText("Speichern")).perform(click());
        onView(withText("Fertigstellen")).perform(click());
        onView(withText("Bestätigen")).perform(click());

        //Navigation
        //Button Lernen
        onView(withId(2131493039)).perform(click());
        onView(withText("Informatik")).perform(click());
        onView(withText("prog1")).perform(click());
        onView(withText("BundleIntegrationTest")).perform(click());
        //Button Start
        onView(withId(2131492988)).perform(click());
        onView(withText("OK")).perform(click());
        //Test if Bundle is available
        onView(withResourceName("TextView_question")).check(matches(isDisplayed()));
    }
}
