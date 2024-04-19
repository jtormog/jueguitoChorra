public class Enemigo extends Criatura{
    static int tipoDeEnemigo;
    public static String nombreEnemigo;
    static final int ESQUELETO = 1;
    static final int VAMPIRO = 2;
    static final int MOCO = 3;

    public Enemigo(){
        tipoDeEnemigo = GameManager.random(3);
        nivel = Personaje.piso;
        vida = GameManager.random(5) * nivel;
        ataque = GameManager.random(3)* nivel;

        switch (tipoDeEnemigo){
            case ESQUELETO -> nombreEnemigo = "esqueleto";
            case VAMPIRO -> nombreEnemigo = "vampiro";
            case MOCO -> nombreEnemigo = "moco";
        }
    }
}
