package App.model.Locatario;

import App.model.Usuario.UsuarioDAO;
import Core.Config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocatarioDAO {
    private DatabaseConfig databaseConfig;

    public LocatarioDAO() {
        databaseConfig = new DatabaseConfig();
    }

    public String addLocatario(Locatario locatario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int usuarioId = usuarioDAO.adicionarUsuario(locatario);

        if (usuarioId == -1) {
            return "Erro ao adicionar usuário. Locatário não adicionado.";
        }

        // Agora adiciona o locatário usando o ID do usuário
        String sql = "INSERT INTO locatario (id_Usuario, CPF) VALUES (?, ?)";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, usuarioId);
            pstmt.setString(2, locatario.getCPF());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                locatario.setId(generatedKeys.getInt(1));
            }
            return "Locatário salvo com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao salvar Locatário: " + e.getMessage();
        }
    }

    public Locatario getLocatarioById(int id) {
        String sql = "SELECT * FROM locatario WHERE id = ?";
        Locatario locatario = null;

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                locatario = new Locatario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("tipoUsuario"),
                        rs.getString("CPF")
                );
                locatario.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locatario;
    }

    public Locatario getLocatarioByUserId(int id) {
        String sql = "SELECT u.id, u.nome, u.email, u.password, u.tipoUsuario, l.CPF " +
                "FROM locatario l " +
                "JOIN usuario u ON l.id_Usuario = u.id " +
                "WHERE l.id_Usuario = ?";
        Locatario locatario = null;

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    locatario = new Locatario(
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("tipoUsuario"),
                            rs.getString("CPF")
                    );
                    locatario.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locatario;
    }

    public List<Locatario> getAllLocatarios() {
        String sql = "SELECT l.id, l.CPF, u.nome, u.email, u.password, u.tipoUsuario " +
                     "FROM locatario l " +
                     "JOIN usuario u ON l.id_Usuario = u.id";

        List<Locatario> locatarios = new ArrayList<>();

        try (Connection conn = databaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String tipoUsuario = rs.getString("tipoUsuario");
                String CPF = rs.getString("CPF");

                Locatario locatario = new Locatario(nome, email, password, tipoUsuario, CPF);
                locatario.setId(id);
                locatarios.add(locatario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locatarios;
    }

    public String updateLocatario(Locatario locatario) {
        String sql = "UPDATE locatario SET id_Usuario = ?, CPF = ? WHERE id = ?";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, locatario.getId());
            pstmt.setString(2, locatario.getCPF());
            pstmt.setInt(3, locatario.getId());

            pstmt.executeUpdate();
            return "Locatario atualizado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar Locatario: " + e.getMessage();
        }
    }

    public String deleteLocatario(int id) {
        String sql = "DELETE FROM locatario WHERE id = ?";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return "Locatario deletado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao deletar Locatario: " + e.getMessage();
        }
    }
}