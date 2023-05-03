public class Artigo {
    private String titulo;
    private int anoPublicacao;
    private String tituloRevista;
    private Lista<Pesquisador> pesquisadores = new Lista<Pesquisador>();

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

    public Lista<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void addPesquisador(Pesquisador pesquisador) {
        this.pesquisadores.add(pesquisador);
    }
}
