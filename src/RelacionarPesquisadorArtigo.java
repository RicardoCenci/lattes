class RelacionarPesquisadorArtigo extends ControllerOption {
    
    protected void _display() {
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

        IO.write("Título do artigo: ");
        String tituloArtigo = IO.read();
        Lista<Artigo> artigos = Dados.getArtigos();
        Artigo artigo = artigos.find(art -> {
            return art.getTitulo().equals(tituloArtigo);
        });
        if (artigo == null) {
            IO.write("Nenhum artigo com este titulo foi encontrado.");
            return;
        }
        if (pesquisador.getArtigo(artigo.getTitulo()) != null) {
            IO.write("Pesquisador já cadastrado neste artigo.");
            return;
        }

        pesquisador.addArtigo(artigo);
        artigo.addPesquisador(pesquisador);
        IO.write("Artigo vinculado ao pesquisador.");
    }

}