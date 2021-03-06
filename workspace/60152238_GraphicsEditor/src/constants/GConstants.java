package constants;

import shapes.GShape;

import shapes.GEllipse;
import shapes.GLine;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GSelect;

public class GConstants {
	// JFrame attributes
	public final static String MAINFRAME_TITLE = "GraphicsEditor";
	public final static String FILEMENU_TITLE = "File";
	public final static String EDITMENU_TITLE = "Edit";
	
	public enum EAnchors {NN, NE, NW, SS, SE, SW, EE, WW, RR, MM};

	public static enum EMainFrame {
		X(100), Y(100), W(400), H(600);
		private int value;
		private EMainFrame(int value) {
			this.value = value;
		}
		public int getValue() { return this.value; }
	}
	public static enum EFileMenuItem {
		nnew("new"), 
		open("open"), 
		close("close"), 
		save("save"), 
		saveAs("saveAs"),
		print("print"),
		exit("exit");
		private String text;
		private EFileMenuItem(String text) {
			this.text = text;
		}
		public String getText() { return this.text; }
	}
	public static enum EEditMenuItem {
		cut("cut"), 
		copy("copy"), 
		paste("paste"), 
		delete("delete"), 
		redo("redo"),
		undo("undo"),
		group("group"),
		unGroup("unGroup");
		private String text;
		private EEditMenuItem(String text) {
			this.text = text;
		}
		public String getText() { return this.text; }
	}
	public static enum EDrawingType {
		TP, NP;
	}
	public static enum EToolBarButton {
		rectangle("rsc/rectangle.gif", "rsc/rectangleSLT.gif", new GRectangle()),
		ellipse("rsc/ellipse.gif", "rsc/ellipseSLT.gif", new GEllipse()),
		line("rsc/line.gif", "rsc/lineSLT.gif", new GLine()),
		polygon("rsc/polygon.gif", "rsc/polygonSLT.gif", new GPolygon()),
		select("rsc/select.gif", "rsc/selectSLT.gif", new GSelect());
		
		private String iconName;
		private String selectedIconName;
		private GShape shape;
		
		private EToolBarButton(
				String iconName, String selectedIconName, 
				GShape shape) {
			this.iconName = iconName;
			this.selectedIconName = selectedIconName;
			this.shape = shape;
		}
		public String getIconName() { return this.iconName; }
		public String getSelectedIconName() { return this.selectedIconName; }
		public GShape getShape() { return this.shape; }
	}
	
}
