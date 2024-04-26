public class Jefe extends Enemigo{
    public final boolean jefe = true;
    Jefe(){
        vida = 10;
        nivel = Personaje.piso + 2;
        vida = GameManager.random(5) + nivel;
        ataque = GameManager.random(3)+ nivel;
    }
    @Override
    public static void recompensa(){
        int opcion = GameManager.random(3);
        int cantidad = 2+ (Personaje.piso -1);
        
       switch (opcion) {
        case ORO:
            Personaje.oro += cantidad*2;
            System.out.println("Has obtenido " + cantidad*2 + " monedas de oro");
            break;
        case EXPERIENCIA:
            GameManager.pj.subirExperiencia(cantidad);
            System.out.println("Has obtenido " + cantidad*2 + " puntos de experiencia");
            break;
        case Armadura:
            Personaje.armadura+=2;
            System.out.println("Has obtenido +2 de armadura");
            break;
       }
    }
}
