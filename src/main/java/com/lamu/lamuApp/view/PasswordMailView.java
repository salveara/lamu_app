package com.lamu.lamuApp.view;

import com.lamu.lamuApp.business.PasswordMailBusiness;
import com.lamu.lamuApp.util.WebException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by santi on 24/04/2017.
 */
@SpringView(name = PasswordMailView.VIEW_NAME)
public class PasswordMailView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "password";

    TextField txtClient;
    Button btnEnviar;

    @Autowired
    PasswordMailBusiness passwordMailBusinness;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @PostConstruct
    public void init() {
        txtClient = new TextField("Usuario o email:");
        txtClient.setWidth("300px");
        txtClient.setRequiredIndicatorVisible(true);

        btnEnviar = new Button("Login", this::enviarButtonClick);
        btnEnviar.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnEnviar.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        VerticalLayout fields = new VerticalLayout(txtClient, btnEnviar);
        fields.setCaption("Enviar contraseña");
        fields.setComponentAlignment(btnEnviar, Alignment.TOP_CENTER);
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

    private void enviarButtonClick(Button.ClickEvent p) {
        try {
            passwordMailBusinness.sendMail(txtClient.getValue());
            Notification.show("El correo ha sido enviado");
            UI.getCurrent().getNavigator().navigateTo(AutenticationView.VIEW_NAME);
        } catch (WebException webEx) {
            System.out.println(webEx.getTechnicalMessage());
            Notification.show(webEx.getUserMessage());
        }
    }
}
