# Project 2 recover hashed passwords
# Shannon Smith
# 6.24.2023
# ECE 5480

# python smith_shannon_p2_recover_hashed_passwords.py

from timeit import default_timer as timer

file1 = open("p2_RECOVERED_PASSWORD_HASHES.txt")
recovered_hashes = file1.readlines()
file1.close()

file2 = open("smith_shannon_rainbow_table.txt") # https://docs.python.org/3/library/functions.html#enumerate
indexed_hashes = list(enumerate(file2))
file2.close()

file3 = open("p2_10K_PLAINTEXT_PASSWORDS.txt")
plaintext_passwords = file3.readlines()
file3.close()

# for each candidate hash in recovered_hashes
for candidate in recovered_hashes:
    # you'll need some way to stop the inner for loop search
    # maybe use a flag variable (True/False)
    myflag = False
    
    # start timer
    startTime = timer()
    
    for i,hash in indexed_hashes: # [0:3000]
    
        if candidate.rstrip() == hash.rstrip():
            # stop timer
            endTime = timer()
                        
            print("MATCH: hash # " + hash + " = " + plaintext_passwords[i])
            print("The search took ", (endTime - startTime)*1000000, " microseconds")
            myflag = True
          
    # this part of the code is to be executed if there is no match after a search through
    # the entire list of indexed_hashes. maybe condition on your flag variable
        
    if myflag == False: 
        # stop timer
        endTime = timer()
        
        print("NO MATCH FOUND FOR ", candidate.rstrip())
        print("The search took ", (endTime - startTime)*1000000, " microseconds")
