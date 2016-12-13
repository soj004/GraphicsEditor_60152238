package tranformer;

import java.awt.Graphics2D;
import java.util.Vector;

import constants.GConstants.EAnchors;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import shapes.Anchors;
import shapes.GGroup;
import shapes.GShape;

public class GResizer extends GTransformer {
	private Vector<GShape> gChildList;
	
	public GResizer(GShape shape) {
		super(shape);
	}
	private Point getResizeAnchor(){
		Anchors anchors = shape.getAnchors();
		Point resizeAnchor = new Point();
		switch (shape.getCurrentEAnchor()){
		case EE: resizeAnchor.setLocation(anchors.getBounds(EAnchors.WW).getX(), 0); break;
		case WW: resizeAnchor.setLocation(anchors.getBounds(EAnchors.EE).getX(), 0); break;
		case SS: resizeAnchor.setLocation(anchors.getBounds(EAnchors.NN).getY(), 0); break;
		case NN: resizeAnchor.setLocation(anchors.getBounds(EAnchors.SS).getY(), 0); break;
		case SE: resizeAnchor.setLocation(anchors.getBounds(EAnchors.NW).getX(), 0); break;
		case NE: resizeAnchor.setLocation(anchors.getBounds(EAnchors.SW).getX(), 0); break;
		case SW: resizeAnchor.setLocation(anchors.getBounds(EAnchors.NE).getX(), 0); break;
		case NW: resizeAnchor.setLocation(anchors.getBounds(EAnchors.SE).getX(), 0); break;
		default: break;
		}
		return resizeAnchor;
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().setPoint(x, y);
		this.getShape().draw(g2d);
//		oldP = new Point(x, y);
//		anchorP = getResizeAnchor();
//		affTransform.setToTranslation(-anchorP.getX(), -anchorP.getY());
//		
//		getShape().setShape(affTransform.createTransformedShape(getShape().getShape()));
//		getShape().getAnchors().setTransShape(affTransform);
	}
	private Point2D computeResize(Point exP, Point curP){
		int dW = 0;
		int dH = 0;
		
		switch(shape.getCurrentEAnchor()){
		case EE: dW = curP.x - exP.x; dH = 0; break;
		case WW: dW = -(curP.x - exP.x); dH = 0; break;
		case SS: dW = 0; dH = curP.y - exP.y; break;
		case NN: dW = 0; dH = -(curP.y - exP.y); break;
		case SE: dW = curP.x - exP.x; dH = curP.y - exP.y; break;
		case NE: dW = curP.x - exP.x; dH = -(curP.y - exP.y); break;
		case SW: dW = -(curP.x - exP.x); dH = curP.y - exP.y; break;
		case NW: dW = -(curP.x - exP.x); dH = -(curP.y - exP.y); break;
		default: break;
		}
		
		double curW = shape.getBounds().getWidth();
		double curH = shape.getBounds().getHeight();
		double xFactor = 1.0;
		double yFactor = 1.0;
		
		if(curW > 0.0){
			xFactor = dW / curW + xFactor;
		}
		if(curH > 0.0){
			yFactor = dH / curH + yFactor;
		}
		
		return new Point2D.Double(xFactor, yFactor);
	}
	private Point2D computeCResize(Point exP, Point curP){
		int dW = 0;
		int dH = 0;
		
		switch(shape.getCurrentEAnchor()){
		case EE: dW = curP.x - exP.x; dH = 0; break;
		case WW: dW = -(curP.x - exP.x); dH = 0; break;
		case SS: dW = 0; dH = curP.y - exP.y; break;
		case NN: dW = 0; dH = -(curP.y - exP.y); break;
		case SE: dW = curP.x - exP.x; dH = curP.y - exP.y; break;
		case NE: dW = curP.x - exP.x; dH = -(curP.y - exP.y); break;
		case SW: dW = -(curP.x - exP.x); dH = curP.y - exP.y; break;
		case NW: dW = -(curP.x - exP.x); dH = -(curP.y - exP.y); break;
		default: break;
		}
		
		double curW = shape.getBounds().getWidth();
		double curH = shape.getBounds().getHeight();
		double xFactor = 1.0;
		double yFactor = 1.0;
		
		if(curW > 0.0){
			xFactor = dW / curW + xFactor;
		}
		if(curH > 0.0){
			yFactor = dH / curH + yFactor;
		}
		
		return new Point2D.Double(xFactor, yFactor);
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().draw(g2d);
		this.getShape().resize(x, y);
		this.getShape().draw(g2d);
//		AffineTransform saveAt = g2d.getTransform();
//		g2d.translate(this.getAnchorP().getX(), this.getAnchorP().getY());
//		this.getShape().draw(g2d);
//		Point2D resizeFactor = computeResize(oldP, new Point(x, y));
//		affTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());
//		getShape().setShape(affTransform.createTransformedShape(getShape().getShape()));
//		getShape().getAnchors().setTransShape(affTransform);
//		
//		if(getShape() instanceof GGroup){
//			GGroup gChild = (GGroup)getShape();
//			gChildList = gChild.getGroupList();
//			for(GShape childShape: gChildList){
//				Point2D CresizeFactor = computeCResize(oldP, new Point(x, y));
//				affTransform.setToScale(CresizeFactor.getX(), CresizeFactor.getY());
//				childShape.setShape(affTransform.createTransformedShape(childShape.getShape()));
//			}
//		}
//		oldP.setLocation(x, y);
//		this.getShape().draw(g2d);
//		g2d.setTransform(saveAt);
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		affTransform.setToTranslation(anchorP.getX(), anchorP.getY());
		getShape().setShape(affTransform.createTransformedShape(getShape().getShape()));
		getShape().getAnchors().setTransShape(affTransform);
	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

}
