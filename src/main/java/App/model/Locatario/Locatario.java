package App.model.Locatario;

import App.model.Usuario.Usuario;

public class Locatario extends Usuario {
    int id;
    String CPF;

    public Locatario(String nome, String email, String password, String tipoUsuario, String CPF) {
        super(nome, email, password, tipoUsuario);
        this.CPF = CPF;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", CPF: " + CPF;
    }
}
