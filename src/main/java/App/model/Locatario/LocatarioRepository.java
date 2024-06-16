package App.model.Locatario;

import App.model.Locador.Locador;

import java.util.List;
public class LocatarioRepository {
    LocatarioDAO dao;

    public LocatarioRepository(){
        this.dao = new LocatarioDAO();
    }

    public void addLocatario(Locatario locatario){

        String response = dao.addLocatario(locatario);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void getLocatarioById(int id){

        String response = String.valueOf(dao.getLocatarioById(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void getAllLocatarios(){

        List<Locatario> response = dao.getAllLocatarios();
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void updateLocatario(Locatario locatario){

        String response = dao.updateLocatario(locatario);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void deleteLocatario(int id){

        String response = String.valueOf(dao.deleteLocatario(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
}
