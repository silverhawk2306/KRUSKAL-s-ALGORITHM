package draw;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.Transient;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import models.MyPopupMenu;

public class OptionPanel extends JPanel implements ActionListener{
	private MyPopupMenu popupMenu;
	private PaintPanel myDraw;
	private JButton updateBt, pointBt, lineBt, moveBt, backspacePointBt, backspaceLineBt, clearBt;
	public OptionPanel(PaintPanel myDraw){
		this.myDraw = myDraw;
		setLayout(new BoxLayout(OptionPanel.this, BoxLayout.Y_AXIS));
		this.setBackground(Color.WHITE);
		initPanel();
		initPopupMenu();
	}
	void initPopupMenu(){
		popupMenu = new MyPopupMenu();
		popupMenu.add(createMenuItem("Change Cost", 0, 0));
		popupMenu.add(createMenuItem("Delete", 0, 0));
		myDraw.setComponentPopupMenu(popupMenu);
	}
	private JMenuItem createMenuItem(String title, int keyEvent, int event){
		JMenuItem item = new JMenuItem(title);
		item.setMnemonic(keyEvent);
		item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, event));
		item.addActionListener(this);
		return item;
	}
	void initPanel(){
		Icon icon;

		icon = getIcon("pointIcon.png");
		add(pointBt = createButtonImage(icon, "Draw Point"));
		//BufferedImage bi = ImageIO.read(new File(OptionPanel.class.getResource("/draw/icon/"+"pointIcon.png")));

		icon = getIcon("lineIcon.png");
		add(lineBt = createButtonImage(icon, "Draw Line"));

		icon = getIcon("moveIcon.png");
		add(moveBt = createButtonImage(icon, "Move Point"));

		icon = getIcon("backspacePointIcon.png");
		add(backspacePointBt = createButtonImage(icon, "Backspace Point"));
		
		icon = getIcon("backspaceLineIcon.png");
		add(backspaceLineBt = createButtonImage(icon, "Backspace Line"));
		
		icon = getIcon("clearIcon.png");
		add(clearBt = createButtonImage(icon, "Clear Graph"));
	}
	private JButton createButtonImage(Icon icon, String toolTip) {
		//System.out.println("LINK: "+icon);
		JButton btn = new JButton(icon);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(true);
		btn.setRolloverIcon(icon);
		btn.setMargin(new Insets(0, 0, 0, 0));
		//btn.setPreferredSize(new Dimension(70, 70));
		//btn.setSize(70, 70);
		btn.addActionListener(this);
		btn.setToolTipText(toolTip);
		return btn;
	}
	private ImageIcon getIcon(String link) {
		return new ImageIcon(OptionPanel.class.getResource("/draw/icon/"+link));
	}
	void actionDrawPoint() {
		myDraw.setDraw(1);
		setDrawResultOrStep(false);
	}
	private void setDrawResultOrStep(boolean check) {
		myDraw.setDrawResult(check);
		myDraw.setDrawStep(check);
	}
	private void actionDrawLine() {
		myDraw.setDraw(2);
		setDrawResultOrStep(false);
	}
	private void actionNew() {
		setDrawResultOrStep(false);
		myDraw.setResetGraph(true);
		myDraw.repaint();
		myDraw.init();
	}
	private void actionBackspacePoint(){
		myDraw.backspacePoint();
		//myDraw.repaint();
	}
	private void actionBackspaceLine(){
		myDraw.backspaceLine();
		myDraw.repaint();
	}
	private void showDialogChangeCost(){
		int index = myDraw.indexLineContain(popupMenu.getPoint());
		if(index>0){
			myDraw.changeCost(index);
		}
		else JOptionPane.showMessageDialog(null, "Haven't Line Selected!");
	}
	private void showDialogDelete() {
		int index = myDraw.indexPointContain(popupMenu.getPoint());
		if (index <= 0) {
			index = myDraw.indexLineContain(popupMenu.getPoint());
			if (index > 0) {
				// show message dialog
				models.MyLine ml = myDraw.getData().getArrMyLine().get(index);
				String message = "Do you want delete the line from "
						+ ml.getIndexPointA() + " to " + ml.getIndexPointB();
				int select = JOptionPane.showConfirmDialog(this, message,
						"Delete line", JOptionPane.OK_CANCEL_OPTION);
				if (select == 0) {
					myDraw.deleteLine(index);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Haven't point or line seleced!");
			}
		} else {
			// show message dialog
			String message = "Do you want delete the point " + index;
			int select = JOptionPane.showConfirmDialog(null, message,
					"Delete point", JOptionPane.OK_CANCEL_OPTION);
			if (select == 0) {
				myDraw.deletePoint(index);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == pointBt) actionDrawPoint();
		 if(e.getSource()== moveBt) myDraw.setDraw(3);
		else if(e.getSource()==lineBt) actionDrawLine();
		else if(e.getSource()== clearBt) actionNew();
		else if(e.getSource()== backspacePointBt) actionBackspacePoint();
		else if(e.getSource() == backspaceLineBt) actionBackspaceLine();
		 
		if(e.getActionCommand()=="Change Cost") showDialogChangeCost();
		else if(e.getActionCommand()=="Delete") showDialogDelete();
	}
}
