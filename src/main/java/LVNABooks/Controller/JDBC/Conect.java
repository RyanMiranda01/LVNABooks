package LVNABooks.Controller.JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conect {

    public static Connection conexao(){
        try {
            String url = "jdbc:Mysql://localhost:3306/historico_pessoal";
            String user = "root";
            String pass = "";

            return DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
