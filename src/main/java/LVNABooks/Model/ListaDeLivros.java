package LVNABooks.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaDeLivros {
    private @JsonAlias("totalItems") int totalLivros;
    private @JsonAlias("items") List<Item> lista;

    public void setTotalLivros(int totalLivros) {
        this.totalLivros = totalLivros;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }

    public int getTotalLivros() {
        return totalLivros;
    }

    public List<Item> getLista() {
        return lista;
    }

    public ListaDeLivros() {
        this.lista = new ArrayList<>();
    }
}
