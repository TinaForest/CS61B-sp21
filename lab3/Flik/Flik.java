import java.util.Objects;

/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        if(a!=b) {
            System.out.println("a" + a);
            System.out.println("b" + b);
        }
        return Objects.equals(a, b);
    }
}
