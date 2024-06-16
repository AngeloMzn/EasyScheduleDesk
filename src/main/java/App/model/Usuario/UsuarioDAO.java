package App.model.Usuario;

import App.model.Locador.LocadorDAO;
import App.model.Locatario.LocatarioDAO;
import Core.Config.DatabaseConfig;
import App.model.Usuario.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private DatabaseConfig databaseConfig;

    public UsuarioDAO() {
        databaseConfig = new DatabaseConfig();
    }

    public int adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, email, password, tipoUsuario) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getTipoUsuario());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir usuário, nenhuma linha afetada.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Retorna o ID gerado
            } else {
                throw new SQLException("Falha ao obter o ID gerado do usuário.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Ou outro tratamento de erro adequado
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        Usuario usuario = null;

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obter tipo de usuário do resultado
                String tipoUsuario = resultSet.getString("tipoUsuario");

                if ("Locatario".equalsIgnoreCase(tipoUsuario)) {
                    LocatarioDAO locatarioDAO = new LocatarioDAO();
                    usuario = locatarioDAO.getLocatarioById(id);
                } else if ("Locador".equalsIgnoreCase(tipoUsuario)) {
                    LocadorDAO locadorDAO = new LocadorDAO();
                    usuario = locadorDAO.getLocadorById(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<Usuario> listarTodosUsuarios() {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Obter tipo de usuário do resultado
                String tipoUsuario = resultSet.getString("tipoUsuario");

                if ("Locatario".equalsIgnoreCase(tipoUsuario)) {
                    LocatarioDAO locatarioDAO = new LocatarioDAO();
                    Usuario locatario = locatarioDAO.getLocatarioById(resultSet.getInt("id"));
                    usuarios.add(locatario);
                } else if ("Locador".equalsIgnoreCase(tipoUsuario)) {
                    LocadorDAO locadorDAO = new LocadorDAO();
                    Usuario locador = locadorDAO.getLocadorById(resultSet.getInt("id"));
                    usuarios.add(locador);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public String atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, email = ?, password = ?, tipoUsuario = ? WHERE id = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getTipoUsuario());
            statement.setInt(5, usuario.getId());
            statement.executeUpdate();
            return "Usuário atualizado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar usuário: " + e.getMessage();
        }
    }

    public String deletarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            return "Usuário deletado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao deletar usuário: " + e.getMessage();
        }
    }
}
