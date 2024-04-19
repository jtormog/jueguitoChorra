

public class Main {
    static char[][] tablero = new char[3][3];
    static char[][] tableroVisible = new char[3][3];
    static Personaje pj = new Personaje();
    static Cofre cofre = new Cofre();
    static final int DERECHA = 1;
    static final int ABAJO = 2;

    static final char COFRE = 'C';
    static final char TIENDA = 'T';
    static final char ENEMIGO = 'E';
    static final char JEFE = 'J';

    public static void main(String[] args) {
        llenarTablero();

        while (Personaje.piso <4){
            comprobarCasilla();
            if (pj.vida <= 0) break;
            mostrarTablero();
            desplazamiento();
        }
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

    static void desplazamiento(){

        System.out.println("¿Hacia dónde te quieres mover?");
        int i = 1;

        if (Personaje.posicionHorizontal <2){
            System.out.println(i+". Derecha");
            ++i;
        }
        if (Personaje.posicionVertical <2) System.out.println(i+". Abajo");

        int opcion = GameManager.castingNumero();

        if (Personaje.posicionHorizontal >=2 && opcion == 1 || Personaje.posicionHorizontal >=2 && opcion == 2) opcion++;
        if (Personaje.posicionVertical >=2 && opcion == 2) opcion++;

        switch (opcion){
            case DERECHA:
                pj.desplazarseDerecha();
                break;
            case ABAJO:
                pj.desplazarseAbajo();
                break;
            default:
                System.out.println("Número invalido");
                break;
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
                pj.combate();
                break;
        }
    }

}