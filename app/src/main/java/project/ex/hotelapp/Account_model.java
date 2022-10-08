package project.ex.hotelapp;

public class Account_model {
    private String Name,E_mail,Password;

    public Account_model(String name, String e_mail, String password) {
        Name = name;
        E_mail = e_mail;
        Password = password;
    }

    public Account_model() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getE_mail() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
