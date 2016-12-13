package frames;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Vector;

import constants.GConstants;
import shapes.GShape;
import frames.GDrawingPanel;

public class GCursor {
	private GDrawingPanel dPanel;
	private GConstants.EAnchors eAnchor;
	
	public GCursor(){}
	public void init(GDrawingPanel dPanel){
		this.dPanel = dPanel;
	}
	public void changeCursor(int x, int y, Vector<GShape> shapeVector){
		for(GShape shape: shapeVector){
			eAnchor = shape.contains(x, y);
			if(eAnchor != null){
				switch(eAnchor){
				case NN:
					dPanel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
					return;
				case NE:
					dPanel.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
					return;
				case NW:
					dPanel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
					return;
				case SS:
					dPanel.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
					return;
				case SE:
					dPanel.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
					return;
				case SW:
					dPanel.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
					return;
				case EE:
					dPanel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
					return;
				case WW:
					dPanel.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
					return;
				case RR:
					//dPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
					Toolkit toolKit = Toolkit.getDefaultToolkit();
					dPanel.setCursor(toolKit.createCustomCursor(toolKit.getImage("rsc/rotateCursor.png"), new Point(16, 16), "rotation"));
					return;
				case MM:
					dPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
					return;
				}
			}
		}
		dPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
