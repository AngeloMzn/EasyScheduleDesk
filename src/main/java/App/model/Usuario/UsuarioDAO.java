package App.model.Usuario;

import App.model.Locador.LocadorDAO;
import App.model.Locatario.Locatario;
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
        String sql = "INSERT INTO usuario (nome, email, password, tipoUsuario) VALUES (?, ?, ?, ?)";

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
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Falha ao obter o ID gerado do usuário.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
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

    public Usuario buscarUsuarioPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email LIKE ?";
        Usuario usuario = null;

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + email + "%");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tipoUsuario = resultSet.getString("tipoUsuario");
                int id = resultSet.getInt("id");
                if ("locatário".equalsIgnoreCase(tipoUsuario)) {
                    LocatarioDAO locatarioDAO = new LocatarioDAO();
                    usuario = locatarioDAO.getLocatarioByUserId(id);
                } else if ("locador".equalsIgnoreCase(tipoUsuario)) {
                    LocadorDAO locadorDAO = new LocadorDAO();
                    usuario = locadorDAO.getLocadorByUserId(id);
                }
                System.out.println(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<Usuario> listarTodosUsuarios() {
        String sql = "SELECT * FROM usuario";
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
        String sql = "UPDATE usuario SET nome = ?, email = ?, password = ?, tipoUsuario = ? WHERE id = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getTipoUsuario());
            statement.setInt(5, usuario.getUserId());
            statement.executeUpdate();
            return "Usuário atualizado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar usuário: " + e.getMessage();
        }
    }

    public Locatario buscarUsuarioLocatario(int userId) {
        String sql = "SELECT u.id AS userId, u.nome, u.email, u.password, u.tipoUsuario, l.CPF, l.id AS locatarioId " +
                "FROM locatario l " +
                "JOIN usuario u ON l.id_Usuario = u.id " +
                "WHERE l.id = ?";
        Locatario locatario = null;

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    locatario = new Locatario(
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("tipoUsuario"),
                            rs.getString("CPF")
                    );

                    locatario.setUserId(rs.getInt("userId"));
                    locatario.setId(rs.getInt("locatarioId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locatario;
    }


    public String deletarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

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

class MainTest{
    public static void main(String[] args) {
        Usuario user = (new UsuarioDAO()).buscarUsuarioLocatario(3);
        System.out.println(user);
    }
}
