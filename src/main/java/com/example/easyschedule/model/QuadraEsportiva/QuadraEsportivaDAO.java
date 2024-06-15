package com.example.easyschedule.model.QuadraEsportiva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuadraEsportivaDAO {
    private DatabaseConfig databaseConfig;

    public QuadraEsportivaDao() {
        databaseConfig = new DatabaseConfig();
    }

    public void adicionarQuadra(QuadraEsportiva quadra) {
        String sql = "INSERT INTO quadras_esportivas (nome, tipo, preco_por_hora, disponivel, dono) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, quadra.getNome());
            statement.setString(2, quadra.getTipo());
            statement.setDouble(3, quadra.getPrecoPorHora());
            statement.setBoolean(4, quadra.isDisponivel());
            statement.setString(5, quadra.getDono().getNome());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public QuadraEsportiva buscarQuadraPorNome(String nome) {
        String sql = "SELECT * FROM quadras_esportivas WHERE nome = ?";
        QuadraEsportiva quadra = null;

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Locador dono = new Locador(resultSet.getString("dono")); // Presume que Locador tem um construtor que aceita o nome.
                quadra = new QuadraEsportiva(
                        resultSet.getString("nome"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("preco_por_hora"),
                        dono
                );
                quadra.setDisponivel(resultSet.getBoolean("disponivel"));
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
                Locador dono = new Locador(resultSet.getString("dono")); // Presume que Locador tem um construtor que aceita o nome.
                QuadraEsportiva quadra = new QuadraEsportiva(
                        resultSet.getString("nome"),
                        resultSet.getString("tipo"),
                        resultSet.getDouble("preco_por_hora"),
                        dono
                );
                quadra.setDisponivel(resultSet.getBoolean("disponivel"));
                quadras.add(quadra);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quadras;
    }

    public void atualizarQuadra(QuadraEsportiva quadra) {
        String sql = "UPDATE quadras_esportivas SET tipo = ?, preco_por_hora = ?, disponivel = ?, dono = ? WHERE nome = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, quadra.getTipo());
            statement.setDouble(2, quadra.getPrecoPorHora());
            statement.setBoolean(3, quadra.isDisponivel());
            statement.setString(4, quadra.getDono().getNome());
            statement.setString(5, quadra.getNome());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarQuadra(String nome) {
        String sql = "DELETE FROM quadras_esportivas WHERE nome = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nome);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
