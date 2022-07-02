import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Main {
    private static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (int number = 1; number <= 13; number++) {
            for (int m = 1; m <= 4; m++) {
                String mark;
                if (m == 1) {
                    mark = "spade";
                }
                else if (m == 2) {
                    mark = "heart";
                }
                else if (m == 3) {
                    mark = "diamond";
                }
                else {
                    mark = "club";
                }

                Card card = new Card(mark, number);
                deck.add(card);
            }
        }

        return deck;
    };

    private static String judgeWinner(int playerTotal, int dealerTotal) {
        String winner = "";
        if (playerTotal > 21 && dealerTotal > 21) {
            winner = "no winner";
        }
        else if (playerTotal > 21) {
            winner = "dealer";
        }
        else if (dealerTotal > 21) {
            winner = "player";
        }
        else if (playerTotal == dealerTotal) {
            winner = "no winner";
        }
        else if (playerTotal > dealerTotal) {
            winner = "player";
        }
        else {
            winner = "dealer";
        }

        return winner;
    }

    public static void main(String[] args) throws Exception {
        // ３枚のカードを合計して２１により近い人が勝ち
        // ２１を超えていたら負け
        // 同数どうしは引き分けとする
        // どちらもバーストの場合は引き分けとする
        // 本物と違いカードの追加はなし
        // ２〜１０はそのままの値として使う
        // １０以上はは１０として一律で扱う
        // １は１または１１として扱う

        // デッキ作る
        List<Card> deck = createDeck();

        // シャッフル
        Collections.shuffle(deck);

        // プレイヤー作成
        Person player = new Person();
        for (int i=0; i < 3; i++) {
            Card c = deck.remove(deck.size() - 1);
            player.hand.add(c);
        }
        
        // ディーラー作成
        Person dealer = new Person();
        for (int i=0; i < 3; i++) {
            Card c = deck.remove(deck.size() - 1);
            dealer.hand.add(c);
        }

        int playerTotal = player.calcTotal();
        int dealerTotal = dealer.calcTotal();

        // 勝敗判定
        String winner = judgeWinner(playerTotal, dealerTotal);

        System.out.println("player:");
        player.showHand();
        System.out.println(playerTotal);

        System.out.println("dealer:");
        dealer.showHand();
        System.out.println(dealerTotal);

        System.out.println("winner");
        System.out.println(winner);
    }
}
