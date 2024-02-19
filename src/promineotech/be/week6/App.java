package promineotech.be.week6;

import java.util.*;

public class App {
	// Utility function
	public static Deck createStandardCardDeck() {
		LinkedList<Card> cards = new LinkedList<Card>();

		// Suites
		String cardSuites[] = { "Hearts", "Diamonds", "Spades", "Clubs" },
			valueNames[] = new String[13];
		// Generate value names based on the 13 cards
		for(int i = 0; i < 13; ++i) {
			StringBuilder sb = new StringBuilder();
			switch(i) {
			// Special Cards
			case 0: sb.append("Ace"); break;
			case 10: sb.append("Jack"); break;
			case 11: sb.append("Queen"); break;
			case 12: sb.append("King"); break;
			// Numeric Cards
			default: sb.append((i+1)); break;
			}
			sb.append(" of ");
			valueNames[i] = sb.toString();
			sb = null;
		}
		// Generate Cards
		for(int i = 0; i < cardSuites.length; ++i) {
			for(int j = 0; j < valueNames.length; ++j) {
				String cardName = valueNames[j] + cardSuites[i];
				// Ace (j=0) is high
				byte value = (j == 0) ? (byte)valueNames.length : (byte)(j);
				cards.add(new Card(value, cardName));
			}
		}
		return new Deck(cards);
	}

	// Utility function
	public static Player createPlayer(Scanner _input, String _msg, String _default) {
		System.out.println(_msg);
		String userInput = _input.nextLine().strip();
		return new Player((userInput == "") ? _default : userInput);
	}

	// Entry point
	public static void main(String args[]) {
		// Create keyboard input
		Scanner kb = new Scanner(System.in);

		// War Game: Simplified
		// The rules according to the slack post: https://promineotech.slack.com/archives/C04N2ER4SP6/p1707798191878429?thread_ts=1707792376.493909&cid=C04N2ER4SP6
		// 1. Create Deck, shuffle, and deal cards to each player
		// 2. The play: While cards are available, flip 1 card per player and increment score of the player with greater card or none when tied
		// 3. Announce winner or tie when no cards are left.
		WarCardGame wcGame = new WarCardGame(
			createStandardCardDeck(),
			createPlayer(kb, "Set player 1's name: ", "Player 1"),
			createPlayer(kb, "Set player 2's name: ", "Player 2")
			).shuffleDeck(1000)
			.dealCards();
		// Unset the keyboard input
		kb = null;
		// Shuffle the deck and play game!
		try {
			while(wcGame.canPlay())
				wcGame.playCards();
			// Game is complete, describe the game
			wcGame.announceWinner();
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		// Unset the game at the end
		System.out.println("msg: Exiting game");
		wcGame = null;
	}
}
