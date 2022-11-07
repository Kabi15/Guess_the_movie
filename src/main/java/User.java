import java.util.Scanner;

public class User {
    private final Scanner scanner = new Scanner(System.in);
    private int points = 10;
    private boolean exit = false;

    public int getPoints(){
        return points;
    }

    public void lostPoints(){
        if(!this.hasNoPoints()){
            this.points--;
        };
    }

    public boolean exits(){
        return this.exit;
    }

    public boolean hasNoPoints(){
        return this.getPoints()==0;
    }

    public String getMovieList(){
        String movieNames = scanner.nextLine();
        return movieNames.isBlank() ? "movies.txt" : movieNames;
    }

    public String getGuess(){
        String getInput = scanner.nextLine();
        this.exit = getInput.equalsIgnoreCase("exit");
        return getInput;
    }

}
