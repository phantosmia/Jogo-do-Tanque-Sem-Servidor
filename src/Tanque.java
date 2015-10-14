import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;


public class Tanque {
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
public Color getCor() {
	return cor;
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
public void setCor(Color cor) {
	this.cor = cor;
}
private double velocidade;
private Color cor;
private boolean estaAtivo;
public Tanque(int x, int y, int a, Color cor){
	this.x = x;
	this.y = y;
	this.angulo = 90 - a;
	this.cor = cor;
	velocidade = 0;
	this.estaAtivo = false;
}
public void aumentarVelocidade(){
	velocidade++;
}
public void girarHorario(int a){
	angulo += a;
}
public void girarAntiHorario(int a){
	angulo -= a;
}
public void mover(){
	x = Math.abs(x + Math.cos(Math.toRadians(angulo)) * velocidade);
	//System.out.println(x);
	y = Math.abs(y + Math.sin(Math.toRadians(angulo)) * velocidade);
	System.out.println(y);
	//System.out.println(angulo);
}
public void setEstaAtivo(boolean estaAtivo){
	this.estaAtivo = estaAtivo;
}
public void draw(Graphics2D g2d){
	AffineTransform antes = g2d.getTransform();
	AffineTransform depois = new AffineTransform();
	depois.translate(x,y);
	depois.rotate(Math.toRadians(angulo));
	g2d.transform(depois);
	g2d.setColor(cor);
	g2d.fillRect(-10, -12, 20, 24);
	for(int i = -12; i <= 8; i += 4){
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(-15, i, 5, 4);
		g2d.fillRect(10, i, 5, 4);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(-15, i, 5, 4);
		g2d.fillRect(10, i, 5, 4);
	}//canhao
	g2d.setColor(Color.LIGHT_GRAY);
	g2d.fillRect(-3, -25, 6, 25);
	g2d.setColor(cor);
	g2d.drawRect(-3, -25, 6, 25);
	//
	if(estaAtivo){
		g2d.setColor(new Color(120,120,120));
		Stroke linha = g2d.getStroke();
		g2d.setStroke(new BasicStroke(1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0, new float[]{8},0));
		g2d.drawRect(-24, -32, 48, 55);
		g2d.setStroke(linha);
		}
	g2d.setTransform(antes);
}
public Shape getRectEnvolvente(){
	AffineTransform at = new AffineTransform();
	at.translate(x, y);
	at.rotate(Math.toRadians(angulo));
	Rectangle rect = new Rectangle(-24,-32,48,55);
	return at.createTransformedShape(rect);
}
}
