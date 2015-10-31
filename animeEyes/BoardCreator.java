/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package animehalloweenprograms.animeeyes.boardcreation;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
//import org.apache.commons.io.FileUtils;
import java.util.Random;

/**
 *
 * @author Phyllis
 */
public class BoardCreator {

    public static final int MAX_ROUND_IMAGES = 40;
    public static final int MAX_FINALS_IMAGES = 55;
    public static final int NUM_ROUNDS = 3;
    public static final int NUM_FINALS = 1;
    private static Random rng;

    public static ArrayList<ArrayList<ImageInfo>> allImagesList;
    public static ArrayList<ArrayList<String>> seriesList;
    public static ArrayList<ArrayList<ImageInfo>> rounds;
    public static ImageInfo[] finals; // shouldn't assume finals is one round... but just for now.

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rando = new Random((new Date()).getTime());
        boolean hasEnteredInput = false;
        int maxDifficulty = 0, totalImages[] = null, maxImages[] = null;
        allImagesList = new ArrayList<ArrayList<String>>();
        rng = new Random(System.currentTimeMillis());

        //Create a series list for each round and initialize
        seriesList = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < (NUM_ROUNDS + NUM_FINALS); i++) {
            seriesList.add(new ArrayList<String>());
        }

        Scanner inSC = new Scanner(System.in);

        //get the max difficulty (starting with 1)
        do {
            System.out.println("Enter in the max difficulty:");

            if (!inSC.hasNextInt()) {
                System.out.println("Please enter a number.");
            } else {
                maxDifficulty = inSC.nextInt();
                totalImages = new int[maxDifficulty];
                maxImages = new int[maxDifficulty];
                hasEnteredInput = true;
            }
        } while (!hasEnteredInput);

        hasEnteredInput = false;
        File dir = null;

        //get the directory name with the difficulties
        do {
            System.out.println("Please type in the directory where the images have"
                    + " been sorted by difficulty.");
            String directory = inSC.next();

            dir = new File(directory);
            if (!dir.isDirectory()) {
                System.out.println("That was not a valid directory, try again.");
            } else {
                hasEnteredInput = true;
            }
        } while (!hasEnteredInput);

        hasEnteredInput = false;
        ArrayList<Integer> percentDifficulties = new ArrayList<Integer>();

        //get the percent of image difficulties for preliminary rounds
        do {
            System.out.println("What percentage of image difficulties"
                    + " do you want in the finals rounds? Separate by comma. **All " + maxDifficulty + " numbers must add up to 100!**"); // was preliminary round before
            String percentages = inSC.nextLine();

            Scanner lineSC = new Scanner(percentages);
            lineSC.useDelimiter(",");
            int percentTotal = 0;

            while (lineSC.hasNextInt()) {
                //percentDifficulties.add(lineSC.nextInt());
                int percent = lineSC.nextInt();
                percentDifficulties.add();
                percentTotal += percent;
            }

            if (percentDifficulties.size() == maxDifficulty && percentTotal == 100) {
                hasEnteredInput = true;
            } else {
                System.out.println("Please list the same number of difficulties as max difficulty. Make sure they add up to 100.");
            }
        } while (!hasEnteredInput);

        //Get the number of images per difficulty
        for (File difficultyFolder : dir.listFiles()) {
            if (difficultyFolder.isDirectory()) {
                String folderName = difficultyFolder.getName();
                int currDifficulty = Integer.parseInt(folderName) - 1;
                totalImages[currDifficulty] = difficultyFolder.list().length;

                // add the show names from each difficulty folder
                for (int i = 0; i < difficultyFolder.list().length; i++) {
                  if (allImagesList.size() <= currDifficulty) {
                    allImagesList.add(new ArrayList<ImageInfo>());
                  }

                  allImagesList.get(currDifficulty).add(new ImageInfo(difficultyFolder.list()[i], currDifficulty));
                }
            }
        }

        for (int i = 0; i < maxDifficulty; i++) {
          maxImages[i] = (int)(MAX_ROUND_IMAGES * percentDifficulties.get(i) / 100.0);

          if (maxImages[i] > totalImages[currDifficulty] / (NUM_ROUNDS * 1.0)) {
            maxImages[i] = (int)Math.floor(totalImages[currDifficulty] / (NUM_ROUNDS * 1.0));
          }
        }

        // build evenly, and then after building the rounds, check how many images is in each. Starting from starting difficulty, add the rest of the images.
        int currRound = 0;
        ArrayList<ArrayList<String>> roundsSeriesList = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < NUM_ROUNDS; i++) {
          roundsSeriesList.add(new ArrayList<String>());
        }

        for (int i = 0; i < maxDifficulty; i++) {
          for (int j = 0; j < maxImages[i]; j++) {
            for (currRound = 0; currRound < NUM_ROUNDS; currRound++) {
              if (rounds.size() <= NUM_ROUNDS) {
                rounds.add(new ArrayList<ImageInfo>());
              }

              ImageInfo chosenImage = allImagesList.get(i).get(rng.nextInt(allImagesList.get(i).size()));

              if (!roundsSeriesList.get(currRound).contains(chosenImage)) {
                roundsSeriesList.get(currRound).add(chosenImage);
                rounds.get(currRound).add(allImagesList.get(i).get(chosenImage));
              } else {
                currRound--;
              }
            }
          }
        }

        printRounds();

        //Calculate how many images per difficulty go into each round
        /*maxImages[0][0] = totalImages[0] / NUM_ROUNDS;
         maxImages[0][1] = totalImages[1] / NUM_ROUNDS;
         if (maxImages[0][1] + maxImages[0][0] > MAX_ROUND_IMAGES) {
         maxImages[0][1] = MAX_ROUND_IMAGES - maxImages[0][0];
         }

         if (maxImages[0][0] + maxImages[0][1] < MAX_ROUND_IMAGES) {
         maxImages[0][2] = totalImages[2] / NUM_ROUNDS;
         if (maxImages[0][2] + maxImages[0][1] + maxImages[0][0] > MAX_ROUND_IMAGES) {
         maxImages[0][2] = MAX_ROUND_IMAGES - maxImages[0][0] - maxImages[0][1];
         }
         } else {
         maxImages[0][2] = 0;
         }*/

        /*int totalImagesInRound = 0;
        for (int i = 0; i < maxDifficulty; i++) {
            maxImages[i] = (int)((totalImages[i] * (percentDifficulties.get(i))) / 100.0
                    / (NUM_ROUNDS * 1.0));
            if (totalImagesInRound + maxImages[i] > MAX_ROUND_IMAGES) {
                maxImages[i] = 0;
            } else {
                totalImagesInRound += maxImages[i];
            }
        }

        for (int i = 0; i < maxImages.length; i++) {
          System.out.println(maxImages[i]);
        }
        if (totalImagesInRound < MAX_ROUND_IMAGES) {
            for (int i = 0; i < maxDifficulty; i++) {
                if (maxImages[i] * NUM_ROUNDS < totalImages[i]) {
                    maxImages[i] += MAX_ROUND_IMAGES - totalImagesInRound;
                    totalImagesInRound += MAX_ROUND_IMAGES - totalImagesInRound;

                    if (maxImages[i] * NUM_ROUNDS > totalImages[i]) {
                        int decrement = (maxImages[i] * NUM_ROUNDS) - totalImages[i];
                        totalImagesInRound -= decrement;
                        maxImages[i] -= decrement;
                    } else {
                        break;
                    }
                }
            }
        }

        //make the Round directories
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(Calendar.getInstance().getTime());

        for (int i = 1; i <= (NUM_ROUNDS + NUM_FINALS); i++) {
            if (i == 4) {
                (new File("files/animeEyes/" + year + "/Finals")).mkdirs();
            } else {
                (new File("files/animeEyes/" + year + "/Round " + i)).mkdirs();
            }
        }

        System.out.println("Rounds and Finals folders created. Now occupying them with images...");

        System.out.println("Building finals round now.");

        //build the board for Finals
        int totalFinalImages = 0;
        for (int i = 0; ((i < maxDifficulty) && (totalFinalImages < MAX_FINALS_IMAGES)); i++) {
            File difficultyFolder = dir.listFiles()[i];

            while (totalImages[i] > 0 && totalFinalImages < MAX_FINALS_IMAGES) { // why a while. This endless loops. Why is all my code broken...
                int fileIndex = rando.nextInt(totalImages[i]);
                File image = difficultyFolder.listFiles()[fileIndex];

                String[] filename = image.getName().split("\\.");
                if (filename[filename.length - 1].toLowerCase().equals("png")
                        || filename[filename.length - 1].toLowerCase().equals("jpg")
                        || filename[filename.length - 1].toLowerCase().equals("jpeg")
                        || filename[filename.length - 1].toLowerCase().equals("gif")) {
                    String series = filename[0].split(" - ")[0];

                    if (!seriesList.get(seriesList.size() - 1).contains(series)) {
                        seriesList.get(seriesList.size() - 1).add(series);

                        image.renameTo(new File("files/animeEyes/" + year + "/Finals/"
                                + image.getName()));
                        totalImages[i]--;
                        totalFinalImages++;
                    }
                }

                //System.out.println(totalImages[i]);
            }
        }

        System.out.println("Building boards now.");

        //Add the images into the rounds!
        //int currRound = 0;
        int currNumOfFiles = 0;

        for (File difficultyFolder : dir.listFiles()) {
            if (difficultyFolder.isDirectory()) {
                int currDifficulty = Integer.parseInt(difficultyFolder.getName());
                System.out.println("Current difficulty: " + currDifficulty);

                for (int currRound = 0; currRound < NUM_ROUNDS; currRound++) {
                    for (int i = 0; i < maxImages[currDifficulty - 1]; i++) {
                        int fileIndex = rando.nextInt(totalImages[currDifficulty - 1]);
                        File image = difficultyFolder.listFiles()[fileIndex];

                        String[] filename = image.getName().split("\\.");
                        if (filename[filename.length - 1].toLowerCase().equals("png")
                                || filename[filename.length - 1].toLowerCase().equals("jpg")
                                || filename[filename.length - 1].toLowerCase().equals("jpeg")
                                || filename[filename.length - 1].toLowerCase().equals("gif")) {
                            String series = filename[0].split(" - ")[0];

                            if (!seriesList.get(currRound).contains(series)) {
                                seriesList.get(currRound).add(series);

                                image.renameTo(new File("files/animeEyes/" + year + "/Round "
                                        + Integer.toString(currRound + 1) + "/" + image.getName()));
                                //File destination = new File("files/animeEyes/" + year + "/Round " + Integer.toString(currRound + 1) + "/" + image.getName());
                                //FileUtils.copyFile(image, destination);
                                totalImages[currDifficulty - 1]--;
                            } else {
                                i--;
                            }
                        } else {
                            i--;
                            //This should instead move the image out of the folders so it's not used as any other file extensions won't work
                        }
                    }
                }
            }
        }*/
    }

    private static void printRounds()
    {
      for (int i = 0; i < rounds.size(); i++) {
        System.out.println("Round " + Integer.toString(i + 1));
        int totalDiff = 0;

        for (int j = 0; j < rounds.get(i).size(); j++) {
          System.out.println("\t" + rounds.get(i).get(j));
          totalDiff += rounds.get(i).get(j).getDifficulty();
        }

        System.out.println("===Difficulty Total: " + totalDiff + "===");
      }
    }

    private static void printDifficultyShows()
    {
      for (int i = 0; i < shows.size(); i++) {
        System.out.println("Difficulty " + Integer.toString(i + 1) + " (" + allImagesList.get(i).size() + " Total)");
        for (int j = 0; j < allImagesList.get(i).size(); j++) {
          System.out.println("\t" + shows.get(i).get(j));
        }
      }
    }

    private static string getSeries(string filename)
    {
        return filename.split(" - ")[0];
    }

    private static string getCharacterName(string filename)
    {
      string[] fileParts = filename.split("\\.");

      return fileParts[0].split(" - ")[1];
    }

    private static string getExt(string filename)
    {
      string[] fileParts = filename.split("\\.");

      return fileParts[fileParts.length - 1];
    }
}
