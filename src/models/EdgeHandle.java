package models;
import java.awt.Color;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.RepaintManager;

import log.LogPanel;

import draw.PaintPanel;

public class EdgeHandle extends Thread{
	int step = 0;
	Boolean isExecuted;
	LogPanel logPanel;
	PaintPanel paintPanel;
	ArrayList<MyLine> arrLine;
	ArrayList<MyLine> result;
	int pointNumber;
	ArrayList<Integer> tempResult;
	public EdgeHandle(ArrayList<MyLine> arrLine, int pointNumber, PaintPanel paintPanel, LogPanel logPanel){
		this.logPanel = logPanel;
		this.paintPanel = paintPanel;
		this.pointNumber = pointNumber;
		this.arrLine = arrLine;
		result = new ArrayList<>();
	}
	public EdgeHandle(ArrayList<MyLine> arrLine, int pointNumber, PaintPanel paintPanel, LogPanel logPanel, Boolean isExecuted){
		this.logPanel = logPanel;
		this.paintPanel = paintPanel;
		this.pointNumber = pointNumber;
		this.arrLine = arrLine;
		this.isExecuted = isExecuted;
		result = new ArrayList<>();
	}
	
	@Override
	public void run() {
		super.run();
		tempResult = new ArrayList<>();
		sortArrLine();
			for(int i=0; i<arrLine.size(); i++){
				if(!checkCircle(arrLine.get(i).getIndexPointA(), arrLine.get(i).getIndexPointB())){
					tempResult.add(arrLine.get(i).getIndexPointA());
					tempResult.add(arrLine.get(i).getIndexPointB());
					result.add(arrLine.get(i));
				}
			}
			if(isExecuted==null)
			{
				for(int i=0; i<result.size(); i++)
					result.get(i).setLineColor(Color.RED);
				logPanel.setResultField(result);
			}
			else 
			{
				result.get(0).setLineColor(Color.RED);
				step = 0;
				logPanel.setResultField(result.get(step), result.get(step).getIndexPointA()
						+" - "+result.get(step).getIndexPointB()
						+": "+result.get(step).getCost(), result.get(step).getCost());
			}
				paintPanel.repaint();
	}
	public void runStep(){
		isExecuted = false;
		try{
			step++;
			if(step<result.size()){
				try{
					result.get(step).setLineColor(Color.RED);
					String str = "";
					int total = 0;
					for(int i=0; i<=step; i++)
					{
						str = str +" "+ result.get(i).getIndexPointA()
								+" - "+result.get(i).getIndexPointB()
								+": "+result.get(i).getCost();
						total+= result.get(i).getCost();
					}
					logPanel.setResultField(result.get(step), str, total);
				}
				catch(Exception ex){
					result.clear();
					logPanel.setResultField("");
					this.start();
				}
				paintPanel.repaint();
			}
			else{
				result.clear();
				isExecuted = false;
			}
		}
		catch(Exception ex){
			step--;
			result.clear();
			logPanel.setResultField("");
		}
	}
	void sortArrLine(){
		Collections.sort(arrLine, new MyLineComparator());
	}
	boolean checkCircle(int pointA, int pointB){
		if(!tempResult.contains(pointA)||!tempResult.contains(pointB)) return false;
		Boolean[] points = new Boolean[pointNumber+1];
		ArrayList<Integer> data = new ArrayList<>();
		for(int i=0; i<tempResult.size(); i++){
			if(tempResult.get(i)==pointB){
				if(i%2==0){
					data.add(tempResult.get(i+1));
					points[tempResult.get(i+1)]=true;
				}
				else{
					data.add(tempResult.get(i-1));
					points[tempResult.get(i-1)]=true;
				}
			}
		}
		int x=0;
		while(data.size()>0){
			x = data.remove(data.size()-1);
			if(x==pointA) return true;
			int var;
			for(int i=0; i<tempResult.size(); i++){
				if(tempResult.get(i)==x){
					if(i%2==0){
						var = tempResult.get(i+1);
						if(points[var]==null){
							points[var]=true;
							data.add(var);
						}
					}
					else{
						var = tempResult.get(i-1);
						if(points[var]==null){
							points[var] = true;
							data.add(var);
						}
					}
				}
			}
		}
		return false;
	}
}