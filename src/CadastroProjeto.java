
class CadastroProjeto extends ControllerOption{

        protected void _display(){
            IO.write("Cadastro de Projeto");
            Projeto projeto = new Projeto();

            IO.write("Titulo do projeto");
            projeto.setTitulo(IO.read());
            IO.write("Descricao do projeto");
            projeto.setDescricao(IO.read());

            IO.write("Data de inicio do projeto");
            projeto.setDataInicio(IO.read());
            IO.write("Data de termino do projeto");
            projeto.setDataFinal(IO.read());

            Dados.addProjeto(projeto);

            IO.write("Projeto cadastrado com sucesso");
        }
        
}