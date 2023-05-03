public class Universidade {

    private String nome;

    private Lista<Pesquisador> pesquisadores = new Lista<Pesquisador>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void addPesquisador(Pesquisador pesquisador) {
        this.pesquisadores.add(pesquisador);
    }
    
    public Lista<Pesquisador> getPesquisadores() {
        return this.pesquisadores;
    }
}
