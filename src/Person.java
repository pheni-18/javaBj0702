import java.util.ArrayList;
import java.util.List;

public class Person {
    List<Card> hand;

    Person() {
        this.hand = new ArrayList<>();;
    }

    public int calcTotal() {
        int total = 0;
        boolean hasOne = false;
        for (int i=0; i < 3; i++) {
            Card c = this.hand.get(i);
            int n = c.number;
            if (n >= 11) {
                n = 10;
            }
            if (n == 1) {
                hasOne = true;
            }
            total += n;
        }

        if (hasOne && total <= 11) {
            total += 10;
        }

        return total;
    }

    public void showHand() {
        this.hand.stream().forEach(card -> {
            System.out.println("mark: " + card.mark + ", number: " + card.number);
        });
    }
}
