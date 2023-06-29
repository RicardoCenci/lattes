public class Pesquisador {
    private String nome;
    private String area;
    private Universidade universidade;
    private Lista<Artigo> artigos = new Lista<Artigo>();
    private Lista<Projeto> projetos = new Lista<Projeto>();

    public String getArea() {
        if (area == null) {
            return "Area não informada";
        }
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Artigo getArtigo(String tituloArtigo) {
        return this.artigos.find(art -> {
            return art.getTitulo().equals(tituloArtigo);
        });
    }

    public void addArtigo(Artigo artigo) {
        this.artigos.add(artigo);
    }

    public Lista<Projeto> getProjetos() {
        return this.projetos;
    }
    public Lista<Artigo> getArtigos(){
        return this.artigos;
    }
    public Projeto getProjeto(String tituloProjeto) {
        return this.projetos.find(proj -> {
            return proj.getTitulo().equals(tituloProjeto);
        });
    }

    public void addProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }

}
