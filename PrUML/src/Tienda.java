public class Tienda {
    static final int VIDA = 1;
    static final int ARMADURA = 2;
    static final int POCION = 3;
    static final int SALIR = 9;

    public static void entrarTienda(Personaje pj){
        System.out.println("\nEntras en una tienda");
        boolean comprando = Personaje.oro >2;

        if(!comprando) {
            System.out.println("Por desgracia no puedes comprar nada\n");
            System.out.println("Te marchas\n");
        }

        while (comprando){
            System.out.println("Tienes "+Personaje.oro +" monedas de oro");
            
            System.out.println("Puedes comprar:\n1. 2 Vida -- 3 monedas de oro");
            if (Personaje.oro >4) System.out.println("2. 1 Armadura -- 7 monedas de oro");
            if (Personaje.oro >9) System.out.println("3. 1 Poción -- 10 monedas de oro");
            System.out.println("\n\nIntroduce 9 para salir");

            int opcion = GameManager.castingNumero();
            if (Personaje.oro<5 && opcion == 2 || Personaje.oro<5 && opcion == 3) opcion++;
            if (Personaje.oro<10 && opcion == 3) opcion++;

            switch (opcion){
                case VIDA:
                    pj.vida+=2;
                    Personaje.oro-=3;
                    break;
                case ARMADURA:
                    Personaje.armadura++;
                    Personaje.oro-=7;
                    break;
                case POCION:
                    Personaje.pociones++;
                    Personaje.oro-=10;
                    break;
                case SALIR:
                    comprando = false;
                    System.out.println("Dejas la tienda");
                    break;
                default:
                    System.out.println("Numero invalido");
                    break;
            }
            if (Personaje.oro<3){
                System.out.println("No puedes comprar nada más en la tienda, te marchas\n");
                break;
            }
        }


    }
}
