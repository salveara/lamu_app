package com.lamu.lamuApp.business;

import com.lamu.lamuApp.dao.ClientDao;
import com.lamu.lamuApp.model.Client;
import com.lamu.lamuApp.util.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientBusiness {

    private ClientDao clientDao;

    @Autowired
    public ClientBusiness(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void CheckPassword(String password) throws WebException {
        if (password.length() < 7) {
            WebException webEx = new WebException();
            webEx.setUserMessage("La contraseña debe ser mayor de 7 caracteres");
            webEx.setTechnicalMessage("password.lenght menor a 7 caracteres");
            throw webEx;
        }
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            WebException webEx = new WebException();
            webEx.setUserMessage("La contraseña debe contener numeros y letras");
            webEx.setTechnicalMessage("password does not contains numbers and letters");
            throw webEx;
        }
    }

    public void CheckUser(String user) throws WebException {
        if (user.length() < 3) {
            WebException webEx = new WebException();
            webEx.setUserMessage("El usuario o su nombre debe ser de minimo 3 caracteres");
            webEx.setTechnicalMessage("user.lenght menor a 3 caracteres");
            throw webEx;
        } else {
            if (user != null && !"".equals(user)) {
                String regex = "[0-9]+";
                if (user.matches(regex)) {
                    WebException webEx = new WebException();
                    webEx.setUserMessage("El usuario o su nombre debe contener por lo menos una letra");
                    webEx.setTechnicalMessage("Phone contains letters");
                    throw webEx;
                }
            }
        }
    }

    public void CheckPhone(String phone) throws WebException {
        if (phone.length() < 7) {
            WebException webEx = new WebException();
            webEx.setUserMessage("Número de telefono no valido");
            webEx.setTechnicalMessage("phone.lenght menor a 7 caracteres");
            throw webEx;
        } else {
            if (phone != null && !"".equals(phone)) {
                String regex = "[0-9]+";
                if (!phone.matches(regex)) {
                    WebException webEx = new WebException();
                    webEx.setUserMessage("El telefono solo puede contener números");
                    webEx.setTechnicalMessage("Phone contains letters");
                    throw webEx;
                }
            }

        }
    }

    public void CheckDuplicateEmail(String email) throws WebException {
        List<Client> list = clientDao.findByEmail(email);

        if (!list.isEmpty()) {
            WebException webEx = new WebException();
            webEx.setUserMessage("El correo ya se encuentra registrado");
            webEx.setTechnicalMessage("la lista clientDao.findByEmail(email) no está vacia");
            throw webEx;

        }
    }

    public void CheckDuplicateUser(String user) throws WebException {
        List<Client> list = clientDao.findByUser(user);

        if (!list.isEmpty()) {
            WebException webEx = new WebException();
            webEx.setUserMessage("El usuario ya se encuentra registrado");
            webEx.setTechnicalMessage("la lista clientDao.findByUser(user) no está vacia");
            throw webEx;
        }
    }

    public void CheckSpecialCharacter(String string) throws WebException {
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            WebException webEx = new WebException();
            webEx.setUserMessage("El usuario o su nombre no pueden contener caracteres especiales");
            webEx.setTechnicalMessage("The user or name contains special characters");
            throw webEx;
        }
    }

    public void CheckEmail(String email) throws WebException {
        Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            WebException webEx = new WebException();
            webEx.setUserMessage("El correo electronico debe tener un formato valido");
            webEx.setTechnicalMessage("The email does not have a correct format");
            throw webEx;
        }
    }

    public void SaveClient(Client client) {
        clientDao.save(client);
        clientDao.delete(client);
    }
}
