package shapes;

import java.awt.geom.Ellipse2D;
import constants.GConstants.EDrawingType;


public class GEllipse extends GShape{
	private static final long serialVersionUID = 1L;
	
	private Ellipse2D.Double ellipse;
	public GEllipse(){
		super(EDrawingType.TP, new Ellipse2D.Double());
		this.ellipse = (Ellipse2D.Double)this.getShape();
	}
	@Override
	public void setOrigin(int x, int y) {
		this.ellipse.x = x;
		this.ellipse.y = y;
	}
	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	@Override
	public void move(int x, int y) {
		this.ellipse.x += x - px;
		this.ellipse.y += y - py;
		this.setPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		
	}
	@Override
	public void resize(int x, int y) {
		if(this.getCurrentEAnchor()==null){
			this.ellipse.width = x - this.ellipse.x;
			this.ellipse.height = y - this.ellipse.y;
			return;
		}
		switch (this.getCurrentEAnchor()) {
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			this.ellipse.width = x - this.ellipse.x;
			this.ellipse.height = y - this.ellipse.y;				
			break;
		case SW:
			break;
		case EE:
			break;
		case WW:
			break;
		default:
			break;
		}
		this.setPoint(x, y);
	}
}