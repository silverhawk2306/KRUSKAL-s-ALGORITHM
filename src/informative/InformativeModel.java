package informative;

import log.LogPanel;
import log.ResultPanel;
import models.EdgeHandle;
import models.MyLine;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import draw.PaintPanel;

import models.MyData;

public class InformativeModel extends DefaultTableModel{
	EdgeHandle edgeHandle;
	public static boolean isExecuted = false;
	MyData myData;
	public InformativeModel(MyData myData) {
		this.myData = myData;
			setColumnCount(myData.getArrMyPoint().size());
			setRowCount(myData.getArrMyPoint().size());
	}
	public void fireTableColumnChanged(){
		//setColumnCount(myData.getArrMyPoint().size()-1);
		setColumnCount(myData.getArrMyPoint().size()-1);
		//setNumRows(myData.getArrMyPoint().size()-1);
		setNumRows(myData.getArrMyPoint().size()-1);
		fireTableDataChanged();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		columnIndex++;
		rowIndex++;
		String value = "∞";
		ArrayList<MyLine> arr = myData.getArrMyLine();
		for(int i=0; i< arr.size(); i++){
			MyLine myLine = arr.get(i);
			if(myLine.getIndexPointA()==columnIndex && myLine.getIndexPointB()==rowIndex){
				value=""+myLine.getCost();
				break;
			}
		}
		return value;
	}
	public void actionRunAll(PaintPanel paintPanel, LogPanel logPanel){
		EdgeHandle edgeHandle = new EdgeHandle(myData.getArrMyLine(), myData.getArrMyPoint().size(), paintPanel, logPanel);
		edgeHandle.start();
	}
	public void actionRunStep(PaintPanel paintPanel, LogPanel logPanel){
		System.out.println("isE: "+isExecuted);
		if(!isExecuted){
			System.out.println("NEW HANDLE");
			edgeHandle = new EdgeHandle(myData.getArrMyLine(), myData.getArrMyPoint().size(), paintPanel, logPanel, isExecuted);
			edgeHandle.start();
			isExecuted = true;
		}
		else{
			edgeHandle.runStep();
		}
	}
	@Override
	public String getColumnName(int columnIndex) {
		if(myData.getArrMyPoint().size()==0) return "No Value";
		return ""+(columnIndex+1);
	}
	public MyData getMyData() {
		return myData;
	}
	public void setMyData(MyData myData) {
		this.myData = myData;
	}
}
