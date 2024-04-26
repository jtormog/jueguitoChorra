import java.util.Scanner;
import java.util.InputMismatchException;

public class GameManager {

    static Scanner scanner = new Scanner(System.in);

    static Personaje pj = new Personaje();
    static Cofre cofre = new Cofre();

    static final char COFRE = 'C';
    static final char TIENDA = 'T';
    static final char ENEMIGO = 'E';
    static final char JEFE = 'J';


    public static char[][] tablero = new char[3][3];
    public static char[][] tableroVisible = new char[3][3];

    public static int castingNumero(){
        int numero = 0;
        boolean valido = false;

        while (!valido){
            try {
                System.out.println(" ");
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

    static void llenarTablero(){
        for (int i = 0; i< tablero.length; i++){
            for (int j = 0; j < tablero.length; j++){
                tablero[i][j] = ENEMIGO;
            }
        }
        for (int i = 0; i< tablero.length; i++){
            for (int j = 0; j < tableroVisible.length; j++){
                tableroVisible[i][j] = 'O';
            }
        }
        tablero[2][2] = JEFE;
        int random = (int) (Math.random()*2+1);
        switch (random){
            case 1:
                tablero[2][1] = TIENDA;
                break;
            case 2:
                tablero[1][2] = TIENDA;
                break;
        }
        random = (int) (Math.random()*4+1);

        switch (random){
            case 1:
                tablero[0][0] = COFRE;
                break;
            case 2:
                tablero[1][1] = COFRE;
                break;
            case 3:
                tablero[2][0] = COFRE;
                break;
            case 4:
                tablero[0][2] = COFRE;
                break;
        }
    }

    static void mostrarTablero(){
        for (int i = 0; i< tablero.length; i++){
            for (int j = 0; j < tablero.length; j++){
                if (Personaje.posicionVertical == i && Personaje.posicionHorizontal == j) {
                    System.out.print("\033[1;33m[\tX\t]\033[0m\t");
                    tableroVisible[i][j] = tablero[i][j];
                } else if (j == Personaje.posicionHorizontal + 1 && i == Personaje.posicionVertical) {
                    System.out.print("[\t" + tablero[i][j] + "\t]\t");
                    tableroVisible[i][j] = tablero[i][j];
                } else if (j == Personaje.posicionHorizontal && i == Personaje.posicionVertical + 1) {
                    System.out.print("[\t" + tablero[i][j] + "\t]\t");
                    tableroVisible[i][j] = tablero[i][j];
                } else {
                    System.out.print("[\t" + tableroVisible[i][j] + "\t]\t");
                }
            }
            System.out.println("\n\n");
        }
    }

    static void comprobarCasilla(){
        char casillaActual = tablero[Personaje.posicionVertical][Personaje.posicionHorizontal];

        switch (casillaActual){
            case COFRE:
                cofre.abrirCofre(pj);
                break;
            case TIENDA:
                Tienda.entrarTienda(pj);
                break;
            case ENEMIGO:
                pj.combate(casillaActual);
                break;
                case JEFE:
                pj.combate(casillaActual);
                if (pj.vida > 0){
                    Personaje.piso++;
                    Personaje.posicionHorizontal = 0;
                    Personaje.posicionVertical = 0;
                    GameManager.llenarTablero();

                    if (Personaje.piso != 4){
                        System.out.println("Has matado al jefe de la zona\nOtro cadaver más en tu camino hacia abandonar tu" +
                                " humanidad\n" +
                                "Pasas al piso " + Personaje.piso + "\n");
                    }
                }
                break;

        }
    }
}