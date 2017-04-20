package models;

import informative.InformativeModel;

public class MainModel {
	MyData myData;
	InformativeModel informativeModel;
	public MainModel() {
		// TODO Auto-generated constructor stub
		myData = new MyData();
		informativeModel = new InformativeModel(myData);
	}
	public InformativeModel getInformativeModel() {
		return informativeModel;
	}
	public void setInformativeModel(InformativeModel informativeModel) {
		this.informativeModel = informativeModel;
	}
	public MyData getMyData() {
		return myData;
	}
	public void setMyData(MyData myData) {
		this.myData = myData;
	}
	
}
