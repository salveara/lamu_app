package com.lamu.lamuApp.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.lamu.lamuApp.business.MainBusiness;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class MainUI extends UI {
	
    @Autowired
    private SpringViewProvider viewProvider;
    @Autowired
	MainBusiness mainBusiness;
    
	@Override
	protected void init(VaadinRequest request) {
		
		setSizeFull();

        VerticalLayout uiLayout = new VerticalLayout();
        uiLayout.setSizeFull();
        
        setContent(uiLayout);
        
        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        uiLayout.addComponent(viewContainer);
        uiLayout.setExpandRatio(viewContainer, 1.0f);

        
        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
        
	}
	
}
