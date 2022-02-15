package labs.week2.nestedclasses;
	
public class OuterClass {					// Top-level Class

    //some types of nested class 
    public int x = 42;
    
    public void method1() {
        // local classes are inner classes but not members of the outer class
        class LocalClass {					// Local Inner Class

            public void localPrint() {
                System.out.println("In local class");
                System.out.println(x);
            }
        }
        LocalClass lc = new LocalClass();
        lc.localPrint();
    }

    public void method2() {
        Runnable r = new Runnable() {		// Anonymous Local Inner Class

            @Override
            public void run() {
                System.out.println("In an anonymous local class method");
                System.out.println(x);
            }
        };
        r.run();
    }
    public Runnable r = new Runnable() {	// Anonymous Inner Class

        @Override
        public void run() {
            System.out.println("In an anonymous class method");
            System.out.println(x);
        }
    };
    Object o = new Object() {				// Anonymous Inner Class

        @Override
        public String toString() {
            return "In an anonymous class method";
        }
    };

    public class InnerClass {				// Member Inner Class

        // hides OuterClass x
        public int x = 43;
        //static requires final
        public static final int y = 44;

        public void innerPrint() {
            System.out.println("In a inner class method");
            System.out.println(x);
        }
    }

    // not an inner class because it is static
    public static class StaticNestedClass {	// Static Nested Class

        public void staticNestedPrint() {
            System.out.println("In a static nested class method");
            //compile error
            //System.out.println(x);
            
        }
    }

    public class A {						// Member Inner Class

        public class B {					// Member Inner Class

            public void method() {
                class C {  					// Local Inner Class
                }
                System.out.println("A.B.method()...");
            }
        }
    }
}