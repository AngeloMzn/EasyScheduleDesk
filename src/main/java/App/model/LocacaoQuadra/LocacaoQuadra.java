package App.model.LocacaoQuadra;

import App.model.Locatario.Locatario;
import App.model.QuadraEsportiva.QuadraEsportiva;


import java.time.LocalDateTime;

public class LocacaoQuadra {
    int id;
    QuadraEsportiva quadra;
    Locatario locatario;
    LocalDateTime dataInicio;
    LocalDateTime dataFim;
    String horaInicio;
    String horaFim;
    public LocacaoQuadra(QuadraEsportiva quadra, Locatario locatario, LocalDateTime dataInicio, LocalDateTime dataFim, String horaInicio, String horaFim) {
        if (quadra.isDisponivel() == 0) {
            throw new IllegalArgumentException("A quadra não está disponível para locação.");
        }
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.quadra = quadra;
        this.locatario = locatario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
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

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataHoraInicio) {
        this.dataInicio = dataHoraInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataFim = dataHoraFim;
    }

    public double calcularValor() {
        long horas = java.time.Duration.between(dataInicio, dataFim).toHours();
        return horas * quadra.getPrecoPorHora();
    }

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
                ", dataHoraInicio=" + dataInicio +
                ", dataHoraFim=" + dataFim +
                ", valor=" + calcularValor() +
                '}';
    }

}
