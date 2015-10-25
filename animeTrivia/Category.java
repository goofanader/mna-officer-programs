/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package animehalloweenprograms.animetrivia.boardcreation;

import java.util.Objects;

/**
 *
 * @author pdouglas
 */
public class Category {
   private String[] questions;
   private String[] answers;
   private String[] series;
   private int difficulty;
   private String title;
   private String description;
   private String author;

   public Category() {
      questions = new String[Main.TOTAL_QUESTIONS];
      answers = new String[Main.TOTAL_QUESTIONS];
      series = new String[Main.TOTAL_QUESTIONS];
      title = description = author = "";
      difficulty = 0;
   }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getSeries() {
        return series;
    }

    public void setSeries(String[] series) {
        this.series = series;
    }

   public String[] getAnswers() {
      return answers;
   }

   public void setAnswers(String[] answers) {
      this.answers = answers;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getDifficulty() {
      return difficulty;
   }

   public void setDifficulty(int difficulty) {
      this.difficulty = difficulty;
   }

   public String[] getQuestions() {
      return questions;
   }

   public void setQuestions(String[] questions) {
      this.questions = questions;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   @Override
   public String toString() {
      return "Category: " + title + "; Difficulty: " + difficulty;
   }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (!Objects.equals(this.title.toLowerCase(), other.title.toLowerCase())) {
            return false;
        }
        return true;
    }
}
