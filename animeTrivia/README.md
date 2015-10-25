# Anime Trivia Board Creator
Created 10/27/2013 by Phyllis Douglas (goofanader@gmail.com, me@phyllis.li)

Version 0.5.0 (10/30/2013)

__This program is intended for officer use only.__ It builds rounds for Halloween Party based off the categories officers have made. The files created are intended to be used with [Nur Monson's Questionable program](http://theidiotproject.com/Apps/questionable/). GitHub link to his program: https://github.com/samiamwork/Questionable

Please note that the algorithm used to build the boards is greedy and does not necessarily build optimal boards nor does it offer different board options.

This is a non-GUI implementation. You will need access to a console/terminal on your OS. Please have the Webmaster use this program as they should most likely be a programmer.

## Install
This is a Java-based program, so make sure you have the latest [JRE/JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed.

Grab the TriviaBoardCreator.jar file from this directory and put it anywhere on your computer. ...And that's it :)

## Setup
__You will need console/terminal access to use this program.__ It is not GUI-based.

After creating categories for each officer, create .csv files of the data.

### Google Sheets Way
The easiest way to do this is through Google Sheets (since I'm assuming you guys are using that to make categories, right?). Make the categories to import to this program like so:

![](https://github.com/goofanader/mna-officer-programs/blob/master/images/googleSheetsCategoryFormat.png)

Have a header at the top of each officer's Sheet (whatever you like really, but something similar to this):

    Category Name,Points,Question,Answer [White Text],Comments

It's important that each officer has their own page on the Google Sheet. Each officer lists out their categories in their individual sheets.

When you all are done making categories in this way, simply go to "File > Download as > Comma-separated values" for each officer page.

Place each officer's .csv file in the following file structure:

    <directory holding TriviaBoardCreator.jar>
    |
    --> TriviaBoardCreator.jar
    --> officerCategories
        |
        --> Officer1 Trivia.csv
        --> Officer2 Trivia.csv
        --> ...
        --> OfficerN Trivia.csv

#### How Program Determines Conflicts
This program uses series to determine conflicts, so __make sure that matching series have the same exact name.__ If you have the Japanese name of a series and an English, the program won't know they're the same. Similarly for misspelled series names. You'll be able to check what series the program finds after importing the .csvs.

The program obtains the series' names from the answer column. If the series' name is in the question column, set the category to "flip" by putting that word in the column header, next to difficulty:

![](https://github.com/goofanader/mna-officer-programs/blob/master/images/googleSheetsFlip.png)

In the event that the question/answer is not from an anime/series, just let the answer be, don't format it.

For series or sequels, use one name to represent it for the program (ex. "Natsume's Book of Friends" rather than "Natsume's Book of Friends 3"), and then edit the question in Questionable if need be.

__There is no implementation to link similar categories together at the moment.__

## How to Run

    java -jar TriviaBoardCreator.jar

## How to Use: Make Halloween Party Boards
1. You will get a prompt as to what folder contains the officer trivia. It uses relative paths, so if you did the folder structure above, type ```officerCategories```

2. A menu of choices will appear. __Only #3, #5, and q work.__
    * 3: Make boards
    * 5: List out the series the program found
    * q: Quit the program

3. Choose option 3 to make the boards for Halloween Party.

    If the program hangs, then you either don't have enough categories or there is no possible combination of boards or I wrote a really terrible algorithm (which is true).

4. The boards can be found in

    ```
    <directory holding TriviaBoardCreator.jar>
    |
    --> files
        |
        --> animeTrivia
            |
            --> <year you used this program, ex. 2015>
                |
                --> boards
                    |
                    --> R1B1.triviaqm
                    --> R1B2.triviaqm
                    --> R2B1.triviaqm
                    --> R2B2.triviaqm
                    --> R3B1.triviaqm
                    --> R3B2.triviaqm
                    --> RFB1.triviaqm
                    --> RFB2.triviaqm
                    --> RFB3.triviaqm
    ```

    __How to Read the Filenames:__ R1B1 translates to "Round 1 Board 1". Similarly, RFB1 translates to "Round Finals Board 1".

If you run this program again, it will overwrite what's in that folder. It will also most likely create new board combinations since it uses a random number generator to generate the boards, seeded with the current time.

## To-Do
* Make a GUI implementation
* Use a branch-and-bound algorithm, killing it after 5 plausible choices or something like that
* Optionally take the category folders from the command line
* Prepare the save files to be updated for my iteration of MnA Anime Trivia
* Alert user if there's not enough categories/cannot make boards
    * Far future: show the best options if there's conflicts
* Handle categories in series
