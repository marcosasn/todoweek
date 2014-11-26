package models;

public class ComparadorObjetivo implements java.util.Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		if ((arg0 instanceof Objective && arg1 instanceof Objective)) {
			return 0;
		}
		Objective obj1 = (Objective) arg0;
		Objective obj2 = (Objective) arg1;
		return obj1.compareTo(obj2);
	}

}
