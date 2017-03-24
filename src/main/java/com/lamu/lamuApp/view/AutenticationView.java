package com.lamu.lamuApp.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.lamu.lamuApp.business.AutenticationBusiness;
import com.lamu.lamuApp.util.WebException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = AutenticationView.VIEW_NAME)
public class AutenticationView extends VerticalLayout implements View{
	
	public static final String VIEW_NAME = "";

	TextField txtUser;
	TextField txtClient;
    PasswordField txtPassword;
    Button btnLogin;
    
    @Autowired
	AutenticationBusiness autenticationBusiness;
    
    @PostConstruct
    void init() {
    	txtUser = new TextField("Usuario o email:");
		txtUser.setWidth("300px");
		txtUser.setRequiredIndicatorVisible(true);

		txtPassword = new PasswordField("Contraseña:");
		txtPassword.setWidth("300px");
		txtPassword.setRequiredIndicatorVisible(true);
		
		txtClient = new TextField("Empresa:");
		txtClient.setWidth("300px");
		txtClient.setRequiredIndicatorVisible(true);
		
		btnLogin = new Button("Login", this::loginButtonClick);
		btnLogin.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnLogin.setClickShortcut(KeyCode.ENTER);
		
		VerticalLayout fields = new VerticalLayout(txtUser, txtPassword, txtClient, btnLogin);
        fields.setCaption("Autenticación de Empleados)");
        fields.setComponentAlignment(btnLogin, Alignment.TOP_CENTER);
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
        // This view is constructed in the init() method()
    }
    
    public void loginButtonClick(Button.ClickEvent e) {
    	
        if (txtUser.isEmpty() || txtPassword.isEmpty() || txtClient.isEmpty()){
       	 Notification.show("Ingrese los datos requeridos");
       	 
        }else{
       	 
       	 try {
				autenticationBusiness.checkData(txtUser.getValue(), txtPassword.getValue(), txtClient.getValue());
				Notification.show("Autenticación correcta");
				//getUI().getNavigator().navigateTo(ClientView.VIEW_NAME);
				
			} catch (WebException webEx) {
				
				System.out.println(webEx.getTechnicalMessage());
				Notification.show(webEx.getUserMessage());
				
			}
   
        }
		 
     }
    
}