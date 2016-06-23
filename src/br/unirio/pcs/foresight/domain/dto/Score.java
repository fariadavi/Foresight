package br.unirio.pcs.foresight.domain.dto;

public class Score implements Comparable<Score>{

	private String player;
	private int score;
	
	public Score(String player, int score) {
		this.player = player;
		this.score = score;
	}
	
	public Score() {
	}
	
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void upScore(int score) {
		this.score += score;
	}

	@Override
	public int compareTo(Score highScore) {
		return highScore.score - this.score;
	}
}
