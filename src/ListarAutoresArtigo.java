
class ListarAutoresArtigo extends ControllerOption {

    protected void _display() {
        Lista<Artigo> artigos = Dados.getArtigos();
        if (artigos.size() == 0) {
            IO.write("Nenhum artigo cadastradado.");
            return;
        }

        IO.write("Digite o titulo do Artigo");
        String tituloArtigo = IO.read();
        Artigo artigo = artigos.find(art -> {
            return art.getTitulo().equals(tituloArtigo);
        });

        if (artigo == null) {
            IO.write("Artigo não encontrado");
            return;
        }
        
        Lista<Pesquisador> pesquisadores = artigo.getPesquisadores();
        if (pesquisadores.size() == 0) {
            IO.write("Nenhum pesquisador vinculado a este artigo.");
            return;
        }
        IO.write("Pesquisadores: ");
        for (int i = 0; i < pesquisadores.size(); i++) {
            Pesquisador pesquisador = pesquisadores.get(i);
            IO.write(" - " + pesquisador.getNome());
        }
    }
    
}