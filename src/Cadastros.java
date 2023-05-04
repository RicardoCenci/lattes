
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