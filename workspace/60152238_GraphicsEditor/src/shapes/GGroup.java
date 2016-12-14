package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import constants.GConstants.EDrawingType;

public class GGroup extends GShape{
	private static final long serialVersionUID = 1L;
	
	private Vector<GShape> groupList;
	
	public GGroup(){
		super(EDrawingType.TP, new Rectangle());
		this.shape = (Rectangle)this.getShape();
		groupList = new Vector<GShape>();
	}
	public Vector<GShape> getGroupList(){
		return groupList;
	}
	
	@Override
	public void setOrigin(int x, int y) {
		for(GShape shape: groupList){
			shape.setOrigin(x, y);
		}
	}

	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void addPoint(int x, int y) {
		for(GShape shape: groupList){
			shape.addPoint(x, y);
		}
	}

	@Override
	public void resize(int x, int y) {
		// TODO Auto-generated method stub
	}

	@Override
	public void move(int x, int y) {
		for(GShape shape: groupList){
			shape.move(x, y);
		}
	}
	
	public void add(GShape newShape){
		newShape.gShape = this;
		groupList.add(0, newShape);
		if(groupList.size() == 1){
			this.shape = newShape.getBounds();
		} else{
			this.shape = this.shape.getBounds().createUnion(newShape.getBounds());
		}
	}
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		for(GShape shape: groupList){
			shape.draw(g2d);
		}
	}
}
