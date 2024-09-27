A basic system to track the energy usage of an experimental electric shuttle bus as it operates.
The dedicated route is perfectly straight, and consists of equal length blocks, numbered
sequentially from negative numbers in one direction to positive numbers in the other direction,
with block zero in the middle of the route. There is a recharging station located at block zero.

The shuttle makes a series of non-stop trips between locations. Passengers may board and/or exit
the shuttle while it is stopped. No energy is used while the shuttle is stopped, nor while the
shuttle is empty. Energy usage is based upon the distance of each trip, and how many passengers
are on board during the trip. Trips are considered either long or short, depending on a
configurable distance limit.

Short trips, up to the prevailing limit, use energy at a single fixed rate for the entire trip.
Long trips, longer than the prevailing limit, use energy at two different rates. For the short
portion of the trip, the usage is the same as for a short trip. For the remainder of the
distance, the trip uses energy at a different rate, and may travel faster or slower per block.

ACADEMIC NOTE: Use only primitive types int and double, with all fields stored as int type.

ACADEMIC NOTE: You may NOT use any methods of the Math class, except as explicitly specified in
the comments below.

ACADEMIC NOTE: You may NOT use any of the following: branches; loops; conditionals; arrays; or
collections. We haven't yet covered any of these topics well enough quite yet, and it's also a
great exercise to produce a solution without them anyway.

 Template and Comments © 2022 Prof. Oliva (for Summer 2022)
