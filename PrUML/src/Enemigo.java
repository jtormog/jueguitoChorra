public class Enemigo extends Criatura{
    static int tipoDeEnemigo;
    public static String nombreEnemigo;
    static final int ESQUELETO = 1;
    static final int VAMPIRO = 2;
    static final int MOCO = 3;
    public final boolean jefe = false;

    static final int ORO = 1;
    static final int EXPERIENCIA = 2;
    static final int Armadura = 3;

    public Enemigo(){
        tipoDeEnemigo = GameManager.random(3);
        nivel = Personaje.piso;
        vida = GameManager.random(5) + nivel;
        ataque = GameManager.random(3)+ nivel;

        switch (tipoDeEnemigo){
            case ESQUELETO:
             nombreEnemigo = "esqueleto";
             break;
            case VAMPIRO:
             nombreEnemigo = "vampiro";
                break;
            case MOCO:
             nombreEnemigo = "moco";
             break;
        }
    }

    public void recompensa(){
        int opcion = GameManager.random(3);
        int cantidad = 2+ (nivel-1);
        
       switch (opcion) {
        case ORO:
            System.out.println("Has obtenido " + cantidad + " monedas de oro");
            Personaje.oro += cantidad;
            break;
        case EXPERIENCIA:
            System.out.println("Has obtenido " + cantidad + " puntos de experiencia");
            GameManager.pj.subirExperiencia(cantidad);
            break;
        case Armadura:
            Personaje.armadura++;
            System.out.println("Has obtenido +1 de armadura");
            break;
       }
    }
}
