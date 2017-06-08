package Attempt2;/*
    Project: Dissertation
    Created by: Joker
    Created date: 13/02/2017
*/

import Attempt2.Data.TimeTable;

public class Population {
    // The default size of a population
    private static final int DEFAULT_SIZE = 100;

    // The population of time tables
    private TimeTable[] population;

    /**
     * Creates a new population of a certain size
     * @param populationSize the population size
     * @param init should the population be randomly generated
     */
    Population(int populationSize, boolean init) {
        population = new TimeTable[populationSize];
        if (init) {
            init();
        }
    }

    /**
     * Initialises the population
     * @param init if true randomly generate
     */
    Population(boolean init) {
        population = new TimeTable[DEFAULT_SIZE];
        if (init) {
            init();
        }
    }

    /**
     * Randomly generate the population
     */
    private void init() {
        // Randomly generate the complete population following some constraints
        for (int i=0; i < size(); i++) {
            population[i] = new TimeTable();
            population[i].generate();
        }
    }

    /**
     * Get's the size of the population
     * @return the size of the population
     */
    public int size() {
        return population.length;
    }

    /**
     * Sets an individual in the population
     * @param pos the position of the population
     * @param timeTable the timetable
     */
    void setIndividual(int pos, TimeTable timeTable) {
        population[pos] = new TimeTable(timeTable);
    }

    /**
     * Get's an individual timetable from a certain position
     * @param pos the position in the array of the individual
     * @return returns the timetable individual
     */
    TimeTable getIndividual(int pos) {
        return population[pos];
    }

    /**
     * Get's the total fitness
     * @return the total fitness of the population
     */
    double getTotalFitness() {
        double totalFitness = 0;

        for (int i=0; i < population.length; i++) {
            totalFitness += Algorithm.getFitness(getIndividual(i));
        }

        return totalFitness;
    }

    /**
     * Get the average fitness
     * @return the average fitness of the population
     */
    double getAverageFitness() {
        return getTotalFitness()/population.length;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i=0; i<size(); i++) {
            out = String.format("%s\n%s",population[i],out);
        }
        return out;
    }
}
