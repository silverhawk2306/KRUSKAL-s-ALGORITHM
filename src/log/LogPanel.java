package log;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import models.MyLine;

public class LogPanel extends JTextField{
	int total = 0;
	JTextField resultField;
	public LogPanel() {
		setBorder(new TitledBorder("Log"));
		setResultField();
	}
	void setResultField(){
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.setPreferredSize(new Dimension(100, 150));
		this.setText("Path: ");
		this.setEditable(false);
		this.setBackground(Color.WHITE);
	}
	public void setResultField(ArrayList<MyLine> arrMyLine){
		total = 0;
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=1; i<arrMyLine.size(); i++){
			stringBuffer.append("  "+arrMyLine.get(i).getIndexPointA()
					+" -"+arrMyLine.get(i).getIndexPointB()+": "+
					arrMyLine.get(i).getCost()+"  ");
			total+=arrMyLine.get(i).getCost();
		}
		
		this.setText("Path: "+stringBuffer.toString()+" Total: "+total);
	}
	public void setResultField(MyLine myLine, String str, int total){
		this.setText("Path: "+str+"\n Total: "+total);
	}
	public void setResultField(String str){
		this.setText("Path: ");
	}
}
