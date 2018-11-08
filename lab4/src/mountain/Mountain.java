package mountain;

import fractal.*;

public class Mountain extends Fractal {
	
	private Point p1;
	private Point p2;
	private Point p3;
	
	public Mountain (Point p1, Point p2, Point p3) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public String getTitle () {
		return "Mountain";
	}
	
	public void draw (TurtleGraphics g) {
		g.moveTo(p1.getX(), p1.getY());
		fractalTriangle (g, order, p1, p2, p3);
	}
	
	private void fractalTriangle (TurtleGraphics g, int order, Point a, Point b, Point c) {
		if (order == 0) {
			g.forwardTo(b.getX(), b.getY());
			g.forwardTo(c.getX(), c.getY());
			g.forwardTo(a.getX(), a.getY());
		} else {
			fractalTriangle(g, order - 1, a, newPoint(a, b), newPoint(a, c));
			fractalTriangle(g, order - 1, newPoint(a, b), b, newPoint(b, c));
			fractalTriangle(g, order - 1, newPoint(a,c), newPoint(b, c), c);
		}
	}
	
	private Point newPoint (Point a, Point b) {
		
		int x = a.getX() + ((a.getX() - b.getX()) / 2);
		int y = a.getY() + ((b.getY() - a.getY()) / 2);
		
		return new Point(x, y);
	}
}
