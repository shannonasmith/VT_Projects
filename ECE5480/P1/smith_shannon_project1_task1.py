# Project 1 Task 1
# Shannon Smith
# 6.10.2023
# ECE 5480

# python smith_shannon_project1_task1.py


import requests

def getPageRequests(url):
    # call to requests.get()
    user_agent = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.47 Safari/537.36'
    headers = {'User-Agent': user_agent}
    myres = requests.get(url, headers=headers)

    # return myres.text
    return myres.text
    
    
# get url from user
ui = input("Enter URL: ")
url = getPageRequests(ui)

# open file for writing
gpr = open("egg.txt", "w")

# write getPageRequests(url)
gpr.write(url)

# close file
gpr.close()
