package App.model.Locatario;

import App.model.Usuario.Usuario;

public class Locatario extends Usuario {
    String CPF;

    public Locatario(int id, String nome, String email, String password, String tipoUsuario, String CPF) {
        super(id, nome, email, password, tipoUsuario);
        this.CPF = CPF;
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
