# Grad-Div

## 7.3-7.10 Plan

* Add Search function
* Add Match Table For University ✅
* Add Match Table For Company
* Update Score Part For StudyExp, Student and WorkExp

## Introduction

During the application season, the school will receive a huge number of applications. 
In order to improve the efficiency of the review, we have come up with such a system to sort the applicants.



## Structure

The project will set up a simple DB and reporting system to help with the reviewing process
of graduate admission data.

Areas of enhancements include：
* addition of missing information
* calibration of Univ/degree standing
* calibration on applicant's GPA
* a set of threshold screening rules


## Give a summary of your accomplishments in the first phase of the project.

It is now determined which programming languages, frameworks, dependencies, and databases will be used by the project as a whole.
Determine a plan for the entire, and some rules that may be used later.
Through the study of the framework, a basic demo has been completed, and some basic operations of the project such as data addition,
deletion, update and query can be performed.
It also implements the interface of file transfer, and realizes the function of writing data into the database directly through the Excel file.

## What knowledge/skills (both technical and non-technical) did you gain?

* Basic knowledge of how to use Scala
* Basic knowledge of how to use Play Framework
* Basic knowledge of how to use Postgres Database
* How to design a backend project

## What are the difficulties/challenges you faced this phase of the project, if any? How did you overcome (or plan to overcome) them?

First, I know barely nothing about play framework, and there is very less documentation that I can get to know how to use play framework. 
The only thing I got is the official documentation. So I have to read the example it gives and construct the demo.

Second, at first I want to put every data into one table, but due to the limit of scala tuple, it can only take 22 elements in it. 
So I can not put every thing into one table. To fix this problem, I divide the data into several tables.

## Explain how did you address your defined objectives in the course of the first phase of the project.

First, I divide the whole system in to several parts. For example, I divide the student collection part into five functions, create, delete, update, query and file read.
Then I focus on each part and try to realize the function.

Second, I will check the official documentation or some other references from the Internet to get some solutions. Then I will write some demo code about the solutions and 
try to understand how the code run. After that, I will try to write the function for the system, and test it whether it works.

# Record of Developing

## Finished Part

* Upload File and read data into database
* create, update, delete data( include student data, university data, company data)


## What to do next?

* Deal with the wrong data. (For example: wrong university name)
  * First Match the database to find whether the university is in database or not.
  * If the university is in database, then pass them through.
  * If not university is not in database, then use match algorithm to match the result.
  * How to define the match algorithm? First is to match the shortcut, then try to match the words, limit is 5 char.
* Score (Figure out a way to score the student)
  * How to score?
* User Management
* Better Pages(Frontend)
  * Show Students data