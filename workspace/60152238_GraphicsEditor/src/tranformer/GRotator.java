package tranformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GGroup;
import shapes.GShape;

public class GRotator extends GTransformer {
	private Point cP;
	private Vector<GShape> gChildList;
	
	public GRotator(GShape shape) {
		super(shape);
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		this.setOldP(x, y);
		cP = new Point((int)shape.getBounds().getCenterX(), (int)shape.getBounds().getCenterY());
	}
	
	private double computeRotateAngle(Point startP, Point exP, Point curP){
		double startAngle = Math.toDegrees(Math.atan2(startP.getX() - exP.getX(), startP.getY() - exP.getY()));
		double endAngle = Math.toDegrees(Math.atan2(startP.getX() - curP.getX(), startP.getY() - curP.getY()));
		double angle = startAngle - endAngle;
		if(angle < 0){
			angle += 360;
		}
		return angle;
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().draw(g2d);
		AffineTransform saveAt = g2d.getTransform();
		g2d.translate(this.getAnchorP().getX(), this.getAnchorP().getY());
		double rotateAngle = computeRotateAngle(cP, oldP, new Point(x, y));
		affTransform.setToRotation(Math.toRadians(rotateAngle), cP.getX(), cP.getY());
		getShape().setShape(affTransform.createTransformedShape(getShape().getShape()));
		
		getShape().getAnchors().setTransShape(affTransform);
		if(getShape() instanceof GGroup){
			GGroup groupChild = (GGroup)getShape();
			gChildList = groupChild.getGroupList();
			for(GShape childShape: gChildList){
				double rotateGAngle = computeRotateAngle(cP, oldP, new Point(x, y));
				affTransform.setToRotation(Math.toRadians(rotateGAngle), cP.getX(), cP.getY());
				childShape.setShape(affTransform.createTransformedShape(childShape.getShape()));
			}
		}
		this.setOldP(x, y);
		this.getShape().draw(g2d);
		g2d.setTransform(saveAt);
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

}
