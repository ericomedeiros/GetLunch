package com.getLunch.backend.ejb;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.MessageListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.getLunch.backend.message.Restaurante;
import com.getLunch.backend.utils.Listener;
import com.getLunch.backend.utils.Sender;
import com.getLunch.backend.utils.VotacaoSemanal;
import com.getLunch.ui.Cliente;

/**
 * Message-Driven Bean implementation class for: QueueController
 *
 */

public class QueueController extends JFrame implements MessageListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel 						contentPane;
	private JTextField 					tFNomeRes;
	private JTextField 					tFNrCli;
	private JButton 					btnCriar;
	private JButton 					btnDeletar;
	private JButton 					btnIniciar;
	
	private ArrayList<Restaurante> 		restaurantes;
	private ArrayList<VotacaoSemanal> 	votosSemanais;
	private VotacaoSemanal				currentSemana; 
	private int 						nrCliente;
	private int 						nrVotados;
	
	private void initiateApp() {
		// TODO Auto-generated method stub
		restaurantes  = new ArrayList<Restaurante>();
    	votosSemanais = new ArrayList<VotacaoSemanal>();
    	currentSemana = null;
    	nrCliente     = 0;
    	nrVotados	  = 0;
    	
    	
	}
	
    /**
     * Default constructor. 
     */
    public QueueController() {
        // TODO Auto-generated constructor stub
    	
    	initiateApp();
    	
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Central");
		setBounds(100, 100, 551, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("Restaurantes");
		separator.setBounds(0, 84, 552, 14);
		contentPane.add(separator);
		
		JLabel lblRestaurante = new JLabel("Restaurante");
		lblRestaurante.setBounds(10, 11, 80, 14);
		contentPane.add(lblRestaurante);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(20, 46, 46, 14);
		contentPane.add(lblNome);
		
		tFNomeRes = new JTextField();
		tFNomeRes.setBounds(76, 45, 230, 20);
		contentPane.add(tFNomeRes);
		tFNomeRes.setColumns(10);
		
		btnCriar = new JButton("Criar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewRestaurante(tFNomeRes.getText());
				tFNomeRes.setText("");
			}
		});
		btnCriar.setBounds(333, 44, 89, 23);
		contentPane.add(btnCriar);
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRestaurante(tFNomeRes.getText());
				tFNomeRes.setText("");
			}
		});
		btnDeletar.setBounds(432, 44, 89, 23);
		contentPane.add(btnDeletar);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(10, 97, 46, 14);
		contentPane.add(lblClientes);
		
		JLabel lblNumero = new JLabel("Quantidade:");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumero.setBounds(15, 132, 80, 20);
		contentPane.add(lblNumero);
		
		tFNrCli = new JTextField();
		tFNrCli.setToolTipText("Informe o numero de clientes para selecionar o almo\u00E7o de hoje.");
		tFNrCli.setBounds(95, 132, 112, 20);
		contentPane.add(tFNrCli);
		tFNrCli.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setToolTipText("Restaurantes");
		separator_1.setBounds(0, 175, 704, 2);
		contentPane.add(separator_1);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startSelectLunch(tFNrCli.getText());
				
			}
		});
		btnIniciar.setBounds(20, 207, 89, 23);
		contentPane.add(btnIniciar);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		btnFinalizar.setBounds(181, 207, 89, 23);
		contentPane.add(btnFinalizar);
		
    }

	private void startSelectLunch(String text) {
		// TODO Auto-generated method stub
		
		if(restaurantes.size() <= 0){
			JOptionPane.showMessageDialog(null, "Para iniciar é necessário ter restaurantes cadastrados");
			return;
		} 
		if(votosSemanais.size() <= 0 || currentSemana == null){
			
			currentSemana = new VotacaoSemanal(Arrays.copyOf(restaurantes.toArray(), restaurantes.toArray().length, Restaurante[].class));
			System.out.println(currentSemana);
		}
		
		try {
			nrCliente = Integer.parseInt(text);
		
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Para iniciar é necessário ter a quantidade de clientes e em numeros");
			return;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
		
		if(nrCliente == 0){
			JOptionPane.showMessageDialog(null, "Para iniciar é necessário ter a quantidade de clientes");
			return;
		}
		
		for (int i = 0; i < nrCliente; i++) {
			Cliente cli = new Cliente(Arrays.copyOf(restaurantes.toArray(), restaurantes.toArray().length, Restaurante[].class));
			Listener.addTopicListener(cli);
			cli.setVisible(true);
		}
		
	}

	private void deleteRestaurante(String text) {
		// TODO Auto-generated method stub
		Restaurante re = new Restaurante(text, restaurantes.size());
    	if(!restaurantes.contains(re)){
    		JOptionPane.showMessageDialog(null, "Este restaurante não existe");
    	}else{
    		if(restaurantes.remove(re)){
    			JOptionPane.showMessageDialog(null, "Este restaurante foi removido");
    		}else{
    			JOptionPane.showMessageDialog(null, "Ocorreu um erro, não foi possivel remover restaurante");
    		}
    	}
	}

	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
        try {
            if (message instanceof TextMessage) {
                System.out.println("Queue: I received a TextMessage at "
                        + new Date());
                TextMessage msg = (TextMessage) message;
                System.out.println("Message is : " + msg.getText());
            } else if (message instanceof ObjectMessage) {
                System.out.println("Queue: I received an ObjectMessage at "
                        + new Date());
                ObjectMessage msg = (ObjectMessage) message;
                Restaurante re = (Restaurante) msg.getObject();
                System.out.println("Restaurante recebido: "+re);
                
                currentSemana.addVoto(re);
                nrVotados++;
                System.out.println("Qt, de votos:" + nrVotados);
                if(nrVotados == nrCliente){
                	System.out.println("Entrou no if");
                	Sender.sendObjectMessageToTopic(currentSemana.getToDayRestaurante());
                	nrCliente = 0;
                	nrVotados = 0;
                }
                
            } else {
                System.out.println("Not valid message for this Queue MDB");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    private void addNewRestaurante(String text) {
		// TODO Auto-generated method stub
		
    	Restaurante re = new Restaurante(text, restaurantes.size());
    	if(restaurantes.contains(re)){
    		JOptionPane.showMessageDialog(null, "Este restaurante já existe");
    	}else{
    		if(restaurantes.add(re)){
    			JOptionPane.showMessageDialog(null, "Este restaurante foi adicionado");
    		}else{
    			JOptionPane.showMessageDialog(null, "Ocorreu um erro, não foi possivel adicionar restaurante");
    		}
    	}
    	
	}
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueueController frame = new QueueController();
					Listener.addQueueReceiver(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
