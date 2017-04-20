package models;


import java.awt.geom.Ellipse2D.Float;
import java.awt.geom.Line2D.Double;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * ----------------- @author nguyenvanquan7826 -----------------
 * ---------------nguyenvanquan7826.wordpress.com --------------
 */
public class MyData implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<MyPoint> arrMyPoint;
	private ArrayList<MyLine> arrMyLine;

	final int r = 15, r2 = 2 * r;
	public void addPoint(MyPoint mp){
		arrMyPoint.add(mp);
	}
	public void addLine(Double l, int indexPointA, int indexPointB, int cost){
		arrMyLine.add(new MyLine(l, indexPointA, indexPointB, cost));
	}
	public ArrayList<MyPoint> getArrMyPoint() {
		return arrMyPoint;
	}

	public void setArrMyPoint(ArrayList<MyPoint> arrMyPoint) {
		this.arrMyPoint = arrMyPoint;
	}

	public ArrayList<MyLine> getArrMyLine() {
		return arrMyLine;
	}

	public void setArrMyLine(ArrayList<MyLine> arrMyLine) {
		this.arrMyLine = arrMyLine;
	}
	public MyPoint getPointAt(int index){
		return arrMyPoint.get(index);
	}
	public MyLine getLineAt(int index){
		return arrMyLine.get(index);
	}
	public int getArrPointSize(){
		return arrMyPoint.size();
	}
	public int getArrLineSize(){
		return arrMyLine.size();
	}
	public MyData() {
		arrMyPoint = new ArrayList<MyPoint>();
		arrMyLine = new ArrayList<MyLine>();
	}
	public void clear(){
		for(int i=0; i<arrMyPoint.size(); i++){
			arrMyPoint.remove(i);
		}
		for(int i=0; i<arrMyLine.size(); i++){
			arrMyLine.remove(i);
		}
	}
}
