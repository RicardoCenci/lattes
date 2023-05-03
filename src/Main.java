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

class MainMenu extends SubMenu{

    public void _display(){
        System.out.println("1. Cadastros");
        System.out.println("2. Relacionar");
        System.out.println("3. Listar");
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
class Cadastros extends SubMenu {

    protected void _display(){
        System.out.println("1. Cadastrar Pesquisador");
        System.out.println("2. Cadastrar Projeto");
        System.out.println("3. Cadastrar Artigo");
    }

    protected SubMenu _handleInput(int option){
        if(option == 1){
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

        if(universidade == null) {
            IO.write("Universidade não encontrada, criando nova universidade");
            universidade = new Universidade();
            universidade.setNome(nomeUniveridade);
            Dados.addUniversidades(universidade);
        }

        pesquisador.setUniversidade(universidade);
        Dados.addPesquisador(pesquisador);
        IO.write("Pesquisador cadastrado com sucesso");

    }

    protected SubMenu _handleInput(int option){

        return Programa.getLastMenu();
    }
}

class Listar extends SubMenu {

    protected void _display(){
        System.out.println("1. Relacionar pesquisadores à universidade");
        System.out.println("2. Relacionar autores à artigo");
        System.out.println("3. Relacionar projetos à pesquisador");
    }

    protected SubMenu _handleInput(int option){
        return this;
    }
}
class Relacionar extends SubMenu {

    protected void _display(){
        System.out.println("1. Cadastrar Pesquisador");
        System.out.println("2. Cadastrar Projeto");
        System.out.println("3. Cadastrar Artigo");
        System.out.println("4. Cadastrar Autores");
    }

    protected SubMenu _handleInput(int option){
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