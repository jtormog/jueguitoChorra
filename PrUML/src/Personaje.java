public class Personaje  extends Criatura {

    public static int armadura;
    public static int pociones;
    public static int oro;
    public static int piso;
    static int experiencia;
    static int numeroDeGolpes;

    static int posicionHorizontal;
    static int posicionVertical;


    static final char JEFE = 'J';
    static final char ENEMIGO = 'E';

    static final int ESQUELETO = 1;
    static final int VAMPIRO = 2;
    static final int MOCO = 3;

    static final int ATACAR = 1;
    static final int POCION = 2;

    static final int DERECHA = 1;
    static final int ABAJO = 2;

    static final char PUNTOS_ACTUALES = '■';

    public Personaje() {
        posicionHorizontal = 0;
        posicionVertical = 0;
        vida = 10;
        nivel = 1;
        piso = 1;
        experiencia = 0;
        armadura = 1;
    }

    public static void desplazarseDerecha() {
        posicionHorizontal++;
    }

    public static void desplazarseAbajo() {
        posicionVertical++;
    }

    public void combate(char tipoDeEnemigo){

        Enemigo enemigo = (tipoDeEnemigo == JEFE) ? new Jefe() : new Enemigo();

        boolean enemigoVivo;
        boolean personajeVivo;

        System.out.println("\nUn " + Enemigo.nombreEnemigo + " te corta el paso");
        do {
            System.out.print("\n\u001B[31mVida:");
            for (int i = 0; i < this.vida; i++) {
                System.out.print(PUNTOS_ACTUALES+" ");
                if (i == 9) System.out.print("\n     ");
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
            if (Personaje.pociones < 1 && opcion == 2) opcion = 0;

            switch (opcion) {
                case ATACAR:
                    atacar(enemigo);
                    break;
                case POCION:
                    System.out.println("Te bebes una poción");
                    this.vida +=6;
                    Personaje.pociones--;
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
            if (enemigo.jefe) Jefe.recompensa();
            if (!enemigo.jefe){
                System.out.println("\nHas matado al pobre " + Enemigo.nombreEnemigo + "...\nAl final tú eras el único monstruo de la habitación\n");
                enemigo.recompensa();
            }
        } else {
            System.out.println("\nEl " + Enemigo.nombreEnemigo + " te ha hecho un favor...\nTu alma vaga por esta mazmorra que ahora también es tu tumba\n");
        }

    }

    public void atacar(Enemigo enemigo) {
        this.ataque = 0;
        int danyoTotal = 0;
        for (int i = 0; i < nivel; i++) {
            int random = GameManager.random(6);
            if (nivel > 1){
                if (i==0 ) System.out.println("Primer golpe:\n");
                if (i==1 ) System.out.println("Segundo golpe:\n");
                if (i==2 ) System.out.println("Tercer golpe:\n");
            }
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
                danyoTotal += this.ataque;
                if (nivel >1) {
                    System.out.println("Haciendole" +this.ataque + " de daño\n");
                }
            } else {
                danyoTotal += random;
                if (nivel>1) {
                    System.out.println("Golpeas al " + Enemigo.nombreEnemigo + " y le haces " + random + " de daño\n");
                }
            }
        }
        System.out.println("Has hecho un daño total de: " + danyoTotal+"\n");

        enemigo.vida -= danyoTotal;
    }

    public void contraAtaque(Enemigo enemigo) {
        int random = GameManager.random(6);
        if (random == 1) {
            System.out.println("El " + Enemigo.nombreEnemigo + " no te ataca...\nParece que no quiere hacerte daño");
        } else if (random == 6) {
            System.out.println("El " + Enemigo.nombreEnemigo + " te golpea en un hueco de tu armadura y te hace " + enemigo.ataque + " de daño");
            this.vida -= enemigo.ataque;
        } else {
            if (enemigo.ataque - armadura <= 0) {
                System.out.println("El " + Enemigo.nombreEnemigo + " golpea tu armadura\nNo te hace daño");
            } else {
                System.out.println("El " + Enemigo.nombreEnemigo + " te hace " + (enemigo.ataque - armadura) + " de daño");
                this.vida -= (enemigo.ataque - armadura);
            }
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
                Personaje.desplazarseDerecha();
                break;
            case ABAJO:
                Personaje.desplazarseAbajo();
                break;
            default:
                System.out.println("Número invalido");
                break;
        }

    }
    public void subirExperiencia(int experiencia){
        Personaje.experiencia += experiencia;
        if (Personaje.experiencia >= 6){
            this.nivel++;
            System.out.println("Has subido a nivel 2\n");
        }
        if (experiencia >= 18) {
            this.nivel++;
            System.out.println("Has subido a nivel 3\n");
        }
        if (experiencia >= 36) {
            this.nivel++;
            System.out.println("Has subido a nivel 4\n");
            
        }
    }
}




