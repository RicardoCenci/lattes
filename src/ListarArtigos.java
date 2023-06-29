
class ListarArtigos extends ControllerOption{
    protected void _display(){
        Lista<Artigo> artigos = Dados.getArtigos();

        if(artigos.size() == 0){
            IO.write("Nenhum artigo cadastrado");
            return;
        }

        IO.write("Artigos:");

        for (Artigo artigo : artigos) {
            IO.write(artigo.getTitulo());
            IO.write("Descricao: " + artigo.getTituloRevista(), 1);
            IO.write("Ano Publicacao: " + artigo.getAnoPublicacao(), 1);

            if(artigo.getPesquisadores().size() != 0){
                IO.write("Pesquisadores: ", 1);
                for (Pesquisador pesquisador : artigo.getPesquisadores()) {
                    IO.write(pesquisador.getNome(), 2);
                }
                IO.write("");
            }
        }
    }
}