import java.util.Scanner;
import java.util.InputMismatchException;

public class GameManager {

    static Scanner scanner = new Scanner(System.in);

    public static int castingNumero(){
        int numero = 0;
        boolean valido = false;

        while (!valido){
            try {
                numero = scanner.nextInt();
                valido = true;
            }catch (InputMismatchException e){
                scanner.next();
                System.out.println("Se esperaba otro tipo de valor");
            }
        }

        return numero;
    }

    public static int random(int max){
        return (int) (Math.random()*max+1);
    }
}
