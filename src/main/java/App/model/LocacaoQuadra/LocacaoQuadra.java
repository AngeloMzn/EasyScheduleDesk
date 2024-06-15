package App.model.LocacaoQuadra;

import App.model.Locatario.Locatario;
import App.model.QuadraEsportiva.QuadraEsportiva;


import java.time.LocalDateTime;

public class LocacaoQuadra {
    QuadraEsportiva quadra;
    Locatario locatario;
    LocalDateTime dataHoraInicio;
    LocalDateTime dataHoraFim;
    public LocacaoQuadra(QuadraEsportiva quadra, Locatario locatario, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        if (!quadra.isDisponivel()) {
            throw new IllegalArgumentException("A quadra não está disponível para locação.");
        }

        this.quadra = quadra;
        this.locatario = locatario;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.quadra.setDisponivel(false);
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

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public double calcularValor() {
        long horas = java.time.Duration.between(dataHoraInicio, dataHoraFim).toHours();
        return horas * quadra.getPrecoPorHora();
    }

    @Override
    public String toString() {
        return "LocacaoQuadra{" +
                "quadra=" + quadra +
                ", locatario='" + locatario + '\'' +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", valor=" + calcularValor() +
                '}';
    }

}
