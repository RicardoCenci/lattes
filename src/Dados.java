import java.util.ArrayList;
import java.util.function.Predicate;

public class Dados {
    private static Lista<Pesquisador> pesquisadores = new Lista<Pesquisador>();
    private static Lista<Universidade> universidades = new Lista<Universidade>();
    private static Lista<Projeto> projetos = new Lista<Projeto>();
    private static Lista<Artigo> artigos = new Lista<Artigo>();

    public static Lista<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }
    public static void addPesquisador(Pesquisador pesquisador) {
        Dados.pesquisadores.add(pesquisador);
    }

    public static Lista<Universidade> getUniversidades() {
        return universidades;
    }
    
    public static void addUniversidades(Universidade universidade) {
        Dados.universidades.add(universidade);
    }

    public static void addProjeto(Projeto projeto) {
        Dados.projetos.add(projeto);
    }
    public static Lista<Projeto> getProjetos() {
        return projetos;
    }

    public static void addArtigo(Artigo artigo) {
        Dados.artigos.add(artigo);
    }
    public static Lista<Artigo> getArtigos() {
        return artigos;
    }

}
class Lista<T> extends ArrayList<T>{
    public T find( Predicate<T> predicate){
        for (T item: this) {
            if(predicate.test(item)){
                return item;
            }
        }
        return null;
    }

    public Lista<T> filter(Predicate<T> predicate){
        Lista<T> lista = new Lista<T>();
        for (T item: this) {
            if(predicate.test(item)){
                lista.add(item);
            }
        }
        return lista;
    }
}