package skojarzenia;

public class Test implements Runnable {
    Quiz quiz;
    public Test(Quiz quiz)
    {
        this.quiz = quiz;

        Thread t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
    	
        while(!this.quiz.clickGame.buttonClicked) {
            System.out.println("");
        }

        this.quiz.displayAnswer();
    }
}
