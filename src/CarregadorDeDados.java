import java.util.*;
import java.io.*;

public class CarregadorDeDados {

    private BufferedReader leitor;

    public void carregarDados() {
        try {
            FileReader fr = new FileReader("src/Lattes-Reestruturado.txt");
			this.leitor = new BufferedReader(fr);
			boolean acabou = false;
			while (!acabou) {
                String linha = this.leitor.readLine();
                if (linha != null) {
                    if (linha.contains("#Pesquisador")) {
                        String nome = this.getValueLine();
                        String nomeUniversidade = this.getValueLine();
    
                        Pesquisador pesquisador = new Pesquisador();
                        pesquisador.setNome(nome);
    
                        Lista<Universidade> universidades = Dados.getUniversidades();
    
                        Universidade universidade = universidades.find(uni -> {
                            return uni.getNome().equals(nomeUniversidade);
                        });
                    
                        if (universidade == null) {
                            IO.write("Universidade não encontrada, criando nova universidade");
                            universidade = new Universidade();
                            universidade.setNome(nomeUniversidade);
                            universidade.addPesquisador(pesquisador);
                            Dados.addUniversidades(universidade);
                        } else {
                            universidade.addPesquisador(pesquisador);
                        }
                    
                        pesquisador.setUniversidade(universidade);
                        Dados.addPesquisador(pesquisador);
                        IO.write("Pesquisador criado.");
                    }
            
                    if (linha.contains("#Projeto")) {
                        Projeto projeto = new Projeto();
            
                        String dataInicio = this.getValueLine();
                        projeto.setDataInicio(dataInicio);
                        String dataFinal = this.getValueLine();
                        projeto.setDataFinal(dataFinal);
                        String titulo = this.getValueLine();
                        projeto.setTitulo(titulo);
                        String descricao = this.getValueLine();
                        projeto.setDescricao(descricao);
                
                        String pesquisadoresString = this.getValueLine();
                        String[] pesqusiadoresArray = pesquisadoresString.split(";");
                        Lista<Pesquisador> pesquisadores = Dados.getPesquisadores();
                        for (int j = 0; j < pesqusiadoresArray.length; j++) {
                            String pesquisadorNome = pesqusiadoresArray[j].trim();
                            Pesquisador pesquisador = pesquisadores.find(pesq -> {
                                return pesq.getNome().equals(pesquisadorNome);
                            });
                            if (pesquisador != null) {
                                pesquisador.addProjeto(projeto);
                                projeto.addPesquisador(pesquisador);
                                IO.write("Vinculando pesquisador " + pesquisadorNome);
                            } 
                        }
                        Dados.addProjeto(projeto);
                        IO.write("Projeto criado.");
                    }
            
                    if (linha.contains("#Artigo")) {
                        Artigo artigo = new Artigo();
                        artigo.setTitulo(this.getValueLine());
                        artigo.setAnoPublicacao(Integer.parseInt(this.getValueLine()));
                        artigo.setTituloRevista(this.getValueLine());
                        Lista<Pesquisador> pesquisadores = Dados.getPesquisadores();
                        String[] autoresArray = this.getValueLine().split(";");
                        
                        for (int i = 0; i < autoresArray.length; i++) {
                            String autorNome = autoresArray[i].trim();
                            Pesquisador pesquisador = pesquisadores.find(pesq -> {
                                return pesq.getNome().equals(autorNome);
                            });
                            if (pesquisador != null) {
                                pesquisador.addArtigo(artigo);
                                artigo.addPesquisador(pesquisador);
                                IO.write("Vinculando autor " + autorNome);
                            }
                        }
                        Dados.addArtigo(artigo);
                        IO.write("Artigo criado.");
                    }
                } else {
                    acabou = true;
                }
			}
			fr.close();
        } catch (Exception e) {
            IO.write("Arquivo carregado.");
        }
    }

    private String getValueLine() throws Exception {
        try {
            String linha = this.leitor.readLine();
            int start = linha.indexOf(":") + 1;
            return linha.substring(start).trim();
        } catch (FileNotFoundException e) {
            throw new Exception("Erro ao buscar linha.");
        } catch (IOException e) {
            throw new Exception("Erro ao buscar linha.");
        } 
    }
    
}
