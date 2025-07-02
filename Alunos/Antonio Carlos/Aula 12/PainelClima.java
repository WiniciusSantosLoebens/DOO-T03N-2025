package ui;

import modelo.DiaClima;
import modelo.RespostaClima;
import servico.ClienteApiClima;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PainelClima extends JPanel {
    private ClienteApiClima clienteApi;

    // Componentes da UI
    private JTextField campoCidade;
    private JButton botaoBuscar;
    private JLabel rotuloLocalizacao;
    private JLabel rotuloTemperatura;
    private JLabel rotuloMinMax;
    private JLabel rotuloCondicoes;
    private JLabel rotuloUmidade;
    private JLabel rotuloPrecipitacao;
    private JLabel rotuloVento;
    private JLabel rotuloIconeClima; // Este JLabel vai segurar o nosso ImageIcon

    public PainelClima() {
        this.clienteApi = new ClienteApiClima();
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- PAINEL DE ENTRADA (NORTE) ---
        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelEntrada.add(new JLabel("Cidade:"));
        campoCidade = new JTextField("Cascavel", 20); // Valor padrão
        painelEntrada.add(campoCidade);
        botaoBuscar = new JButton("Buscar");
        painelEntrada.add(botaoBuscar);
        add(painelEntrada, BorderLayout.NORTH);

        // --- PAINEL DE DADOS (CENTRO) ---
        JPanel painelDados = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        Font fonteDados = new Font("Segoe UI", Font.BOLD, 16);
        Font fonteTitulo = new Font("Segoe UI", Font.PLAIN, 14);
        
        rotuloLocalizacao = new JLabel("Cidade, País");
        rotuloLocalizacao.setFont(new Font("Segoe UI", Font.BOLD, 22));
        
        // O JLabel para o ícone agora é criado sem texto
        rotuloIconeClima = new JLabel(); 
        rotuloIconeClima.setPreferredSize(new Dimension(80, 80)); // Define um tamanho fixo para o ícone
        rotuloIconeClima.setHorizontalAlignment(SwingConstants.CENTER);
        rotuloIconeClima.setVerticalAlignment(SwingConstants.CENTER);

        rotuloTemperatura = new JLabel("-- °C");
        rotuloTemperatura.setFont(new Font("Segoe UI", Font.BOLD, 48));
        
        rotuloCondicoes = new JLabel("Condição do tempo");
        rotuloCondicoes.setFont(fonteTitulo);
        
        rotuloMinMax = new JLabel("Máx: --° / Mín: --°");
        rotuloMinMax.setFont(fonteTitulo);

        rotuloUmidade = new JLabel("Umidade: -- %");
        rotuloUmidade.setFont(fonteTitulo);
        
        rotuloPrecipitacao = new JLabel("Precipitação: -- mm");
        rotuloPrecipitacao.setFont(fonteTitulo);

        rotuloVento = new JLabel("Vento: -- km/h (---)");
        rotuloVento.setFont(fonteTitulo);

        // Adicionando componentes à grade
        gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 0; painelDados.add(rotuloLocalizacao, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridheight = 2; painelDados.add(rotuloIconeClima, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridheight = 1; painelDados.add(rotuloTemperatura, gbc);
        gbc.gridx = 1; gbc.gridy = 2; painelDados.add(rotuloCondicoes, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = 3; painelDados.add(rotuloMinMax, gbc);
        gbc.gridx = 0; gbc.gridy = 4; painelDados.add(rotuloUmidade, gbc);
        gbc.gridx = 0; gbc.gridy = 5; painelDados.add(rotuloPrecipitacao, gbc);
        gbc.gridx = 0; gbc.gridy = 6; painelDados.add(rotuloVento, gbc);

        add(painelDados, BorderLayout.CENTER);

        // --- LISTENERS (Ouvintes de eventos) ---
        botaoBuscar.addActionListener(e -> buscarDadosDoTempo());
        campoCidade.addActionListener(e -> buscarDadosDoTempo());
        
        // Busca o tempo para a cidade padrão ao iniciar
        buscarDadosDoTempo();
    }
    
    private void buscarDadosDoTempo() {
        String cidade = campoCidade.getText().trim();
        if (cidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite o nome de uma cidade.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new SwingWorker<RespostaClima, Void>() {
            @Override
            protected RespostaClima doInBackground() throws Exception {
                return clienteApi.obterClimaParaCidade(cidade);
            }

            @Override
            protected void done() {
                try {
                    RespostaClima clima = get();
                    if (clima != null) {
                        atualizarInterface(clima);
                    } else {
                        JOptionPane.showMessageDialog(PainelClima.this,
                                "Não foi possível obter os dados do tempo para a cidade informada.",
                                "Erro na API", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(PainelClima.this,
                            "Ocorreu um erro ao processar a resposta.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    
    private void atualizarInterface(RespostaClima clima) {
        DiaClima diaAtual = clima.getDias().get(0);

        rotuloLocalizacao.setText(clima.getEnderecoResolvido());
        rotuloTemperatura.setText(String.format("%.1f °C", clima.getCondicoesAtuais().getTemperatura()));
        rotuloCondicoes.setText(clima.getCondicoesAtuais().getCondicoes());
        
        rotuloMinMax.setText(String.format("Máx: %.1f° / Mín: %.1f°", 
                diaAtual.getTempMaxima(), 
                diaAtual.getTempMinima()));
                
        rotuloUmidade.setText(String.format("Umidade: %.0f %%", clima.getCondicoesAtuais().getUmidade()));
        rotuloPrecipitacao.setText(String.format("Precipitação (dia): %.1f mm", diaAtual.getPrecipitacao()));
        
        rotuloVento.setText(String.format("Vento: %.1f km/h (%s)", 
                clima.getCondicoesAtuais().getVelocidadeVento(),
                obterDirecaoVento(clima.getCondicoesAtuais().getDirecaoVento())));

        // Lógica para definir o ícone da imagem
        String nomeIconeApi = clima.getCondicoesAtuais().getIcone();
        ImageIcon icone = carregarIconeClima(nomeIconeApi);
        rotuloIconeClima.setIcon(icone);
    }

    private ImageIcon carregarIconeClima(String nomeIconeApi) {
        String nomeArquivo = "";
        switch (nomeIconeApi.toLowerCase()) {
            case "snow":
                nomeArquivo = "/neve.png";
                break;
            case "rain":
                nomeArquivo = "/chuva.png";
                break;
            case "cloudy":
            case "partly-cloudy-day":
            case "partly-cloudy-night":
            case "fog":
                nomeArquivo = "/nuvem.png";
                break;
            case "clear-day":
            case "clear-night":
            default:
                nomeArquivo = "/sol.png";
                break;
        }

        try {
            // Carrega a imagem como um recurso do classpath
            URL urlImagem = getClass().getResource(nomeArquivo);
            if (urlImagem == null) {
                System.err.println("Não foi possível encontrar o recurso: " + nomeArquivo);
                return null;
            }
            
            ImageIcon iconeOriginal = new ImageIcon(urlImagem);
            
            // Redimensiona a imagem para um tamanho de 64x64 pixels
            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            
            return new ImageIcon(imagemRedimensionada);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String obterDirecaoVento(double graus) {
        if (graus >= 337.5 || graus < 22.5) return "N";
        if (graus < 67.5) return "NE";
        if (graus < 112.5) return "L";
        if (graus < 157.5) return "SE";
        if (graus < 202.5) return "S";
        if (graus < 247.5) return "SO";
        if (graus < 292.5) return "O";
        if (graus < 337.5) return "NO";
        return "";
    }
}
