

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