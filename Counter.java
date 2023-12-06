
public class Counter {
    private PowerSet<Card> cardps;
    private Card starter;
    public Counter(Card[] hand, Card starter) {
        this.starter = starter; //construct starter card
        this.cardps = new PowerSet<>(hand); //create subset(s) of hand using powerset

    }
    private int countPairs() {
        int points = 0;
        for (int i = 1; i < cardps.getLength(); i++) { //loop through set length
            for (int z = 0; z < cardps.getSet(i).getLength(); z++) { //loop through subset length
                if (cardps.getSet(i).getLength() == 2) { //if the set is two cards, we can compare
                    for (int x = z + 1; x < cardps.getSet(i).getLength(); x++) { // linear search algorithim
                        if (cardps.getSet(i).getElement(z).getLabel() == cardps.getSet(i).getElement(x).getLabel()) {
                            points = points + 2; // add 2 points if same card rank/suit
                        }
                    }
                }
            }
        }
        return points;
    }
    private boolean isRun (Set<Card> set) {
        int n = set.getLength();
        if (n <= 2) return false; // Run must be at least 3 in length.

        int[] rankArr = new int[13];
        for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.
        for (int i = 0; i < n; i++) {
            rankArr[set.getElement(i).getRunRank() - 1] += 1;
        }

        // Now search in the array for a sequence of n consecutive 1's.
        int streak = 0;
        int maxStreak = 0;
        for (int i = 0; i < 13; i++) {
            if (rankArr[i] == 1) {
                streak++;
                if (streak > maxStreak) maxStreak = streak;
            } else {
                streak = 0;
            }
        }
        if (maxStreak == n) { // Check if this is the maximum streak.
            return true;
        } else {
            return false;
        }
    }
    private int countRuns() {
        int score = 0;
        for (int i = 1; i < cardps.getLength(); i++) { //loop through cardps set
            if (isRun(cardps.getSet(i)) && cardps.getSet(i).getLength() > score) { //check to see if the set is a run and if it is the highest run length
                score = cardps.getSet(i).getLength(); //if yes this is the score
            }

        }
        int counter = 0; // counter to count
        for (int i = 0; i < cardps.getLength(); i++) { //loop through cardps one more time
            if (isRun(cardps.getSet(i)) && cardps.getSet(i).getLength() == score) { //check if there are dublicate runs of the same length
                counter++; //make sure we do not count the already original run
                if (counter > 1) { // if more than one run, add whatever the length of the run is
                    score += cardps.getSet(i).getLength();
                }
            }

        }
        return score;
    }
    private int countFifteen() {
        int score = 0;
        for (int i = 0; i < cardps.getLength(); i++) { // loop through cardps set
            int tempScore = 0;
            for (int z = 0; z < cardps.getSet(i).getLength(); z++) { //loop through subset
                tempScore += cardps.getSet(i).getElement(z).getFifteenRank(); //see if the subset adds up to 15
            }
            if (tempScore == 15) { //if yes +2 points
                score += 2;
            }
        }
        return score;
    }
    private int Flush() {
        int score = 0;
        int counter = 0;
        Set<Card> sSet = new Set<>(); //create new set with cards not including the starter
        for (int i = 0; i < (cardps.getSet(cardps.getLength() - 1).getLength()); i++) { //loop through the 5 cards
            if (cardps.getSet(cardps.getLength() - 1).getElement(i) != starter) { //add to set only if it is not starter card
                sSet.add(cardps.getSet(cardps.getLength() -1).getElement(i));
            }
        }
        String suit = sSet.getElement(0).getSuit(); // default suit to compare
        for (int i = 0; i < sSet.getLength(); i++) { //loop through sSet
            if (sSet.getElement(i).getSuit() == suit) { //for it to be flush, the rest of the cards must be the same as suit
                counter++; //check if the flush is true or false
            }
        }
        if (counter == 4) { //if counter is 4 then it is a flush
            score = score + 4;
        }
        if (suit == starter.getSuit() && counter == 4) { //if the suit also matches the starter, +1 point
            score++;
        }
        return score;
    }
    private int countHisKnobs() {
        for (int z = 0; z < cardps.getLength(); z++) { // loop through set
            if (cardps.getSet(z).getLength() == 4) { // if subset is 4 cards, then it is probably the hand
                for (int i = 0; i < (cardps.getSet(z).getLength()); i++) { //loop through the hand
                    if (cardps.getSet(z).getElement(i).getLabel() == "J" && cardps.getSet(z).getElement(i) != starter) { // if the hand contains a J then:
                        if (cardps.getSet(z).getElement(i).getSuit() == starter.getSuit()) { //check if its suit is the same as starter
                            return 1; // return a point if it does
                        }
                    }
                }
            }
        }
        return 0;
    }
    public int countPoints() {
        int Points = countFifteen() + countRuns() + Flush() + countHisKnobs() + countPairs(); //add up all the private classes to get all the points
        return Points; //return points
    }

}