public class Personaje  extends Criatura {

    public static int armadura;
    public static int pociones;
    public static int oro;
    public static int piso;
    static int posicionHorizontal;
    static int posicionVertical;
    static int experiencia;
    static int numeroDeGolpes;

    static final int ESQUELETO = 1;
    static final int VAMPIRO = 2;
    static final int MOCO = 3;

    static final int ATACAR = 1;
    static final int POCION = 2;

    static final char PUNTOS_ACTUALES = '■';

    public Personaje() {
        posicionHorizontal = 0;
        posicionVertical = 0;
        vida = 5;
        nivel = 1;
        piso = 1;
        experiencia = 0;
    }

    public void desplazarseDerecha() {
        posicionHorizontal++;
    }

    public void desplazarseAbajo() {
        posicionVertical++;
    }

    public void combate() {
        Enemigo enemigo = new Enemigo();
        boolean enemigoVivo;
        boolean personajeVivo;

        System.out.println("\nUn " + Enemigo.nombreEnemigo + " te corta el paso");
        do {
            System.out.print("\n\u001B[31mVida:");
            for (int i = 0; i < this.vida; i++) {
                System.out.print(PUNTOS_ACTUALES+" ");
            }
            if (Personaje.armadura > 0) {
                System.out.print("\n\u001B[34mArmadura:");
                for (int i = 0; i < Personaje.armadura; i++) {
                    System.out.print(PUNTOS_ACTUALES+" ");
                }
            }
            if (Personaje.pociones > 0) {
                System.out.print("\n\u001B[35mPociones:");
                for (int i = 0; i < Personaje.pociones; i++) {
                    System.out.print(PUNTOS_ACTUALES+" ");
                }
            }
            System.out.println("\u001B[0m\n\n¿Qué haces?");
            System.out.println("1. Atacar");
            if (Personaje.pociones > 0) System.out.println("2. Beber una poción");
            int opcion = GameManager.castingNumero();
            if (Personaje.pociones < 1 && opcion == 2) {
                System.out.println("Numero invalido");
                opcion = 0;
            }

            switch (opcion) {
                case ATACAR:
                    atacar(enemigo);
                    break;
                case POCION:
                    if (Personaje.pociones > 0) {
                        System.out.println("Te bebes una poción");
                        this.vida +=6;
                        Personaje.pociones--;
                    } else System.out.println("No puedes usar ninguna poción");
                    break;
                default:
                    System.out.println("Numero invalido");
                    break;
            }
            enemigoVivo = enemigo.vida > 0;
            if (enemigoVivo) contraAtaque(enemigo);
            personajeVivo = this.vida > 0;
            
        } while (enemigoVivo && personajeVivo);
        
        if (!enemigoVivo) {
            System.out.println("\nHas matado al pobre " + Enemigo.nombreEnemigo + "...\nAl final tú eras el único monstruo de la habitación\n");
        } else {
            System.out.println("\nEl " + Enemigo.nombreEnemigo + " te ha hecho un favor...\nTu alma vaga por esta mazmorra que ahora también es tu tumba\n");
        }

    }

    public void atacar(Enemigo enemigo) {
        this.ataque = 0;
        for (int i = 0; i < enemigo.nivel; i++) {
            int random = GameManager.random(6);

            if (random == 1) {
                System.out.println("Has fallado...\nHa sido bastante lamentable...");

                switch (Enemigo.tipoDeEnemigo) {
                    case ESQUELETO:
                        System.out.println("Agradeces que el esqueleto no tenga ojos y nadie haya " +
                                "visto el ridículo que acabas de hacer\n");
                        break;
                    case VAMPIRO:
                        System.out.println("Te sientes juzgado por el vampiro\n");
                        break;
                    case MOCO:
                        System.out.println("Le das asco al moco\n");
                        break;
                }

            } else if (random == 6) {
                System.out.println("\nGolpeas con toda tu furia al pobre "+ Enemigo.nombreEnemigo+"\n");
                this.ataque = random*2;
            } else {
                this.ataque = random;
            }
        }
        System.out.println("Has hecho un daño total de: " + this.ataque+"\n");

        enemigo.vida -= this.ataque;
    }

    public void contraAtaque(Enemigo enemigo) {
        int random = GameManager.random(6);
        if (random == 1) {
            System.out.println("El " + Enemigo.nombreEnemigo + " no te ataca...\nParece que no quiere hacerte daño");
        } else if (random == 6) {
            System.out.println("El " + Enemigo.nombreEnemigo + " te golpea en un hueco de tu armadura y te hace " + enemigo.ataque + " de daño");
            this.vida -= enemigo.ataque;
        } else {
            if (enemigo.ataque - armadura < 0) {
                System.out.println("El " + Enemigo.nombreEnemigo + " golpea tu armadura\nNo te hace daño");
            } else {
                System.out.println("El " + Enemigo.nombreEnemigo + " te hace " + enemigo.ataque + " de daño");
                this.vida -= (enemigo.ataque - armadura);
            }
        }
    }
}




