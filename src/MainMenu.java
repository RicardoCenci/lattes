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
            return new ListarSubMenu();
        }
        return this;
    }
}