class RelacionarPesquisadorProjeto extends ControllerOption {
    protected void _display(){
        IO.write("Nome do pesquisador: ");

        String nomePesquisador = IO.read();
        Lista<Pesquisador> pesquisadores = Dados.getPesquisadores();
        Pesquisador pesquisador = pesquisadores.find(pesq -> {
            return pesq.getNome().equals(nomePesquisador);
        });
        if (pesquisador == null) {
            IO.write("Nenhum pesquisador com este nome foi encontrado.");
            return;
        }

        IO.write("Título do projeto: ");
        String tituloProjeto = IO.read();
        Lista<Projeto> projetos = Dados.getProjetos();

        Projeto projeto = projetos.find(proj -> {
            return proj.getTitulo().equals(tituloProjeto);
        });

        if (projeto == null) {
            IO.write("Nenhum projeto com este titulo foi encontrado.");
            return;
        }
        if (pesquisador.getProjeto(projeto.getTitulo()) != null) {
            IO.write("Pesquisador já cadastrado neste projeto.");
            return;
        }

        pesquisador.addProjeto(projeto);
        projeto.addPesquisador(pesquisador);
        IO.write("Projeto vinculado ao pesquisador.");
    }
}
