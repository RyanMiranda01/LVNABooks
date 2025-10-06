package LVNABooks.Controller.DAO;


import LVNABooks.Controller.JDBC.Conect;
import LVNABooks.Model.InformacaoDoVolume;
import LVNABooks.Model.Item;

import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.stream.Collectors;

public class LivroDAO {
    private Connection conn;

    public LivroDAO() {
        this.conn = Conect.conexao();
    }

    public boolean salvarLivro(Item livro, int usuario){
        try {
            String sql = "INSERT INTO `historico_pessoal`.`livros_salvos` (`titulo_livro`, `subtitulo_livro`, `autores_livro`, `paginas_livro`, `categoria_livro`, `avaliacao`, `numero_de_avaliacao`, `ano_de_publicacao`, `id_usuario`)" +
                    " VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement stmt = conn.prepareStatement(sql);

            String autores = livro.getVolumeInfo().getAutores().stream()
                    .collect(Collectors.joining(", "));
            String categoria = livro.getVolumeInfo().getCategoria().stream()
                    .collect(Collectors.joining(", "));


            stmt.setString(1, livro.getVolumeInfo().getTitulo());
            stmt.setString(2,livro.getVolumeInfo().getSubtitulo());
            stmt.setString(3, autores);
            stmt.setInt(4,livro.getVolumeInfo().getPaginas());
            stmt.setString(5, categoria);
            stmt.setInt(6, livro.getVolumeInfo().getAvaliacao());
            stmt.setInt(7, livro.getVolumeInfo().getNumeroDeAvaliacao());
            stmt.setString(8, livro.getVolumeInfo().getAnoDePublicacao());
            stmt.setInt(9, usuario);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<InformacaoDoVolume> listarLivrosSalvos(int id){
        try {
            String sql = "SELECT " +
                    "l.id," +
                    "l.titulo_livro," +
                    "l.subtitulo_livro," +
                    "l.autores_livro," +
                    "l.paginas_livro," +
                    "l.categoria_livro," +
                    "l.avaliacao," +
                    "l.numero_de_avaliacao," +
                    "l.ano_de_publicacao," +
                    "u.nome_usuario" +
                    " FROM livros_salvos l inner join usuario u on(l.id_usuario = u.id) where u.id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            List<InformacaoDoVolume> livrosSalvos = new ArrayList<>();

                while (rs.next()){
                    InformacaoDoVolume livro = new InformacaoDoVolume();
                    livro.setIdLivro(rs.getInt("id"));
                    livro.setTitulo(rs.getString("titulo_livro"));
                    livro.setSubtitulo(rs.getString("subtitulo_livro"));
                    livro.getAutores().add(rs.getString("autores_livro"));
                    livro.setPaginas(rs.getInt("paginas_livro"));
                    livro.getCategoria().add(rs.getString("categoria_livro"));
                    livro.setAvaliacao(rs.getInt("avaliacao"));
                    livro.setNumeroDeAvaliacao(rs.getInt("numero_de_avaliacao"));
                    livro.setAnoDePublicacao(rs.getString("ano_de_publicacao"));
                    livro.setNomeUsuario(rs.getString("u.nome_usuario"));
                    livrosSalvos.add(livro);
                }


            return livrosSalvos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletarLivro(int id_livro){
        try {
            String sql = "delete from livros_salvos where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_livro);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
