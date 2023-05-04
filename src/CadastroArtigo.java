

class CadastroArtigo extends ControllerOption {

    protected void _display(){
        IO.write("Cadastro de Artigo");
        Artigo artigo = new Artigo();

        IO.write("Titulo do artigo");
        artigo.setTitulo(IO.read());
        IO.write("Ano de publicacao");
        artigo.setAnoPublicacao(Integer.parseInt(IO.read()));

        IO.write("Titulo da revista");
        artigo.setTituloRevista(IO.read());

        Dados.addArtigo(artigo);

        IO.write("Artigo cadastrado com sucesso");
    }

}