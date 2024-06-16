package App.model.Locador;

import App.model.Usuario.UsuarioDAO;
import Core.Config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocadorDAO {
    private DatabaseConfig databaseConfig;

    public LocadorDAO() {
        databaseConfig = new DatabaseConfig();
    }

    public String addLocador(Locador locador) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int usuarioId = usuarioDAO.adicionarUsuario(locador);

        if (usuarioId == -1) {
            return "Erro ao adicionar usuário. Locador não adicionado.";
        }

        // Agora adiciona o locador usando o ID do usuário
        String sql = "INSERT INTO locador (id_Usuario, CNPJ, nQuadras) VALUES (?, ?, ?)";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, usuarioId); // ID do usuário inserido
            pstmt.setString(2, locador.getCNPJ());
            pstmt.setInt(3, locador.getnQuadras());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                locador.setId(generatedKeys.getInt(1));
            }
            return "Locador salvo com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao salvar Locador: " + e.getMessage();
        }
    }

    public Locador getLocadorById(int id) {
        String sql = "SELECT * FROM locadores WHERE id = ?";
        Locador locador = null;

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                locador = new Locador(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("tipoUsuario"),
                        rs.getString("CNPJ")
                );
                locador.setnQuadras(rs.getInt("nQuadras"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locador;
    }

    public Locador getLocadorByUserId(int id) {
        String sql = "SELECT u.id, u.nome, u.email, u.password, u.tipoUsuario, l.CNPJ, l.nQuadras " +
                "FROM locador l " +
                "JOIN usuario u ON l.id_Usuario = u.id " +
                "WHERE l.id_Usuario = ?";
        Locador locador = null;

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                locador = new Locador(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("tipoUsuario"),
                        rs.getString("CNPJ")
                );
                locador.setnQuadras(rs.getInt("nQuadras"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locador;
    }

    public List<Locador> getAllLocadores() {
        String sql = "SELECT l.id, l.CNPJ, l.nQuadras, u.nome, u.email, u.password, u.tipoUsuario " +
                     "FROM locador l " +
                     "JOIN usuario u ON l.id_Usuario = u.id";

        List<Locador> locadores = new ArrayList<>();

        try (Connection conn = databaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String tipoUsuario = rs.getString("tipoUsuario");
                String cnpj = rs.getString("CNPJ");
                int nQuadras = rs.getInt("nQuadras");

                Locador locador = new Locador(nome, email, password, tipoUsuario, cnpj);
                locador.setId(id);
                locador.setnQuadras(nQuadras);
                locadores.add(locador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locadores;
    }

    public String updateLocador(Locador locador) {
        String sql = "UPDATE locadores SET id = ?, CNPJ = ?, nQuadras = ? WHERE id = ?";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, locador.getId());
            pstmt.setString(2, locador.getCNPJ());
            pstmt.setInt(3, locador.getnQuadras());
            pstmt.setInt(4, locador.getId());

            pstmt.executeUpdate();
            return "Locador atualizado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar Locador: " + e.getMessage();
        }
    }

    public String deleteLocador(int id) {
        String sql = "DELETE FROM locadores WHERE id = ?";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return "Locador deletado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao deletar Locador: " + e.getMessage();
        }
    }
}