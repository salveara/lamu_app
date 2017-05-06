package com.lamu.lamuApp.view;

import com.lamu.lamuApp.business.ClientBusiness;
import com.lamu.lamuApp.model.Client;
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

@SpringView(name = ClientView.VIEW_NAME)
public class ClientView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "client";

    TextField txtUser;
    TextField txtEmail;
    TextField txtNombre;
    PasswordField txtPassword;
    TextField txtPhone;
    Button btnRegister;
    Label label;

    @Autowired
    ClientBusiness clientBusiness;

    @PostConstruct
    void init() {
        txtUser = new TextField("Usuario:");
        txtUser.setWidth("300px");
        txtUser.setRequiredIndicatorVisible(true);

        txtNombre = new TextField("Nombre:");
        txtNombre.setWidth("300px");
        txtNombre.setRequiredIndicatorVisible(true);

        txtEmail = new TextField("Correo electronico:");
        txtEmail.setWidth("300px");
        txtEmail.setRequiredIndicatorVisible(true);

        txtPassword = new PasswordField("Contraseña:");
        txtPassword.setWidth("300px");
        txtPassword.setRequiredIndicatorVisible(true);

        txtPhone = new TextField("Telefono:");
        txtPhone.setWidth("300px");
        txtPhone.setRequiredIndicatorVisible(true);

        btnRegister = new Button("Registrar", this::registerButtonClick);
        btnRegister.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnRegister.setClickShortcut(KeyCode.ENTER);
        btnRegister.setId("btnRegister");

        label = new Label();
        label.setId("label");
        label.setVisible(false);

        VerticalLayout fields = new VerticalLayout(txtUser, txtNombre, txtEmail, txtPassword, txtPhone, btnRegister, label);
        fields.setCaption("Crear Cliente");
        fields.setComponentAlignment(btnRegister, Alignment.TOP_CENTER);
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

    @Override
    public void enter(ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }

    public void registerButtonClick(Button.ClickEvent e) {

        if (txtUser.isEmpty() || txtPassword.isEmpty() || txtNombre.isEmpty() || txtEmail.isEmpty() || txtPhone.isEmpty()) {
            label.setValue("Ingrese los datos faltantes");
            label.setVisible(true);

        } else {
            try {
                String password = txtPassword.getValue();
                String email = txtEmail.getValue();
                String user = txtUser.getValue();
                String name = txtNombre.getValue();
                String phone = txtPhone.getValue();

                //Falta realizar mas validaciones
                clientBusiness.CheckSpecialCharacter(user);
                clientBusiness.CheckSpecialCharacter(name);
                clientBusiness.CheckEmail(email);
                clientBusiness.CheckPassword(password);
                clientBusiness.CheckUser(user);
                clientBusiness.CheckUser(name);
                clientBusiness.CheckPhone(phone);
                clientBusiness.CheckDuplicateEmail(email);
                clientBusiness.CheckDuplicateUser(user);

                Client client = new Client(user, password, name, email, phone);
                clientBusiness.SaveClient(client);
                label.setValue("Cliente registrado exitosamente");
                label.setVisible(true);

            } catch (WebException webEx) {
                System.out.println(webEx.getTechnicalMessage());
                label.setValue(webEx.getUserMessage());
                label.setVisible(true);
            }
        }
    }
}
