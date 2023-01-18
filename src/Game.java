import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Game {
    private String playerLetter = null;
    private ArrayList movie = new ArrayList<String>();
    public String nameMovie;
    private String blankMovieName;
    boolean gameEnd = false;
    private String containerLetter = "";
    char[] charBlankMovieName;
    char[] charNameMovie;
    int points = 10;
    boolean win = false;

/*
    public static void main(String[] args) throws FileNotFoundException {
        Game myGame = new Game();
        myGame.loadVideoTitle();

        myGame.countNameMovie();

        System.out.println("Hi! You need guess the movie name!");

        System.out.println("Guess a movie name: " + (myGame.nameMovie));

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
*/

    // Method that load data from file (video.txt)
    public void loadVideoTitle() throws FileNotFoundException {
        try {
            File file = new File("src/video.txt");
            if (file.exists()) {
                Scanner filescanner = new Scanner(file);
                while (filescanner.hasNextLine()) {
                    String line = filescanner.nextLine();
                    movie.add(line);
                }
                //    System.out.println(movie);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File missing!");
            throw exception;
        }
    }

    // Method random movie name
    public String randomMovie(){
        int movieIndex = (int) (Math.random()*movie.size());
        return (String) movie.get(movieIndex);
    }

    // Count how many letters are in the name of the movie
    public void countNameMovie(){
        this.nameMovie = randomMovie();
        String[] nameM = nameMovie.split(" ");
        List<String> nameMovieList = Collections.singletonList(Arrays.toString(nameM));
    }


    public String hideMovieName(){
        blankMovieName = nameMovie.trim().replaceAll("[a-zA-Z]","_");
        charBlankMovieName = blankMovieName.toCharArray();
        charNameMovie = nameMovie.toCharArray();
        return blankMovieName;
    }

    // Checking if the input letter is in the name of the movie
    public void checkGuessLetter(){
        containerLetter = containerLetter + playerLetter;
        if (nameMovie.contains(playerLetter)){
            int indexM = nameMovie.indexOf(playerLetter);
            do {
                charBlankMovieName[indexM] = playerLetter.charAt(0);
                indexM = nameMovie.indexOf(playerLetter,indexM+1);

            } while (indexM >= 0);
            String temp = Arrays.toString(charBlankMovieName);
            System.out.println("Wow, you guess a letter!");
        }
        else {points -= 1;}
        if (points>0 && (Arrays.toString(charBlankMovieName).equals(Arrays.toString(charNameMovie)))){
            gameEnd = true;
            win = true;
        }
        else if (points<=0){
            gameEnd = true;
            win = false;
        }


    }

    // Put the guessed letter in the right place

    // Method that asking player to put a letter and check
    public String inputLetter() {
        boolean wrongLetter = true;
        do {
            Scanner scanner = new Scanner(System.in);
            String letterScanner = scanner.nextLine().toLowerCase();

                if (letterScanner.matches("[a-zA-Z]")) {
                    if (containerLetter.contains(letterScanner)){
                        System.out.println("You used that letter. Try another: ");
                        wrongLetter = true;
                    }
                    else {
                        this.playerLetter = letterScanner;
                        wrongLetter = false;
                        return this.playerLetter; }
                }
                else {
                    System.out.println("That not a letter!. Try again: ");
                    wrongLetter = true;}
        } while (wrongLetter);
        return null;
    }


}