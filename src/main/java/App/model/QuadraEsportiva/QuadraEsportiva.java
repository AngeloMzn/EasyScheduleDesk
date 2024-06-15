package App.model.QuadraEsportiva;

public class QuadraEsportiva {

    String nome;
    String tipo;
    Double precoPorHora;
    Locador dono;
    public QuadraEsportiva(String nome, String tipo, double precoPorHora, Locador dono) {
        this.nome = nome;
        this.tipo = tipo;
        this.precoPorHora = precoPorHora;
        this.disponivel = true;
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public void setPrecoPorHora(double precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "QuadraEsportiva{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precoPorHora=" + precoPorHora +
                ", disponivel=" + disponivel +
                '}';
    }


}
