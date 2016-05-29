package com.team.flipagain.client.gui.login;

import com.team.flipagain.gui.login.LoginActivity;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Luca on 04/04/2016.
 */
public class LoginActivityTest extends TestCase {

    private LoginActivity loginActivity;
    private Method publicIsEmailValid;
    private Method publicIsPasswordValid;

    @Before
    public void setUp() throws NoSuchMethodException {
        try {
            loginActivity = new LoginActivity();
            publicIsEmailValid = loginActivity.getClass().getDeclaredMethod("isEmailValid", java.lang.String.class);
            publicIsEmailValid.setAccessible(true);
            publicIsPasswordValid = loginActivity.getClass().getDeclaredMethod("isPasswordValid", java.lang.String.class);
            publicIsPasswordValid.setAccessible(true);
        } catch (NoSuchMethodException noSuchMethod){
            System.out.println("NoSuchMethodException");
        }
    }

    @Test
    public void testEmailValidation() throws InvocationTargetException, IllegalAccessException {
        try {
            assertTrue("valid mail was not accepted", (boolean) publicIsEmailValid.invoke(loginActivity, "l1romano@hsr.ch"));
            assertFalse("invalid mail was accepted", (boolean) publicIsEmailValid.invoke(loginActivity, "l1romano#hsr.ch"));
        } catch (InvocationTargetException invocationTarget){
            System.out.println("InvocationTargetException");
        } catch (IllegalAccessException illegalAccess) {
            System.out.println("IllegalAccessException");
        }
    }

    @Test
    public void testPasswordValidation() throws InvocationTargetException, IllegalAccessException {
        try {
            assertTrue("valid password was not accepted", (boolean) publicIsPasswordValid.invoke(loginActivity, "testPW"));
            assertFalse("invalid password was accepted", (boolean) publicIsPasswordValid.invoke(loginActivity, "tpw"));
        } catch (InvocationTargetException invocationTarget){
            System.out.println("InvocationTargetException");
        } catch (IllegalAccessException illegalAccess) {
            System.out.println("IllegalAccessException");
        }
    }
}