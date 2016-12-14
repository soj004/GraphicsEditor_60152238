package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;

abstract public class GShape implements Serializable {
	private static final long serialVersionUID = 1L;
	// attributes
	private EDrawingType eDrawingType;
	private boolean selected;
	private EAnchors currentEAnchor;
	// components
	protected Shape shape;	
	protected Anchors anchors;
	// working variables
	protected int px, py;
	public GGroup gShape;
	// getters & setters
	public EDrawingType geteDrawingType() {	return eDrawingType;}
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	public EAnchors getCurrentEAnchor() {return currentEAnchor;}
	public Anchors getAnchors(){return anchors;}

	public Shape getShape() { return shape;	}
	public void setShape(Shape shape){this.shape = shape;}
	public void setGroupShape(GGroup group){this.gShape = group;}
//	public Anchors getAnchors() {return anchors;}
//	public void setAnchors(Anchors anchors) {this.anchors = anchors;}
	
	// constructors
	public GShape(EDrawingType eDrawingType, Shape shape){
		this.eDrawingType = eDrawingType;
		this.selected = false;
		this.currentEAnchor = null;
		
		this.shape = shape;
		this.anchors = new Anchors();
		this.gShape = null;
		
		this.px = this.py = 0;
	}
	public GShape clone() {
		try { return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Rectangle getBounds() {return shape.getBounds();}
	
	// methods
	public void draw(Graphics2D g2d) {
		g2d.draw(this.shape);
		if (this.selected) {
			this.anchors.draw(g2d, this.shape.getBounds());
		}
	}
	public GConstants.EAnchors contains(int x, int y) {
		this.currentEAnchor = null;
		if (this.selected) {
			this.currentEAnchor = this.anchors.contains(x, y);
			if (this.currentEAnchor != null)
				return this.currentEAnchor;
		}
		if (this.shape.contains(x, y)) {
			this.currentEAnchor = EAnchors.MM;
		}
		return this.currentEAnchor;
	}
	abstract public void setOrigin(int x, int y);
	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y); 
	abstract public void resize(int x, int y);
	abstract public void move(int x, int y);
}
