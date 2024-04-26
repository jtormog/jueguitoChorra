public class Jefe extends Enemigo{
    static final int DRAGON_ERRANTE = 1;
    static final int CABALLERO_OSCURO = 2;
    static final int DEMONIO_DE_LA_LLAMA = 3;

    Jefe(){
        esJefe = true;
        vida = 10;
        nivel = Personaje.piso + 2;
        vida = GameManager.random(5) + nivel;
        ataque = GameManager.random(3)+ nivel;

        tipoDeEnemigo = GameManager.random(3);

        switch (tipoDeEnemigo){
            case DRAGON_ERRANTE:
                nombreEnemigo = "dragón errante";
                break;
            case CABALLERO_OSCURO:
                nombreEnemigo = "caballero oscuro";
                break;
            case DEMONIO_DE_LA_LLAMA:
                nombreEnemigo = "demonio de la llama";
                break;
        }
    }
    public void recompensa(){
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
