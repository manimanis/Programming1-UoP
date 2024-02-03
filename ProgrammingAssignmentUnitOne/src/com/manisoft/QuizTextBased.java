/**
 *
 */
package com.manisoft;

import java.util.Random;
import java.util.Scanner;

/**
 *
 */
public class QuizTextBased {

    // Quiz data
    private final String[] questions = new String[]{
        "What will be the value of x after the following code snippet?\n"
        + "int x = 5;\n"
        + "x *= 2 + 3;",
        "Which of the following is not a valid Java identifier?",
        "Which of the following is the correct way to declare "
        + "and initialize an array in Java?\n"
        + "a. int[ ] numbers = new int[5] {1, 2, 3, 4, 5};  \n"
        + "b. int[ ] numbers = {1, 2, 3, 4, 5};\n"
        + "c.  int numbers[ ] = new int[ ]{1, 2, 3, 4, 5};\n"
        + "d.  int numbers = [1, 2, 3, 4, 5];",
        "What is the output of the following code snippet?\n"
        + "int x = 5;\n"
        + "int y = 2;\n"
        + "int result = x % y;\n"
        + "System.out.println(result);",
        "What is the output of the following code snippet?\n"
        + "int x = 5;\n"
        + "int y = 2;\n"
        + "System.out.println(x > y ? \"x is greater than y\": "
        + "\"x is less than or equal to y\");",
        "Which of the following is the correct way to declare a method in Java?",
        "Which of the following is the correct way to declare a constant "
        + "variable in Java?",
        "What is the result of the following code snippet?\n"
        + "int x = 10;\n"
        + "int y = 5;\n"
        + "boolean result = (x > y) && (x != y);\n"
        + "System.out.println(result);",
        "Which of the following data types in Java is NOT used to "
        + "store integer values?",
        "What is the value of \"result\" after executing the following "
        + "code snippet?\n"
        + "int result = 10;\n"
        + "result -= 2 * 3;"
    };
    private final String[][] propositions = new String[][]{
        {"10", "15", "25", "30"},
        {"myVariable", "_variable", "123variable", "$variable"},
        {"Option a and Option b", "Option b and Option c",
            "Option c", "Option b"},
        {"3", "2", "1", "0"},
        {"x is greater than y", "x is less than or equal to y", "5", "2"},
        {"int myMethod( ) { }", "void myMethod { }",
            "myMethod( ) { }", "int myMethod;"},
        {"final double PI = 3.14;", "const double PI = 3.14;",
            "double PI = 3.14;", "static double PI = 3.14;"},
        {"true", "false", "10", "5"},
        {"int", "long", "byte", "float"},
        {"4", "6", "8", "10"}
    };
    private final int[] answers = new int[]{2, 2, 1, 2, 0, 0, 0, 0, 3, 0};

    /**
     * The questions are shuffled in this Array to present different questions
     * order at each program run.
     */
    private final int[] questionsNumbers;

    /**
     * Stores user answers for each question in a numeric form. (A -> 0, B -> 1,
     * C -> 2, D -> 3)
     */
    private final int[] userAnswers = new int[questions.length];

    /**
     * The user score (number of correct answers).
     */
    private int score;

    /**
     * Number of questions can range between (1 and questions.length).
     */
    private int nbQuestions;

    private final Scanner scan = new Scanner(System.in);

    public QuizTextBased() {
        questionsNumbers = new int[questions.length];
    }

    /**
     * Displays question text, propositions, answer and feedback.
     *
     * @param numQuestion the question to be displayed
     * @param numAnswer indicates the question's answer (-1 to hide the answer)
     * @param userAnswer display if the user answer is correct or not (-1 to
     * hide)
     */
    public void showQuestion(int numQuestion, int numAnswer, int userAnswer) {
        System.out.println(questions[numQuestion]);
        System.out.println("\nPropositions:");
        for (int i = 0; i < propositions[numQuestion].length; i++) {
            if (numAnswer != i) {
                System.out.println(String.format(" %c . %s",
                        (char) ('A' + i), propositions[numQuestion][i]));
            } else {
                System.out.println(String.format("[%c]. %s",
                        (char) ('A' + i), propositions[numQuestion][i]));
            }
        }
        // Displays feedback only if the question is answered
        if (userAnswer != -1) {
            System.out.print("You answered ");
            System.out.print((char) ('A' + userAnswer) + " - Your answer is ");
            System.out.println(userAnswer == numAnswer ? "Correct!" : "Incorrect!!!");
        }
        System.out.println();
    }

    /**
     * Displays only question text and propositions. No answer neither feedback.
     *
     * @param numQuestion the question to be displayed
     */
    public void showQuestion(int numQuestion) {
        showQuestion(numQuestion, -1, -1);
    }

    /**
     * Prompts the user's answer.
     *
     * @param numQuestion Question to be answered
     * @return 0 -> "A", 1 -> "B", 2 -> "C" and 3 -> "D"
     */
    public int askUserAnswer(int numQuestion) {
        String answer;
        // The question may have more or less than 4 choices
        // maxLetter would be 'D' if there are 4 choices
        char maxLetter = (char) ('A' + (propositions[numQuestion].length - 1));
        int numTry = 0;
        do {
            // Displayed if the user input an incorrect answer
            if (numTry != 0) {
                System.out.println("Incorrect choice!");
            }
            // Asks the user for his answer
            System.out.print(String.format("Your answer [A, %c]? ", maxLetter));
            // Remove trailing spaces or carriage return
            // and transform the string to uppercase in order
            // to ignore character case. We accept 'a' or 'A'
            answer = scan.nextLine().trim().toUpperCase();
            numTry++;
            // To be accepted user entry should be composed of exactly one character
            // and it should range in 'A' to 'D'
        } while (answer.length() != 1
                || answer.charAt(0) < 'A'
                || answer.charAt(0) > maxLetter);
        return answer.charAt(0) - 'A';
    }

    /**
     * Shuffles questions and start the quiz.
     */
    public void showQuestions() {
        score = 0;
        shuffleQuestions();
        System.out.println("Quiz on Java Programming\n\n");
        for (int i = 0; i < nbQuestions; i++) {
            System.out.println("---- Question "
                    + (i + 1) + "/" + nbQuestions + " ----");
            int numQ = questionsNumbers[i];
            // Displays questions and choices
            showQuestion(numQ);
            // Prompts user answer
            userAnswers[numQ] = askUserAnswer(numQ);
            // Score the answer
            score += (userAnswers[numQ] == answers[numQ]) ? 1 : 0;
            System.out.println();
        }
    }

    /**
     * Displays final review after the end of the quiz.
     */
    public void showReview() {
        int percent = score * 100 / nbQuestions;
        System.out.println("-------  Review  -------\n\n");
        for (int i = 0; i < nbQuestions; i++) {
            System.out.println("---- Question " + (i + 1) + " ----");
            int numQuestion = questionsNumbers[i];
            showQuestion(numQuestion,
                    answers[numQuestion], userAnswers[numQuestion]);
            System.out.println();
        }
        System.out.println(
                "Your score is " + score + " / " + nbQuestions
                + " = " + percent + "%");
        System.out.println("Grade: " + getLetterGrade(percent));
        System.out.println();
    }

    /**
     * Convert the percent value to a grade letter.
     *
     * @param percent a value between 0 and 100
     *
     * @return A letter grade [A - F]
     */
    private static String getLetterGrade(int percent) {
        int d = percent / 10;
        int r = percent % 10;
        String grade = switch (d) {
            case 10 ->
                "A+";
            case 9 ->
                "A";
            case 8 ->
                "B";
            case 7 ->
                "C";
            case 6 ->
                "D";
            default ->
                "F";
        };
        if (d >= 6 && d < 10) {
            if (r >= 8) {
                grade += "+";
            } else if (r <= 2) {
                grade += "-";
            }
        }
        return grade;
    }

    /**
     * Launches the Quiz with exactly 5 questions.
     */
    public void startQuiz() {
        startQuiz(5);
    }

    /**
     * Launches the Quiz with the specified questions count.
     *
     * @param nbQuestions Number of questions included in the Quiz.
     */
    public void startQuiz(int nbQuestions) {
        this.nbQuestions = nbQuestions;
        // The number of question should not exceed 
        // the number of available questions
        if (nbQuestions > questions.length || nbQuestions <= 0) {
            this.nbQuestions = questions.length;
        }

        // Shows the quiz questions
        showQuestions();

        // Shows the review
        showReview();
    }

    /**
     * Shuffles the questions changing their orders.
     */
    private void shuffleQuestions() {
        for (int i = 0; i < questionsNumbers.length; i++) {
            questionsNumbers[i] = i;
        }
        Random rand = new Random();
        // Randomly exchanges the questions order
        for (int i = 0; i < questionsNumbers.length; i++) {
            int j = rand.nextInt(questionsNumbers.length);
            int aux = questionsNumbers[i];
            questionsNumbers[i] = questionsNumbers[j];
            questionsNumbers[j] = aux;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Launches the Quiz
        new QuizTextBased().startQuiz();
    }
}
