import java.awt.Color;

import javax.swing.JFrame;


public class Jogo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arena arena = new Arena(600,400);
		arena.adicionaTanque(new Tanque(100,200,0,Color.BLUE));
		arena.adicionaTanque(new Tanque(200,200,45,Color.RED));
		arena.adicionaTanque(new Tanque(470,360,0,Color.GREEN));
		arena.adicionaTanque(new Tanque(450,50,157,Color.PINK));
		JFrame janela = new JFrame("Raquel");
		janela.getContentPane().add(arena);
		janela.pack();
		janela.setVisible(true);
		janela.setDefaultCloseOperation(3);
	}

}
