package bubble.test.ex15;

public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {};
	default public void attack() {};
}
