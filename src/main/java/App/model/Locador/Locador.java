package App.model.Locador;

import App.model.Usuario.Usuario;
public class Locador extends Usuario {

    int id;
    private String CNPJ;
    private int nQuadras;

    public Locador(String nome, String email, String password, String tipoUsuario, String CNPJ) {
        super(nome, email, password, tipoUsuario);
        this.CNPJ = CNPJ;
        this.nQuadras = 0;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cNPJ) {
        this.CNPJ = cNPJ;
    }

    public int getnQuadras() {
        return nQuadras;
    }

    public void setnQuadras(int nQuadras) {
        this.nQuadras = nQuadras;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", CNPJ: " + CNPJ;
    }

}
