public class Tienda {
    static final int VIDA = 1;
    static final int ARMADURA = 2;
    static final int POCION = 3;
    static final int SALIR = 9;

    public static void entrarTienda(Personaje pj){
        System.out.println("\nEntras en una tienda");
        boolean comprando = Personaje.oro >1;

        if(!comprando) {
            System.out.println("Por desgracia no puedes comprar nada\n");
            System.out.println("Te marchas\n");
        }

        while (comprando){
            System.out.println("Tienes "+Personaje.oro +" monedas de oro");
            
            System.out.println("Puedes comprar:\n1. 3 Vida -- 2 monedas de oro");
            if (Personaje.oro >4){
                System.out.println("2. 1 Armadura -- 5 monedas de oro");
                System.out.println("3. 1 Poción -- 5 monedas de oro");
            } 
            System.out.println("\n\nIntroduce 9 para salir");

            int opcion = GameManager.castingNumero();
            if (Personaje.oro<5 && opcion == 2 || Personaje.oro<5 && opcion == 3) opcion = 0;

            switch (opcion){
                case VIDA:
                    if (pj.vida <= 17){
                    pj.vida+=3;
                    Personaje.oro-=2;}
                    else System.out.println("No puedes comprar más vida\n");
                    break;
                case ARMADURA:
                    Personaje.armadura++;
                    Personaje.oro-=5;
                    break;
                case POCION:
                    Personaje.pociones++;
                    Personaje.oro-=5;
                    break;
                case SALIR:
                    comprando = false;
                    System.out.println("Dejas la tienda");
                    break;
                default:
                    System.out.println("Numero invalido");
                    break;
            }
            if (Personaje.oro<2){
                System.out.println("No puedes comprar nada más en la tienda, te marchas\n");
                break;
            }
        }


    }
}
