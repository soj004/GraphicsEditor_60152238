package tranformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Vector;
import shapes.GShape;

abstract public class GTransformer {
	protected GShape shapeManager;	
	protected Point oldP, anchorP;
	protected AffineTransform affTransform;
	protected Vector<GShape> groupList;
	protected GShape shape;
	
	public GShape getShape() { return this.shape; }
	public void setShape(GShape shape){this.shape = shape;}
	public void setOldP(int x, int y){this.oldP.x = x;	this.oldP.y = y;}
	public Point getAnchorP(){return anchorP;}
	public Vector<GShape> getGroupList() {return groupList;}
	public void setGroupList(Vector<GShape> groupList) {this.groupList = groupList;}
	
	public GTransformer(GShape shape) {
		this.shape = shape;
		oldP = new Point(0, 0);
		anchorP = new Point(0, 0);
		affTransform = new AffineTransform();
		groupList = new Vector<GShape>();
	}
	abstract public void initTransforming(int x, int y, Graphics2D g2D);
	abstract public void keepTransforming(int x, int y, Graphics2D g2D);
	abstract public void finishTransforming(int x, int y, Graphics2D g2D);
	abstract public void continueTransforming(int x, int y, Graphics2D g2D);
}

