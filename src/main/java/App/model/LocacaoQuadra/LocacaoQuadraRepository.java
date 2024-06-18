package App.model.LocacaoQuadra;

import java.time.LocalDate;
import java.util.List;

public class LocacaoQuadraRepository {
    LocacaoQuadraDAO dao;

    public LocacaoQuadraRepository(){
        this.dao = new LocacaoQuadraDAO();
    }

    public Boolean adicionarLocacao(LocacaoQuadra locacaoQuadra){

        return dao.adicionarLocacao(locacaoQuadra);
    }
    public LocacaoQuadra buscarLocacaoPorId(int id){

        return dao.buscarLocacaoPorId(id);
    }
    public List<LocacaoQuadra> buscarLocacaoPorQuadraId(int quadraId){

        return dao.buscarLocacaoPorQuadraId(quadraId);
    }
    public LocacaoQuadra buscarPorHorario(int idQuadra, LocalDate data, String horaInicio, String horaFim){

        return dao.buscarPorHorario(idQuadra,data,horaInicio,horaFim);
    }
    public List<LocacaoQuadra> listarTodasAsLocacoes(){

        return dao.listarTodasAsLocacoes();
    }
    public Boolean atualizarLocacao(LocacaoQuadra locacao){

        return dao.adicionarLocacao(locacao);
    }
    public void deletarLocacao(int id){

        String response = String.valueOf(dao.deletarLocacao(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
}
