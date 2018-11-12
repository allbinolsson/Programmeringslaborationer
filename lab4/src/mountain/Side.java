package mountain;

public class Side {
	
	private Point a;
	private Point b;
	
	public Side (Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	public Point getA () {
		return a;
	}
	
	public Point getB () {
		return b;
	}
	
	@Override
	public boolean equals (Object compare) {
		return false;
	}
	
	@Override
	public int hashCode () {
		return a.hashCode() + b.hashCode();
	}
}
