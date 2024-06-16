package App.model.QuadraEsportiva;

import App.model.Locador.Locador;

public class QuadraEsportiva {

    int id;
    String nome;
    String tipo;
    Double precoPorHora;
    Locador dono;
    int disponivel;
    public QuadraEsportiva(String nome, String tipo, double precoPorHora, int disponivel, Locador dono) {
        this.nome = nome;
        this.tipo = tipo;
        this.precoPorHora = precoPorHora;
        this.disponivel = 1;
        this.dono = dono;
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

    public Locador getDono() {
        return dono;
    }
    public void setDono(Locador dono) {
        this.dono = dono;
    }
    public int isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(int disponivel) {
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
