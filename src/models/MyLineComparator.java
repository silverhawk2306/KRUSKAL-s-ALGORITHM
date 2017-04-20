package models;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class MyLineComparator implements Comparator<MyLine>{

	@Override
	public int compare(MyLine myLine1, MyLine myLine2) {
		if(myLine1==null && myLine2==null) return 0;
		if(myLine1==null) return -1;
		if(myLine2==null) return 1;
		int value = myLine1.getCost()-myLine2.getCost();
		if(value!=0) return value;
		value = myLine1.getIndexPointA() - myLine2.getIndexPointA();
		if(value!=0) return value;
		return (myLine1.getIndexPointB()-myLine2.getIndexPointB());
	}
}
