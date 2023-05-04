
class ListarPesquisadoresUniversidade extends ControllerOption {

    protected void _display() {
        Lista<Universidade> universidades = Dados.getUniversidades();
        if (universidades.size() == 0) {
            IO.write("Nenhuma universidade cadastradado.");
            return;
        }

        IO.write("Digite o nome da universidade");
        String nomeUniversidade = IO.read();
        Universidade universidade = universidades.find(uni -> {
            return uni.getNome().equals(nomeUniversidade);
        });

        if (universidade == null) {
            IO.write("Universidade não encontrada");
            return;
        }

        Lista<Pesquisador> pesquisadores = universidade.getPesquisadores();
        if (pesquisadores.size() == 0) {
            IO.write("Nenhum pesquisador vinculado a esta universidade.");
            return;
        }
        IO.write("Pesquisadores:");
        for (int i = 0; pesquisadores.size() > i; i++) {
            Pesquisador pesquisador = pesquisadores.get(i);
            IO.write(" - " + pesquisador.getNome() + " Area: " + pesquisador.getArea());
        }
    }
    
}