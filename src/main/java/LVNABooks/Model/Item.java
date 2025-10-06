package LVNABooks.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private @JsonAlias("volumeInfo") InformacaoDoVolume informacaoDoVolume;

    public InformacaoDoVolume getVolumeInfo() {
        return informacaoDoVolume;
    }

    public void setVolumeInfo(InformacaoDoVolume volumeInfo) {
        this.informacaoDoVolume = volumeInfo;
    }
}
