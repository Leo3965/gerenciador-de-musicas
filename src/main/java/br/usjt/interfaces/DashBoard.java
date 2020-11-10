package br.usjt.interfaces;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DashBoard extends JFrame {

    private JButton genereosFavoritos;
    private JButton avaliarMusica;
    private JButton recomendacao;

    public DashBoard() {
        super("DashBoard");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 720);

        
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void initComponents(){

        genereosFavoritos = new JButton("Meus Gêneros Preferidos");
        avaliarMusica = new JButton("Avaliar Músicas");
        recomendacao = new JButton("Quero Recomendações!");

        Container campoBotoes = getContentPane();
        campoBotoes.setLayout(new GridLayout(3,1));
        campoBotoes.add(genereosFavoritos);
        campoBotoes.add(avaliarMusica);
        campoBotoes.add(recomendacao);
    }

}
