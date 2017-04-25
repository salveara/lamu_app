package com.lamu.lamuApp.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.lamu.lamuApp.business.SongBusiness;
import com.lamu.lamuApp.model.Song;
import com.lamu.lamuApp.util.WebException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = SongView.VIEW_NAME)
public class SongView extends VerticalLayout implements View{
	
	public static final String VIEW_NAME = "song";

	TextField txtTittle;
	TextField txtUrl;
	TextField txtArtist;
	TextField txtAlbum;
	TextField txtGenre;
	TextField txtTrack;
	TextField txtYear;
	Button btnUpload;
	
	@Autowired
	SongBusiness SongBusiness;
	
	@PostConstruct
    void init() {
		txtTittle = new TextField("Titulo:");
		txtTittle.setWidth("300px");
		txtTittle.setRequiredIndicatorVisible(true);
		
		txtUrl = new TextField("Url:");
		txtUrl.setWidth("300px");
		txtUrl.setRequiredIndicatorVisible(true);
		
		txtArtist = new TextField("Artista:");
		txtArtist.setWidth("300px");
		txtArtist.setRequiredIndicatorVisible(true);
		
		txtAlbum = new TextField("Album:");
		txtAlbum.setWidth("300px");
		
		txtGenre = new TextField("Genero:");
		txtGenre.setWidth("300px");
		
		txtTrack = new TextField("Track:");
		txtTrack.setWidth("300px");
		
		txtYear = new TextField("Año:");
		txtYear.setWidth("300px");
		
		btnUpload = new Button("Subir", this::uploadButtonClick);
		btnUpload.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnUpload.setClickShortcut(KeyCode.ENTER);
		
		VerticalLayout fields = new VerticalLayout(txtTittle, txtUrl, txtArtist, txtAlbum, txtGenre, txtTrack, txtYear, btnUpload);
        fields.setCaption("Subir pista");
        fields.setComponentAlignment(btnUpload, Alignment.TOP_CENTER);
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
    
    public void uploadButtonClick(Button.ClickEvent e) {
    	
    	if(txtTittle.isEmpty() || txtUrl.isEmpty() || txtArtist.isEmpty()){
    		Notification.show("Los campos requeridos");
    	}else{
    		
    		String tittle = txtTittle.getValue();
    		String url = txtUrl.getValue();
    		String album = txtAlbum.getValue();
    		String artist = txtArtist.getValue();
    		String genre = txtGenre.getValue();
    		String track = txtTrack.getValue();
    		String year = txtYear.getValue();
    		
    		try {
				//FALTA REALIZAR MAS VALIDACIONES
				SongBusiness.CheckDuplicateUrl(url);
				SongBusiness.CheckDuplicateTittleArtist(tittle, artist);
				SongBusiness.CheckTrackOnlyContainsNumbers(track);
				SongBusiness.CheckYearFormat(year);
				
				Song song = new Song(url, tittle, artist, album, genre, track, year);
				SongBusiness.SaveSong(song);
				Notification.show("Canción subida con éxito");
				
			} catch (WebException webEx) {
				System.out.println(webEx.getTechnicalMessage());
				Notification.show(webEx.getUserMessage());
			}

    	}
    }

}
