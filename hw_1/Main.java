import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        OptionalDouble average = numbers.stream()
                .filter(num -> num % 2 == 0) 
                .mapToInt(Integer::intValue) 
                .average(); 

        if (average.isPresent()) {
            System.out.println("Среднее значение четных чисел: " + average.getAsDouble());
        } else {
            System.out.println("Четных чисел нет.");
        }
    }
}
