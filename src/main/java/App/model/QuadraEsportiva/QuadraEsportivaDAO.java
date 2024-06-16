package App.model.QuadraEsportiva;

import App.model.Locador.Locador;
import App.model.Locador.LocadorDAO;
import Core.Config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuadraEsportivaDAO {
    private DatabaseConfig databaseConfig;

    public QuadraEsportivaDAO() {
        databaseConfig = new DatabaseConfig();
    }

    public String adicionarQuadra(QuadraEsportiva quadra) {
        String sql = "INSERT INTO quadraesportiva (nome, tipo, precoPorHora, disponivel, id_Locador) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, quadra.getNome());
            statement.setString(2, quadra.getTipo());
            statement.setDouble(3, quadra.getPrecoPorHora());
            statement.setBoolean(4, quadra.isDisponivel());
            statement.setInt(5, quadra.getDono().getId());

            statement.executeUpdate();
            return "Quadra salva com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao salvar Quadra: " + e.getMessage();
        }
    }

    public QuadraEsportiva buscarQuadraPorId(int id) {
        String sql = "SELECT * FROM quadraesportiva WHERE id = ?";
        QuadraEsportiva quadra = null;

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LocadorDAO locadorDAO = new LocadorDAO();
                Locador dono = locadorDAO.getLocadorById(resultSet.getInt("id_dono"));
                quadra = new QuadraEsportiva(
                        resultSet.getString("nome"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("precoPorHora"),
                        dono
                );
                quadra.setDisponivel(resultSet.getBoolean("disponivel"));
                quadra.setId(resultSet.getInt("id"));
                int idDono = resultSet.getInt("id_Locador");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quadra;
    }

    public List<QuadraEsportiva> listarTodasAsQuadras() {
        String sql = "SELECT q.id, q.nome AS quadraNome, q.tipo, q.precoporHora, q.id_Locador, q.disponivel, " +
                "l.id AS locadorId, l.CNPJ, l.nQuadras, " +
                "u.id AS usuarioId, u.nome AS usuarioNome, u.email, u.password, u.tipoUsuario " +
                "FROM quadraesportiva q " +
                "JOIN locador l ON q.id_Locador = l.id " +
                "JOIN usuario u ON l.id_Usuario = u.id";

        List<QuadraEsportiva> quadras = new ArrayList<>();

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int locadorId = resultSet.getInt("locadorId");
                String usuarioNome = resultSet.getString("usuarioNome");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String tipoUsuario = resultSet.getString("tipoUsuario");
                String cnpj = resultSet.getString("CNPJ");
                int nQuadras = resultSet.getInt("nQuadras");

                Locador locador = new Locador(usuarioNome, email, password, tipoUsuario, cnpj);
                locador.setId(locadorId);
                locador.setnQuadras(nQuadras);

                int quadraId = resultSet.getInt("id");
                String quadraNome = resultSet.getString("quadraNome");
                String tipo = resultSet.getString("tipo");
                double precoPorHora = resultSet.getDouble("precoporHora");
                boolean disponivel = resultSet.getBoolean("disponivel");

                QuadraEsportiva quadra = new QuadraEsportiva(quadraNome, tipo, precoPorHora, locador);
                quadra.setId(quadraId);
                quadra.setDisponivel(disponivel);

                quadras.add(quadra);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quadras;
    }

    public String atualizarQuadra(QuadraEsportiva quadra) {
        String sql = "UPDATE quadraesportiva SET tipo = ?, precoPorHora = ?, disponivel = ?, id_Locador = ? WHERE id = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, quadra.getTipo());
            statement.setDouble(2, quadra.getPrecoPorHora());
            statement.setBoolean(3, quadra.isDisponivel());
            statement.setInt(4, quadra.getDono().getId());
            statement.setInt(5, quadra.getId());
            statement.executeUpdate();
            return "Quadra atualizada com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar Quadra: " + e.getMessage();
        }
    }

    public String deletarQuadra(int id) {
        String sql = "DELETE FROM quadraesportiva WHERE id = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            return "Quadra deletada com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao deletar Quadra: " + e.getMessage();
        }
    }
}



