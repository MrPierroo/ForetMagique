package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.sun.javafx.tk.Toolkit;

import controler.ButtonHandler;
import model.Agent;
import model.Elements;
import model.Environnement;
import model.Parametres;

/** ======================================= Dessiner les fenetres et tous les elements a mettre a jour ===========================================================*/

public class ViewAgent{

	// -------------------------------------------------Attributs-------------------------------------------------------------------------------

	JFrame frame;
	Canvas canvas;

	BufferStrategy bufferStrategy;

	private int WIDTH = 700;
	private int HEIGHT = 700;  


	private int intervalle = (int)(WIDTH/Parametres.getTAILLE_GRILLE()*0.99);

	/**image*/
	private Image monstre;
	private Image caca;
	private Image vent;
	private Image crevasse;
	private Image heroHaut;
	private Image heroBas;
	private Image heroDroite;
	private Image heroGauche;
	private Image portail;

	private ArrayList<Elements> List;

	// -------------------------------------------------Constructeur-------------------------------------------------------------------------------

	public ViewAgent(String titre, ArrayList<Elements> list){

		//Chargement des ressources utiles
		List = list;
		try {
			this.monstre = ImageIO.read(getClass().getResource("/resources/monstre.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {
			this.caca = ImageIO.read(getClass().getResource("/resources/caca.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {
			this.vent = ImageIO.read(getClass().getResource("/resources/vent.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {
			this.crevasse = ImageIO.read(getClass().getResource("/resources/crevasse.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {
			this.heroHaut = ImageIO.read(getClass().getResource("/resources/heroHaut.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {
			this.heroBas = ImageIO.read(getClass().getResource("/resources/heroBas.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {
			this.heroDroite = ImageIO.read(getClass().getResource("/resources/heroDroite.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {
			this.heroGauche = ImageIO.read(getClass().getResource("/resources/heroGauche.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {
			this.portail = ImageIO.read(getClass().getResource("/resources/portail.png"));
		} catch (IOException e) {e.printStackTrace();}

		//Makes a new window
		frame = new JFrame();
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(Color.black);
		frame.pack();

		//this will make the frame not re-sizable
		frame.setResizable(false);

		//this will place the frames on different positions
		switch(titre) {
		case Parametres.NOM_ENVIRONNEMENT : frame.setLocation(0, 0);
		break;
		case Parametres.NOM_AGENT : frame.setLocation(WIDTH + 20, 0);
		break;
		default : break;
		}


		frame.setVisible(true);

		//this will add the canvas to our frame
		panel.add(canvas);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		//this will make sure the canvas has focus, so that it can take input from mouse/keyboard
		canvas.requestFocus();

		//this will set the background to black
		canvas.setBackground(Color.black);
		// This will add our buttonhandler to our program
		canvas.addKeyListener(new ButtonHandler());
    }
	

	// -------------------------------------------------Mise a jour avec le thread de l environnement-------------------------------------------------------------------------------

	public void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	protected void render(Graphics2D g){

		List = Environnement.agent.getListElementObs();
		intervalle = (int)(WIDTH/Parametres.getTAILLE_GRILLE()*0.99);
		
		int T =(int) (intervalle*0.9); // Taille d un element 
		g.setColor(Color.black);
		g.setBackground(Color.black);
		// drawing de la grille
		for (int i = 0; i < Parametres.getTAILLE_GRILLE(); i++) {
			for (int j = 0; j < Parametres.getTAILLE_GRILLE(); j++) {
				g.drawRect(CO(i), CO(j),T,T );
				g.setColor(Color.black);
				g.fillRect(CO(i), CO(j),T,T );
				g.setColor(Color.darkGray);
				g.drawRect(CO(i), CO(j),T,T );
			}
		}
		
		for (int i = 0; i < Environnement.agent.getListElementObs().size(); i++){
			Elements e = Environnement.agent.getListElementObs().get(i);
			g.setColor(Color.white);
			g.fillRect(CO(e.getX()), CO(e.getY()),T,T);
		}

		for (int i = 0; i < Environnement.agent.getListElementObs().size(); i++) {
			Elements e = Environnement.agent.getListElementObs().get(i);
			if(e.getNom() == Parametres.NOM_MONSTRE) {
				g.drawImage(monstre,CO(e.getX()),CO(e.getY()),T,T,null);
			}
			if(e.getNom() == Parametres.NOM_VENT) {
				g.drawImage(vent,CO(e.getX()),CO(e.getY()),T,T,null);
			}
			if(e.getNom() == Parametres.NOM_CREVASSE) {
				g.drawImage(crevasse,CO(e.getX()),CO(e.getY()),T,T,null);
			}
			if(e.getNom() == Parametres.NOM_PORTAIL) {
				g.drawImage(portail,CO(e.getX()),CO(e.getY()),T,T,null);
			}
			if(e.getNom() == Parametres.NOM_CACA) {
				g.drawImage(caca,CO(e.getX()),CO(e.getY()),T,T,null);
			}
		}

		
		// drawing du robot
		if(Environnement.agent.getLastAction()==Environnement.agent.DROITE) {
			g.drawImage(heroDroite, CO(Environnement.agent.getX()), CO(Environnement.agent.getY()), T, T, null);
		}
		else if(Environnement.agent.getLastAction()==Environnement.agent.GAUCHE) {
			g.drawImage(heroGauche, CO(Environnement.agent.getX()), CO(Environnement.agent.getY()), T, T, null);
		}
		else if(Environnement.agent.getLastAction()==Environnement.agent.BAS) {
			g.drawImage(heroBas, CO(Environnement.agent.getX()), CO(Environnement.agent.getY()), T, T, null);
		}
		else if(Environnement.agent.getLastAction()==Environnement.agent.HAUT) {
			g.drawImage(heroHaut, CO(Environnement.agent.getX()), CO(Environnement.agent.getY()), T, T, null);
		}
		else {
			g.drawImage(heroBas, CO(Environnement.agent.getX()), CO(Environnement.agent.getY()), T, T, null);
		}
		
		

	}

	// -------------------------------------------------Generer les cases-------------------------------------------------------------------------------

	private int CO(int x) {	// coordonne en fonction de la taille de grille
		return (int) (x*intervalle+intervalle/10);
	}


}