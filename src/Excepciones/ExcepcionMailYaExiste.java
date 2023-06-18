package Excepciones;

public class ExcepcionMailYaExiste extends Exception {

    private String mail;
    public ExcepcionMailYaExiste(String info, String mail)
    {
        super(info);
        this.mail = mail;
    }

    public String getMessage(){
        return super.getMessage() + getMail();
    }
    public String getMail() {
        return mail;
    }
}
