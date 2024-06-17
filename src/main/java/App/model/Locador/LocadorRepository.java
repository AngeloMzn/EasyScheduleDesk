package App.model.Locador;

import java.util.List;
public class LocadorRepository {
    LocadorDAO dao;

    public LocadorRepository(){
        this.dao = new LocadorDAO();
    }
    public void addLocador(Locador locador){

        String response = dao.addLocador(locador);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public Locador getLocadorById(int id){

        return dao.getLocadorById(id);
    }

    public Locador getLocadorByUserId(int id){

        return dao.getLocadorByUserId(id);
    }

    public List<Locador> getAllLocadores(){
        return dao.getAllLocadores();
    }
    public void updateLocador(Locador locador){

        String response = dao.updateLocador(locador);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void deleteLocador(int id){

        String response = String.valueOf(dao.deleteLocador(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
}
