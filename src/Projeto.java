public class Projeto {
//    adastrar o título do projeto, descrição, data de início, data de final. Vincular os pesquisadores envolvidos (vetor de objetos).
//    Vincular os artigos produzidos (vetor de objetos).
    private String titulo;
    private String descricao;
    private String dataInicio;
    private String dataFinal;
    private Lista<Pesquisador> pesquisadores = new Lista<Pesquisador>();

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setPesquisadores(Lista<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    public Lista<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void addPesquisador(Pesquisador pesquisador){
        this.pesquisadores.add(pesquisador);
    }


}
