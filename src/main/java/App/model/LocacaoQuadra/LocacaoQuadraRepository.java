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
    public void buscarLocacaoPorId(int id){

        String response = String.valueOf(dao.buscarLocacaoPorId(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void listarTodasAsLocacoes(){

        List<LocacaoQuadra> response = dao.listarTodasAsLocacoes();
        if(!response.isEmpty()){
            System.out.println(response);
        }
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
