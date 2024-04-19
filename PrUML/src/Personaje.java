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

        System.out.println(STR."""

Un \{Enemigo.nombreEnemigo} te corta el paso""");
        do {
            System.out.println("\n¿Qué haces?");
            System.out.println("1. Atacar");
            if (Personaje.pociones > 0) System.out.println("2. Beber una poción");
            int opcion = GameManager.castingNumero();

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
        
        if (!enemigoVivo) System.out.println(STR."""
Has matado al pobre \{Enemigo.nombreEnemigo}...
Al final tú eras el único monstruo de la habitación
""");
        else{
            System.out.println(STR."""

El \{Enemigo.nombreEnemigo} te ha hecho un favor...
Tu alma vaga por esta mazmorra que ahora también es tu tumba""" );
        }

    }

    public void atacar(Enemigo enemigo) {
        this.ataque = 0;
        for (int i = 0; i < enemigo.nivel; i++) {
            int random = GameManager.random(6);

            if (random == 1) {
                System.out.println("Has fallado...\nHa sido bastante lamentable...");

                switch (Enemigo.tipoDeEnemigo) {
                    case ESQUELETO -> System.out.println("Agradeces que el esqueleto no tenga ojos y nadie haya " +
                            "visto el ridículo que acabas de hacer");
                    case VAMPIRO -> System.out.println("Te sientes juzgado por el vampiro");
                    case MOCO -> System.out.println("Le das asco al moco");
                }

            } else if (random == 6) {
                System.out.println(STR."Golpeas con toda tu furia al pobre \{Enemigo.nombreEnemigo}");
                this.ataque = random*2;
            } else this.ataque = random;
        }
        System.out.println(STR."""

Has hecho un daño total de: \{this.ataque}""");

        enemigo.vida -= this.ataque;
    }

    public void contraAtaque(Enemigo enemigo) {

        int random = GameManager.random(6);
        if (random == 1) {
            System.out.println(STR."""
El \{Enemigo.nombreEnemigo} no te ataca...
Parece que no quiere hacerte daño""");

        } else if (random == 6) {
            System.out.println(STR."El \{Enemigo.nombreEnemigo} te golpea en un hueco de tu armadura y te hace \{enemigo.ataque} de daño");
            this.vida -= enemigo.ataque;

        } else {
            System.out.println(STR."El \{Enemigo.nombreEnemigo} te hace \{enemigo.ataque} de daño");
            this.vida -= (enemigo.ataque - armadura);
        }
    }
}




