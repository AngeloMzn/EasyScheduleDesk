package App.model.LocacaoQuadra;

import java.time.LocalDate;
import java.util.List;

public class LocacaoQuadraRepository {
    LocacaoQuadraDAO dao;

    public LocacaoQuadraRepository(){
        this.dao = new LocacaoQuadraDAO();
    }

    public void adicionarLocacao(LocacaoQuadra locacaoQuadra){

        String response = dao.adicionarLocacao(locacaoQuadra);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }

    public List<LocacaoQuadra> buscarLocacoesPorLocatario(int locatarioId){
        return dao.buscarLocacoesPorLocatario(locatarioId);
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
    public void atualizarLocacao(LocacaoQuadra locacao){

        String response = dao.adicionarLocacao(locacao);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public String deletarLocacao(int id){

        String response = String.valueOf(dao.deletarLocacao(id));
        if(!response.isEmpty()){
           return response;
        }
        return "Erro: Não foi possivel cancelar";
    }
}
