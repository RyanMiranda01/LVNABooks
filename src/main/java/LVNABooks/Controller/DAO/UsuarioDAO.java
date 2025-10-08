package LVNABooks.Controller.DAO;


import LVNABooks.Controller.JDBC.Conect;
import LVNABooks.Model.Usuario;
import LVNABooks.View.Menus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    private Connection conn;
    private Usuario user;


    public UsuarioDAO() {
        this.conn = Conect.conexao();
    }


    public boolean consultarUsuario(String login, String senha){
        try{
            String sql = "select * from usuario where nome_usuario =? and senha_usuario =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                System.out.println("\nSeja bem vindo: "  + rs.getString("nome_usuario"));
                return false;
            }else{
                Menus menu = new Menus();
                System.out.println("Usuario nao encontrado, verifique Login e senha");
                menu.menuIncial();
                return true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int consultarUsuarioId(String login, String senha){
        try{
            String sql = "select * from usuario where nome_usuario =? and senha_usuario =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {

                return rs.getInt("id");
            }else {

                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean inserirUsuario(String login, String senha){
        try {
            String sql = "insert into usuario (nome_usuario, senha_usuario)" +
                    "value" +
                    "(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public Usuario getUser() {
        return user;
    }
}
