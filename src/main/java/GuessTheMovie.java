import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class GuessTheMovie {


    public static void main(String[] args) throws IOException {

        User user = new User();

        Feedback movieToGuess = getMovie(user);
        Feedback currentFeedback = begin(user, movieToGuess);
        String response = run(user, movieToGuess, currentFeedback);
        System.out.println(response);

    }


    private static String run(User user, Feedback movieToGuess, Feedback currentFeedback){

        while(!currentFeedback.equals(movieToGuess)){
            String guess = user.getGuess();
            if(user.exits()){
                return "Bye";
            }

            char guessedLetter = guess.charAt(0);

            if(currentFeedback.isCorrect(movieToGuess,guessedLetter)){
                currentFeedback = movieToGuess.getHint(currentFeedback, guess.charAt(0));
                System.out.println(currentFeedback);
            }
            else{
                user.lostPoints();
                System.out.println("Wrong! number of chances left: "+ user.getPoints());

                if(user.hasNoPoints()){
                    return "Sorry, you are out of guesses. The movie was: " + movieToGuess;
                }
            }
        }
        return "Correct!";
    }


    private static Feedback getMovie(User user) throws IOException{
        Random random=new Random();
        System.out.println("Select movie list");
        List<String> movies = Files.readAllLines(Path.of(user.getMovieList()));

        int randomIndex = random.nextInt(movies.size());
        String randomMovie = movies.get(randomIndex).trim();
        return new Feedback(randomMovie);
    }

    private static Feedback begin(User user, Feedback movieToGuess){
        Feedback currentFeedback = movieToGuess.getRandomHint();
        System.out.println("Guess the movie: ");
        return currentFeedback;
    }


}
