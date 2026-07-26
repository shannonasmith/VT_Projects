# Project 3 task 1
# Shannon Smith
# 7.15.2023
# ECE 5480

# python smith_shannon_p3_task_1.py

import os

# INPUT THE BASE DIRECTORY --> C:\Users\sasmi\~ECE5480
base_directory = input("Input base directory: ") 
print("\nCurrent directory folder is ", base_directory)

# USE os.listdir() to create a list of all the files and directories in the base directory
directory_contents = os.listdir(base_directory)
print("\nList of directory contents:")
for item in directory_contents:
   print(item)
   
# create an empty files list and an empty dirs list
files = []
dirs = []

fp = open("smith_shannon_found_files.txt", "w")
dp = open("smith_shannon_found_dirs.txt", "w")

for item in directory_contents: 

    # write your found files to a text file
    if os.path.isfile(os.path.join(base_directory, item)):
        fp.write(os.path.join("\n" + base_directory + "\\" + item))

    # write your found directories to a text file   
    elif os.path.isdir(os.path.join(base_directory, item)):
        dp.write(os.path.join("\n" + base_directory + "\\" + item))
