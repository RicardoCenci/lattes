import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InterfaceDeUsuario ui = new InterfaceDeUsuario();
        ui.run();
    }
}

class InterfaceDeUsuario{
    public void run(){
        SubMenu menu = new MainMenu();
        SubMenu nextMenu;
        Programa.start();

        while(Programa.isRunning()){
            menu.display();

            String option = IO.read();

            if(!Utils.isNumeric(option)){
                IO.write("Opção inválida");
                continue;
            }

            nextMenu = menu.handleInput(Integer.parseInt(option));

            if(nextMenu != menu && !(menu instanceof ControllerOption)){
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

class RelacionarPesquisadorArtigo extends ControllerOption {
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

}

class RelacionarPesquisadorProjeto extends ControllerOption {
    protected void _display(){
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

        IO.write("Título do projeto: ");
        String tituloProjeto = IO.read();
        Lista<Projeto> projetos = Dados.getProjetos();

        Projeto projeto = projetos.find(proj -> {
            return proj.getTitulo().equals(tituloProjeto);
        });

        if (projeto == null) {
            IO.write("Nenhum projeto com este titulo foi encontrado.");
            return;
        }
        if (pesquisador.getProjeto(projeto.getTitulo()) != null) {
            IO.write("Pesquisador já cadastrado neste projeto.");
            return;
        }

        pesquisador.addProjeto(projeto);
        projeto.addPesquisador(pesquisador);
        IO.write("Projeto vinculado ao pesquisador.");
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

class CadastroArtigo extends ControllerOption {

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

}

class CadastroProjeto extends ControllerOption{

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

}

class CadastroPesquisador extends ControllerOption {

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

}

class ListarPesquisadoresUniversidade extends ControllerOption {

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
    
}

class ListarProjetosPesquisador extends ControllerOption {

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
    
}

class ListarAutoresArtigo extends ControllerOption {

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
    
}

class Listar extends SubMenu {

    protected void _display(){
        IO.write("0. Listar pesquisadores de uma universidade");
        IO.write("1. Listar autores de um artigo");
        IO.write("2. Listar projetos de um pesquisador");
        IO.write("3. Listar pesquisadores de projetos já finalizados");
        IO.write("4. Listar todos pesquisadores");
        IO.write("5. Listar todos projetos");
        IO.write("6. Listar todos artigos");
        IO.write("7. Listar todas universidades");
    }

    protected SubMenu _handleInput(int option){
        if (option == 0) {
            return new ListarPesquisadoresUniversidade();
        }
        if (option == 1) {
            return new ListarAutoresArtigo();
        }
        if (option == 2) {
            return new ListarProjetosPesquisador();
        }

        if(option == 3){
            return new ListarPesquisadoresProjetosFinalizados();
        }
        if(option == 4){
            return new ListarPesquisadores();
        }
        if(option == 5){
            return new ListarProjetos();
        }
        if(option == 6){
            return new ListarArtigos();
        }
        if(option == 7){
            return new ListarUniversidades();
        }
        return this;
    }
}
class ListarUniversidades extends ControllerOption{
    protected void _display(){
        Lista<Universidade> universidades = Dados.getUniversidades();

        if(universidades.size() == 0){
            IO.write("Nenhuma universidade cadastrada");
            return;
        }

        IO.write("Universidades:");

        for (Universidade universidade : universidades) {
            IO.write(universidade.getNome());
            if(universidade.getPesquisadores().size() != 0){
                IO.write("Pesquisadores: ", 1);
                for (Pesquisador pesquisador : universidade.getPesquisadores()) {
                    IO.write(pesquisador.getNome(), 2);
                }
            }
        }
    }
}
class ListarArtigos extends ControllerOption{
    protected void _display(){
        Lista<Artigo> artigos = Dados.getArtigos();

        if(artigos.size() == 0){
            IO.write("Nenhum artigo cadastrado");
            return;
        }

        IO.write("Artigos:");

        for (Artigo artigo : artigos) {
            IO.write(artigo.getTitulo());
            IO.write("Descricao: " + artigo.getTituloRevista(), 1);
            IO.write("Ano Publicacao: " + artigo.getAnoPublicacao(), 1);

            if(artigo.getPesquisadores().size() != 0){
                IO.write("Pesquisadores: ", 1);
                for (Pesquisador pesquisador : artigo.getPesquisadores()) {
                    IO.write(pesquisador.getNome(), 2);
                }
            }
        }
    }
}
class ListarProjetos extends ControllerOption{
    protected void _display(){
        Lista<Projeto> projetos = Dados.getProjetos();

        if(projetos.size() == 0){
            IO.write("Nenhum projeto cadastrado");
            return;
        }

        IO.write("Projetos:");

        for (Projeto projeto : projetos) {
            IO.write(projeto.getTitulo());
            IO.write("Descricao: " + projeto.getDescricao(), 1);
            IO.write("Data de inicio: " + projeto.getDataInicio(), 1);
            IO.write("Data de termino: " + projeto.getDataFinal(), 1);

            if(projeto.getPesquisadores().size() != 0){
                IO.write("Pesquisadores: ", 1);
                for (Pesquisador pesquisador : projeto.getPesquisadores()) {
                    IO.write(pesquisador.getNome(), 2);
                }
            }


        }
    }
}
class ListarPesquisadores extends ControllerOption{


    protected void _display(){
        Lista<Pesquisador> pesquisadores = Dados.getPesquisadores();

        if(pesquisadores.size() == 0){
            IO.write("Nenhum pesquisador cadastrado");
            return;
        }

        IO.write("Pesquisadores:");


        for (Pesquisador pesquisador : pesquisadores) {

            IO.write(pesquisador.getNome());
            IO.write("Area: " + pesquisador.getArea(), 1);
            IO.write("Universidade: " + pesquisador.getUniversidade().getNome(), 1);

            if (pesquisador.getProjetos().size() != 0) {
                IO.write("Projetos: ", 1);
                for (Projeto projeto : pesquisador.getProjetos()) {
                    IO.write(projeto.getTitulo(), 2);
                }
            }




            if (pesquisador.getArtigos().size() != 0) {
                IO.write("Artigos: ", 1);
                for (Artigo artigo : pesquisador.getArtigos()) {
                    IO.write(artigo.getTitulo(), 2);
                }
            }

        };

    }
}
abstract class ControllerOption extends SubMenu{

    @Override
    public void display() {
        try{
            super.display();
        }catch (Exception e){
            IO.write("Erro ao executar ação");
        }
    }

    protected SubMenu _handleInput(int option){

        return Programa.popLastMenu(2);
    }
}
abstract class SubMenu {

    public void display(){
        this._display();
        if(Programa.peekLastMenu() != null){
            IO.write("8. Voltar");
        }
        IO.write("9. Sair");
    }

    public SubMenu handleInput(int option){
        if(option == 9){
            Programa.exit();
            return this;
        }

        if(option == 8 && Programa.peekLastMenu() != null){
            return Programa.getLastMenu();
        }

        return this._handleInput(option);
    }

    protected abstract void _display();
    protected abstract  SubMenu _handleInput(int option);
}

class ListarPesquisadoresProjetosFinalizados extends ControllerOption{
    protected void _display(){
        Lista<Projeto> projetos = Dados.getProjetos();
        if (projetos.size() == 0) {
            IO.write("Nenhum projeto cadastradado.");
            return;
        }

        IO.write("Nome do pesquisador ");
        String nomePesquisador = IO.read();
        Pesquisador pesquisador = Dados.getPesquisadores().find(pesq -> {
            return pesq.getNome().equals(nomePesquisador);
        });

        if (pesquisador == null) {
            IO.write("Pesquisador não encontrado");
            return;
        }

        Date hoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Lista<Projeto> projetosFinalizados = pesquisador.getProjetos().filter(projeto -> {
            try {
                Date dataFinal = formatter.parse(projeto.getDataFinal());

                return hoje.after(dataFinal);
            } catch (ParseException e) {
                return false;
            }
        });

        IO.write("Projetos finalizados: ");

        if(projetosFinalizados.size() == 0){
            IO.write("Nenhum projeto finalizado");
            return;
        }

        projetosFinalizados.forEach(projeto -> {
            IO.write(projeto.getTitulo());
        });

    }
}