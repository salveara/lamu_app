package com.lamu.lamuApp.view;

import com.lamu.lamuApp.business.AutenticationBusiness;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class MainUI extends UI {

    @Autowired
    private SpringViewProvider viewProvider;
    @Autowired
    AutenticationBusiness autenticationBusiness;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout uiLayout = new VerticalLayout();
        setSizeFull();
        setContent(uiLayout);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addComponent(createNavigationButton("Empleados", AutenticationView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Crear cliente", ClientView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Subir canciones", SongView.VIEW_NAME));
        uiLayout.addComponent(navigationBar);
        uiLayout.setSizeFull();

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        uiLayout.addComponent(viewContainer);
        uiLayout.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);

    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

}
