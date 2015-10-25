# Anime Eyes Board Creator
Created 10/27/2013 by Phyllis Douglas (goofanader@gmail.com, me@phyllis.li)

Version 0.3.0 (10/30/2013)

__This program is intended for officer use only.__ It builds rounds for Halloween Party based off the images officers gathered and rated by difficulty. The files created are intended to be used with [Nur Monson's ShippoSearch program](https://github.com/samiamwork/ShippoSearch).

Please note that the algorithm used to build the boards is greedy and does not necessarily build optimal boards nor does it offer different board options.

This is a non-GUI implementation. You will need access to a console/terminal on your OS. Please have the Webmaster use this program as they should most likely be a programmer.

## Install
This is a Java-based program, so make sure you have the latest [JRE/JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) installed.

Grab the EyesBoardCreator.jar file from this directory and put it anywhere on your computer. ...And that's it :)

## Warning
This program moves your images, it does not copy them!!! Make sure to make a copy of the images that the officers chose and put that somewhere safe.

## Setup
__You will need console/terminal access to use this program.__ It is not GUI-based.

Have the officers gather images. __Each filename must be in this format:__

    <series name> - <character name>.<extension: jpg, jpeg, png, gif>

__Please note that the filename is delimited by " - " in the program, so if the series name contains that, remove the spaces around the hyphen.__

Place the images in directories denoting their difficulty. Example:

    <directory holding EyesBoardCreator.jar>
    |
    --> EyesBoardCreator.jar
    --> animeEyesImages
        |
        --> 1
            |
            --> <here are images that are of difficulty 1>
        --> 2
            |
            --> <here are images that are of difficulty 2>
        --> 3
            |
            --> <here are images that are of difficulty 3>
        --> 4
            |
            --> <here are images that are of difficulty 4>
        --> 5
            |
            --> <here are images that are of difficulty 5>

#### How Program Determines Conflicts
This program uses series to determine conflicts, so __make sure that matching series have the same exact name.__ If you have the Japanese name of a series and an English, the program won't know they're the same. Similarly for misspelled series names.

The program obtains the series' names from the filename.

For series or sequels, use one name to represent it for the program (ex. "Natsume's Book of Friends" rather than "Natsume's Book of Friends 3"), and then edit the filename after if need be.

__There is no implementation to balance which officers get images into boards at the moment.__

## How to Run

    java -jar EyesBoardCreator.jar

## How to Use: Make Halloween Party Boards
1. You will get a prompt asking what the highest difficulty is. Enter it (it's most likely 5).

1. You will get a prompt as to what folder contains the anime eyes images. It uses relative paths, so if you did the folder structure above, type ```animeEyesImages```

2. You will get a prompt asking for how many images you want in the initial rounds, by percentage.

    For example, if you only had a max difficulty of 3, and if you wanted 50% of difficulty 1 images, 30% of difficulty 2, and 20% of difficulty 3 images, you'd enter ```50 30 20```. __You must list out percentages for each difficulty.__

4. The program will then move images to create the rounds. The rounds can be found in

    ```
    <directory holding EyesBoardCreator.jar>
    |
    --> files
        |
        --> animeEyes
            |
            --> <year you used this program, ex. 2015>
                |
                --> Round 1
                --> Round 2
                --> Round 3
                --> Finals
    ```

__These images are moved over so you can see what images were leftover. I'd highly suggest making a copy of the difficulties folder.__

If you run this program again (provided you give it the animeEyesImages directory again, all filled back up), __it will not overwrite what's in that folder.__ Delete the year folder to overwrite what's in there. The new run of the program will most likely put different images than the previous run - it uses a random number generator seeded with the current time to determine images to put in the rounds.

## To-Do
* Make a GUI implementation
* Optionally take the eyes folder from the command line
* Ask for how many images you want for rounds and then for finals - don't hardcode it
* Ask for how many rounds there are
* Alert user if there's not enough images
* Allow to check series before making rounds
* Use a copy of the difficulties folder - don't move from the native folder!!
* Clear out the year folder if it already exists
