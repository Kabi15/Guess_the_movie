import java.util.Random;

public class Feedback {

    private String value;

    public Feedback(String value){
        this.value = value;
    }

    public String toString(){
        return this.value;
    }

    public boolean equate(Object object){
        Feedback match = (Feedback) object;
        return this.value.equalsIgnoreCase(match.toString());

    }

    public Feedback getHint(Feedback response, char guess){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < this.value.length();i++){
            if(guess == this.value.charAt(i)){
                result.append(guess);
            }
            else{
                result.append(response.toString().charAt(i));
            }

        }
        return new Feedback(response.toString());

    }

    public boolean hasLetter(char letter){

        return this.value.indexOf(letter) >= 0;
    }

    public Feedback getRandomHint(){
        Random random = new Random();
        int randomIndex = random.nextInt(this.value.length());
        String underscores = "_".repeat(this.value.length());
        return this.getHint(new Feedback(underscores), this.value.charAt(randomIndex));
    }

    public boolean isCorrect(Feedback toGuess, char letter){
        return toGuess.hasLetter(letter)&& !this.hasLetter(letter);
    }
}
