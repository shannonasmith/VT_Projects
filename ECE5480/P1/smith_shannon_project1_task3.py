# Project 1 Task 3
# Shannon Smith
# 6.10.2023
# ECE 5480

# python smith_shannon_project1_task3.py IOT security

import requests, sys, webbrowser
from bs4 import BeautifulSoup

print("Searching for " + "+".join(sys.argv[1:]))

user_agent = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.47 Safari/537.36'
headers = {'User-Agent': user_agent}
myres = requests.get("https://scholar.google.com/scholar?hl=en&q=" + "+".join(sys.argv[1:]) + "&*")

soup = BeautifulSoup(myres.text, "html.parser")

elems = soup.select(".gs_rt a")

num = min(len(elems), 5)

for i in range(num):
    print(elems[i].get("href"))
    
for i in range(num):
    webbrowser.open(elems[i].get("href"))
