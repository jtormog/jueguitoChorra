public class Cofre{

    public String recompensa(Personaje pj){
        int random = GameManager.random(3);
        String recompensa ="";

        switch (random){
            case 1:
                Personaje.oro+=7;
                recompensa = "7 monedas de oro";
                break;
            case 2:
                recompensa = "2 monedas de oro";
                if (pj.vida <=18){
                    pj.vida +=2;
                    recompensa += " y 2 de vida";
                }
                Personaje.oro+=2;

                break;
            case 3:
                Personaje.armadura+=1;
                Personaje.oro+=2;
                recompensa = "2 monedas de oro y 1 de armadura";
                break;
        }
        return recompensa;
    }

    public void abrirCofre(Personaje pj){
        System.out.println("\n\nEncuentras un cofre en medio de la sala, al abrirlo obtienes:\n" + recompensa(pj) + "\n");
    }
}
