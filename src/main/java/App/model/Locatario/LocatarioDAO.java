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
        String sql = "INSERT INTO Locatario (idUsuario, CPF) VALUES (?, ?)";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, usuarioId); // ID do usuário inserido
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
        String sql = "SELECT * FROM Locatario WHERE id = ?";
        Locatario locatario = null;

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                locatario = new Locatario(
                        rs.getInt("idUsuario"),
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

    public List<Locatario> getAllLocatarios() {
        String sql = "SELECT * FROM Locatario";
        List<Locatario> locatarios = new ArrayList<>();

        try (Connection conn = databaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Locatario locatario = new Locatario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("tipoUsuario"),
                        rs.getString("CPF")
                );
                //locatario.setId(rs.getInt("id"));
                locatarios.add(locatario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locatarios;
    }

    public String updateLocatario(Locatario locatario) {
        String sql = "UPDATE Locatario SET idUsuario = ?, CPF = ? WHERE id = ?";

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
        String sql = "DELETE FROM Locatario WHERE id = ?";

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