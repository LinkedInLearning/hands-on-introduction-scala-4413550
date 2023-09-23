# Hands-On Introduction: Scala
This is the repository for the LinkedIn Learning course Hands-On Introduction: Scala. The full course is available from [LinkedIn Learning][lil-course-url].

![course-name-alt-text][lil-thumbnail-url] 

_See the readme file in the main branch for updated instructions and information._
## Instructions
This repository has branches for each of the videos in the course. You can use the branch pop up menu in github to switch to a specific branch and take a look at the course at that stage, or you can add `/tree/BRANCH_NAME` to the URL to go to the branch you want to access.

## Branches
The branches are structured to correspond to the videos in the course. The naming convention is `CHAPTER#_MOVIE#`. As an example, the branch named `02_03` corresponds to the second chapter and the third video in that chapter. 
Some branches will have a beginning and an end state. These are marked with the letters `b` for "beginning" and `e` for "end". The `b` branch contains the code as it is at the beginning of the movie. The `e` branch contains the code as it is at the end of the movie. The `main` branch holds the final state of the code when in the course.

When switching from one exercise files branch to the next after making changes to the files, you may get a message like this:

    error: Your local changes to the following files would be overwritten by checkout:        [files]
    Please commit your changes or stash them before you switch branches.
    Aborting

To resolve this issue:
	
    Add changes to git using this command: git add .
	Commit changes using this command: git commit -m "some message"

## Installation
This course is intended to be run using GitHub Codespaces, which means you can run the course code in the browser using Visual Studio Code.
Check out the **Using GitHub Codespaces with this course** video to learn how to get started.

However, if you would like to run this repo on your local machine, check out my course [Introduction to Scala](https://www.linkedin.com/learning/introduction-to-scala/install-intellij-for-mac?resume=false),
which explains the installation process. Note that those videos have not been updated since recording! 
I'm also noting some helpful links below:
- JDK: https://www.oracle.com/java/technologies/downloads/
- Scala: https://www.scala-lang.org/download/ 
- Scala: https://docs.scala-lang.org/getting-started/index.html
- IntelliJ IDEA install: https://www.jetbrains.com/idea/download
- Getting started with Scala in IntelliJ: https://docs.scala-lang.org/getting-started/intellij-track/getting-started-with-scala-in-intellij.html
- Visual Studio Code: https://code.visualstudio.com/download
- Metals Plugin for Visual Studio Code: https://scalameta.org/metals/docs/editors/vscode#installation
- sbt: https://www.scala-sbt.org/download.html


[0]: # (Replace these placeholder URLs with actual course URLs)

[lil-course-url]: https://www.linkedin.com/learning/
[lil-thumbnail-url]: http://

