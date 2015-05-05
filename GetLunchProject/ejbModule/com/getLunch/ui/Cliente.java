package com.getLunch.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.getLunch.backend.ejb.QueueController;
import com.getLunch.backend.message.Restaurante;
import com.getLunch.backend.utils.ClientUtility;
import com.getLunch.backend.utils.Listener;
import com.getLunch.backend.utils.Sender;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class Cliente extends JFrame implements MessageListener{
	
	private static final long 		serialVersionUID = 1L;
	private JPanel 					contentPane;
	private JComboBox<Restaurante>	comboBox;
	private JLabel 					label;
	private JButton 				btnSelecionar;
	private JButton 				btnFechar;

	/**
	 * Launch the application.
	 */

	public Cliente(Restaurante[] restaurantes) {
		setTitle("Selecionador de restaurante");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<Restaurante>(restaurantes);
		comboBox.setBounds(23, 21, 379, 20);
		
		contentPane.add(comboBox);
		
		btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Restaurante re = (Restaurante)comboBox.getSelectedItem();
				Sender.sendObjectMessageToQueue(re);
				btnSelecionar.setEnabled(false);
				comboBox.setEnabled(false);
				label.setText("O restaurante que escolheu foi: "+ re.toString());
				
			}
		});
		btnSelecionar.setBounds(160, 66, 100, 23);
		contentPane.add(btnSelecionar);
		
		label = new JLabel("");
		label.setBounds(23, 128, 379, 61);
		contentPane.add(label);
		
		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnFechar.setEnabled(false);
		btnFechar.setBounds(313, 223, 89, 23);
		contentPane.add(btnFechar);
	}
	
	public void onMessage(Message message){
		try {
            if (message instanceof TextMessage) {
                System.out.println("Queue: I received a TextMessage at "
                        + new Date());
                TextMessage msg = (TextMessage) message;
                System.out.println("Message is : " + msg.getText());
            } else if (message instanceof ObjectMessage) {
                
                ObjectMessage msg = (ObjectMessage) message;
                Restaurante re = (Restaurante) msg.getObject();
                String st = label.getText() + "\n" +
                		"\n" +
                		"E o restaurante escolhido foi: "+ re + ". Por favor finalize a operação para escolher o próximo almoço";
                label.setText(st);
                btnFechar.setEnabled(true);
            } else {
                System.out.println("Not valid message for this Queue MDB");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
	}
	
}
