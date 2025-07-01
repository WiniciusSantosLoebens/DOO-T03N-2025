package ui;

import modelo.DiaClima;
import modelo.RespostaClima;
import servico.ClienteApiClima;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PainelClima extends JPanel {
    private ClienteApiClima clienteApi;
    private JTextField campoCidade;
    private JButton botaoBuscar;
    private JLabel rotuloLocalizacao;
    private JLabel rotuloTemperatura;
    private JLabel rotuloCondicoes;
    private JLabel rotuloIconeClima;
    private JLabel rotuloMinMax, rotuloUmidade, rotuloPrecipitacao, rotuloVento;

    public PainelClima() {
        this.clienteApi = new ClienteApiClima();

        setLayout(new BorderLayout(0, 20));
        setBackground(UIColorsTeal.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(15, 25, 20, 25));

        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        painelBusca.setOpaque(false);

        campoCidade = new JTextField("São Paulo", 20);
        campoCidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoCidade.setBorder(new RoundBorder(UIColorsTeal.BORDER_COLOR, 15));
        campoCidade.setBackground(UIColorsTeal.CARD_BACKGROUND);
        
        botaoBuscar = new JButton();
        customizeButton(botaoBuscar); 

        painelBusca.add(new JLabel("Cidade:"));
        painelBusca.add(campoCidade);
        painelBusca.add(botaoBuscar);
        add(painelBusca, BorderLayout.NORTH);

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setOpaque(false);

        JPanel painelHeader = new JPanel(new BorderLayout(15, 0));
        painelHeader.setOpaque(false);

        JPanel painelTextoHeader = new JPanel();
        painelTextoHeader.setLayout(new BoxLayout(painelTextoHeader, BoxLayout.Y_AXIS));
        painelTextoHeader.setOpaque(false);

        rotuloLocalizacao = new JLabel("Buscando...");
        rotuloLocalizacao.setFont(new Font("Segoe UI", Font.BOLD, 32));
        rotuloLocalizacao.setForeground(UIColorsTeal.TEXT_PRIMARY);

        rotuloTemperatura = new JLabel("--°");
        rotuloTemperatura.setFont(new Font("Segoe UI Light", Font.PLAIN, 72));
        rotuloTemperatura.setForeground(UIColorsTeal.TEXT_PRIMARY);

        rotuloCondicoes = new JLabel("Condição");
        rotuloCondicoes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        rotuloCondicoes.setForeground(UIColorsTeal.TEXT_SECONDARY);

        painelTextoHeader.add(rotuloLocalizacao);
        painelTextoHeader.add(Box.createRigidArea(new Dimension(0, 5)));
        painelTextoHeader.add(rotuloTemperatura);
        painelTextoHeader.add(rotuloCondicoes);

        rotuloIconeClima = new JLabel();
        rotuloIconeClima.setHorizontalAlignment(SwingConstants.CENTER);
        
        painelHeader.add(painelTextoHeader, BorderLayout.CENTER);
        painelHeader.add(rotuloIconeClima, BorderLayout.EAST);
        
        JPanel painelDetalhes = new JPanel(new GridLayout(2, 2, 25, 20));
        painelDetalhes.setOpaque(false);
        painelDetalhes.setBorder(BorderFactory.createEmptyBorder(25, 10, 10, 10));

        rotuloMinMax = criarLabelDetalhe("MÁX / MÍN", "--° / --°");
        rotuloUmidade = criarLabelDetalhe("UMIDADE", "-- %");
        rotuloPrecipitacao = criarLabelDetalhe("PRECIPITAÇÃO", "-- mm");
        rotuloVento = criarLabelDetalhe("VENTO", "-- km/h");

        painelDetalhes.add(rotuloMinMax);
        painelDetalhes.add(rotuloUmidade);
        painelDetalhes.add(rotuloPrecipitacao);
        painelDetalhes.add(rotuloVento);
        
        painelConteudo.add(painelHeader);
        painelConteudo.add(Box.createVerticalStrut(20));
        painelConteudo.add(new JSeparator());
        painelConteudo.add(painelDetalhes);
        
        add(painelConteudo, BorderLayout.CENTER);
        
        botaoBuscar.addActionListener(e -> buscarDadosDoTempo());
        campoCidade.addActionListener(e -> buscarDadosDoTempo());
        buscarDadosDoTempo();
    }
    
    private void customizeButton(JButton button) {
        button.setText("Buscar"); 
        button.setBackground(UIColorsTeal.ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setMargin(new Insets(8, 25, 8, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private JLabel criarLabelDetalhe(String titulo, String valorInicial) {
        JLabel label = new JLabel("<html><div style='text-transform: uppercase; font-size:9px; color:rgb(120,134,142);'>" + titulo + "</div>" +
                                 "<div style='font-size:16px; color:rgb(38,50,56);'>" + valorInicial + "</div></html>");
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        return label;
    }
    private String formatarDetalhe(String titulo, String valor) {
        return "<html><div style='text-transform: uppercase; font-size:9px; color:rgb(120,134,142);'>" + titulo + "</div>" +
               "<div style='font-size:16px; color:rgb(38,50,56);'>" + valor + "</div></html>";
    }
    private void buscarDadosDoTempo() {
        String cidade = campoCidade.getText().trim();
        if (cidade.isEmpty()) return;
        new SwingWorker<RespostaClima, Void>() {
            @Override protected RespostaClima doInBackground() throws Exception {
                return clienteApi.obterClimaParaCidade(cidade);
            }
            @Override protected void done() {
                try {
                    RespostaClima clima = get();
                    if (clima != null) atualizarInterface(clima);
                    else JOptionPane.showMessageDialog(PainelClima.this, "Não foi possível obter os dados do tempo.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(PainelClima.this, "Ocorreu um erro na busca.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }
    private void atualizarInterface(RespostaClima clima) {
        DiaClima diaAtual = clima.getDias().get(0);
        rotuloLocalizacao.setText(clima.getEnderecoResolvido());
        rotuloTemperatura.setText(String.format("%.0f°", clima.getCondicoesAtuais().getTemperatura()));
        rotuloCondicoes.setText(clima.getCondicoesAtuais().getCondicoes());
        rotuloMinMax.setText(formatarDetalhe("MÁX / MÍN", String.format("%.0f° / %.0f°", diaAtual.getTempMaxima(), diaAtual.getTempMinima())));
        rotuloUmidade.setText(formatarDetalhe("UMIDADE", String.format("%.0f %%", clima.getCondicoesAtuais().getUmidade())));
        rotuloPrecipitacao.setText(formatarDetalhe("PRECIPITAÇÃO", String.format("%.1f mm", diaAtual.getPrecipitacao())));
        rotuloVento.setText(formatarDetalhe("VENTO", String.format("%.1f km/h (%s)", clima.getCondicoesAtuais().getVelocidadeVento(), obterDirecaoVento(clima.getCondicoesAtuais().getDirecaoVento()))));
        String nomeIconeApi = clima.getCondicoesAtuais().getIcone();
        ImageIcon icone = carregarIconeClima(nomeIconeApi, 128, 128);
        rotuloIconeClima.setIcon(icone);
    }
    private ImageIcon carregarIconeClima(String nomeIconeApi, int width, int height) {
        String nomeArquivo = "/sol.png";
        switch (nomeIconeApi.toLowerCase()) {
            case "snow": nomeArquivo = "/neve.png"; break;
            case "rain": nomeArquivo = "/chuva.png"; break;
            case "cloudy": case "partly-cloudy-day": case "partly-cloudy-night": case "fog":
                nomeArquivo = "/nuvem.png"; break;
            case "clear-day": nomeArquivo = "/sol.png"; break;
            case "clear-night": nomeArquivo = "/lua.png"; break;
        }
        try {
            URL urlImagem = getClass().getResource(nomeArquivo);
            if (urlImagem == null) return null;
            Image imagemRedimensionada = new ImageIcon(urlImagem).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(imagemRedimensionada);
        } catch (Exception e) {
            e.printStackTrace(); return null;
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