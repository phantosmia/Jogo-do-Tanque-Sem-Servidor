import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;










import javax.swing.JComponent;
import javax.swing.Timer;


public class Arena extends JComponent implements MouseListener, ActionListener, KeyListener {
private int largura, altura;
private HashSet<Tanque> tanques;
private Timer contador;
public Arena(int largura, int altura){
	this.largura = largura;
	this.altura = altura;
	tanques = new HashSet<Tanque>();
	addMouseListener(this);
	addKeyListener(this);
	setFocusable(true);
	contador = new Timer(500,this);
	contador.start();
}
public void adicionaTanque(Tanque t){
	tanques.add(t);
}
public Dimension getMaximumSize(){
	return getPreferredSize();
}
public Dimension getMinimumSize(){
	return getPreferredSize();
}
public Dimension getPreferredSize(){
	return new Dimension(largura, altura);
}
protected void paintComponent(Graphics g){
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D)g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2d.setColor(new Color(245,245,255));
	g2d.fillRect(0, 0, largura, altura);
	g2d.setColor(new Color(220,220,220));
	for(int _largura = 0; _largura <= largura; _largura +=20)
		g2d.drawLine(0, _largura, largura, _largura);
	for(int _altura = 0; _altura <= largura; _altura +=20)
		g2d.drawLine(0, _altura, largura, _altura);
	for(Tanque t: tanques) t.draw(g2d);
}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		for(Tanque t:tanques)
		{
			if (t.getX() < (getWidth() - 10) && t.getY()< (getHeight() - 10) && t.getX()-10 >= 0 && t.getY()-10 >= 0)
			{
				t.setAngulo(t.getAngulo());
				//System.out.println("Nao entrou no else");
			}
			else{
				t.setAngulo((t.getAngulo()+180)%360);
				t.setFlag(true);
			}
			t.mover();
		repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for(Tanque t: tanques)
			t.setEstaAtivo(false);
		for(Tanque t:tanques){
			boolean clicado = t.getRectEnvolvente().contains(arg0.getX(),arg0.getY());
			if(clicado){
				t.setEstaAtivo(true);
				switch(arg0.getButton()){
			//	case MouseEvent.BUTTON1: t.girarAntiHorario(3); break;
				//case MouseEvent.BUTTON2: t.aumentarVelocidade(); break;
				//case MouseEvent.BUTTON3: t.girarHorario(3); break;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		for(Tanque t:tanques){
			if(t.isEstaAtivo() == true){
	    if (key == KeyEvent.VK_LEFT) {
	    	 t.girarAntiHorario(3); 
	    	 break;
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	    	t.girarHorario(3); break;
	    }

	    if (key == KeyEvent.VK_UP) {
	    	 t.aumentarVelocidade(); 
	    	 break;
	    }

	    if (key == KeyEvent.VK_DOWN) {
	        
	    }
	    if(key == KeyEvent.VK_SPACE){
	    	System.out.println("BOOOOOOOOM!");
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
