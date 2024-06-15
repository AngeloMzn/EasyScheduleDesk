package com.example.easyschedule.model.LocacaoQuadra;

import com.example.easyschedule.model.Locatario.Locatario;
import com.example.easyschedule.model.QuadraEsportiva.QuadraEsportiva;

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

    public String getLocatario() {
        return locatario;
    }

    public void setLocatario(String locatario) {
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
