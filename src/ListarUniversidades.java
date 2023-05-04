
class ListarUniversidades extends ControllerOption{
    protected void _display(){
        Lista<Universidade> universidades = Dados.getUniversidades();

        if(universidades.size() == 0){
            IO.write("Nenhuma universidade cadastrada");
            return;
        }

        IO.write("Universidades:");

        for (Universidade universidade : universidades) {
            IO.write(universidade.getNome());
            if(universidade.getPesquisadores().size() != 0){
                IO.write("Pesquisadores: ", 1);
                for (Pesquisador pesquisador : universidade.getPesquisadores()) {
                    IO.write(pesquisador.getNome(), 2);
                }
            }
        }
    }
}