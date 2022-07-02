import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

public class Main {
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
        List<HashMap<String,Integer>> deck = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 1; j <= 4; j++) {
                HashMap<String,Integer> card = new HashMap<>();
                card.put("mark", j);
                card.put("number", i);
                deck.add(card);
            }
        }

        // シャッフル
        Collections.shuffle(deck);

        // プレイヤーハンド作成
        List<HashMap<String,Integer>> playerHand = new ArrayList<>();
        for (int i=0; i < 3; i++) {
            HashMap<String,Integer> c = deck.remove(deck.size() - 1);
            playerHand.add(c);
        }

        // ディーラーハンド作成
        List<HashMap<String,Integer>> dealerHand = new ArrayList<>();
        for (int i=0; i < 3; i++) {
            HashMap<String,Integer> c = deck.remove(deck.size() - 1);
            dealerHand.add(c);
        }

        int playerTotal = 0;
        boolean hasOne = false;
        for (int i=0; i < 3; i++) {
            HashMap<String,Integer> c = playerHand.get(i);
            int n = c.get("number");
            if (n >= 11) {
                n = 10;
            }
            if (n == 1) {
                hasOne = true;
            }
            playerTotal += n;
        }

        if (hasOne && playerTotal <= 11) {
            playerTotal += 10;
        }

        int dealerTotal = 0;
        hasOne = false;
        for (int i=0; i < 3; i++) {
            HashMap<String,Integer> c = dealerHand.get(i);
            int n = c.get("number");
            if (n >= 11) {
                n = 10;
            }
            if (n == 1) {
                hasOne = true;
            }
            dealerTotal += n;
        }

        if (hasOne && dealerTotal <= 11) {
            dealerTotal += 10;
        }

        // 勝敗判定
        String winner = "";
        if (playerTotal > 21 && dealerTotal > 21) {
            winner = "";
        }
        else if (playerTotal > 21) {
            winner = "dealer";
        }
        else if (dealerTotal > 21) {
            winner = "player";
        }
        else if (playerTotal == dealerTotal) {
            winner = "";
        }
        else if (playerTotal > dealerTotal) {
            winner = "player";
        }
        else {
            winner = "dealer";
        }

        System.out.println("player:");
        playerHand.stream().forEach(m -> {System.out.println(m);});
        System.out.println(playerTotal);

        System.out.println("dealer:");
        dealerHand.stream().forEach(m -> {System.out.println(m);});
        System.out.println(dealerTotal);

        System.out.println("winner");
        System.out.println(winner);
    }
}
