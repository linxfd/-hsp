import java.util.Vector;

/**
 * @version 1.0
 */
public class Leet {
    public static void main(String[] args) {
        System.out.println("asa");
        B b = new B();
        A a = new A();
        Vector<A> as = new Vector<>();
        Vector<B> bs = new Vector<>();
        for (int i = 0; i <bs.size();i++){
            as.add(bs.get(i));
        }
    }

}
class A{

}
class B extends A{

}
