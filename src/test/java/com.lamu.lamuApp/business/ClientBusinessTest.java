package com.lamu.lamuApp.business;

import com.lamu.lamuApp.dao.ClientDao;
import com.lamu.lamuApp.model.Client;
import com.lamu.lamuApp.util.WebException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientBusinessTest {

    private ClientBusiness clientBusiness;
    private Client client;

    @Mock
    private ClientDao clientDao;

    @Before
    public void init() {
        this.clientBusiness = new ClientBusiness(clientDao);
        this.client = new Client("Daisy J", "gatito3", "Daisy J", "daisy@hotmail.com", "3567895");
    }

    @Test(expected = WebException.class)
    public void ifPasswordHasLessThanSevenCharacteresThrowsError() throws WebException {
        this.client.setPassword("hi123");
        clientBusiness.CheckPassword(client.getPassword());
    }

    @Test(expected = WebException.class)
    public void ifPasswordHasJustLetterThrowsError() throws WebException {
        this.client.setPassword("gatitoP");
        clientBusiness.CheckPassword(client.getPassword());
    }

    @Test(expected = WebException.class)
    public void ifPasswordHasJustNumbersThrowsError() throws WebException {
        this.client.setPassword("12345678");
        clientBusiness.CheckPassword(client.getPassword());
    }

    @Test(expected = WebException.class)
    public void ifUserdHasLessThanThreeCharacteresThrowsError() throws WebException {
        this.client.setUser("L");
        clientBusiness.CheckUser(client.getUser());
    }

    @Test(expected = WebException.class)
    public void ifPhoneHasLessThan7NumbersThrowsError() throws WebException {
        this.client.setPhone("356");
        clientBusiness.CheckPhone(client.getPhone());
    }

    @Test(expected = WebException.class)
    public void ifUserEmailExistsThrowsError() throws WebException {
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        when(clientDao.findByEmail(client.getEmail())).thenReturn(clients);
        clientBusiness.CheckDuplicateEmail(client.getEmail());
    }

    @Test(expected = WebException.class)
    public void ifUserExistsThrowsError() throws WebException {
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        when(clientDao.findByUser(client.getUser())).thenReturn(clients);
        clientBusiness.CheckDuplicateUser(client.getUser());
    }

    @Test(expected = WebException.class)
    public void ifUserContainsEspecialCharacteresThrowsError() throws WebException {
        client.setUser("&#nyCa");
        clientBusiness.CheckSpecialCharacter(client.getUser());
    }

    @Test(expected = WebException.class)
    public void ifClientNameContainsEspecialCharacteresThrowsError() throws WebException {
        client.setName("&#nyCa");
        clientBusiness.CheckSpecialCharacter(client.getName());
    }

    @Test(expected = WebException.class)
    public void ifEmailIsNotInTheCorrectFormatThrowsError() throws WebException {
        client.setEmail("pruebahotmail.com");
        clientBusiness.CheckEmail(client.getEmail());
    }
}
