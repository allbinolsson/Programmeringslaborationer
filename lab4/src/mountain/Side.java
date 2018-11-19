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
		if (compare instanceof Side) {		// Checks if compare is of class Side
			if (((Side) compare).getA() == a && ((Side) compare).getB() == b) {			// (a == a && b == b)?
				return true;
			} else if (((Side) compare).getA() == b && ((Side) compare).getB() == a) {	// (a == b && b == a)?
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode () {
		return a.hashCode() + b.hashCode();
	}
}
