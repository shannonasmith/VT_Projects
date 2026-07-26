# Project 1 Task 4
# Shannon Smith
# 6.10.2023
# ECE 5480

# python smith_shannon_project1_task4.py 
    
import subprocess

# mine = "192.168.0.22"
# spectrum = "174.99.72.47"
# adham = "34.149.36.179"
# allthingsopen = "68.183.133.1"
# github = "192.30.253.113" - NO RESPONSE

ips = ["192.168.0.22", "174.99.72.47", "34.149.36.179", "68.183.133.1", "192.30.253.113"]

for x in range(len(ips)):

    # Pinging each IP address 3 times 
    proc = subprocess.Popen(
        ['ping', '-n', '3', ips[x]],
        stdout=subprocess.PIPE)
        
    stdout, stderr = proc.communicate()

    if proc.returncode == 0:
        print('{} is UP'.format(ips[x]))
        
    else:
        print('{} is DOWN <OR> there is a NETWORK ERROR'.format(ips[x]))
