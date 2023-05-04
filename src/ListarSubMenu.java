
class ListarSubMenu extends SubMenu {

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