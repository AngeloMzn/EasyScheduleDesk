package App.model.Usuario;

import App.model.Locatario.Locatario;

import java.util.List;
public class UsuarioRepository {
    UsuarioDAO dao;

    public UsuarioRepository(){
        this.dao = new UsuarioDAO();
    }
    public void buscarUsuarioPorId(int id){

        String response = String.valueOf(dao.buscarUsuarioPorId(id));
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return dao.buscarUsuarioPorEmail(email);
    }
    public void listarTodosUsuarios(){

        List<Usuario> response = dao.listarTodosUsuarios();
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void atualizarUsuario(Usuario usuario){

        String response = dao.atualizarUsuario(usuario);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
    public void deletarUsuario(int id){

        String response = dao.deletarUsuario(id);
        if(!response.isEmpty()){
            System.out.println(response);
        }
    }
}
