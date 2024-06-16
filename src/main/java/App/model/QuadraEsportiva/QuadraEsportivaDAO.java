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
        String sql = "INSERT INTO quadras_esportivas (nome, tipo, preco_por_hora, disponivel, id_dono) VALUES (?, ?, ?, ?, ?)";

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
        String sql = "SELECT * FROM quadras_esportivas WHERE id = ?";
        QuadraEsportiva quadra = null;

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LocadorDAO locadorDAO = new LocadorDAO();
                Locador dono = locadorDAO.getLocadorById(resultSet.getInt("id_dono"));
                quadra = new QuadraEsportiva(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("preco_por_hora"),
                        dono
                );
                quadra.setDisponivel(resultSet.getBoolean("disponivel"));
                quadra.setId(resultSet.getInt("id"));
                int idDono = resultSet.getInt("id_dono");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quadra;
    }

    public List<QuadraEsportiva> listarTodasAsQuadras() {
        String sql = "SELECT * FROM quadras_esportivas";
        List<QuadraEsportiva> quadras = new ArrayList<>();

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                LocadorDAO locadorDAO = new LocadorDAO();
                Locador dono = locadorDAO.getLocadorById(resultSet.getInt("id_dono"));
                QuadraEsportiva quadra = new QuadraEsportiva(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("preco_por_hora"),
                        dono
                );
                quadra.setDisponivel(resultSet.getBoolean("disponivel"));
                quadra.setId(resultSet.getInt("id"));
                int idDono = resultSet.getInt("id_dono");

                quadras.add(quadra);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quadras;
    }

    public String atualizarQuadra(QuadraEsportiva quadra) {
        String sql = "UPDATE quadras_esportivas SET tipo = ?, preco_por_hora = ?, disponivel = ?, id_dono = ? WHERE id = ?";

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
        String sql = "DELETE FROM quadras_esportivas WHERE id = ?";

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