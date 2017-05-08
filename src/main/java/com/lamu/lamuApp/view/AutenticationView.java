package com.lamu.lamuApp.view;

import com.lamu.lamuApp.business.AutenticationBusiness;
import com.lamu.lamuApp.util.WebException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = AutenticationView.VIEW_NAME)
public class AutenticationView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";

    TextField txtEmail;
    PasswordField txtPassword;
    Button btnLogin;
    Button btnPassword;
    Label label;

    @Autowired
    AutenticationBusiness autenticationBusiness;

    @PostConstruct
    void init() {
        txtEmail = new TextField("Email:");
        txtEmail.setWidth("300px");
        txtEmail.setRequiredIndicatorVisible(true);
        txtEmail.setId("txtEmailAutentication");

        txtPassword = new PasswordField("Contraseña:");
        txtPassword.setWidth("300px");
        txtPassword.setRequiredIndicatorVisible(true);
        txtPassword.setId("txtPasswordAutentication");

        btnLogin = new Button("Login", this::loginButtonClick);
        btnLogin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnLogin.setClickShortcut(KeyCode.ENTER);
        btnLogin.setId("btnLogin");

        btnPassword = new NativeButton("¿Olvidó su contraseña?", this::passwordButtonClick);
        btnPassword.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnPassword.setId("btnPassword");

        label = new Label();
        label.setId("label");
        label.setVisible(false);

        VerticalLayout fields = new VerticalLayout(txtEmail, txtPassword, btnLogin, btnPassword, label);
        fields.setCaption("Autenticación de Empleados");
        fields.setComponentAlignment(btnLogin, Alignment.TOP_CENTER);
        fields.setComponentAlignment(btnPassword, Alignment.TOP_CENTER);
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        VerticalLayout uiLayout = new VerticalLayout(fields);
        uiLayout.setSizeFull();
        uiLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);

        addComponent(uiLayout);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
    }

    private void passwordButtonClick(Button.ClickEvent p) {
        UI.getCurrent().getNavigator().navigateTo(PasswordMailView.VIEW_NAME);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }

    public void loginButtonClick(Button.ClickEvent e) {
        if (txtEmail.isEmpty() || txtPassword.isEmpty()) {
            label.setValue("Ingrese los datos requeridos");
            label.setVisible(true);
        } else {
            try {
                autenticationBusiness.CheckEmail(txtEmail.getValue());
                autenticationBusiness.checkData(txtEmail.getValue(), txtPassword.getValue());
                label.setValue("Autenticación correcta");
                label.setVisible(true);
            } catch (WebException webEx) {
                System.out.println(webEx.getTechnicalMessage());
                label.setValue(webEx.getUserMessage());
                label.setVisible(true);
            }
        }
    }
}
