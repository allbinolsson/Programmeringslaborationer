package mountain;

import fractal.*;

public class Mountain extends Fractal {
	
	double startDev;
	private Point p1;
	private Point p2;
	private Point p3;
	
	public Mountain (double startDev, Point p1, Point p2, Point p3) {
		super();
		this.startDev = startDev;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public String getTitle () {
		return "Mountain";
	}
	
	public void draw (TurtleGraphics g) {
		// g.moveTo(p1.getX(), p1.getY());
		fractalTriangle (g, order, p1, p2, p3);
	}
	
	private void fractalTriangle (TurtleGraphics g, int order, Point a, Point b, Point c) {
		if (order == 0) {
			g.moveTo(a.getX(), a.getY());
			g.forwardTo(b.getX(), b.getY());
			g.forwardTo(c.getX(), c.getY());
			g.forwardTo(a.getX(), a.getY());
		} else {
//			fractalTriangle(g, order - 1, a, newPoint(a, b), newPoint(a, c));
//			fractalTriangle(g, order - 1, newPoint(a, b), b, newPoint(b, c));
//			fractalTriangle(g, order - 1, newPoint(a, c), newPoint(b, c), c);
//			fractalTriangle(g, order - 1, newPoint(a, b), newPoint(b, c), newPoint(a, c));
			
			fractalTriangle(g, order - 1, a, newPoint(a, b, startDev / 2), newPoint(a, c, startDev / 2));
			fractalTriangle(g, order - 1, newPoint(a, b, startDev / 2), b, newPoint(b, c, startDev / 2));
			fractalTriangle(g, order - 1, newPoint(a, c, startDev / 2), newPoint(b, c, startDev / 2), c);
			fractalTriangle(g, order - 1, newPoint(a, b, startDev / 2), newPoint(b, c, startDev / 2), newPoint(a, c, startDev / 2));
		}
	}
	
	private Point newPoint (Point a, Point b, double dev) {
		int x = a.getX() + ((b.getX()- a.getX()) / 2);
//		int y = a.getY() + ((b.getY() - a.getY()) / 2);
		int y = a.getY() + ((b.getY() - a.getY()) / 2) + (int)RandomUtilities.randFunc(dev);
		return new Point(x, y);
	}
}
