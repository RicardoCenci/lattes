
class ListarProjetosPesquisador extends ControllerOption {

    protected void _display() {
        Lista<Pesquisador> pesquisadores = Dados.getPesquisadores();
        if (pesquisadores.size() == 0) {
            IO.write("Nenhum pesquisador cadastradado.");
            return;
        }

        IO.write("Digite o nome completo do pesquisador");
        String nomePesquisador = IO.read();
        Pesquisador pesquisador = pesquisadores.find(pesq -> {
            return pesq.getNome().equals(nomePesquisador);
        });

        if (pesquisador == null) {
            IO.write("Pesquisador não encontrado");
            return;
        }
        
        Lista<Projeto> projetos = pesquisador.getProjetos();
        if (projetos.size() == 0) {
            IO.write("Nenhum projeto vinculado a este pesquisador.");
            return;
        }
        IO.write("Projetos: ");
        for (int i = 0; i < projetos.size(); i++) {
            Projeto projeto = projetos.get(i);
            IO.write(" - " + projeto.getTitulo());
        }
    }
}