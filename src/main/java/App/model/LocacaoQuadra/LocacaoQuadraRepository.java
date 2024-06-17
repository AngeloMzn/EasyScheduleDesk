package App.model.LocacaoQuadra;

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
    public LocacaoQuadra buscarLocacaoPorId(int id){

        return dao.buscarLocacaoPorId(id);
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
    public void deletarLocacao(int id){

        String response = String.valueOf(dao.deletarLocacao(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
}
