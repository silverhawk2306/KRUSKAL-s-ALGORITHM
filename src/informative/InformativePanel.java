package informative;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ChoiceFormat;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import draw.DrawPanel;
import draw.PaintPanel;

import log.LogPanel;
import log.ResultPanel;
import models.MainModel;
import models.MyData;

public class InformativePanel extends JPanel implements ActionListener
{
	CheckboxGroup typeGroup;
	Checkbox undirectedBox, directedBox;
	JScrollPane jp;
	MyData myData;
	JButton runAllBt, runStepBt;
	JTable table;
	InformativeModel informativeModel;
	Choice beginChoice, endChoice;
	PaintPanel paintPanel;
	LogPanel logPanel;
	public InformativePanel(MainModel mainModel, PaintPanel paintPanel, LogPanel logPanel) {
		this.logPanel = logPanel;
		this.paintPanel = paintPanel;
		this.informativeModel = mainModel.getInformativeModel();
		this.myData = mainModel.getInformativeModel().getMyData();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		table = new JTable(informativeModel);
		jp = new JScrollPane(table);
		jp.setPreferredSize(new Dimension(250, 530));
		jp.setBorder(new TitledBorder("Information"));
		
		//add(mapTypePanel());
		//add(choicePanel());
		add(jp);
		add(executePanel());
	}
	public void notifyPointChanged(){
		beginChoice.removeAll();
		endChoice.removeAll();
		beginChoice.add("Begin");
		endChoice.add("End");
		for(int i=0; i<myData.getArrMyPoint().size()-1; i++){
			beginChoice.add(""+(i+1));
			endChoice.add(""+(i+1));
		}
		informativeModel.fireTableColumnChanged();
		notifyLineChanged();
	}
	public void notifyLineChanged(){
		informativeModel.fireTableDataChanged();
	}
	JPanel choicePanel(){
		beginChoice = new Choice();
		beginChoice.add("Begin");
		endChoice = new Choice();
		endChoice.add("End");
		
		JPanel choicePanel = new JPanel();
		choicePanel.setBorder(new TitledBorder("Point"));
		choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.X_AXIS));
		choicePanel.add(beginChoice);
		choicePanel.add(endChoice);
		return choicePanel;
	}
	JPanel executePanel(){
		runAllBt = new JButton("Run All");
		runStepBt = new JButton("Run Step");
		runAllBt.setPreferredSize(runStepBt.getPreferredSize());
		JPanel executePanel = new JPanel();
		executePanel.setBorder(new TitledBorder("Execute"));
		//executePanel.setLayout(new BoxLayout(executePanel, BoxLayout.X_AXIS));
		//executePanel.setPreferredSize(new Dimension())
		//executePanel.getPreferredSize().width = jp.getPreferredSize().width;
		runAllBt.addActionListener(this);
		runStepBt.addActionListener(this);
		
		executePanel.add(runAllBt);
		executePanel.add(runStepBt);
		return executePanel;
	}
	void actionRunAll(){
		informativeModel.actionRunAll(paintPanel, logPanel);
	}
	JPanel mapTypePanel(){
		typeGroup = new CheckboxGroup();
		undirectedBox = new Checkbox("Undirected", typeGroup, true);
		directedBox = new Checkbox("Directed", typeGroup, false);
		
		JPanel mapTypePanel = new JPanel();
		mapTypePanel.setBorder(new TitledBorder("Map Type"));
		mapTypePanel.add(undirectedBox);
		mapTypePanel.add(directedBox);
		return mapTypePanel;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == runAllBt) actionRunAll();
		if(event.getSource()== runStepBt) informativeModel.actionRunStep(paintPanel, logPanel);
	}
}
