import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Main {
    public static void main(String[] args) {
        Class<String> stringClass = String.class;

        Method[] methods = stringClass.getDeclaredMethods();

        System.out.println("Методы класса String с сигнатурой:");
        for (Method method : methods) {
            System.out.print(method.getReturnType().getSimpleName() + " " + method.getName() + "(");
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                System.out.print(parameters[i].getType().getSimpleName() + " " + parameters[i].getName());
                if (i < parameters.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }
    }
}
