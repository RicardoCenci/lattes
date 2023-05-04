
class CadastroPesquisador extends ControllerOption {

    protected void _display(){
        IO.write("Cadastro de Pesquisador");
        Pesquisador pesquisador = new Pesquisador();

        IO.write("Nome do pesquisador");
        pesquisador.setNome(IO.read());
        IO.write("Area do pesquisador");
        pesquisador.setArea(IO.read());

        IO.write("Universidade");

        String nomeUniveridade = IO.read();

        Lista<Universidade> universidades = Dados.getUniversidades();

        Universidade universidade = universidades.find(uni -> {
            return uni.getNome().equals(nomeUniveridade);
        });

        if (universidade == null) {
            IO.write("Universidade não encontrada, criando nova universidade");
            universidade = new Universidade();
            universidade.setNome(nomeUniveridade);
            universidade.addPesquisador(pesquisador);
            Dados.addUniversidades(universidade);
        } else {
            universidade.addPesquisador(pesquisador);
        }
        
        pesquisador.setUniversidade(universidade);
        Dados.addPesquisador(pesquisador);
        IO.write("Pesquisador cadastrado com sucesso");
    }
}