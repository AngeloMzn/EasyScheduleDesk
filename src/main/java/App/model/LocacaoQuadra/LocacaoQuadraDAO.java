package App.model.LocacaoQuadra;

import App.model.QuadraEsportiva.QuadraEsportiva;
import App.model.QuadraEsportiva.QuadraEsportivaDAO;
import Core.Config.DatabaseConfig;
import com.example.easyschedule.model.Locatario.Locatario;

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

    public void adicionarLocacao(LocacaoQuadra locacao) {
        String sql = "INSERT INTO locacoes_quadra (quadra_nome, locatario_nome, data_hora_inicio, data_hora_fim) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, locacao.getQuadra().getNome());
            statement.setString(2, locacao.getLocatario().getNome());
            statement.setObject(3, locacao.getDataHoraInicio());
            statement.setObject(4, locacao.getDataHoraFim());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LocacaoQuadra buscarLocacaoPorQuadraENome(String quadraNome, String locatarioNome) {
        String sql = "SELECT * FROM locacoes_quadra WHERE quadra_nome = ? AND locatario_nome = ?";
        LocacaoQuadra locacao = null;

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, quadraNome);
            statement.setString(2, locatarioNome);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                QuadraEsportivaDAO quadraDao = new QuadraEsportivaDAO();
                QuadraEsportiva quadra = quadraDao.buscarQuadraPorNome(resultSet.getString("quadra_nome"));

                Locatario locatario = new Locatario(resultSet.getString("locatario_nome")); // Presume que Locatario tem um construtor que aceita o nome.

                locacao = new LocacaoQuadra(
                        quadra,
                        locatario,
                        resultSet.getObject("data_hora_inicio", LocalDateTime.class),
                        resultSet.getObject("data_hora_fim", LocalDateTime.class)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacao;
    }

    public List<LocacaoQuadra> listarTodasAsLocacoes() {
        String sql = "SELECT * FROM locacoes_quadra";
        List<LocacaoQuadra> locacoes = new ArrayList<>();

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                QuadraEsportivaDAO quadraDao = new QuadraEsportivaDAO();
                QuadraEsportiva quadra = quadraDao.buscarQuadraPorNome(resultSet.getString("quadra_nome"));

                Locatario locatario = new Locatario(resultSet.getString("locatario_nome")); // Presume que Locatario tem um construtor que aceita o nome.

                LocacaoQuadra locacao = new LocacaoQuadra(
                        quadra,
                        locatario,
                        resultSet.getObject("data_hora_inicio", LocalDateTime.class),
                        resultSet.getObject("data_hora_fim", LocalDateTime.class)
                );
                locacoes.add(locacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacoes;
    }

    public void atualizarLocacao(LocacaoQuadra locacao) {
        String sql = "UPDATE locacoes_quadra SET data_hora_inicio = ?, data_hora_fim = ? WHERE quadra_nome = ? AND locatario_nome = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, locacao.getDataHoraInicio());
            statement.setObject(2, locacao.getDataHoraFim());
            statement.setString(3, locacao.getQuadra().getNome());
            statement.setString(4, locacao.getLocatario().getNome());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarLocacao(String quadraNome, String locatarioNome) {
        String sql = "DELETE FROM locacoes_quadra WHERE quadra_nome = ? AND locatario_nome = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, quadraNome);
            statement.setString(2, locatarioNome);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

