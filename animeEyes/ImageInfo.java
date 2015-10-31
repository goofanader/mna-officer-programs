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
public class ImageInfo {
   private int difficulty;
   private String name;
   private String series;
   private String fileExtension;
   private String filename;

   public ImageInfo() {
      name = series = filename = fileExtension = "";
      difficulty = 0;
   }

   public ImageInfo(string name, string series, int difficulty)
   {
     this();

     this.name = name;
     this.series = series;
     this.difficulty = difficulty;
   }

   public ImageInfo(string filename, int difficulty) {
     this();

     string[] fileParts = filename.split("\\.");

     this.name = fileParts[0].split(" - ")[1];
     this.series = filename.split(" - ")[0];
     this.fileExtension = fileParts[fileParts.length - 1];
     this.filename = filename;

     this.difficulty = difficulty;
   }

   public String getFilename() {
     return filename;
   }
   public void setFilename(String filename) {
     this.filename = filename;
   }

   public String getFileExtension() {
     return filename;
   }
   public void setFileExtension(String fileExtension) {
     this.fileExtension = fileExtension;
   }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

   public int getDifficulty() {
      return difficulty;
   }

   public void setDifficulty(int difficulty) {
      this.difficulty = difficulty;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return filename;//"Name: " + name + "; Series: " + series + "; Difficulty: " + difficulty;
   }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImageInfo other = (ImageInfo) obj;
        //if (!Objects.equals(this.name.toLowerCase(), other.name.toLowerCase())) { // matches on name. Should match on series?
        if (!Objects.equals(this.series.toLowerCase(), other.series.toLowerCase())) { // matches on name. Should match on series?
            return false;
        }
        return true;
    }
}
