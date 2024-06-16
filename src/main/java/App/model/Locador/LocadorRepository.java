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
    public void getLocadorById(int id){

        String response = String.valueOf(dao.getLocadorById(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void getAllLocadores(){

        List<Locador> response = dao.getAllLocadores();
        if(!response.isEmpty()){
            System.out.println(response);
        }
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
