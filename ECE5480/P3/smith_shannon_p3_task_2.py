# Project 3 task 2
# Shannon Smith
# 7.15.2023
# ECE 5480

# python smith_shannon_p3_task_2.py

# put needed imports here
import os
import datetime
import calendar

# read in the dns log file
logfile = open("dns_log_file.txt") # open for reading
lines = [line.rstrip() for line in logfile.readlines()] # Read existing file
logfile.close()

# canonical line:
# 07-Nov-2011 00:14:19.671 queries: info: client 7.204.241.161#49698: query: smtp.usna.bluenet IN A +

# create an empty list to hold the good records
good_records = [] 

# we'll check each line of the log file and filter on the records to keep
# create a for loop
# split the record based on whitespace
# determine how you are going to filter each record
# append the record to your good records list if the record passes your filter

# create a for loop
for line in lines:
    if "IN A +" in line:
        good_records.append(line)
        
print("\n", "3) Total number of records = ", str(len(good_records))) # 30405

# You don't need to solve this in one big for loop -- complex code is seldom better
# dictionaries and running trackers are initialized once, before the loop, so they
# accumulate across every record instead of resetting each pass

ip_addresses = {}
domains = {}
earliest_dt = None
latest_dt = None

for line in good_records:
    space_split = line.split(" ") # split the record based on whitespace

# for 1 and 2, see the calendar_time_example.py

    dmy = space_split[0].split("-",2)
    d = dmy[0]
    mo = dmy[1]
    y = dmy[2]
    hms = space_split[1].split(":",2)
    h = hms[0]
    mi = hms[1]
    mysec = hms[2].split(".",1)
    s = mysec[0]
    ms = mysec[1]
    mydatetime = datetime.datetime(int(y),list(calendar.month_abbr).index(mo),int(d),int(h),int(mi),int(s),int(ms)*1000)

    # track earliest/latest as we go, instead of sorting a list built one record at a time
    if earliest_dt is None or mydatetime < earliest_dt:
        earliest_dt = mydatetime
    if latest_dt is None or mydatetime > latest_dt:
        latest_dt = mydatetime

    # 4.) Number of unique client IP addresses (ignore port #)
    # find the good_record element containing the ip address and port number
    ip_and_port = space_split[5]
    # separate the ip address from the port number
    ip_split = ip_and_port.split("#")
    ip = ip_split[0]

    # add the ip address to the dictionary as the key and increment its count as the value
    if ip in ip_addresses:
        ip_addresses[ip] = ip_addresses[ip] + 1
    else:
        ip_addresses[ip] = 1

    # 5.) Number of unique query domains (ignore query IP addresses)
    # do similar for the query domain
    domain = space_split[7]

    if domain in domains:
        domains[domain] = domains[domain] + 1
    else:
        domains[domain] = 1

# 1.) Earliest record time and date
print("\n", "1) Earliest record time and date = ", str(earliest_dt))

# 2.) Last record date/time
print("\n", "2) Last record time and date = ", str(latest_dt))

# 3.) Total number of records (answered above)

# 4.) Number of unique client IP addresses (ignore port #)
print("\n", "4) We have " + str(len(ip_addresses.keys())) + " unique client IP addresses")

# 5.) Number of unique query domains (ignore query IP addresses)
print("\n", "5) We have " + str(len(domains.keys())) + " unique query domains")

# 6.) Most common client address and number of occurrences
# a dictionary's keys() is a list of all the keys in the dictionary
# dict[key] refers to the value associated with key key
# look at each value for each key and find the largest value -- the
# key associated with the largest value is the most common client ip address
most_common_ip = None
most_common_ip_count = 0

for ip in ip_addresses.keys():
    if ip_addresses[ip] > most_common_ip_count:
        most_common_ip = ip
        most_common_ip_count = ip_addresses[ip]

print("\n", "6) Most common client address = " + most_common_ip + ", with " + str(most_common_ip_count) + " occurrences")

# 7.) Most common query domain and number of occurrences
# do something similar
most_common_domain = None
most_common_domain_count = 0

for domain in domains.keys():
    if domains[domain] > most_common_domain_count:
        most_common_domain = domain
        most_common_domain_count = domains[domain]

print("\n", "7) Most common query domain = " + most_common_domain + ", with " + str(most_common_domain_count) + " occurrences")
