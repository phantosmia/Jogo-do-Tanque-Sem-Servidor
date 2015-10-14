
public class Tiro {



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
	public Tiro(int x, int y, int a){
		this.x = x;
		this.y = y;
		this.angulo = 90 - a;
		
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

	}


