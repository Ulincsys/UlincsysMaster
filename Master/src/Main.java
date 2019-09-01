import ulincsys.pythonics.lists.*;
import static ulincsys.pythonics.Util.*;
import static ulincsys.extras.Strings.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		print(isPalindrome("Test"), isPalindrome(444));
		print(reverse(input("What's the string you'd like to reverse?")));
	}
}