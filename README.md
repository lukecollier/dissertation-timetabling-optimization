# Timetable Optimization Using an Genetic Algorithm

This project demonstrates my understanding of genetic algorithms by solving the timetable optimization problem using the algorithm.

The project was made as a part of my 3rd year degree at Bangor University studying computer science.

Required Java to run, the output html file is a simple representation of the output content and can be styled using CSS.

## Explanation

The timetabling process is one that is not easily solved by computers. The problem is known as NP-complete, which is to say it cannot be completed in polynomial time. To solve this problem there are a few different approaches as covered in my disserations research. I decided upon using a genetic algorithm. Genetic algorithms are a powerful solution to NP-complete problems as the algorithm can be stopped at any point and produce a valid output. As a optimisation technique generating the initial population randomly allows for the process to be improved upon. The random nature of this process allows for different solutions with every generation.

## Genetic Algorithm

A genetic algorithm is a process by which each generation of solution improves upon the last using a technique observed in real world. Survival of the fittest, in that the population keeps the most fit for purpose and "kills off" the not so fit of the population. This process is then repeated until a desirable solution is found. In terms of the timetabling problem the population is a population of many timetables, each timeslot is a chromosome and these timetables mix their chromosomes with every generation to create a new timetable. The fitness function is in my opinion the most important aspect of an genetic algorithm as it provides the basis for what is sought after by the algorithm.
