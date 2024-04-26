

public class Main {
    public static void main(String[] args) {
        GameManager.llenarTablero();

        while (Personaje.piso <4){
            if (Personaje.piso == 4) break;
            GameManager.comprobarCasilla();
            if (GameManager.pj.vida <= 0) break;
            GameManager.mostrarTablero();
            Personaje.desplazamiento();
        }
        if (Personaje.piso == 4) System.out.println("Tras dejar inumerables cadaveres a tu paso has salido de la mazmorra\n"
        + "has ganado, y has aprobado Entornos de Desarrollo, pero la persona que entró en esta mazmorra no es la misma que ha salido\n"+
        "¿Ha valido la pena?");
        }
    }

}