import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tiro {


	BufferedImage image;
	private double x, y;
	private boolean flag = false;
	public boolean isFlag() {
		return flag;
	}
 
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getAngulo() {
		return angulo;
	}
	public double getVelocidade() {
		return velocidade;
	}
	public Rectangle getBordasTiro(){
		return new Rectangle((int)x, (int)y, 10, 5);
	}
	
	public boolean isEstaAtivo() {
		return estaAtivo;
	}
	private double angulo;
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}
	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}
	
	private double velocidade;

	private boolean estaAtivo;
	public Tiro(double d, double e, double f, int v) {
		this.x = d + 6;
		this.y = e - 25;
		this.angulo = f;
		
		velocidade = v;
		this.estaAtivo = true;
	}
	
	public void mover(){
		x = x + Math.cos(Math.toRadians(angulo+270)) * velocidade;
		y = y + Math.sin(Math.toRadians(angulo+270)) * velocidade;

	}

	}


