package br.usjt.interfaces;

import java.awt.Container;
import java.awt.GridLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.usjt.conectores.ConectorMySQL;



public class TelaInicial extends JFrame {

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoCadastar;

    public TelaInicial() {
        super("Tela Inicial");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 720);

        
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents(){

        campoLogin = new JTextField();
        campoSenha = new JPasswordField();
        botaoEntrar = new JButton("OK");
        botaoCadastar = new JButton("Não tem cadastro? Cadastre-se aqui!");

        campoLogin.setBorder(BorderFactory.createTitledBorder("Digite o seu login"));
        campoSenha.setBorder(BorderFactory.createTitledBorder("Digite a sua senha"));


        Container campos = getContentPane();
        campos.setLayout(new GridLayout(4, 1));
        campos.add(campoLogin);
        campos.add(campoSenha);
        campos.add(botaoEntrar);
        campos.add(botaoCadastar);
        
        botaoEntrar.addActionListener((e) -> {
            String login = campoLogin.getText();
            String senha = new String(campoSenha.getPassword());

            if(campoLogin.getText().equals("") || campoSenha.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Login ou senha inválido");
            }else {
                String sql = ("Select * from td_usuarios where login = '"+ campoLogin.getText()+"';");
                ConectorMySQL conector = new ConectorMySQL();
                try (Connection c = conector.obtemConexao()){
                    PreparedStatement ps = c.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        String nomeBD = rs.getString("nome");
                        String loginBD = rs.getString("login");
                        String senhaBD = rs.getString("senha");

                        if(campoLogin.getText().equals(loginBD) && campoSenha.getText().equals(senhaBD)){
                            String screen = String.format("Bem vindo(a) %s", nomeBD);
                            JOptionPane.showMessageDialog(null, screen);
                            DashBoard dash = new DashBoard();
                            dash.setVisible(true);


                        }else {
                            JOptionPane.showMessageDialog(null, "Login ou senha inválido");
                            campoLogin.setText("");
                            campoSenha.setText("");
                        }

                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }


            }


        });

        botaoCadastar.addActionListener((f) -> {
            TelaCadastro telaCadastro = new TelaCadastro();
            telaCadastro.setVisible(true);
        });
        
    }
  
    public static void main(String args[]) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
            });
    }

}
