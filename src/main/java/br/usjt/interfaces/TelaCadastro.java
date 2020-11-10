package br.usjt.interfaces;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.usjt.conectores.ConectorMySQL;

import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TelaCadastro extends JFrame{
    
    private JTextField campoNome;
    private JTextField campoLogin;
    private JTextField campoSenha;
    private JButton botaoConfirmar;

    public TelaCadastro() {
        super("Tela Cadastro");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 720);

        
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents(){
        campoNome = new JTextField();
        campoLogin = new JTextField();
        campoSenha = new JTextField();
        botaoConfirmar = new JButton("OK");

        campoNome.setBorder(BorderFactory.createTitledBorder("Digite o seu nome"));
        campoLogin.setBorder(BorderFactory.createTitledBorder("Digite o seu login"));
        campoSenha.setBorder(BorderFactory.createTitledBorder("Digite a sua senha"));

        Container campoCadastro = getContentPane();
        campoCadastro.setLayout(new GridLayout(4,1));
        campoCadastro.add(campoNome);
        campoCadastro.add(campoLogin);
        campoCadastro.add(campoSenha);
        campoCadastro.add(botaoConfirmar);
        botaoConfirmar.addActionListener((e) -> {
            String nome = campoNome.getText();
            String login = campoLogin.getText();
            String senha = campoSenha.getText();

            if(campoNome.getText().equals("") || campoLogin.getText().equals("") || campoSenha.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Dados inválidos");
            }else {
                String sql = "INSERT INTO td_usuarios(login, nome, senha) VALUES (?, ?, ?)";
                ConectorMySQL conector = new ConectorMySQL();
		        try (Connection c = conector.obtemConexao()){
			        PreparedStatement ps = c.prepareStatement(sql);
			        ps.setString(1, login);
			        ps.setString(2, nome);
			        ps.setString(3, senha);
                    ps.execute();
                    String screen = String.format("Conta criada com sucesso Bem vindo(a) %s", nome);
                            JOptionPane.showMessageDialog(null, screen);
                            DashBoard dash = new DashBoard();
                            dash.setVisible(true);
                }
		        catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });

    }

}
