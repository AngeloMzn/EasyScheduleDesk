package App.model.LocacaoQuadra;

import App.model.Locatario.LocatarioDAO;
import App.model.QuadraEsportiva.QuadraEsportiva;
import App.model.QuadraEsportiva.QuadraEsportivaDAO;
import App.model.Usuario.Usuario;
import Core.Config.DatabaseConfig;
import App.model.Locatario.Locatario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocacaoQuadraDAO {
    private DatabaseConfig databaseConfig;

    public LocacaoQuadraDAO() {
        databaseConfig = new DatabaseConfig();
    }

    public String adicionarLocacao(LocacaoQuadra locacao) {
        String sql = "INSERT INTO locacaoquadra (id_QuadraEsportiva, id_Locatario, data, horaInicio, horaFim) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, locacao.getQuadra().getId());
            statement.setInt(2, locacao.getLocatario().getId());
            statement.setObject(3, locacao.getData());
            statement.setString(5, locacao.getHoraInicio());
            statement.setString(6, locacao.getHoraFim());
            statement.executeUpdate();
            return "Locação salva com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao salvar locação: " + e.getMessage();
        }
    }

    public LocacaoQuadra buscarLocacaoPorId(int id) {
        String sql = "SELECT * FROM locacaoquadra WHERE id = ?";
        LocacaoQuadra locacao = null;

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                QuadraEsportivaDAO quadraDao = new QuadraEsportivaDAO();
                QuadraEsportiva quadra = quadraDao.buscarQuadraPorId(resultSet.getInt("id_QuadraEsportiva"));

                LocatarioDAO locatarioDAO = new LocatarioDAO();
                Locatario locatario = locatarioDAO.getLocatarioByUserId(resultSet.getInt("id_Locatario"));

                locacao = new LocacaoQuadra(
                        quadra,
                        locatario,
                        resultSet.getObject("data", LocalDateTime.class),
                        resultSet.getString("horaInicio"),
                        resultSet.getString("horaFim")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacao;
    }

    public List<LocacaoQuadra> listarTodasAsLocacoes() {
        String sql = "SELECT * FROM locacaoquadra";
        List<LocacaoQuadra> locacoes = new ArrayList<>();

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idQuadra = resultSet.getInt("id_QuadraEsportiva");
                int idLocatario = resultSet.getInt("id_Locatario");
                LocalDateTime data = resultSet.getObject("data", LocalDateTime.class);
                String horaInicio = resultSet.getString("horaInicio");
                String horaFim = resultSet.getString("horaFim");

                QuadraEsportivaDAO quadraDao = new QuadraEsportivaDAO();
                QuadraEsportiva quadra = quadraDao.buscarQuadraPorId(resultSet.getInt("id_QuadraEsportiva"));

                LocatarioDAO locatarioDAO = new LocatarioDAO();
                Locatario locatario = locatarioDAO.getLocatarioByUserId(resultSet.getInt("id_Locatario"));

                LocacaoQuadra locacao = new LocacaoQuadra(quadra, locatario, data, horaInicio, horaFim);
                locacoes.add(locacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacoes;
    }

    public String atualizarLocacao(LocacaoQuadra locacao) {
        String sql = "UPDATE locacaoquadra SET id_QuadraEsportiva = ?, id_Locatario = ?, data = ?, horaInicio = ?, horaFim = ? WHERE id = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, locacao.getQuadra().getId());
            statement.setInt(2, locacao.getLocatario().getId());
            statement.setObject(3, locacao.getData());
            statement.setString(5, locacao.getHoraInicio());
            statement.setString(6, locacao.getHoraFim());
            statement.setInt(7, locacao.getId());
            statement.executeUpdate();
            return "Locação atualizada com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar locação: " + e.getMessage();
        }
    }

    public String deletarLocacao(int id) {
        String sql = "DELETE FROM locacaoquadra WHERE id = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            return "Locação deletada com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao deletar locação: " + e.getMessage();
        }
    }
}

