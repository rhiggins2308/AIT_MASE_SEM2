package labs.week2.nestedclasses;

public class NestedClassesMain {
    
    public static void main(String[] args) {
        
        OuterClass co = new OuterClass();
        //In local class
        //42
        //co.method1();
        
        //In an anonymous local class method
        //42
        //co.method2();

        //In an anonymous class method
        //42
        //co.r.run();
        
        //In a inner class method
        //43
        //OuterClass.InnerClass i = co.new InnerClass();
        //i.innerPrint();
        
        //OuterClass.StaticNestedClass sn = new OuterClass.StaticNestedClass();
        //sn.staticNestedPrint();
        
        // A.B.method()...
        //OuterClass.A.B inner = co.new A().new B();
        //nested.method();
    }
}