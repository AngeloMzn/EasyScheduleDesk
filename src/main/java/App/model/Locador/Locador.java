package App.model.Locador;

public class Locador {
    int id;
    String nome;
    String CNPJ;
    String email;
    String password;
    double nQuadra;

    public Locador(String nome, String CNPJ, String email, String password, double nQuadra) {
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.email = email;
        this.password = password;
        this.nQuadra = nQuadra;
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

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cNPJ) {
        this.CNPJ = cNPJ;
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

    public double getnQuadra() {
        return nQuadra;
    }

    public void setnQuadra(double nQuadra) {
        this.nQuadra = nQuadra;
    }

    @Override
    public String toString() {
        return id +": " + nome + ", CNPJ: " + CNPJ + ", email=" + email;
    }

}
