import java.util.*;

public class CarregadorDeDados {

    private static Random random = new Random();
    private static String[] nomesUniversidade = {"UCS", "USP", "UFRJ"};
    private static String[] titulosProjetos = {"Projetinho verao", "Projeto de nave", "Projeto de foguete"};
    private static String[] titulosArtigo = {"Artigo sobre rodas", "Artigos de poder", "Artigos de luxo"};
    private static String[] titulosRevista = {"Mundo estranho", "Turma da mônica", "UOL"};

    public static void carregarDados() {
        gerarArtigo();
        gerarProjeto();
        gerarUniversidades();
        Lista<Universidade> universidades = Dados.getUniversidades();
        for (int i = 0; i < nomesUniversidade.length; i++) {
            gerarPesquisadores(universidades.get(i));
        }
        
    }

    private static void gerarUniversidades() {
        for (int i = 0; i < nomesUniversidade.length; i++) {
            Universidade universidade = new Universidade();
            universidade.setNome(nomesUniversidade[i]);
            Dados.addUniversidades(universidade);
        } 
    }

    // Gera 3 pesquisadores para cada universidade e adiciona até 3 artigos e projetos aleatórios.
    private static void gerarPesquisadores(Universidade universidade) {
        for (int i = 0; i < 3; i++) {
            Pesquisador pesquisador = new Pesquisador();
            pesquisador.setArea("Area de atuacao");
            pesquisador.setNome(gerarNomePessoa());
            pesquisador.setUniversidade(universidade);

            Lista<Artigo> artigos = Dados.getArtigos();
            for (int j = 0; j < random.nextInt(titulosArtigo.length); j++) {
                Artigo artigo = artigos.find(art -> {
                    return art.getTitulo().equals(titulosArtigo[random.nextInt(titulosArtigo.length)]);
                });
                if (artigo == null || pesquisador.getProjeto(artigo.getTitulo()) != null) {
                    continue;
                }
                artigo.addPesquisador(pesquisador);
                pesquisador.addArtigo(artigo);
            }

            Lista<Projeto> projetos = Dados.getProjetos();
            for (int j = 0; j < random.nextInt(titulosProjetos.length); j++) {
                Projeto projeto = projetos.get(random.nextInt(titulosProjetos.length));
                if (projeto == null || pesquisador.getProjeto(projeto.getTitulo()) != null) {
                    continue;
                }
                projeto.addPesquisador(pesquisador);
                pesquisador.addProjeto(projeto);
            }            
            universidade.addPesquisador(pesquisador);
            Dados.addPesquisador(pesquisador);
        }
    }

    private static void gerarArtigo() {
        for (int i = 0; i < titulosArtigo.length; i++) {
            Artigo artigo = new Artigo();
            artigo.setAnoPublicacao(2023);
            artigo.setTitulo(titulosArtigo[i]);;
            artigo.setTituloRevista(titulosRevista[i]);
            Dados.addArtigo(artigo);
        }
    }

    private static void gerarProjeto() {
        for (int i = 0; i < titulosProjetos.length; i++) {
            Projeto projeto = new Projeto();
            projeto.setDataFinal(gerarDataFim());
            projeto.setDataInicio(gerarDataInicio());
            projeto.setDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            projeto.setTitulo(titulosProjetos[i]);
            Dados.addProjeto(projeto);
        }
    }

    public static String gerarDataInicio() {
        return "10/01/2021";
    }
    
    public static String gerarDataFim() {
        return "11/12/2022";
    }
  
    public static String gerarNomePessoa() {
        String[] primeiroNome = {"Joao", "Maria", "Jose", "Anonio", "Davi", "Vinicius", "Ricardo", "Emilio"};
        String[] segundoNome = {"Santos", "Silva", "Cenci", "Fabris", "Souza", "Ferreira", "Rodrigues", "Pereira"};
        return primeiroNome[random.nextInt(primeiroNome.length)] + " " + segundoNome[random.nextInt(segundoNome.length)];
    }

    public static String gerarNomeUniversidade() {
        return nomesUniversidade[random.nextInt(nomesUniversidade.length)];
    }

}
