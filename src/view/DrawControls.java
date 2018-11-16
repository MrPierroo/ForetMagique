package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controler.ButtonHandler;
import javafx.scene.text.Text;
import model.Elements;
import model.Environnement;
import model.Parametres;

public class DrawControls {

	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;

	private int WIDTH = 400;
	private int HEIGHT = 400;

	public DrawControls(){

		//Makes a new window
		frame = new JFrame();
		frame.setTitle("Informations et controle");
		frame.setSize(WIDTH, HEIGHT);
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		panel.setBackground(Color.white);

		//this will make the frame not re-sizable
		frame.setResizable(false);
		//this will place the frames on different positions
		frame.setLocation(0, 175);
		frame.setVisible(true);

		JButton bouton = new JButton("Lancer l'agent avec Espace");
		bouton.setBounds(WIDTH/2-(WIDTH-10)/2, 10, WIDTH-10, 50);
		panel.add(bouton);

		bouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Environnement.agent.doCycle();
			}
		});

		JLabel informationControles = new JLabel();
		informationControles.setText("Se deplacer avec les fleches");
		informationControles.setBounds(WIDTH/2-(WIDTH-10)/2, 70, WIDTH-10, 20);
		panel.add(informationControles);

		JLabel informationControles2 = new JLabel();
		informationControles2.setText("Tirer avec A et passer les portails avec Z");
		informationControles2.setBounds(WIDTH/2-(WIDTH-10)/2, 100, WIDTH-10, 20);
		panel.add(informationControles2);
		panel.repaint();

		canvas = new Canvas();
		canvas.setBounds(0, 110, WIDTH, HEIGHT);

		//this will add the canvas to our frame
		panel.add(canvas);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		//this will set the background to black
		canvas.setBackground(Color.white);

	}
	
	public void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	protected void render(Graphics2D g){
		g.setColor(Color.gray);
		g.drawString("Score : "+(Environnement.getScoreEnvironnement()-Environnement.agent.getEnergieDepense()), 5, 110);
		g.drawString("Score environnement : "+Environnement.getScoreEnvironnement(), 5,130);
		g.drawString("Cout energie : "+Environnement.agent.getEnergieDepense(), 5, 150);
		
	}

}
