import ulincsys.pythonics.lists.*;
import static ulincsys.pythonics.Util.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Plist sortTest = new Plist();
		
		sortTest.fill(50);
		sortTest.add("This", "is", "a", "test", 57.999);
		sortTest.read(10);
		sortTest.sortAlpha();
		sortTest.sortInts();
		sortTest.read(10);
	}

}
