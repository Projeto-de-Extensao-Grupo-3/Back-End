package school.sptech.CleanArchitecture.core.domain.entity;


public class Imagem {

    private Integer idImagem;
    private String url;

    public Imagem() {
    }

    public Imagem(Integer idImagem) {
        this.idImagem = idImagem;
    }

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(Integer idImagem) {
        this.idImagem = idImagem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "idImagem=" + idImagem +
                ", url='" + url + '\'' +
                '}';
    }
}