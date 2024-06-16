package App.model.QuadraEsportiva;

import App.model.LocacaoQuadra.LocacaoQuadraDAO;
import java.util.List;

public class QuadraEsportivaRepository {
    QuadraEsportivaDAO dao;

    public QuadraEsportivaRepository(){
        this.dao = new QuadraEsportivaDAO();
    }
    public void adicionarQuadra(QuadraEsportiva quadra){

        String response = dao.adicionarQuadra(quadra);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void buscarQuadraPorId(int id){

        String response = String.valueOf(dao.buscarQuadraPorId(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void listarTodasAsQuadras(){

        List<QuadraEsportiva> response = dao.listarTodasAsQuadras();
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void atualizarQuadra(QuadraEsportiva quadra){

        String response = dao.adicionarQuadra(quadra);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void deletarQuadra(int id){

        String response = String.valueOf(dao.deletarQuadra(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
}
