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