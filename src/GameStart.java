import java.util.Scanner;
import java.util.*;

public class GameStart {
    Entities[][] land = new Entities[7][7];
    Entities [] turnsArray;
    int turn = 0;
    int goblinsAlive;
    int humansAlive;
    int iCharacter;
    int jCharacter;
    int turnsAux ;
    String move = "" ;
    public GameStart(){
        createLand(this.land);
    }
    public void play(Scanner scanner){
        generatePlayers(land);
        goblinsAlive = countGoblins(land);
        humansAlive = countHumans(land);
        turnsAux = (goblinsAlive + humansAlive) -1 ;
        Humans humanAux = new Humans();
        Goblins goblinAux = new Goblins();

        System.out.println("==================This is the Map==========================");
        System.out.println("\uD83D\uDC7D Goblins: "+goblinsAlive);
        System.out.println("\uD83E\uDD16 Humans: "+humansAlive);

        printLand(land);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
       do {

           if (turn  > turnsAux){
               turn = 0;
           }


           if (turnsArray[turn] != null) {
               System.out.println("================MAP================");
               System.out.println("                                                                                 \uD83D\uDC7D : "+goblinsAlive);
               System.out.println("                                                                                 \uD83E\uDD16 : "+humansAlive);
               showPlayerTurn(land,turnsArray[turn]);
               printLand(land);
               System.out.println("====================================");
               if (turnsArray[turn].getClass() == humanAux.getClass()){
                   System.out.println("\uD83E\uDD16 Youre A Human \uD83E\uDD16");
               }else{
                   System.out.println("\uD83D\uDC7D Youre A Goblin \uD83D\uDC7D");
               }
               System.out.println("================================");
               System.out.println("Move your character: ");
               System.out.println();
               System.out.println("  w               (up) ");
               System.out.println("a s d      (Left)(down)(Right)");
               System.out.println();
               System.out.println("Write yor move: ");

               move = scanner.next();
               move = move.toLowerCase();

               iCharacter = turnsArray[turn].getI();
               jCharacter = turnsArray[turn].getJ();

               switch (move){
                   case "w":
                       if (moveUp(iCharacter,jCharacter,land,turnsArray[turn],turn ,turnsArray)){
                           //System.out.println("Move Up");
                           turn++;
                           break;
                       }else{
                           break;
                       }
                   case "s":
                       if (moveDown(iCharacter,jCharacter,land,turnsArray[turn],turn ,turnsArray)){
                           //System.out.println("Move Down");
                           turn++;
                           break;
                       }else{
                           break;
                       }
                   case "a":
                       if (moveLeft(iCharacter,jCharacter,land,turnsArray[turn],turn ,turnsArray)){
                           //System.out.println("Move Left");
                           turn++;
                           break;
                       }else{
                           break;
                       }
                   case "d":
                       if (moveRight(iCharacter,jCharacter,land,turnsArray[turn],turn ,turnsArray)){
                           //System.out.println("Move Right");
                           turn++;
                           break;
                       }else{
                           break;
                       }
               }

           }else{
               turn++;
           }
           goblinsAlive = countGoblins(land);
           humansAlive = countHumans(land);

           if (humansAlive <= 0 || goblinsAlive <= 0){
               break;
           }
       }while(humansAlive != 0 || goblinsAlive !=0);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       printLand(land);
        System.out.println();
       if (humansAlive <= 0){

           System.out.println("\uD83D\uDC7D Goblins wins \uD83D\uDC7D");
       }else if (goblinsAlive <= 0){
           System.out.println("\uD83E\uDD16 Humans wins \uD83E\uDD16 ");
       }

        System.out.println();
        System.out.println("Thanks for playing");

       scanner.close();
    }

    private boolean moveUp(int i, int j,Entities[][] land,Entities entity,int turn,Entities[] turnsArray){
        Goblins auxGoblin = new Goblins();
        Humans auxHuman = new Humans();
        boolean aux = false;

        if (i-1 < 0){
            System.out.println("You cant go out of the land");
            aux = false;
        }else {
            //Si el el jugador es  un  humano y la posicion a donde se mueve es un goblin
            if ( (entity.getClass() == auxHuman.getClass()) && (land[i-1][j].getClass() == auxGoblin.getClass()) ){
                if (startBattle((Humans) entity,(Goblins) land[i-1][j]).equals("Human Win")){
                    land[i-1][j] = entity;
                    land[i-1][j].setEmoji();
                    entity.setPosition(i-1,j);
                    land[i][j] = new Land();
                    for (int k = 0; k < turnsArray.length; k++) {
                        if (turnsArray[k] != null){
                            if (  ((land[i-1][j].getI() == turnsArray[k].getI()) && (land[i-1][j].getJ()==turnsArray[k].getJ()))){
                                turnsArray[k] = null;
                            }
                        }
                    }
                }else {
                    turnsArray[turn] = null;
                    land[i][j] = new Land();
                }
                aux=true;
            }
            //si el jugador es un goblin y la posicion a donde se mueve es un humano
            else if ((entity.getClass() == auxGoblin.getClass()) && (land[i-1][j].getClass() == auxHuman.getClass())) {

                if(startBattle((Humans) land[i-1][j],(Goblins) entity).equals("Goblin Win")){
                    land[i-1][j] = entity;
                    land[i-1][j].setEmoji();
                    entity.setPosition(i-1,j);
                    land[i][j] = new Land();
                    for (int k = 0; k < turnsArray.length; k++) {
                        if (turnsArray[k] != null) {
                            if ((land[i-1][j].getI() == turnsArray[k].getI()) && (land[i-1][j].getJ()==turnsArray[k].getJ())){
                                turnsArray[k] = null;
                            }
                        }
                    }
                }else{
                    turnsArray[turn] = null;
                    land[i][j] = new Land();
                }
                aux = true;
            }
            //Si el jugador es un humano y la posicion a donde se quiere mover hay otro humano
            else if ( (entity.getClass() == auxHuman.getClass()) && (land[i-1][j].getClass() == auxHuman.getClass())  ) {
                System.out.println("There is a  human in the place, move to other side");
                aux = false;
            }
            //Si el jugador es un goblin y la posicion a donde se quiere mover hay otro goblin
            else if ( (entity.getClass() == auxGoblin.getClass()) && (land[i-1][j].getClass() == auxGoblin.getClass())  ) {
                System.out.println("There is a goblin in the place, move to other side");
                aux = false;
            }
            else{
                land[i][j].setEmoji();
                land[i-1][j] = land[i][j];
                entity.setPosition(i-1,j);
                land[i][j] = new Land();
                aux = true;
            }

        }
        return aux;
    }

    private boolean moveDown(int i, int j,Entities[][] land, Entities entity,int turn,Entities[] turnsArray){
        Goblins auxGoblin = new Goblins();
        Humans auxHuman = new Humans();
        boolean aux = false;

        if (i+1 > land.length-1){
            System.out.println("You cant go out of the land");
            aux = false;

        }else {

            //Si el el jugador es  un  humano y la posicion a donde se mueve es un goblin
            if ( (entity.getClass() == auxHuman.getClass()) && (land[i+1][j].getClass() == auxGoblin.getClass()) ){
                if (startBattle((Humans) entity,(Goblins) land[i+1][j]).equals("Human Win")){
                    land[i+1][j] = entity;
                    land[i+1][j].setEmoji();
                    entity.setPosition(i+1,j);
                    land[i][j] = new Land();
                    for (int k = 0; k < turnsArray.length; k++) {
                        if (turnsArray[k] != null){
                            if ((land[i+1][j].getI() == turnsArray[k].getI()) && (land[i+1][j].getJ()==turnsArray[k].getJ())){
                                turnsArray[k] = null;
                            }
                        }
                    }
                }else {
                    land[i][j] = new Land();
                    turnsArray[turn] = null;
                }
                aux=true;
            }
            //si el jugador es un goblin y la posicion a donde se mueve es un humano
            else if ((entity.getClass() == auxGoblin.getClass()) && (land[i+1][j].getClass() == auxHuman.getClass())) {
                if (startBattle((Humans) land[i+1][j],(Goblins) entity ).equals("Goblin Win")){
                    land[i+1][j] = entity;
                    land[i+1][j].setEmoji();
                    entity.setPosition(i+1,j);
                    land[i][j] = new Land();
                    for (int k = 0; k < turnsArray.length; k++) {
                        if (turnsArray[k] != null){
                            if ((land[i+1][j].getI() == turnsArray[k].getI()) && (land[i+1][j].getJ()==turnsArray[k].getJ())){
                                turnsArray[k] = null;
                            }
                        }
                    }
                }else {
                    land[i][j] = new Land();
                    turnsArray[turn] = null;
                }
                aux=true;
            }
            //Si el jugador es un humano y la posicion a donde se quiere mover hay otro humano
            else if ( (entity.getClass() == auxHuman.getClass()) && (land[i+1][j].getClass() == auxHuman.getClass())  ) {
                System.out.println("There is a  human in the place, move to other side");
                aux = false;
            }
            //Si el jugador es un goblin y la posicion a donde se quiere mover hay otro goblin
            else if ( (entity.getClass() == auxGoblin.getClass()) && (land[i+1][j].getClass() == auxGoblin.getClass())  ) {
                System.out.println("There is a  goblin in the place, move to other side");
                aux = false;
            }else{

                land[i][j].setEmoji();
                land[i+1][j] = land[i][j];
                entity.setPosition(i+1,j);
                land[i][j] = new Land();
                aux = true;
            }

        }
        return  aux;
    }

    private boolean moveLeft(int i, int j,Entities[][] land, Entities entity,int turn,Entities[] turnsArray){
        Goblins auxGoblin = new Goblins();
        Humans auxHuman = new Humans();
        boolean aux = false;

        if (j-1 < 0){
            System.out.println("You cant go out of the land");
            aux = false;;
        }else {

            //Si el el jugador es  un  humano y la posicion a donde se mueve es un goblin
            if ( (entity.getClass() == auxHuman.getClass()) && (land[i][j-1].getClass() == auxGoblin.getClass()) ){
                if (startBattle((Humans) entity,(Goblins) land[i][j-1]).equals("Human Win")){
                    land[i][j-1] = entity;
                    land[i][j-1].setEmoji();
                    entity.setPosition(i,j-1);
                    land[i][j] = new Land();
                    for (int k = 0; k < turnsArray.length; k++) {
                        if (turnsArray[k] != null) {
                            if ((land[i][j-1].getI() == turnsArray[k].getI()) && (land[i][j-1].getJ()==turnsArray[k].getJ())){
                                turnsArray[k] = null;
                            }
                        }
                    }
                }else {
                    turnsArray[turn] = null;
                    land[i][j] = new Land();
                }
                aux=true;
            }
            //si el jugador es un goblin y la posicion a donde se mueve es un humano
            else if ((entity.getClass() == auxGoblin.getClass()) && (land[i][j-1].getClass() == auxHuman.getClass())) {
                if (startBattle((Humans) land[i][j-1],(Goblins) entity ).equals("Goblin Win")){
                    land[i][j-1] = entity;
                    land[i][j-1].setEmoji();
                    entity.setPosition(i,j-1);
                    land[i][j] = new Land();
                    for (int k = 0; k < turnsArray.length; k++) {
                        if (turnsArray[k] != null){
                            if ((land[i][j-1].getI() == turnsArray[k].getI()) && (land[i][j-1].getJ()==turnsArray[k].getJ())){
                                turnsArray[k] = null;
                            }
                        }
                    }
                }else {
                    turnsArray[turn] = null;
                    land[i][j] = new Land();
                }
                aux=true;
            }
            //Si el jugador es un humano y la posicion a donde se quiere mover hay otro humano
            else if ( (entity.getClass() == auxHuman.getClass()) && (land[i][j-1].getClass() == auxHuman.getClass())  ) {
                System.out.println("There is a  human in the place, move to other side");
                aux = false;
            }
            //Si el jugador es un goblin y la posicion a donde se quiere mover hay otro goblin
            else if ( (entity.getClass() == auxGoblin.getClass()) && (land[i][j-1].getClass() == auxGoblin.getClass())  ) {
                System.out.println("There is a  goblin in the place, move to other side");
                aux = false;
            }

            else{
                land[i][j].setEmoji();
                land[i][j-1] = land[i][j];
                entity.setPosition(i,j-1);
                land[i][j] = new Land();
                aux =  true;
            }

        }
        return  aux;
    }

    private boolean moveRight(int i, int j,Entities[][] land, Entities entity,int turn,Entities[] turnsArray){
        Goblins auxGoblin = new Goblins();
        Humans auxHuman = new Humans();
        boolean aux = false;

        if (j+1 > land.length-1){
            System.out.println("You cant go out of the land");
            aux = false;
        }else {

            //Si el el jugador es  un  humano y la posicion a donde se mueve es un goblin
            if ( (entity.getClass() == auxHuman.getClass()) && (land[i][j+1].getClass() == auxGoblin.getClass()) ){
                if (startBattle((Humans) entity,(Goblins) land[i][j+1]).equals("Human Win")){
                    land[i][j+1] = entity;
                    land[i][j+1].setEmoji();
                    entity.setPosition(i,j+1);
                    land[i][j] = new Land();
                    for (int k = 0; k < turnsArray.length; k++) {
                        if (turnsArray[k] != null){
                            if ((land[i][j+1].getI() == turnsArray[k].getI()) && (land[i][j+1].getJ()==turnsArray[k].getJ())){
                                turnsArray[k] = null;
                            }
                        }
                    }
                }else {
                    land[i][j] = new Land();
                    turnsArray[turn] = null;
                }
                aux=true;
            }
            //si el jugador es un goblin y la posicion a donde se mueve es un humano
            else if ((entity.getClass() == auxGoblin.getClass()) && (land[i][j+1].getClass() == auxHuman.getClass())) {
                if (startBattle((Humans) land[i][j+1],(Goblins) entity ).equals("Goblin Win")){
                    land[i][j+1] = entity;
                    land[i][j+1].setEmoji();
                    entity.setPosition(i,j+1);
                    land[i][j] = new Land();
                    for (int k = 0; k < turnsArray.length; k++) {
                        if (turnsArray[k] != null){
                            if ((land[i][j+1].getI() == turnsArray[k].getI()) && (land[i][j+1].getJ()==turnsArray[k].getJ())){
                                turnsArray[k] = null;
                            }
                        }

                    }
                }else {
                    land[i][j] = new Land();
                    turnsArray[turn] = null;
                }
                aux=true;
            }
            //Si el jugador es un humano y la posicion a donde se quiere mover hay otro humano
            else if ( (entity.getClass() == auxHuman.getClass()) && (land[i][j+1].getClass() == auxHuman.getClass())  ) {
                System.out.println("There is a  human in the place, move to other side");
                aux = false;
            }
            //Si el jugador es un goblin y la posicion a donde se quiere mover hay otro goblin
            else if ( (entity.getClass() == auxGoblin.getClass()) && (land[i][j+1].getClass() == auxGoblin.getClass())  ) {
                System.out.println("There is a  goblin in the place, move to other side");
                aux = false;
            }

            else{
                land[i][j].setEmoji();
                land[i][j+1] = land[i][j];
                entity.setPosition(i,j+1);
                land[i][j] = new Land();
                aux = true;
            }

        }
        return aux;
    }

    private void showPlayerTurn(Entities [][] land , Entities entity){
        land[entity.getI()][entity.getJ()].setEmojiSelected();
    }

    private String startBattle(Humans human,Goblins goblin){
        String winner = "";
        boolean battle = true;
        System.out.println("========================= \u2694 Batlle \u2694 ===========================================");
        while (battle){

            System.out.println("Human life: " + human.getLife() + "           Goblin life: " + goblin.getLife());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            human.setAttack(randomAttack());
            System.out.println("Human attacked to goblin: " + human.getAttack() );
            goblin.setLife(goblin.getLife()-human.getAttack());
            if (goblin.getLife() <= 0){
                winner = "Human Win";
                goblin.setLife(0);
                break;
            }
            System.out.println();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            goblin.setAttack(randomAttack());
            System.out.println("Human life: " + human.getLife() + "           Goblin life: " + goblin.getLife());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Goblin attacked to Human: " + goblin.getAttack() );
            human.setLife(human.getLife()-goblin.getAttack());
            if (human.getLife() <= 0){
                winner = "Goblin Win";
                human.setLife(0);
                break;
            }
            System.out.println();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Human life: " + human.getLife() + "           Goblin life: " + goblin.getLife());
        System.out.println();
        System.out.println(winner);
        System.out.println();
        return winner;
    }

    public int randomAttack(){
        int max = 0;
        int min =30;
        int range = max - min + 1;
        return (int) (Math.random() * range) + min ;
    }

    private void createLand(Entities[][] land){
        for (int i = 0 ; i < land.length ; i++){
            for (int j = 0; j < land.length ; j++) {
                    land[i][j] = new Land();
            }
        }
    }

    private void generatePlayers(Entities[][] land){
        int iAux;
        int jAux;
        int max = land.length-1;
        int min =0;
        int range = max - min + 1;
        Land landaux = new Land();
        int numberHumans = 2;
        int numberGoblins = 2;
        int turnAux = 1 ;
        this.turnsArray = new Entities[(numberHumans+numberGoblins)];

        while (numberHumans> 0 && numberGoblins > 0 ) {

            if (numberHumans > 0){
                iAux = (int) (Math.random() * range) + min;
                jAux = (int) (Math.random() * range) + min;
                if (land[iAux][jAux].getClass() == landaux.getClass()) {
                    land[iAux][jAux] = new Humans(turnAux, iAux, jAux);
                    generateTurns(land[iAux][jAux]);
                    turnAux++;
                    numberHumans--;
                }
            }
            if (numberGoblins > 0){
                iAux = (int) (Math.random() * range) + min;
                jAux = (int) (Math.random() * range) + min;
                if (land[iAux][jAux].getClass() == landaux.getClass()) {
                    land[iAux][jAux] = new Goblins(turnAux, iAux, jAux);
                    generateTurns(land[iAux][jAux]);
                    turnAux++;
                    numberGoblins--;
                }
            }
        }
    }

    private void generateTurns(Entities entity){

        for (int i = 0; i < turnsArray.length; i++) {
            if (turnsArray[i] == null){
                turnsArray[i] = entity;
                break;
            }
        }
    }

    public void printLand(Entities[][] land) {
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land.length; j++) {
                    System.out.printf(land[i][j].getEmoji());
            }
            System.out.println();
        }
    }

    private int countHumans(Entities [][] land){
        int aux = 0;
        Humans auxHuman = new Humans();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j <land.length ; j++) {
                if (land[i][j].getClass() == auxHuman.getClass()){
                    aux++;
                }
            }
        }
        return aux;
    }

    private int countGoblins(Entities [][] land){
        int aux = 0;
        Goblins auxGoblin = new Goblins();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j <land.length ; j++) {
                if (land[i][j].getClass() == auxGoblin.getClass()){
                    aux++;
                }
            }
        }
        return aux;
    }


}
