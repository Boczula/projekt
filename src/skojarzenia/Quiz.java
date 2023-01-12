package skojarzenia;

import java.awt.event.*;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
//import java.lang.*;
//import java.util.*; 

public class Quiz implements ActionListener{

	String[] questions = 	{
			"9754",
			"Nuty byly falszywe, bo szwy sie rozeszly",
			"913825",
			"Stog siana mial znaczenie, gdyz material się rozdarl"
	};
	String [] answers = {
			"9754",
			"Nuty byly falszywe, bo szwy sie rozeszly",
			"913825",
			"Stog siana mial znaczenie, gdyz material się rozdarl"
	};

	char guess;
	int index;
	int correct_guesses =0;
	int total_questions = questions.length;
	int result;
	int seconds=5;
	
	ClickGame clickGame = null;
	JFrame frame = new JFrame();
	JTextField textfield = new JTextField();
	JTextArea textarea = new JTextArea();
	JTextField TextFieldA = new JTextField(10);
	JLabel time_label = new JLabel();
	JLabel seconds_left = new JLabel();
	JTextField number_right = new JTextField();
	JTextField percentage = new JTextField();
	JButton submit = new JButton("submit");


//timer
	Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			seconds--;
			seconds_left.setText(String.valueOf(seconds));

			if(seconds <= 0) {
				timer.stop();
				clickGame.setComponents();

				test();
			}
		}
	});

	//to assist in passing variables
	public void test() { 
		Test test = new Test(this);
	}

	
	/**
	 * create game window
	 */
	public Quiz()
	{


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650,650);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(null);
		frame.setResizable(false);

		textfield.setBounds(0,0,650,50);
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,30));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);

		textarea.setBounds(0,100,650,100);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(new Color(25,25,25));
		textarea.setForeground(new Color(25,255,0));
		textarea.setFont(new Font("MV Boli",Font.BOLD,25));
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);


		seconds_left.setBounds(535,510,100,100);
		seconds_left.setBackground(new Color(25,25,25));
		seconds_left.setForeground(new Color(255,0,0));
		seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));

		time_label.setBounds(505,475,120,25);
		time_label.setBackground(new Color(50,50,50));
		time_label.setForeground(new Color(255,0,0));
		time_label.setFont(new Font("MV Boli",Font.PLAIN,16));
		time_label.setHorizontalAlignment(JTextField.CENTER);
		time_label.setText("pozostaly czas:");

		number_right.setBounds(225,225,200,100);
		number_right.setBackground(new Color(25,25,25));
		number_right.setForeground(new Color(25,255,0));
		number_right.setFont(new Font("Ink Free",Font.BOLD,50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);

		percentage.setBounds(225,325,200,100);
		percentage.setBackground(new Color(25,25,25));
		percentage.setForeground(new Color(25,255,0));
		percentage.setFont(new Font("Ink Free",Font.BOLD,50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);

		TextFieldA.setBounds(0,200,1000,100);
		TextFieldA.setFont(new Font("MV Boli",Font.BOLD,35));
		TextFieldA.setFocusable(true);
		TextFieldA.setEditable(true);
		TextFieldA.addActionListener(this);

		submit.addActionListener(this);
		submit.setBounds(275,375,100,50);
		submit.setFont(new Font("MV Boli",Font.BOLD,15));


		frame.add(time_label);
		frame.add(seconds_left);
;
		frame.add(TextFieldA);
		frame.add(textarea);
		frame.add(textfield);


		frame.setVisible(true);

		TextFieldA.setVisible(false);
		submit.setVisible(false);

		clickGame = new ClickGame();
		clickGame.jb.addActionListener(this);
		
		remember();

	}
	/**
	 * the first information
	 */
	public void remember() {
		time_label.setVisible(false);
		seconds_left.setVisible(false);
		textfield.setText("Zapamietaj wyswietlajace się zdania");
		textarea.setText("Zaraz zaczynamy!");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
		}
		nextQuestion();
	}
			
	
	
	
	public void nextQuestion() {
		time_label.setVisible(true);
		seconds_left.setVisible(true);
		seconds = 5;
		//check if there are no more questions
		if(index>=total_questions) { 
			results();
		}
		else {
			textfield.setText("Zdanie " + (index+1));
			textarea.setVisible(true);
			textarea.setText(questions[index]);
			timer.start();
		}
	}

	/**
	 * if submit was clicked go to next question
	 * check if answer was correct 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		TextFieldA.setVisible(false);
		submit.setVisible(false);
		

		String answer = TextFieldA.getText();

		if (Objects.equals(answer, answers[index])) {
			System.out.println(answer);
			correct_guesses++;
		}

		if (e.getSource() == submit) {
			index= index +1;
			TextFieldA.setText(null);
			nextQuestion();

		}
		
	}

/**
 * window to answer 
 */
	public void displayAnswer()
	{

		textfield.setText("co zapamietales");
		textarea.setVisible(false);
		TextFieldA.setVisible(true);
		TextFieldA.requestFocusInWindow();
		frame.add(submit);
		submit.setVisible(true);


		this.clickGame.buttonClicked = false;
		seconds = 10;
	}

	public void results(){
		timer.stop();
		this.clickGame.buttonClicked = false;
		TextFieldA.setVisible(false);

		result = (int)((correct_guesses/(double)total_questions)*100);

		textfield.setText("Wyniki");
		textarea.setText("");
		
		number_right.setVisible(true);

		number_right.setText("("+correct_guesses+"/"+total_questions+")");
		percentage.setText(result+"%");

		frame.add(number_right);
		frame.add(percentage);
	}
}
