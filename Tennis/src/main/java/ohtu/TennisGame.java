package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;
    private final String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        if (winner()) return "Win for " + leading();
        if(even()) return getEven();
        if(scoreOverFour()) return advantage();
        return getPlayerScore(player1Score) + "-" + getPlayerScore(player2Score);
    }

    private boolean winner() {
        return scoreOverFour() && (Math.abs(player1Score - player2Score) >= 2);
    }

    private boolean scoreOverFour() {
        return (player1Score >= 4 || player2Score >= 4);
    }

    private String leading() {
        if(player1Score > player2Score) return player1Name;
        return player2Name;
    }

    private boolean even() {
        return player1Score == player2Score;
    }

    private String getEven() {
        if(player1Score < 4) return scores[player1Score] + "-All";
        return "Deuce";
    }

    private String advantage() {
        return "Advantage " + leading();
    }

    private String getPlayerScore(int score) {
        if(score < 4) return scores[score];
        return null;
    }
}
