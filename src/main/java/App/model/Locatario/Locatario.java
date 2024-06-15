package App.model.Locatario;

public class Locatario {
    int id;
    String nome;
    String CPF;
    String email;
    String password;

    public Locatario(String nome, String CPF, String email, String password) {
        this.nome = nome;
        this.CPF = CPF;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id +": " + nome + ", CPF: " + CPF + ", email=" + email;
    }
}
