package shapes;

import java.util.Vector;

public class GSelect extends GRectangle{
	private static final long serialVersionUID = 1L;
	
	public void selectShape(Vector<GShape> shapes){
		for(GShape shapeManager: shapes){
			if(shape.contains(shapeManager.getBounds())){
				shapeManager.setSelected(true);
			}
		}
	}
	public GShape clone(){
		return new GSelect();
	}
}
