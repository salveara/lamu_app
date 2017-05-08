package com.lamu.lamuApp.selenium.test;

import com.lamu.lamuApp.selenium.framework.view.AutenticationUI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AutenticationUITest {

    AutenticationUI autenticationUI;

    @Before
    public void init() {
        this.autenticationUI = new AutenticationUI();
    }

    @Test
    public void testCase1_allFieldAreRequired() {
        autenticationUI.uploadButtonLoginClick();
        String result = autenticationUI.getResponseText();

        Assert.assertEquals("Ingrese los datos requeridos", result);
    }

    @Test
    public void testCase2_passwordIsAeRequiredField() {
        autenticationUI.fillEmail("yenyca@me.com");

        autenticationUI.uploadButtonLoginClick();
        String result = autenticationUI.getResponseText();

        Assert.assertEquals("Ingrese los datos requeridos", result);
    }

    @Test
    public void testCase3_emailMustHaveACorrectFormat() {
        autenticationUI.fillEmail("pruebahotmail.com");
        autenticationUI.fillPassword("gatito2");

        autenticationUI.uploadButtonLoginClick();
        String result = autenticationUI.getResponseText();

        Assert.assertEquals("El correo electronico debe tener un formato valido", result);
    }

    @Test
    public void testCase4_emailIsAeRequiredField() {
        autenticationUI.fillPassword("gatito2");

        autenticationUI.uploadButtonLoginClick();
        String result = autenticationUI.getResponseText();

        Assert.assertEquals("Ingrese los datos requeridos", result);
    }
}
