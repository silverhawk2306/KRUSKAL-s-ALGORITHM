package menubar;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import draw.PaintPanel;

import models.MyData;
import models.MyLine;
import models.MyPoint;

public class MyFile {
	MyData myData;
	PaintPanel paintPanel;
	public MyFile(MyData myData, PaintPanel paintPanel) {
		// TODO Auto-generated constructor stub
		this.myData = myData;
		this.paintPanel = paintPanel;
	}
	public void openFile(){
		JFileChooser j = new JFileChooser();
		j.setCurrentDirectory(new java.io.File("."));
		//j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int status = j.showSaveDialog(null);
		if(status == JFileChooser.APPROVE_OPTION){
			File file = j.getSelectedFile();
			actionOpenFile(file);
		}
	}
	public void saveFile(){
		JFileChooser j = new JFileChooser();
		j.setCurrentDirectory(new java.io.File("."));
		//j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int status = j.showSaveDialog(null);
		if(status == JFileChooser.APPROVE_OPTION){
			File file = j.getSelectedFile();
			actionSaveFile(file);
		}
	}
	void actionOpenFile(File file){
		paintPanel.init();
		String regex = "\\s+";
		boolean flag = true;
		myData.clear();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s=br.readLine()) != null){
				if(s.equals("Line")){
					flag= false;
					continue;
				}
				if(s.equals("Point")){
					flag = true;
					continue;
				}
				String[] line = s.split(regex);
				if(flag){
					paintPanel.paintPoint(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
				}
				else{
					paintPanel.addLineToList(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void actionSaveFile(File file){
		try {
			FileOutputStream fos = new FileOutputStream(file);
			PrintWriter printWriter = new PrintWriter(fos);
			ArrayList<MyPoint> arrPoint = myData.getArrMyPoint();
			printWriter.flush();
			for(int i=0; i<arrPoint.size(); i++){
				MyPoint myPoint = arrPoint.get(i);
				printWriter.println(myPoint.getP().x+" "+myPoint.getP().y);
				printWriter.flush();
			}
			ArrayList<MyLine> arrLine = myData.getArrMyLine();
			printWriter.println("Line");
			printWriter.flush();
			for(int i=1; i< arrLine.size(); i++){
				MyLine myLine = arrLine.get(i);
				printWriter.println(myLine.getIndexPointA()+" "+myLine.getIndexPointB()+" "+myLine.getCost());
				printWriter.flush();
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
