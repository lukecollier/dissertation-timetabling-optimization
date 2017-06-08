package com.lute;/*
    Project: Dissertation
    Created by: Joker
    Created date: 07/02/2017
*/

import java.util.Arrays;

public class Population {
    private final int FITNESS_GOAL = 'A' + 'B' + 'C' + 'D';
    private final int MAX_POPULATION;

    private Chromosome[] population;

    /**
     * Creates a new population of size maxPopulation
     * @param maxPopulation the max size of the population
     */
    Population(int maxPopulation) {
        MAX_POPULATION = maxPopulation;
        population = new Chromosome[MAX_POPULATION];
    }

    /**
     * Init the population to be random
     */
    void initialize() {
        // Create and randomly initialize population
        for (int i = 0; i < MAX_POPULATION; i++) {
            population[i] = new Chromosome();
        }
    }

    /**
     * A string representation of the data
     * @return a string
     */
    public String toString() {
        String temp = "";
        // Create and randomly initialize population
        for (int i = 0; i < population.length; i++) {
            temp += String.format("ID %d has a fitness of %d\n%s\n",
                    i, population[i].getFitness(FITNESS_GOAL),population[i].toString());
        }
        return temp;
    }

    /**
     * Sort the array by the fitness value
     */
    void sort() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < MAX_POPULATION-1; i++) {
                // Get the fitness
                int fitnessOne = population[i].getFitness(FITNESS_GOAL);
                int fitnessTwo = population[i+1].getFitness(FITNESS_GOAL);
                if (fitnessOne > fitnessTwo) {
                    // Swap the arrays around
                    char[] arrayOne = population[i].getChromosomes();
                    char[] arrayTwo = population[i+1].getChromosomes();
                    population[i] = new Chromosome(arrayTwo);
                    population[i+1] = new Chromosome(arrayOne);
                    changed = true;
                }
            }
        }
    }

    /**
     * Gets the chromosome at a point
     * @param pos the position of the chromosome in the population
     * @return returns the chromosome at the position in the population
     */
    Chromosome getChromosome(int pos) {
        return population[pos];
    }

    /**
     * Create the next generation and mutate a random chromosome in each
     */
    Population getNewGeneration() {
        Chromosome[] children = new Chromosome[MAX_POPULATION/2];

        for (int i = 0; i < population.length/2; i++) {
            children[i] = new Chromosome(population[i], population[i+1]);
        }

        Population childrenPopulation = new Population(MAX_POPULATION/2);
        childrenPopulation.fillPopulation(children);

        return childrenPopulation;
    }

    /**
     * Join two populations together
     * @param population_one the first population
     * @param population_two the second population
     * @return the two populations joined
     */
    void joinPopulations(Population population_one, Population population_two) {
        for (int i=0;i<population_one.getLength();i++) {
            population[i] = population_one.getChromosome(i);
        }
        for (int i=0;i<population_two.getLength();i++) {
            population[population_one.getLength()+i] = population_two.getChromosome(i);
        }
    }


    /**
     *  Get how many chromosomes exist in the population
     * @return the length of the population array
     */
    int getLength() {
        return population.length;
    }

    /**
     * Culls the population to a number
     * @param cullNumber the population to cull too
     */
    void resizePopulation(int cullNumber) {
        population = Arrays.copyOf(population, cullNumber);
    }

    /**
     * Fills the population with an array of chromosomes
     * @param chromosome the chromosomes
     */
    private void fillPopulation(Chromosome[] chromosome) {
        if (population.length != chromosome.length) {
            throw new java.lang.Error("The population length is different from the chromosomes you wish to add");
        }
        System.arraycopy(chromosome, 0, population, 0, population.length);
    }
}
