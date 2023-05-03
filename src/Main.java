import java.util.*;

public class Main {
    public static void main(String[] args) {
        SubMenu menu = new MainMenu();
        SubMenu nextMenu;
        Programa.start();

        while(Programa.isRunning()){
            menu.display();

            String option = IO.read();


            nextMenu = menu.handleInput(Integer.parseInt(option));
            if(nextMenu != menu){
                Programa.setLastMenu(menu);
            }
            menu = nextMenu;
        }

    }
}

class MainMenu extends SubMenu {
    
    public MainMenu() {
        CarregadorDeDados.carregarDados();
    }

    public void _display(){
        IO.write("1. Cadastros");
        IO.write("2. Relacionar");
        IO.write("3. Listar");
    }

    public SubMenu _handleInput(int option){
        if(option == 1){
            return new Cadastros();
        }

        if(option == 2){
            return new Relacionar();
        }

        if(option == 3){
            return new Listar();
        }
        return this;
    }
}

class Relacionar extends SubMenu {
    protected void _display(){
        IO.write("1. Relacionar Pesquisador a um artigo");
        IO.write("2. Relacionar Pesquisador a um projeto");
    }

    protected SubMenu _handleInput(int option){
        if (option == 1) {
            return new RelacionarPesquisadorArtigo();
        }

        if (option == 2) {
            return new RelacionarPesquisadorProjeto();
        }

        return this;
    }
}

class RelacionarPesquisadorArtigo extends SubMenu {
    protected void _display() {
        IO.write("Nome do pesquisador: ");

        String nomePesquisador = IO.read();
        Lista<Pesquisador> pesquisadores = Dados.getPesquisadores();
        Pesquisador pesquisador = pesquisadores.find(pesq -> {
            return pesq.getNome().equals(nomePesquisador);
        });
        if (pesquisador == null) {
            IO.write("Nenhum pesquisador com este nome foi encontrado.");
            return;
        }

        IO.write("Título do artigo: ");
        String tituloArtigo = IO.read();
        Lista<Artigo> artigos = Dados.getArtigos();
        Artigo artigo = artigos.find(art -> {
            return art.getTitulo().equals(tituloArtigo);
        });
        if (artigo == null) {
            IO.write("Nenhum artigo com este titulo foi encontrado.");
            return;
        }
        if (pesquisador.getArtigo(artigo.getTitulo()) != null) {
            IO.write("Pesquisador já cadastrado neste artigo.");
            return;
        }

        pesquisador.addArtigo(artigo);
        artigo.addPesquisador(pesquisador);
        IO.write("Artigo vinculado ao pesquisador.");
    }

    protected SubMenu _handleInput(int option){
        return this;
    }
}

class RelacionarPesquisadorProjeto extends SubMenu {
    protected void _display(){    
        
    }

    protected SubMenu _handleInput(int option){
        return this;
    }
}

class Cadastros extends SubMenu {

    protected void _display(){
        IO.write("1. Cadastrar Pesquisador");
        IO.write("2. Cadastrar Projeto");
        IO.write("3. Cadastrar Artigo");
    }

    protected SubMenu _handleInput(int option){
        if (option == 1) {
            return new CadastroPesquisador();
        }

        if(option == 2){
            return new CadastroProjeto();
        }

        if(option == 3){
            return new CadastroArtigo();
        }

        return this;
    }
}

class CadastroArtigo extends SubMenu {

    protected void _display(){
        IO.write("Cadastro de Artigo");
        Artigo artigo = new Artigo();

        IO.write("Titulo do artigo");
        artigo.setTitulo(IO.read());
        IO.write("Ano de publicacao");
        artigo.setAnoPublicacao(Integer.parseInt(IO.read()));

        IO.write("Titulo da revista");
        artigo.setTituloRevista(IO.read());

        Dados.addArtigo(artigo);

        IO.write("Artigo cadastrado com sucesso");
    }

    protected SubMenu _handleInput(int option){
        return Programa.popLastMenu(2);
    }
}

class CadastroProjeto extends SubMenu{

        protected void _display(){
            IO.write("Cadastro de Projeto");
            Projeto projeto = new Projeto();

            IO.write("Titulo do projeto");
            projeto.setTitulo(IO.read());
            IO.write("Descricao do projeto");
            projeto.setDescricao(IO.read());

            IO.write("Data de inicio do projeto");
            projeto.setDataInicio(IO.read());
            IO.write("Data de termino do projeto");
            projeto.setDataFinal(IO.read());

            Dados.addProjeto(projeto);

            IO.write("Projeto cadastrado com sucesso");
        }

        protected SubMenu _handleInput(int option){

            return Programa.getLastMenu();
        }
}

class CadastroPesquisador extends SubMenu {

    protected void _display(){
        IO.write("Cadastro de Pesquisador");
        Pesquisador pesquisador = new Pesquisador();

        IO.write("Nome do pesquisador");
        pesquisador.setNome(IO.read());
        IO.write("Area do pesquisador");
        pesquisador.setArea(IO.read());

        IO.write("Universidade");

        String nomeUniveridade = IO.read();

        Lista<Universidade> universidades = Dados.getUniversidades();

        Universidade universidade = universidades.find(uni -> {
            return uni.getNome().equals(nomeUniveridade);
        });

        if (universidade == null) {
            IO.write("Universidade não encontrada, criando nova universidade");
            universidade = new Universidade();
            universidade.setNome(nomeUniveridade);
            universidade.addPesquisador(pesquisador);
            Dados.addUniversidades(universidade);
        } else {
            universidade.addPesquisador(pesquisador);
        }
        
        pesquisador.setUniversidade(universidade);
        Dados.addPesquisador(pesquisador);
        IO.write("Pesquisador cadastrado com sucesso");
    }

    protected SubMenu _handleInput(int option){
        return Programa.getLastMenu();
    }
}

class ListarPesquisadoresUniversidade extends SubMenu {

    protected void _display() {
        Lista<Universidade> universidades = Dados.getUniversidades();
        if (universidades.size() == 0) {
            IO.write("Nenhuma universidade cadastradado.");
            return;
        }

        IO.write("Digite o nome da universidade");
        String nomeUniversidade = IO.read();
        Universidade universidade = universidades.find(uni -> {
            return uni.getNome().equals(nomeUniversidade);
        });

        if (universidade == null) {
            IO.write("Universidade não encontrada");
            return;
        }

        Lista<Pesquisador> pesquisadores = universidade.getPesquisadores();
        if (pesquisadores.size() == 0) {
            IO.write("Nenhum pesquisador vinculado a esta universidade.");
            return;
        }
        IO.write("Pesquisadores:");
        for (int i = 0; pesquisadores.size() > i; i++) {
            Pesquisador pesquisador = pesquisadores.get(i);
            IO.write(" - " + pesquisador.getNome() + " Area: " + pesquisador.getArea());
        }
    }
    
    protected SubMenu _handleInput(int option) {
        return Programa.getLastMenu();
    }
}

class ListarProjetosPesquisador extends SubMenu {

    protected void _display() {
        Lista<Pesquisador> pesquisadores = Dados.getPesquisadores();
        if (pesquisadores.size() == 0) {
            IO.write("Nenhum pesquisador cadastradado.");
            return;
        }

        IO.write("Digite o nome completo do pesquisador");
        String nomePesquisador = IO.read();
        Pesquisador pesquisador = pesquisadores.find(pesq -> {
            return pesq.getNome().equals(nomePesquisador);
        });

        if (pesquisador == null) {
            IO.write("Pesquisador não encontrado");
            return;
        }
        
        Lista<Projeto> projetos = pesquisador.getProjetos();
        if (projetos.size() == 0) {
            IO.write("Nenhum projeto vinculado a este pesquisador.");
            return;
        }
        IO.write("Projetos: ");
        for (int i = 0; i < projetos.size(); i++) {
            Projeto projeto = projetos.get(i);
            IO.write(" - " + projeto.getTitulo());
        }
    }
    
    protected SubMenu _handleInput(int option) {
        return Programa.getLastMenu();
    }
}

class ListarAutoresArtigo extends SubMenu {

    protected void _display() {
        Lista<Artigo> artigos = Dados.getArtigos();
        if (artigos.size() == 0) {
            IO.write("Nenhum artigo cadastradado.");
            return;
        }

        IO.write("Digite o titulo do Artigo");
        String tituloArtigo = IO.read();
        Artigo artigo = artigos.find(art -> {
            return art.getTitulo().equals(tituloArtigo);
        });

        if (artigo == null) {
            IO.write("Artigo não encontrado");
            return;
        }
        
        Lista<Pesquisador> pesquisadores = artigo.getPesquisadores();
        if (pesquisadores.size() == 0) {
            IO.write("Nenhum pesquisador vinculado a este artigo.");
            return;
        }
        IO.write("Pesquisadores: ");
        for (int i = 0; i < pesquisadores.size(); i++) {
            Pesquisador pesquisador = pesquisadores.get(i);
            IO.write(" - " + pesquisador.getNome());
        }
    }
    
    protected SubMenu _handleInput(int option) {
        return Programa.getLastMenu();
    }
}

class Listar extends SubMenu {

    protected void _display(){
        IO.write("1. Listar pesquisadores de uma universidade");
        IO.write("2. Listar autores de um artigo");
        IO.write("3. Listar projetos de um pesquisador");
        IO.write("4. Listar pesquisadores de projetos já finalizados");
    }

    protected SubMenu _handleInput(int option){
        if (option == 1) {
            return new ListarPesquisadoresUniversidade();
        }
        if (option == 2) {
            return new ListarAutoresArtigo();
        }
        if (option == 3) {
            return new ListarProjetosPesquisador();
        }
        return this;
    }
}

abstract class SubMenu {

    public void display(){
        this._display();
        if(Programa.getLastMenu() != null){
            IO.write("8. Voltar");
        }
        IO.write("9. Sair");
    }

    public SubMenu handleInput(int option){
        if(option == 9){
            Programa.exit();
            return this;
        }

        SubMenu ultimoMenu = Programa.getLastMenu();
        if(option == 8 && ultimoMenu != null){
            Programa.setLastMenu(null);
            return ultimoMenu;
        }
        return this._handleInput(option);
    }
    protected abstract void _display();
    protected abstract  SubMenu _handleInput(int option);
}