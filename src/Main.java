import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Game myGame = new Game();
        myGame.loadVideoTitle();

        myGame.countNameMovie();

        System.out.println("Hi! You need guess the movie name!");

        System.out.println("Guess a movie name: " + myGame.hideMovieName());

        System.out.println("Guess a letter: ");
        do {
            String letter = myGame.inputLetter();
            myGame.checkGuessLetter();
            System.out.print("Guess a movie name: ");
            System.out.println(myGame.charBlankMovieName);
            System.out.println("You have " + myGame.points + " points");
        } while (!myGame.gameEnd);

        if (myGame.gameEnd && myGame.win) {
            System.out.println("You guessed: " + myGame.nameMovie);
            System.out.println("You WON");
        }
        else {System.out.println("You lost your points. You LOST");}
        // System.out.println(myGame.playerLetter);   // print a letter
    }
}
