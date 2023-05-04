
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