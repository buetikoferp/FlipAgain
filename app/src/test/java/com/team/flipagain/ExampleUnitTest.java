package com.team.flipagain;

import org.junit.Test;

import org.junit.Before;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private int i;

    @Before
    public void setI(){
        i = 4;
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(i, 2 + 2);
    }

}