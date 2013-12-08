package data;

import java.util.List;

public class Question extends GenericDataObject{
	Integer points;
	String text;
	List<Answer> answers;

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}


	@Override
	public String toString() {
		return "Question [points=" + points + ", text=" + text + ", answers="
				+ answers + "]";
	}


	public static class Answer extends GenericDataObject{
		String text;
		Boolean isCorrect = false;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Boolean getIsCorrect() {
			return isCorrect;
		}

		public void setIsCorrect(Boolean isCorrect) {
			this.isCorrect = isCorrect;
		}

		@Override
		public String toString() {
			return "Answer [text=" + text + ", isCorrect=" + isCorrect + "]";
		}
	}
}
