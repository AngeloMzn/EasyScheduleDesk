package Core.Config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public DatabaseConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            Properties properties = new Properties();
            if (input == null) {
                System.out.println("Desculpe, não foi possível encontrar o arquivo database.properties");
                return;
            }
            properties.load(input);

            driverClassName = properties.getProperty("jdbc.driverClassName");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

            // Carrega o driver JDBC
            Class.forName(driverClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Método principal para teste
    public static void main(String[] args) {
        DatabaseConfig config = new DatabaseConfig();
        try (Connection connection = config.getConnection()) {
            if (connection != null) {
                System.out.println("Conexão com o banco de dados bem-sucedida!");
            } else {
                System.out.println("Falha na conexão com o banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
