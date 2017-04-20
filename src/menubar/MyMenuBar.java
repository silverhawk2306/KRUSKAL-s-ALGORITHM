package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import draw.PaintPanel;

import models.MainModel;
import models.MyData;

public class MyMenuBar extends JMenuBar implements ActionListener{
	MyFile myFile;
	JMenuItem openItem, exitItem, saveItem, helpItem, aboutItem;
	public MyMenuBar(MainModel mainModel, PaintPanel paintPanel) {
		// TODO Auto-generated constructor stub
		myFile = new MyFile(mainModel.getMyData(), paintPanel);
		add(menuFile());
		add(moreFile());
	}
	JMenu menuFile(){
		JMenu menuFile = new JMenu("File");
		menuFile.add(openItem = createJMenuItem("Open"));
		menuFile.add(saveItem = createJMenuItem("Save"));
		menuFile.add(exitItem = createJMenuItem("Exit"));
		return menuFile;
	}
	JMenu moreFile(){
		JMenu moreFile = new JMenu("More");
		moreFile.add(helpItem = createJMenuItem("Help"));
		moreFile.add(aboutItem = createJMenuItem("About"));
		return moreFile;
	}
	JMenuItem createJMenuItem(String title){
		JMenuItem menuItem = new JMenuItem(title);
		menuItem.addActionListener(this);
		return menuItem;
	}
	void actionFileOpen(){
		myFile.openFile();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == openItem) myFile.openFile();
		if(e.getSource() == saveItem) myFile.saveFile();
		if(e.getSource() == exitItem) System.exit(0);
	}
}
