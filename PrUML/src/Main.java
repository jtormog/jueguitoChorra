

public class Main {
    public static void main(String[] args) {
        GameManager.llenarTablero();
        boolean jugando = true;

        while (jugando){
            GameManager.comprobarCasilla();
            if (GameManager.pj.vida <= 0 || Personaje.piso == 4) break;

            if (!(Personaje.posicionHorizontal == 0 && Personaje.posicionVertical == 0 && Personaje.piso>1)) {
                GameManager.mostrarTablero();
            }

            if (Personaje.posicionHorizontal == 0 && Personaje.posicionVertical == 0 && Personaje.piso>1) {
                GameManager.comprobarCasilla();
                if (GameManager.pj.vida <= 0) jugando = false;
                if (!jugando) break;
                GameManager.mostrarTablero();
            }
            if (!jugando) break;
            Personaje.desplazamiento();
        }
        if (Personaje.piso == 4) System.out.println("Tras dejar inumerables cadaveres a tu paso has salido de la mazmorra,"+
                "has ganado, y has aprobado Entornos de Desarrollo, pero la persona que entró en esta mazmorra no es la misma que ha salido\n"+
        "¿Ha valido la pena?");
    }



}