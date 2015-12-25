import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Arena extends JComponent implements MouseListener, ActionListener, KeyListener {
	private int largura, altura;
	public static Tiro balas;
	private ArrayList<Tiro> municao;
	private HashSet<Tanque> tanques;
	private Timer contador;
//	private Sons som;
    EfeitoSonoro som;

	private HashSet<Tanque> tanques2;
	public Arena(int largura, int altura) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		this.largura = largura;
		this.altura = altura;
		tanques = new HashSet<Tanque>();
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		contador = new Timer(100, this);
		contador.start();
	//	som = new Sons();
		EfeitoSonoro.init();
		
	
	}

	public void adicionaTanque(Tanque t) {
		tanques.add(t);
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public Dimension getPreferredSize() {
		return new Dimension(largura, altura);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(245, 245, 255));
		g2d.fillRect(0, 0, largura, altura);
		g2d.setColor(new Color(220, 220, 220));
		for (int _largura = 0; _largura <= largura; _largura += 20)
			g2d.drawLine(0, _largura, largura, _largura);
		for (int _altura = 0; _altura <= largura; _altura += 20)
			g2d.drawLine(0, _altura, largura, _altura);
		for (Tanque t : tanques)
			t.draw(g2d);
		//desenhando os tiros
		for (Tanque t : tanques) {
			ArrayList balas = t.getBalas();
			for (int i = 0; i < balas.size(); i++) {
				Tiro tiro = (Tiro) balas.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect((int) tiro.getX(), (int) tiro.getY(), 10, 5);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	
		//movimentacao dos tiros caso eles tenham sido acionados
		for (Tanque t : tanques) {
			ArrayList balas = t.getBalas();
			for (int i = 0; i < balas.size(); i++) {
				Tiro tiro = (Tiro) balas.get(i);
				if (tiro.isEstaAtivo() == true) {
					tiro.mover();
				} else {
					balas.remove(i);
				}
			}
		}
		//colisao de tiros com a parede
		for (Tanque t : tanques) {
			ArrayList balas = t.getBalas();
			for (int i = 0; i < balas.size(); i++) {
				Tiro tiro = (Tiro) balas.get(i);
				if (tiro.getX() < (getWidth()) && tiro.getY() < (getHeight()) && tiro.getX() >= 0 && tiro.getY() >= 0) {

				} else {
					balas.remove(i); //se chegar na parede, removemos o tiro da lista
				}
			}
		}
		// colisao com as paredes.
		for (Tanque t : tanques) {
			if (t.getX() < (getWidth() - 20) && t.getY() < (getHeight() - 20) && t.getX() - 20 >= 0
					&& t.getY() - 20 >= 0) { //caso nao tenha colidido, apenas segue normalmente
				t.setAngulo(t.getAngulo());
			
			} else {
				// se colidiu, setamos para a direcao mudar para o angulo oposto.
				t.setAngulo((t.getAngulo() + 180) % 360);
				t.setFlag(true);
			}
			t.mover();
			repaint();
		}
		tanques2 = new HashSet<Tanque>();
		//colisao tiro e tanque
		for (Tanque t : tanques) {
			ArrayList balas = t.getBalas();
			
			for (int i = 0; i < balas.size(); i++) {
				for (Tanque t1 : tanques) {
				Tiro tiro = (Tiro) balas.get(i);
				
				if(tiro.getBordasTiro().intersects(t1.getBordasTanque()) && t1.isEstaAtivo() == false){
					EfeitoSonoro.EXPLODIR.play();
					tanques2.add(t1); //evitando a modification exception, criei uma nova lista para armazenar os tanques que serao removidos
					balas.remove(tiro);
					break;
				}
				
			}
			}
		}
		tanques.removeAll(tanques2); //removo os tanques apenas quanto o loop termina.
		//colisao tanques
		for (Tanque t : tanques) {
				for (Tanque t1 : tanques) {
				if(t.getBordasTanque().intersects(t1.getBordasTanque()) && t != t1){
					t1.setAngulo((t1.getAngulo() + 180) %360);
					break;
				}
				
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for (Tanque t : tanques)
			t.setEstaAtivo(false);
		for (Tanque t : tanques) {
			
			boolean clicado = t.getRectEnvolvente().contains(arg0.getX(), arg0.getY());
			if (clicado) {
				t.setEstaAtivo(true);
				EfeitoSonoro.SELECIONADO.play();
		
				switch (arg0.getButton()) {
				}
				break;
			}
		
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		for (Tanque t : tanques) {
			if (t.isEstaAtivo() == true) {
				if (key == KeyEvent.VK_LEFT) {
					t.girarAntiHorario(3);
					break;
				}

				if (key == KeyEvent.VK_RIGHT) {
					t.girarHorario(3);
					break;
				}

				if (key == KeyEvent.VK_UP) {
					t.aumentarVelocidade();
					break;
				}

				if (key == KeyEvent.VK_DOWN) {
					t.diminuirVelocidade();
					break;
				}
				if (key == KeyEvent.VK_SPACE) {
					EfeitoSonoro.TIRO.play();
					t.atirar();
					break;
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
