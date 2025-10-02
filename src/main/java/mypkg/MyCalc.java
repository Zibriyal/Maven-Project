package mypkg;

public class MyCalc {
	public int sum (int a , int b)
	{
		return a+b;
	}
	public int diff (int a , int b)
	{
		return a-b;
	}
	
	public static void main (String[] args) {
		MyCalc Calc = new MyCalc();
		System.out.println("sum is " + Calc.sum(20,10));
		System.out.println("Diff is " + Calc.diff(20,10));

	}

}
