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
            url = "jdbc:mysql://localhost:3306/db_easyshedule";
            username ="root";
            password = "123";
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
