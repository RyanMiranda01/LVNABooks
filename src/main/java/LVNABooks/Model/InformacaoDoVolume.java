package LVNABooks.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class InformacaoDoVolume {
    private int idLivro;
    private @JsonAlias("title") String titulo;
    private @JsonAlias("subtitle") String subtitulo;
    private @JsonAlias("authors") List<String> autores = new ArrayList<>();
    private @JsonAlias("pageCount") int paginas;
    private @JsonAlias("categories") List<String> categoria = new ArrayList<>();
    private @JsonAlias("averageRating") int avaliacao;
    private @JsonAlias("ratingsCount") int numeroDeAvaliacao;
    private @JsonAlias("publishedDate") String anoDePublicacao;
    private String nomeUsuario;



    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public void setNomeUsuario(String noomeUsuario) {
        this.nomeUsuario = noomeUsuario;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setCategoria(List<String> categoria) {
        this.categoria = categoria;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setNumeroDeAvaliacao(int numeroDeAvaliacao) {
        this.numeroDeAvaliacao = numeroDeAvaliacao;
    }

    public void setAnoDePublicacao(String anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }
    public int getIdLivro() {
        return idLivro;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getTitulo() {
        if(titulo == null){
            return  "Valor nao encontrado";
        }else {
            return titulo;
        }
    }

    public String getSubtitulo() {
        if (subtitulo == null) {
            return "Valor nao encontrado";
        } else if(subtitulo.length() > 200){
            return "Valor maior que o suportado";
        }else{
            return subtitulo;
        }
    }

    public List<String> getAutores() {
        if (autores == null) {
            List<String> lista = List.of("Valor nao encontrato");
            return lista;
        } else {
            return autores;
        }
    }
    public int getPaginas() {
        return paginas;
    }

    public List<String> getCategoria() {
        if (categoria == null) {
            List<String> lista = List.of("Valor nao encontrato");
            return lista;
        } else {
            return categoria;
        }
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public int getNumeroDeAvaliacao() {
        return numeroDeAvaliacao;
    }

    public String getAnoDePublicacao() {

        return anoDePublicacao;

    }


    @Override
    public String toString() {
        String s = String.format(
                """       
                Id do livro: %d
                        ----- %s -----
                **************************        
                Subtitulo: %s
                Autores: %s
                Paginas: %d
                Categorias: %s
                Avaliacao: %d
                Numero de Avaliacao: %d
                Ano de Publicacao: %s
                Nome do usuario: %s
                ************************** 
               """,idLivro, titulo,subtitulo, autores, paginas, categoria, avaliacao , numeroDeAvaliacao,anoDePublicacao, nomeUsuario);

        return s;
    }

    public String toStringDaAPI() {
        String s = String.format(
                """       
                        ----- %s -----
                **************************        
                Subtitulo: %s
                Autores: %s
                Paginas: %d
                Categorias: %s
                Avaliacao: %d
                Numero de Avaliacao: %d
                Ano de Publicacao: %s
                Nome do usuario: %s
                ************************** 
               """, titulo,subtitulo, autores, paginas, categoria, avaliacao , numeroDeAvaliacao,anoDePublicacao, nomeUsuario);

        return s;
    }
}

