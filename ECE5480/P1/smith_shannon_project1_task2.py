# Project 1 Task 2
# Shannon Smith
# 6.10.2023
# ECE 5480

# python smith_shannon_project1_task2.py

import requests

from bs4 import BeautifulSoup

# call to requests.get()
user_agent = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.47 Safari/537.36'
headers = {'User-Agent': user_agent}
myres = requests.get("https://www.orionscache.com/books",headers=headers) 
soup = BeautifulSoup(myres.text, "html.parser")

links = soup.findAll("a")    
print("\nprint out the first 10 links on the URL: ")
for line in links[0:10]:
    print(line)
