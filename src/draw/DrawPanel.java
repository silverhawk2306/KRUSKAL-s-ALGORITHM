	package draw;

import informative.InformativeModel;
import informative.InformativePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;


import log.LogPanel;
import log.LogPanel;
import log.ResultPanel;
import menubar.MyMenuBar;
import models.MainModel;
import models.MyData;


public class DrawPanel extends JPanel{
		public PaintPanel paintPanel;
	public DrawPanel(MainModel mainModel) {
		// TODO Auto-generated constructor stub
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		this.setBorder(new TitledBorder(""));		
		paintPanel = new PaintPanel(mainModel);
		OptionPanel optionPanel = new OptionPanel(paintPanel);
		add(optionPanel);
		add(paintPanel);
		this.setBorder(new TitledBorder(" "));
	}
	
	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			//UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
		}
		catch(Exception e){
			System.out.println("FAILED!");
		}
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(DrawPanel.class.getResource("/draw/icon/"+"mainIcon.png"));
		
		JFrame mainFrame = new JFrame("Kruskal Execution");
		mainFrame.setIconImage(img);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.setLocationRelativeTo(null);
		mainFrame.setSize(1200, 720);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
		
		MainModel mainModel = new MainModel();
		ResultPanel resultPanel = new ResultPanel();
		DrawPanel drawPanel = new DrawPanel(mainModel);
		InformativePanel informativePanel = new InformativePanel(mainModel, drawPanel.paintPanel, resultPanel.logPanel);
		MyMenuBar myMenuBar = new MyMenuBar(mainModel, drawPanel.paintPanel);
		
		mainFrame.add(myMenuBar, BorderLayout.NORTH);
		mainFrame.add(drawPanel, BorderLayout.CENTER);
		mainFrame.add(informativePanel, BorderLayout.LINE_START);
		mainFrame.add(resultPanel, BorderLayout.SOUTH);
		
		mainFrame.setVisible(true);
	}
}
