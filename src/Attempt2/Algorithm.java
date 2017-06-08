package Attempt2;
/*
    Project: Dissertation
    Created by: Joker
    Created date: 13/02/2017
*/

import Attempt2.Data.Helper;
import Attempt2.Data.TimeTable;

public class Algorithm {
    private static final boolean ELITISM = true;
    private static final int TOURNAMENT_SIZE = 10;
    private static final double UNIFORM_RATE = 0.5;
    private static final double MUTATE_RATE = 0.05;

    /**
     * Get's the fitness
     * @param timeTable the timetable
     * @return the fitness metric
     */
    public static double getFitness(TimeTable timeTable) {
        double fitness=0;
        // Take away the distance between each lecture as this is not wanted
        fitness-= Helper.getDayTimeDistance(timeTable);
        // Add how many timetables being closed as this is wanted TODO: Make this not push everything onto one day
        fitness+= Helper.getCloseTimeSlots(timeTable);
        // Take away the distance to walk as this is not wanted
        fitness-= Helper.getWalkDistance(timeTable);
        // Get the unused seats and minus them as unused seats are bad
        fitness-= (double)Helper.getUnusedSeats(timeTable)*0.01;
        return fitness;
    }

    /**
     * Get's the fittest in the population
     * @param population the population to check for the fittest from
     * @return the fittest
     */
    public static TimeTable getFittest(Population population) {
        TimeTable fittest = population.getIndividual(0);
        // Loop through individuals to find fittest
        for (int i = 0; i < population.size(); i++) {
            if (getFitness(fittest) <= getFitness(population.getIndividual(i))) {
                fittest = population.getIndividual(i);
            }
        }
        return fittest;
    }

    /**
     * Evolves the population to evolve
     * @param population the population
     * @return the new evolved population
     */
    static Population evolvePopulation(Population population) {
        Population newPopulation = new Population(population.size(),false);

        int elitismOffset;
        if (ELITISM) {
            elitismOffset=0;
        } else {
            elitismOffset=1;
            // Set the first individual to be the same
            newPopulation.setIndividual(0, population.getIndividual(0));
        }

        // Loop over the population size and create new individuals with
        for (int i = elitismOffset; i < population.size(); i++) {
            TimeTable timeTableOne = tournamentSelection(population);
            TimeTable timeTableTwo = tournamentSelection(population);
            // Cross over
            TimeTable newTimeTable = crossOver(timeTableOne,timeTableTwo);
            newPopulation.setIndividual(i, newTimeTable);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            TimeTable mutatedTimeTable = mutate(newPopulation.getIndividual(i));
            newPopulation.setIndividual(i, mutatedTimeTable);
        }

        return newPopulation;
    }

    /**
     * Crosses the genetics of two timetables randomly selecting when to cross over.
     * @param timeTableOne The first timetable to cross over with
     * @param timeTableTwo the second timetable to cross over with
     * @return returns the new time table that has been the product of crossing over timetableone and timetabletwo
     */
    static TimeTable crossOver(TimeTable timeTableOne, TimeTable timeTableTwo) {
        TimeTable newTimeTable = new TimeTable();
        // Loop through genes
        for (int i = 0; i < timeTableOne.size(); i++) {
            // Crossover
            if (Math.random() <= UNIFORM_RATE) {
                newTimeTable.setTimeSlot(i,timeTableOne.getTimeSlot(i));
            } else {
                newTimeTable.setTimeSlot(i,timeTableTwo.getTimeSlot(i));
            }
        }
        return newTimeTable;
    }


    /**
     * Mutate a timetable
     * @param timeTable The timetable to mutate
     * @return the mutated timetable
     */
    static TimeTable mutate(TimeTable timeTable) {
        TimeTable newTimeTable = new TimeTable(timeTable);
        // Loop through genes
        boolean check = true;
        while (check) {
            for (int i = 0; i < timeTable.size(); i++) {
                // Crossover
                if (Math.random() <= MUTATE_RATE) {
                    newTimeTable.getTimeSlot(i).generateTimeDate();
                }
            }
            check = newTimeTable.checkOverlaps();
        }
        return newTimeTable;
    }

    /**
     * Performs a tournament on the population leaving only the strongest
     * @param population the population to perform the tournament selection
     * @return gets the fittest
     */
    private static TimeTable tournamentSelection(Population population) {
        // Create a tournament population
        Population tournament = new Population(TOURNAMENT_SIZE, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * population.size());
            tournament.setIndividual(i, population.getIndividual(randomId));
        }
        // Get the fittest
        return getFittest(tournament);
    }
}
