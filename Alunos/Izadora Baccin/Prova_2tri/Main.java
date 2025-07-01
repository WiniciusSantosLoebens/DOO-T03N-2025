import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
    private static final String DADOS_FILE = "dados.json"; // Nome do arquivo para persistência

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Usuario usuario = null;
        GerenciadorSeries gerenciador = new GerenciadorSeries(); // Necessário para gerenciar séries

        // ele carrega os dados no inicio do programa
        try {
            JSONObject dadosCarregados = carregarDados();
            if (dadosCarregados != null) {
                if (dadosCarregados.has("usuario") && !dadosCarregados.isNull("usuario")) {
                    JSONObject userJson = dadosCarregados.getJSONObject("usuario");
                    usuario = new Usuario(userJson.optString("nome", "Usuário Padrão"), userJson.optString("apelido", "Usuário Padrão"));
                    System.out.println("Bem-vindo de volta, " + usuario.getApelido() + "!");
                } else {
                    System.out.println("Nenhum usuário encontrado nos dados salvos ou dados de usuário inválidos. Por favor, cadastre-se.");
                    usuario = Usuario.cadastrarUsuario(scan); // Cadastra se não houver
                }

                // carrega as series
                if (dadosCarregados.has("favoritas") && !dadosCarregados.isNull("favoritas")) {
                    gerenciador.setFavoritas(parseSeriesFromJsonArray(dadosCarregados.getJSONArray("favoritas")));
                }
                if (dadosCarregados.has("assistidas") && !dadosCarregados.isNull("assistidas")) {
                    gerenciador.setAssistidas(parseSeriesFromJsonArray(dadosCarregados.getJSONArray("assistidas")));
                }
                if (dadosCarregados.has("desejaAssistir") && !dadosCarregados.isNull("desejaAssistir")) {
                    gerenciador.setDesejaAssistir(parseSeriesFromJsonArray(dadosCarregados.getJSONArray("desejaAssistir")));
                }
                System.out.println("Dados carregados com sucesso.");
            } else {
                System.out.println("Nenhum dado salvo encontrado. Iniciando novo sistema.");
                usuario = Usuario.cadastrarUsuario(scan); // Cadastra um novo usuário se não houver dados
            }
        } catch (IOException | JSONException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            System.out.println("Iniciando com dados vazios. Por favor, cadastre um usuário.");
            usuario = Usuario.cadastrarUsuario(scan); // Garante que um usuário seja criado mesmo com erro
        }

        // menu
        int opcao = -1;
        do {
            exibirMenuPrincipal();
            try {
                opcao = Integer.parseInt(scan.nextLine());
                switch (opcao) {
                    case 1:
                        gerenciarUsuario(scan, usuario); //gerenciamento do usuario
                        break;
                    case 2:
                        buscarSeriePorNomeMenu(scan, gerenciador); // busca a serie por nome e adiciona as listas
                        break;
                    case 3:
                        verListasDeSeries(scan, gerenciador); // ve as listas das series e remove
                        break;
                    case 4:
                        ordenarListas(scan, gerenciador); // ordena as listas
                        break;
                    case 5:
                        salvarDados(usuario, gerenciador); // salva os dados e sai
                        System.out.println("Dados salvos. Saindo do sistema. Até mais, " + usuario.getApelido() + "!");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção entre 1-5.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            } catch (Exception e) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }
            if (opcao != 5) {
                System.out.println("\nPressione Enter para continuar...");
                scan.nextLine();
            }
        } while (opcao != 5); // o loop continua ate o usuario colocar 5
        scan.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n===== Menu Principal =====\n");
        System.out.println("1- ⭐Gerenciar Usuário ");
        System.out.println("2- 🔍 Buscar série por nome e adicionar a listas");
        System.out.println("3- 📚 Ver e Gerenciar minhas listas de séries");
        System.out.println("4- 🧭 Ordenar listas");
        System.out.println("5- 💾 Salvar e sair do sistema");
        System.out.print("O que deseja fazer? [1-5]: ");
    }

    private static JSONObject carregarDados() throws IOException, JSONException {
        File file = new File(DADOS_FILE);
        if (!file.exists() || file.length() == 0) {
            return null;
        }

        StringBuilder content = new StringBuilder();
        try (FileReader reader = new FileReader(file)) {
            int character;
            while ((character = reader.read()) != -1) {
                content.append((char) character);
            }
        }
        return new JSONObject(content.toString());
    }

    private static void salvarDados(Usuario usuario, GerenciadorSeries gerenciador) {
        JSONObject dadosParaSalvar = new JSONObject();
        try {
            // aqui ele salva o nome e apel do usuario
            JSONObject userJson = new JSONObject();
            userJson.put("nome", usuario.getNome());
            userJson.put("apelido", usuario.getApelido());
            dadosParaSalvar.put("usuario", userJson);

            // aqui salva as listas das series
            dadosParaSalvar.put("favoritas", createJsonArrayFromSeriesList(gerenciador.getFavoritas()));
            dadosParaSalvar.put("assistidas", createJsonArrayFromSeriesList(gerenciador.getAssistidas()));
            dadosParaSalvar.put("desejaAssistir", createJsonArrayFromSeriesList(gerenciador.getDesejaAssistir()));

            try (FileWriter fileWriter = new FileWriter(DADOS_FILE)) {
                fileWriter.write(dadosParaSalvar.toString(4));
                System.out.println("Dados salvos com sucesso em " + DADOS_FILE);
            }
        } catch (IOException | JSONException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // transforma as listas em JSON
    private static JSONArray createJsonArrayFromSeriesList(List<Serie> series) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Serie serie : series) {
            JSONObject serieJson = new JSONObject();
            serieJson.put("id", serie.getId());
            serieJson.put("nome", serie.getNome());
            serieJson.put("idioma", serie.getIdioma());
            serieJson.put("generos", new JSONArray(serie.getGeneros()));
            serieJson.putOpt("notaGeral", serie.getNotaGeral()); // opt para nulos
            serieJson.put("status", serie.getStatus());
            serieJson.putOpt("dataEstreia", serie.getDataEstreia() != null ? serie.getDataEstreia().toString() : null);
            serieJson.putOpt("dataFim", serie.getDataFim() != null ? serie.getDataFim().toString() : null);
            serieJson.putOpt("emissora", serie.getEmissora());
            jsonArray.put(serieJson);
        }
        return jsonArray;
    }

    private static List<Serie> parseSeriesFromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Serie> series = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject serieJson = jsonArray.getJSONObject(i);

            int id = serieJson.getInt("id");
            String nome = serieJson.getString("nome");
            String idioma = serieJson.getString("idioma");

            List<String> generos = new ArrayList<>();
            JSONArray generosJson = serieJson.optJSONArray("generos");
            if (generosJson != null) {
                for (int j = 0; j < generosJson.length(); j++) {
                    generos.add(generosJson.getString(j));
                }
            }

            Double notaGeral = serieJson.optDouble("notaGeral", Double.NaN); // Use NaN para representar a ausência
            if (Double.isNaN(notaGeral) && !serieJson.has("notaGeral")) { // Se não tem a chave, é nulo
                notaGeral = null;
            }

            String status = serieJson.optString("status", "N/A");

            LocalDate dataEstreia = null;
            if (serieJson.has("dataEstreia") && !serieJson.isNull("dataEstreia")) {
                try {
                    dataEstreia = LocalDate.parse(serieJson.getString("dataEstreia"));
                } catch (DateTimeParseException e) {
                    System.err.println("Erro ao parsear data de estreia para série " + nome + ": " + serieJson.getString("dataEstreia"));
                }
            }

            LocalDate dataFim = null;
            if (serieJson.has("dataFim") && !serieJson.isNull("dataFim")) {
                try {
                    dataFim = LocalDate.parse(serieJson.getString("dataFim"));
                } catch (DateTimeParseException e) {
                    System.err.println("Erro ao parsear data de fim para série " + nome + ": " + serieJson.getString("dataFim"));
                }
            }

            String emissora = serieJson.optString("emissora", null); // optString retorna null se não houver ou for nulo

            series.add(new Serie(id, nome, idioma, generos, notaGeral, status, dataEstreia, dataFim, emissora));
        }
        return series;
    }

    // funcoes do menu
    private static void gerenciarUsuario(Scanner scan, Usuario usuario) {
        System.out.println("\n--- Gerenciar Usuário ---");
        System.out.println("Seu usuário atual é: " + usuario.toString());
        System.out.print("Deseja alterar seu nome ou apelido? (s/n): ");
        String resposta = scan.nextLine().trim().toLowerCase();
        if (resposta.equals("s")) {
            System.out.print("Digite o novo nome completo: ");
            usuario.setNome(scan.nextLine());
            System.out.print("Digite o novo apelido (deixe em branco para usar o nome): ");
            String novoApelido = scan.nextLine();
            usuario.setApelido(novoApelido.isEmpty() ? usuario.getNome() : novoApelido);
            System.out.println("Usuário atualizado para: " + usuario.toString());
        } else if (!resposta.equals("n")) {
            System.out.println("Resposta inválida. Nenhuma alteração no usuário foi feita.");
        }
    }

    private static void buscarSeriePorNomeMenu(Scanner scan, GerenciadorSeries gerenciador) {
        System.out.print("Digite o nome da série que deseja buscar: ");
        String nomeBusca = scan.nextLine();

        Optional<JSONArray> resultadosOpt = TvMazeAPI.buscarSeriesPorNome(nomeBusca);

        if (resultadosOpt.isEmpty() || resultadosOpt.get().length() == 0) {
            System.out.println("Nenhuma série encontrada para '" + nomeBusca + "'.");
            return;
        }

        JSONArray resultados = resultadosOpt.get();
        List<Serie> seriesEncontradas = new ArrayList<>();

        System.out.println("\n--- Resultados da Busca ---");
        for (int i = 0; i < resultados.length(); i++) {
            try {
                JSONObject item = resultados.getJSONObject(i);
                JSONObject show = item.getJSONObject("show");

                int id = show.getInt("id");
                String nome = show.optString("name", "N/A");
                String idioma = show.optString("language", "N/A");

                List<String> generos = new ArrayList<>();
                JSONArray genresJson = show.optJSONArray("genres");
                if (genresJson != null) {
                    for (int j = 0; j < genresJson.length(); j++) {
                        generos.add(genresJson.getString(j));
                    }
                }

                Double notaGeral = null;
                if (show.has("rating") && !show.isNull("rating")) {
                    JSONObject rating = show.getJSONObject("rating");
                    notaGeral = rating.optDouble("average", Double.NaN);
                    if (Double.isNaN(notaGeral)) notaGeral = null;
                }

                String status = show.optString("status", "N/A");

                LocalDate dataEstreia = null;
                if (show.has("premiered") && !show.isNull("premiered")) {
                    try {
                        dataEstreia = LocalDate.parse(show.getString("premiered"));
                    } catch (DateTimeParseException e) {
                        // aqui ele ignora se a data for null
                    }
                }

                LocalDate dataFim = null;
                if (show.has("ended") && !show.isNull("ended")) {
                    try {
                        dataFim = LocalDate.parse(show.getString("ended"));
                    } catch (DateTimeParseException e) {
                    }
                }

                String emissora = null;
                if (show.has("network") && !show.isNull("network")) {
                    JSONObject network = show.getJSONObject("network");
                    emissora = network.optString("name", "N/A");
                } else if (show.has("webChannel") && !show.isNull("webChannel")) { // Algumas séries são de streaming
                    JSONObject webChannel = show.getJSONObject("webChannel");
                    emissora = webChannel.optString("name", "N/A (Web Channel)");
                }

                Serie serie = new Serie(id, nome, idioma, generos, notaGeral, status, dataEstreia, dataFim, emissora);
                seriesEncontradas.add(serie);
                System.out.println((i + 1) + ". " + serie.toString());

            } catch (JSONException e) {
                System.err.println("Erro ao parsear resultado da série: " + e.getMessage());
            }
        }

        if (!seriesEncontradas.isEmpty()) {
            System.out.print("\nDigite o número da série que deseja adicionar a uma lista ou '0' para voltar: ");
            try {
                int escolha = Integer.parseInt(scan.nextLine());
                if (escolha > 0 && escolha <= seriesEncontradas.size()) {
                    Serie serieSelecionada = seriesEncontradas.get(escolha - 1);
                    menuAdicionarSerie(scan, gerenciador, serieSelecionada);
                } else if (escolha == 0) {
                    System.out.println("Voltando ao menu principal.");
                } else {
                    System.out.println("Escolha inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private static void menuAdicionarSerie(Scanner scan, GerenciadorSeries gerenciador, Serie serie) {
        System.out.println("\n--- O que deseja fazer com '" + serie.getNome() + "'? ---");
        System.out.println("1. Adicionar à lista de Favoritas");
        System.out.println("2. Adicionar à lista de Já Assistidas");
        System.out.println("3. Adicionar à lista de Desejo Assistir");
        System.out.println("0. Voltar ao menu anterior");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = Integer.parseInt(scan.nextLine());
            switch (opcao) {
                case 1:
                    gerenciador.adicionarSerieFavorita(serie);
                    break;
                case 2:
                    gerenciador.adicionarSerieAssistida(serie);
                    break;
                case 3:
                    gerenciador.adicionarSerieDesejaAssistir(serie);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
        }
    }

    private static void verListasDeSeries(Scanner scan, GerenciadorSeries gerenciador) {
        System.out.println("\n--- Ver e Gerenciar Listas de Séries ---");
        System.out.println("1. Séries Favoritas");
        System.out.println("2. Séries Já Assistidas");
        System.out.println("3. Séries Que Desejo Assistir");
        System.out.println("0. Voltar");
        System.out.print("Qual lista deseja ver? [1-3]: ");

        List<Serie> listaAlvo = null;
        String nomeLista = "";

        try {
            int escolhaLista = Integer.parseInt(scan.nextLine());
            if (escolhaLista == 0) {
                System.out.println("Voltando...");
                return;
            }

            switch (escolhaLista) {
                case 1:
                    listaAlvo = gerenciador.getFavoritas();
                    nomeLista = "Favoritas";
                    break;
                case 2:
                    listaAlvo = gerenciador.getAssistidas();
                    nomeLista = "Assistidas";
                    break;
                case 3:
                    listaAlvo = gerenciador.getDesejaAssistir();
                    nomeLista = "Que Desejo Assistir";
                    break;
                default:
                    System.out.println("Opção de lista inválida.");
                    return;
            }

            if (listaAlvo.isEmpty()) {
                System.out.println("A lista de " + nomeLista.toLowerCase() + " está vazia.");
                return;
            }

            System.out.print("Deseja ver detalhes completos? (s/n): ");
            boolean exibirDetalhes = scan.nextLine().trim().toLowerCase().equals("s");

            // mostra a lista
            System.out.println("\n--- Minhas Séries " + nomeLista + " ---");
            for (int i = 0; i < listaAlvo.size(); i++) {
                System.out.println((i + 1) + ". " + (exibirDetalhes ? listaAlvo.get(i).toString() : listaAlvo.get(i).toShortString()));
            }

            System.out.print("\nDeseja remover uma série desta lista? (s/n): ");
            String respostaRemover = scan.nextLine().trim().toLowerCase();
            if (respostaRemover.equals("s")) {
                removerSerieDeLista(scan, gerenciador, listaAlvo, nomeLista);
            } else if (!respostaRemover.equals("n")) {
                System.out.println("Resposta inválida. Nenhuma série será removida.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
        }
    }

    private static void removerSerieDeLista(Scanner scan, GerenciadorSeries gerenciador, List<Serie> listaAlvo, String nomeLista) {
        if (listaAlvo.isEmpty()) {
            System.out.println("A lista de " + nomeLista.toLowerCase() + " está vazia. Nada para remover.");
            return;
        }

        System.out.println("\n--- Remover Série da lista de " + nomeLista + " ---");
        for (int i = 0; i < listaAlvo.size(); i++) {
            System.out.println((i + 1) + ". " + listaAlvo.get(i).toShortString());
        }

        System.out.print("Digite o número da série que deseja remover ou '0' para voltar: ");
        try {
            int escolha = Integer.parseInt(scan.nextLine());
            if (escolha > 0 && escolha <= listaAlvo.size()) {
                Serie serieParaRemover = listaAlvo.get(escolha - 1);
                boolean removido = false;
                switch (nomeLista) {
                    case "Favoritas":
                        removido = gerenciador.removerSerieFavorita(serieParaRemover);
                        break;
                    case "Assistidas":
                        removido = gerenciador.removerSerieAssistida(serieParaRemover);
                        break;
                    case "Que Desejo Assistir":
                        removido = gerenciador.removerSerieDesejaAssistir(serieParaRemover);
                        break;
                }
                if (removido) {
                    System.out.println("Série removida com sucesso!");
                } else {
                    System.out.println("Erro ao remover série. Série não encontrada ou outro problema.");
                }
            } else if (escolha == 0) {
                System.out.println("Voltando...");
            } else {
                System.out.println("Escolha inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
        }
    }

    private static void ordenarListas(Scanner scan, GerenciadorSeries gerenciador) {
        System.out.println("\n--- Ordenar Listas ---");
        System.out.println("1. Séries Favoritas");
        System.out.println("2. Séries Já Assistidas");
        System.out.println("3. Séries Que Desejo Assistir");
        System.out.println("0. Voltar");
        System.out.print("Qual lista deseja ordenar? [1-3]: ");

        List<Serie> listaAlvo = null;
        String nomeLista = "";
        try {
            int escolhaLista = Integer.parseInt(scan.nextLine());
            if (escolhaLista == 0) {
                System.out.println("Voltando...");
                return;
            }

            switch (escolhaLista) {
                case 1:
                    listaAlvo = gerenciador.getFavoritas();
                    nomeLista = "Favoritas";
                    break;
                case 2:
                    listaAlvo = gerenciador.getAssistidas();
                    nomeLista = "Já Assistidas";
                    break;
                case 3:
                    listaAlvo = gerenciador.getDesejaAssistir();
                    nomeLista = "Que Desejo Assistir";
                    break;
                default:
                    System.out.println("Opção de lista inválida.");
                    return;
            }

            if (listaAlvo.isEmpty()) {
                System.out.println("A lista de " + nomeLista.toLowerCase() + " está vazia. Nada para ordenar.");
                return;
            }

            System.out.println("\n--- Critério de Ordenação para " + nomeLista + " ---");
            System.out.println("1. Por Nome (A-Z)");
            System.out.println("2. Por Nota Geral (Maior para Menor)");
            System.out.println("3. Por Estado (Ex: Running, Ended)");
            System.out.println("4. Por Data de Estreia (Mais Antiga para Mais Recente)");
            System.out.println("0. Voltar");
            System.out.print("Escolha o critério: [1-4]: ");

            int escolhaCriterio = Integer.parseInt(scan.nextLine());
            switch (escolhaCriterio) {
                case 1:
                    gerenciador.ordenarListaPorNome(listaAlvo);
                    System.out.println("Lista de " + nomeLista + " ordenada por nome.");
                    break;
                case 2:
                    gerenciador.ordenarListaPorNotaGeral(listaAlvo);
                    System.out.println("Lista de " + nomeLista + " ordenada por nota geral.");
                    break;
                case 3:
                    gerenciador.ordenarListaPorEstado(listaAlvo);
                    System.out.println("Lista de " + nomeLista + " ordenada por estado.");
                    break;
                case 4:
                    gerenciador.ordenarListaPorDataEstreia(listaAlvo);
                    System.out.println("Lista de " + nomeLista + " ordenada por data de estreia.");
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção de critério inválida.");
                    break;
            }

            System.out.print("\nDeseja ver a lista de " + nomeLista + " ordenada? (s/n): ");
            boolean exibirOrdenada = scan.nextLine().trim().toLowerCase().equals("s");
            if (exibirOrdenada) {
                // Pergunta se quer ver detalhes completos ou resumidos após a ordenação
                System.out.print("Deseja ver detalhes completos? (s/n): ");
                boolean exibirDetalhesPosOrdem = scan.nextLine().trim().toLowerCase().equals("s");

                switch (escolhaLista) {
                    case 1:
                        gerenciador.mostrarListaFavoritas(exibirDetalhesPosOrdem);
                        break;
                    case 2:
                        gerenciador.mostrarListaAssistidas(exibirDetalhesPosOrdem);
                        break;
                    case 3:
                        gerenciador.mostrarListaDesejaAssistir(exibirDetalhesPosOrdem);
                        break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
        }
    }
}