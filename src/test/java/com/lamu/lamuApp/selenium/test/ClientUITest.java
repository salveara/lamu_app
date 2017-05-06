package com.lamu.lamuApp.selenium.test;

import com.lamu.lamuApp.selenium.framework.view.ClientUI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientUITest {

    ClientUI clientUI;

    @Before
    public void init() {
        this.clientUI = new ClientUI();
    }

    @Test
    public void testCase1_userCanNotContainsSpecialCharacteres() {
        clientUI.fillUser("&#nyCa");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("El usuario o su nombre no pueden contener caracteres especiales", result);
    }

    @Test
    public void testCase2_nameCanNotContainsSpecialCharacteres() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("&#nyCa");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("El usuario o su nombre no pueden contener caracteres especiales", result);
    }

    @Test
    public void testCase3_passwordMustHaveNumbersAndLetters() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("12345678");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("La contraseña debe contener numeros y letras", result);
    }

    @Test
    public void testCase4_emailMustMatchWithACorrectFormat() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("pruebahotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("El correo electronico debe tener un formato valido", result);
    }

    @Test
    public void testCase5_phoneCanNotContainsLetters() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("numerofalso");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("El telefono solo puede contener números", result);
    }

    @Test
    public void testCase6_userIsRequiredButPhonedoesNot() {
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("Ingrese los datos faltantes", result);
    }

    @Test
    public void testCase7_nameIsRequired() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("Ingrese los datos faltantes", result);
    }

    @Test
    public void testCase8_passwordIsRequired() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("Ingrese los datos faltantes", result);
    }

    @Test
    public void testCase9_emailIsRequired() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("Ingrese los datos faltantes", result);
    }

    @Test
    public void testCase10_clientWithAllInformationCanBeSaved() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("Cliente registrado exitosamente", result);
    }

    @Test
    public void testCase11_phoneIsRequired() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("Ingrese los datos faltantes", result);
    }

    @Test
    public void testCase12_userMustHaveAtLeast3Characters() {
        clientUI.fillUser("L");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("El usuario o su nombre debe ser de minimo 3 caracteres", result);
    }

    @Test
    public void testCase13_userWithOnlyNumbersIsNotValid() {
        clientUI.fillUser("2584");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("El usuario o su nombre debe contener por lo menos una letra", result);
    }

    @Test
    public void testCase14_nameMustHaveAtLeast3Characters() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("L");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("El usuario o su nombre debe ser de minimo 3 caracteres", result);
    }

    @Test
    public void testCase15_nameWithOnlyNumbersIsNotValid() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("2584");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("El usuario o su nombre debe contener por lo menos una letra", result);
    }

    @Test
    public void testCase16_passwordWithOnlyLattersISNotValid() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatitoP");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("La contraseña debe contener numeros y letras", result);
    }

    @Test
    public void testCase17_passwordWithLessThan3CharactersIsNotValid() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("hi123");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("3567895");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("La contraseña debe ser mayor de 7 caracteres", result);
    }

    @Test
    public void testCase18_phoneCanOnlyHaveNumbers() {
        clientUI.fillUser("DaisyJ");
        clientUI.fillName("DaisyJ");
        clientUI.fillPassword("gatito3");
        clientUI.fillEmail("Daisy@hotmail.com");
        clientUI.fillPhone("35678hola");

        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("El telefono solo puede contener números", result);
    }

    @Test
    public void testCase19_fieldsAreRequired() {
        clientUI.registerButtonClick();
        String result = clientUI.getResponseText();

        Assert.assertEquals("Ingrese los datos faltantes", result);
    }

}
