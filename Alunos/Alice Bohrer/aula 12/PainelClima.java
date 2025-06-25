package ui;

import modelo.DiaClima;
import modelo.RespostaClima;
import servico.ClienteApiClima;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.net.URL;

public class PainelClima extends JPanel {
    private ClienteApiClima clienteApi;

    // Componentes da UI
    private JTextField campoCidade;
    private JButton botaoBuscar;
    private JLabel rotuloLocalizacao;
    private JLabel rotuloTemperatura;
    private JLabel rotuloCondicoes;
    private JLabel rotuloIconeClima;
    private JLabel rotuloMinMax, rotuloUmidade, rotuloPrecipitacao, rotuloVento;

    public PainelClima() {
        this.clienteApi = new ClienteApiClima();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(UIColorsLight.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        painelBusca.setBackground(UIColorsLight.BACKGROUND);
        painelBusca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        campoCidade = new JTextField("Cascavel", 18);
        campoCidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoCidade.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIColorsLight.BORDER_COLOR, 1, true),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        botaoBuscar = new JButton("Buscar");
        customizeButton(botaoBuscar); // Aplicando o estilo

        painelBusca.add(new JLabel("Cidade:"));
        painelBusca.add(campoCidade);
        painelBusca.add(botaoBuscar);
        add(painelBusca);
        add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel painelDestaque = new JPanel(new BorderLayout(10, 0));
        painelDestaque.setBackground(UIColorsLight.CARD_BACKGROUND);
        painelDestaque.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIColorsLight.BORDER_COLOR, 1, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        painelDestaque.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        rotuloIconeClima = new JLabel();
        rotuloIconeClima.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel painelTextoDestaque = new JPanel();
        painelTextoDestaque.setLayout(new BoxLayout(painelTextoDestaque, BoxLayout.Y_AXIS));
        painelTextoDestaque.setOpaque(false);

        rotuloLocalizacao = new JLabel("Buscando...");
        rotuloLocalizacao.setFont(new Font("Segoe UI", Font.BOLD, 26));
        rotuloLocalizacao.setForeground(UIColorsLight.TEXT_PRIMARY);

        rotuloTemperatura = new JLabel("--°");
        rotuloTemperatura.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 52));
        rotuloTemperatura.setForeground(UIColorsLight.TEXT_PRIMARY);

        rotuloCondicoes = new JLabel("Condição");
        rotuloCondicoes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rotuloCondicoes.setForeground(UIColorsLight.TEXT_SECONDARY);
        
        painelTextoDestaque.add(rotuloLocalizacao);
        painelTextoDestaque.add(rotuloTemperatura);
        painelTextoDestaque.add(rotuloCondicoes);

        painelDestaque.add(painelTextoDestaque, BorderLayout.CENTER);
        painelDestaque.add(rotuloIconeClima, BorderLayout.EAST);
        add(painelDestaque);
        add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelDetalhes = new JPanel(new GridLayout(2, 2, 20, 15));
        painelDetalhes.setBackground(UIColorsLight.BACKGROUND);
        painelDetalhes.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(UIColorsLight.BORDER_COLOR),
            "Detalhes do Dia",
            TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 12),
            UIColorsLight.TEXT_SECONDARY
        ));

        rotuloMinMax = criarLabelDetalhe("Máx / Mín", "--° / --°");
        rotuloUmidade = criarLabelDetalhe("Umidade", "-- %");
        rotuloPrecipitacao = criarLabelDetalhe("Precipitação", "-- mm");
        rotuloVento = criarLabelDetalhe("Vento", "-- km/h");

        painelDetalhes.add(rotuloMinMax);
        painelDetalhes.add(rotuloUmidade);
        painelDetalhes.add(rotuloPrecipitacao);
        painelDetalhes.add(rotuloVento);
        add(painelDetalhes);

        botaoBuscar.addActionListener(e -> buscarDadosDoTempo());
        campoCidade.addActionListener(e -> buscarDadosDoTempo());
        
        buscarDadosDoTempo();
    }
    
    private void customizeButton(JButton button) {
        button.setBackground(UIColorsLight.ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(6, 18, 6, 18));
    }

    private JLabel criarLabelDetalhe(String titulo, String valorInicial) {
        JLabel label = new JLabel("<html><div style='font-size:10px; color:rgb(100,100,120);'>" + titulo + "</div>" +
                                 "<div style='font-size:14px; color:rgb(20,20,40);'>" + valorInicial + "</div></html>");
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
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
                        JOptionPane.showMessageDialog(PainelClima.this, "Não foi possível obter os dados do tempo.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
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

        rotuloMinMax.setText(formatarDetalhe("Máx / Mín", String.format("%.0f° / %.0f°", diaAtual.getTempMaxima(), diaAtual.getTempMinima())));
        rotuloUmidade.setText(formatarDetalhe("Umidade", String.format("%.0f %%", clima.getCondicoesAtuais().getUmidade())));
        rotuloPrecipitacao.setText(formatarDetalhe("Precipitação", String.format("%.1f mm", diaAtual.getPrecipitacao())));
        rotuloVento.setText(formatarDetalhe("Vento", String.format("%.1f km/h (%s)", clima.getCondicoesAtuais().getVelocidadeVento(), obterDirecaoVento(clima.getCondicoesAtuais().getDirecaoVento()))));

        String nomeIconeApi = clima.getCondicoesAtuais().getIcone();
        ImageIcon icone = carregarIconeClima(nomeIconeApi, 100, 100);
        rotuloIconeClima.setIcon(icone);
    }
    
    private String formatarDetalhe(String titulo, String valor) {
        return "<html><div style='font-size:10px; color:rgb(100,100,120);'>" + titulo + "</div>" +
               "<div style='font-size:14px; color:rgb(20,20,40);'>" + valor + "</div></html>";
    }

    private ImageIcon carregarIconeClima(String nomeIconeApi, int width, int height) {
        String nomeArquivo = "/sol.png"; 
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
                nomeArquivo = "/sol.png";
                break;
            case "clear-night":
                nomeArquivo = "/lua.png";
                break;
        }

        try {
            URL urlImagem = getClass().getResource(nomeArquivo);
            if (urlImagem == null) return null;
            ImageIcon iconeOriginal = new ImageIcon(urlImagem);
            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
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
