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
    public Locatario getLocatarioById(int id){

        return dao.getLocatarioById(id);
    }
    public Locatario getLocatarioByUserId(int id){

        return dao.getLocatarioByUserId(id);
    }
    public List<Locatario> getAllLocatarios(){

        return dao.getAllLocatarios();
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
