package com.chrisbrowder.bowling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class BowlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BowlingApplication.class, args);
		Scoreboard scoreboard = new Scoreboard(10);
		boolean play = true;

		while (play) {
			System.out.println("Let's bowl!");

			while (scoreboard.getCurrentFrameIndex() < scoreboard.getTotalFrames()) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter number of pins: ");
				int pins;
				try {
					pins = Integer.parseInt(scanner.nextLine());
					scoreboard.roll(pins);
				} catch (Exception e) {
					System.out.println("Please enter a number between 0-10.");
				}
			}

			System.out.println("Game Over");
			System.out.println("Final score: " + scoreboard.getTotalGameScore());

			Scanner scanner = new Scanner(System.in);
			System.out.println("\nWould you like to play again? (y/n)");
			try {
				if (!scanner.nextLine().toLowerCase(Locale.ROOT).equals("y")) {
					System.out.println("Thanks for playing!");
					play = false;
				}
			} catch (Exception e) {
				System.out.println("Thanks for playing!");
				play = false;
			}
		}
		SpringApplication.run(BowlingApplication.class, args).close();
		System.exit(0);
	}

}
