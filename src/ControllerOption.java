abstract class ControllerOption extends SubMenu {

    @Override
    public void display() {
        try{
            super.display();
        }catch (Exception e){
            e.printStackTrace();
            IO.write("Erro ao executar ação");
        }
    }

    protected SubMenu _handleInput(int option){

        return Programa.popLastMenu(2);
    }
}