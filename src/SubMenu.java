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