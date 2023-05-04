
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
class ListarPesquisadoresProjetosFinalizados extends ControllerOption{
    protected void _display(){
        Lista<Projeto> projetos = Dados.getProjetos();
        if (projetos.size() == 0) {
            IO.write("Nenhum projeto cadastradado.");
            return;
        }

        IO.write("Nome do pesquisador ");
        String nomePesquisador = IO.read();
        Pesquisador pesquisador = Dados.getPesquisadores().find(pesq -> {
            return pesq.getNome().equals(nomePesquisador);
        });

        if (pesquisador == null) {
            IO.write("Pesquisador não encontrado");
            return;
        }

        Date hoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Lista<Projeto> projetosFinalizados = pesquisador.getProjetos().filter(projeto -> {
            try {
                Date dataFinal = formatter.parse(projeto.getDataFinal());

                return hoje.after(dataFinal);
            } catch (ParseException e) {
                return false;
            }
        });

        IO.write("Projetos finalizados: ");

        if(projetosFinalizados.size() == 0){
            IO.write("Nenhum projeto finalizado");
            return;
        }

        projetosFinalizados.forEach(projeto -> {
            IO.write(projeto.getTitulo());
        });

    }
}