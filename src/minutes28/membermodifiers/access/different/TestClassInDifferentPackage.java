package minutes28.membermodifiers.access.different;

import minutes28.membermodifiers.access.ExampleClass;

public class TestClassInDifferentPackage {
	public static void main(String[] args) {
		ExampleClass example = new ExampleClass();

		example.publicVariable = 5;
		example.publicMethod();

		// privateVariable,privateMethod are not visible
		// Below Lines, uncommented, would give compiler error
		// example.privateVariable=5; //COMPILE ERROR
		// example.privateMethod();//COMPILE ERROR

		// protectedVariable,protectedMethod are not visible
		// Below Lines, uncommented, would give compiler error
		// example.protectedVariable = 5; //COMPILE ERROR
		// example.protectedMethod();//COMPILE ERROR

		// defaultVariable,defaultMethod are not visible
		// Below Lines, uncommented, would give compiler error
		// example.defaultVariable = 5;//COMPILE ERROR
		// example.defaultMethod();//COMPILE ERROR
	}
}