package br.com.elisandro.cadastro.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.com.elisandro.cadastro.modelo.dao.PessoaDAO;
import br.com.elisandro.cadastro.util.ModeloGrade;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class Lista extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private static Lista frame;
	
	private JPanel contentPane;
	private JTextField txtValor;
	private JTable table;
	private JPanel panelTabela;
	private JComboBox comboBox;
	
	String [] colunas = {"Nome", "Telefone", "E-mail"};
	Object [][] dados = {
			{"Elisandro Moreira", "47 996485847", "elisandromoreira@gmail.com"},
			{"Nataly D. Moreira", "47 997367441", "ndominiak@hotmail.com"},
			{"Nicolas D. Moreira", "47 99999999", "nick@gmail.com"},
	};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lista frame = new Lista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Lista() {
		janela();
		tabela();
		atualizarGrade();
		populaCombo();
	}

	public void janela() {
		setTitle("Agenda de Contatos");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 40);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCampo = new JLabel("Campo");
		lblCampo.setBounds(10, 15, 48, 14);
		panel.add(lblCampo);
		
		comboBox = new JComboBox();
		comboBox.setBounds(67, 11, 125, 22);
		panel.add(comboBox);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(202, 15, 38, 14);
		panel.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setBounds(250, 12, 175, 20);
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisa();
			}
		});
		btnPesquisar.setBounds(435, 11, 139, 23);
		panel.add(btnPesquisar);
		
		panelTabela = new JPanel();
		panelTabela.setBounds(0, 51, 584, 260);
		contentPane.add(panelTabela);
		panelTabela.setLayout(new GridLayout(1, 1));
		
				
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 321, 584, 40);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnInserir = new JButton("Inserir");
		
		btnInserir.setBounds(35, 10, 88, 23);
		panel_2.add(btnInserir);
		
		JButton btnAlterar = new JButton("Alterar");
		
		btnAlterar.setBounds(133, 10, 88, 23);
		panel_2.add(btnAlterar);
		
		JButton btnDeletar = new JButton("Deletar");
		
		btnDeletar.setBounds(231, 10, 88, 23);
		panel_2.add(btnDeletar);
		
		JButton btnVisualizar = new JButton("Visualizar");
		
		btnVisualizar.setBounds(329, 10, 120, 23);
		panel_2.add(btnVisualizar);
		
		JButton btnFechar = new JButton("Fechar");
		
		btnFechar.setBounds(459, 10, 88, 23);
		panel_2.add(btnFechar);
		
		btnInserir.addActionListener(new btnInserirListener());
		btnAlterar.addActionListener(new btnAlterarListener());
		btnDeletar.addActionListener(new btnDeletarListener());
		btnVisualizar.addActionListener(new btnVisualizarListener());
		btnFechar.addActionListener(new btnFecharListener());
		
	}
	
	public void tabela(){
		table = new JTable(new ModeloGrade());
		table.setToolTipText("Clique em uma linha para selecionar");
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JScrollPane rolagem = new JScrollPane(table);
		panelTabela.add(rolagem);
	}
	
	public void atualizarGrade() {
		ResultSet rs = new PessoaDAO().carregarGrade();
		table.setModel(new ModeloGrade(rs, new String[]{"Código", "Nome"}));
		table.getColumnModel().getColumn(0).setMaxWidth(60);
	}
	
	public void populaCombo() {
        List<String> campos = new PessoaDAO().nomeCampos();
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) comboBox.getModel();

        for (String campo : campos)
            dcm.addElement(campo);
    }
	
	public void pesquisa() {
		ResultSet rs = new PessoaDAO().pesquisa("" + comboBox.getSelectedItem(), txtValor.getText());
		table.setModel(new ModeloGrade(rs, new String[]{"Código", "Nome"}));
		table.getColumnModel().getColumn(0).setMaxWidth(60);
	}
	
	public class btnInserirListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CadastroContato cont = new CadastroContato();
			cont.setVisible(true);
			
			cont.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    super.windowClosed(e);
                    atualizarGrade();
				}
			});
		
		}
	}
	
public class btnAlterarListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = -1;
			linhaSelecionada = table.getSelectedRow();
						
			if (linhaSelecionada >= 0) {
				int id = (int) table.getValueAt(linhaSelecionada, 0);
				CadastroContato cont = new CadastroContato(id);
				cont.setVisible(true);
				
				cont.addWindowListener(new WindowAdapter() {
	                @Override
	                public void windowClosed(WindowEvent e) {

	                    super.windowClosed(e);
	                    atualizarGrade();
					}
				});
				
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um Registro para Editar!");
			}		
		}
	}

	public class btnDeletarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] opcoes = {"Sim", "Não"};
			int linhaSelecionada = -1;
			linhaSelecionada = table.getSelectedRow();
						
			if (linhaSelecionada >= 0) {
				int id = (int) table.getValueAt(linhaSelecionada, 0);
				int opcao = JOptionPane.showOptionDialog(rootPane, "Deseja realmente excluir?", "Confirmação",
                        JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);
                if (JOptionPane.OK_OPTION == opcao) {
                    new PessoaDAO().delete(id);
                    atualizarGrade();
                }
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um Registro para Deletar!");
			}				
		}		
	}
	
	public class btnVisualizarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            int linhaSelecionada = -1;
            linhaSelecionada = table.getSelectedRow();

            if (linhaSelecionada >= 0) {
                int id = (int) table.getValueAt(linhaSelecionada, 0);
                CadastroContato pessoa = new CadastroContato(id);
                pessoa.setVisible(true);
                pessoa.impedeEdicao();

            } else
                JOptionPane.showMessageDialog(null, "Selecione um registro");

        }

    }
	
	public class btnFecharListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);

        }

    }
}
