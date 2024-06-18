package App.model.QuadraEsportiva;

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
    public QuadraEsportiva buscarQuadrasPorId(int id){

        return dao.buscarQuadraPorId(id);

    }
    public List<QuadraEsportiva> listarTodasAsQuadras(){

        return dao.listarTodasAsQuadras();
    }

    public List<QuadraEsportiva> buscarQuadrasPorLocador(int idLocador){

        return dao.listarQuadrasPorLocador(idLocador);
    }

    public List<QuadraEsportiva> buscarQuadrasPorNome(String nome){

        return dao.listarQuadrasPorNome(nome);
    }

    public List<QuadraEsportiva> buscarQuadrasPorTipo(String tipo){

        return dao.listarQuadrasPorTipo(tipo);
    }

    public List<QuadraEsportiva> buscarQuadrasPorDisponibilidade(boolean disponibilidade){

        return dao.listarQuadrasPorDisponibilidade((disponibilidade ? 1 : 0));
    }

    public void atualizarQuadra(QuadraEsportiva quadra){

        String response = dao.atualizarQuadra(quadra);
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
