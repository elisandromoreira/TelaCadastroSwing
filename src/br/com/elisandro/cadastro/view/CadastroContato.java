package br.com.elisandro.cadastro.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.elisandro.cadastro.modelo.dao.PessoaDAO;
import br.com.elisandro.cadastro.modelo.entidades.Pessoa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CadastroContato extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private MaskFormatter fmtTelefone;
	private JButton btnSalvar;
	
	Pessoa pessoa;
	PessoaDAO dao;
	
	private int editar = -1;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroContato frame = new CadastroContato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroContato() {
		janela();
	}
	
	public CadastroContato(int id) {
		janela();
		editar = id;
		mostrarDados(id);
	}
	
	public void janela() {
		setTitle("Agenda de Contatos - CRUD");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Cadastro de Contatos");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(91, 11, 249, 38);
		contentPane.add(lblTitulo);
		
		JLabel lblSubtitulo = new JLabel("Preencha os campos abaixo para salvar o contato na agenda");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setBounds(25, 45, 392, 14);
		contentPane.add(lblSubtitulo);
		
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 93, 119, 14);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setBounds(139, 90, 285, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo");
		lblNomeCompleto.setBounds(10, 123, 119, 14);
		contentPane.add(lblNomeCompleto);
		
		txtNome = new JTextField();
		txtNome.setBounds(139, 120, 285, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 153, 97, 14);
		contentPane.add(lblTelefone);
		
		
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 182, 48, 14);
		contentPane.add(lblEmail);
		
		try {
			fmtTelefone = new MaskFormatter("(##)# ####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		txtTelefone = new JFormattedTextField(fmtTelefone);
		txtTelefone.setBounds(139, 150, 285, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(139, 180, 285, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalvar.setBounds(59, 220, 150, 40);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelar.setBounds(224, 220, 150, 40);
		contentPane.add(btnCancelar);
		
		btnSalvar.addActionListener(new btnSalvarListener());
		btnCancelar.addActionListener(new btnCancelarListener());
	}
	
	public class btnSalvarListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pessoa = new Pessoa();
			
			pessoa.setNome(txtNome.getText());
			pessoa.setTelefone(txtTelefone.getText());
			pessoa.setEmail(txtEmail.getText());
			
			dao = new PessoaDAO();
			if (editar >=0) {
				pessoa.setId(editar);
				dao.update(pessoa);
			} else {
				dao.create(pessoa);
			}
			
			JOptionPane.showMessageDialog(null, "Contato Salvo!");
			
			dispose();
		}
	
	}
	
	public class btnCancelarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CadastroContato.this.dispose();
			

		}

	}
	
	public void mostrarDados(int id) {
		pessoa = new PessoaDAO().readById(id);
		
		txtCodigo.setText(""+pessoa.getId());
		txtNome.setText(pessoa.getNome());
		txtTelefone.setText(pessoa.getTelefone());
		txtEmail.setText(pessoa.getEmail());
	}
	
	public void impedeEdicao() {
		txtCodigo.setEnabled(false);
		txtNome.setEnabled(false);
		txtTelefone.setEnabled(false);
		txtEmail.setEnabled(false);
		
		btnSalvar.setVisible(false);
	}
}
