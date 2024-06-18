package App.model.LocacaoQuadra;

import App.model.Locatario.Locatario;
import App.model.QuadraEsportiva.QuadraEsportiva;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocacaoQuadra {
    int id;
    QuadraEsportiva quadra;
    Locatario locatario;
    LocalDate data;
    String horaInicio;
    String horaFim;
    public LocacaoQuadra(QuadraEsportiva quadra, Locatario locatario, LocalDate data, String horaInicio, String horaFim) {
        if (quadra.isDisponivel() == 0) {
            throw new IllegalArgumentException("A quadra não está disponível para locação.");
        }
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.quadra = quadra;
        this.locatario = locatario;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuadraEsportiva getQuadra() {
        return quadra;
    }

    public void setQuadra(QuadraEsportiva quadra) {
        this.quadra = quadra;
    }

    public Locatario getLocatario() {
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    /*public double calcularValor() {
        long horas = java.time.Duration.between(horaInicio, horaFim).toHours();
        return horas * quadra.getPrecoPorHora();
    }*/

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Override
    public String toString() {
        return "LocacaoQuadra{" +
                "quadra=" + quadra +
                ", locatario='" + locatario + '\'' +
                ", dataHoraInicio=" + data +
                //", valor=" + calcularValor() +
                '}';
    }

}
