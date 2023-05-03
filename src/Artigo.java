public class Artigo {
    private String titulo;
    private int anoPublicacao;
    private String tituloRevista;
    private Pesquisador[] pesquisadores;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public Pesquisador[] getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(Pesquisador[] pesquisadores) {
        this.pesquisadores = pesquisadores;
    }
}
