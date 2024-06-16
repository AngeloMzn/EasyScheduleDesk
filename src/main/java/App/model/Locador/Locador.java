package App.model.Locador;

import App.model.Usuario.Usuario;
public class Locador extends Usuario {
    private String CNPJ;
    private int nQuadras;

    public Locador(int id, String nome, String email, String password, String tipoUsuario, String CNPJ, int nQuadras) {
        super(id, nome, email, password, tipoUsuario);
        this.CNPJ = CNPJ;
        this.nQuadras = nQuadras;
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
