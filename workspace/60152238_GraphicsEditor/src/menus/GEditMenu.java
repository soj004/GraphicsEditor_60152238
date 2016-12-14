package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EEditMenuItem;
import frames.GDrawingPanel;
import shapes.GGroup;
import shapes.GShape;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private GDrawingPanel drawingPanel;
	private Vector<GShape> tempShape;
	private Vector<GGroup> groups;
	private GGroup curGroup;
	
	public GEditMenu() {
		super(GConstants.EDITMENU_TITLE);
		tempShape = new Vector<GShape>();
		ActionHandler actionHandler = new ActionHandler();
		
		for (GConstants.EEditMenuItem eMenuItem: GConstants.EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}
	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
	
	private void undo(){
		drawingPanel.setShape(drawingPanel.getStack().undo());
	}
	private void redo(){
		drawingPanel.setShape(drawingPanel.getStack().redo());
	}
	private void copy() throws CloneNotSupportedException{
		tempShape.clear();
		for(GShape shape: drawingPanel.getShapeVector()){
			if(shape.isSelected()){
				tempShape.add(shape.clone());
			}
			shape.setSelected(false);
		}
	}
	private void paste() throws CloneNotSupportedException{
		if(!tempShape.isEmpty()){
			for(GShape shape: tempShape){
				drawingPanel.getShapeVector().add(shape.clone());
			}
		}
	}
	private void group(){
		curGroup = new GGroup();
		Vector<GShape> groupShape = new Vector<GShape>();
		getSelectShape(groupShape);
		
		for(GShape shapeManager: groupShape){
			curGroup.add(shapeManager);
			shapeManager.setSelected(false);
		}
		curGroup.setSelected(true);
		drawingPanel.getShapeVector().add(curGroup);
		groups.add(curGroup);
	}
	private void unGroup(){
		for(int i = 0; i < groups.size(); i++){
			if(groups.get(i).isSelected()){
				curGroup = groups.get(i);
			};
		}
		if(curGroup != null){
			for(GShape shape: curGroup.getGroupList()){
				drawingPanel.getShapeVector().add(shape);
				shape.setGroupShape(null);
			}
			drawingPanel.getShapeVector().remove(curGroup);
			curGroup.getGroupList().clear();
		}
	}
	
	private void getSelectShape(Vector<GShape> shapes){
		shapes.clear();
		for(int i = 0; i < drawingPanel.getShapeVector().size(); i++){
			if(drawingPanel.getShapeVector().get(i).isSelected()){
				shapes.add(drawingPanel.getShapeVector().get(i));
			}
		}
		for(int i = 0; i < shapes.size(); i++){
			drawingPanel.getShapeVector().remove(shapes.get(i));
		}
	}
	
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(EEditMenuItem.undo.name())) {
				undo();
			} else if (event.getActionCommand().equals(EEditMenuItem.redo.name())) {
				redo();
				
			} else if(event.getActionCommand().equals(EEditMenuItem.copy.name())){
				try {
					copy();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			} else if(event.getActionCommand().equals(EEditMenuItem.paste.name())){
				try {
					paste();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else if (event.getActionCommand().equals(EEditMenuItem.group.name())) {
				group();
				
			} else if (event.getActionCommand().equals(EEditMenuItem.unGroup.name())) {
				unGroup();
				
			}

		}		
	}
}
