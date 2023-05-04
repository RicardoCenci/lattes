class InterfaceDeUsuario {

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