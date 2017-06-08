package com.lute;

public class Main {

    public static void main(String[] args) {
        final int MAX_POPULATION = 100;
        final int FITNESS_GOAL = 'a' + 'b' + 'c' + 'd';

        Population initialPopulation = new Population(MAX_POPULATION);
        initialPopulation.initialize();
        initialPopulation.sort();

        Population children = initialPopulation.getNewGeneration();

        Population population = new Population(children.getLength()+initialPopulation.getLength());
        population.joinPopulations(initialPopulation,children);
        population.sort();
        population.resizePopulation(MAX_POPULATION);

        System.out.print(population);

        int currentLowFitness = 1000;
        final int MAX_ITERATION = 2000;
        int currentIteration = 0;
        // Create subsequent generations while the max iterations has not been met and the fitness is not there
        while (currentLowFitness != 0 && currentIteration != MAX_ITERATION) {
            currentLowFitness = population.getChromosome(0).getFitness(FITNESS_GOAL);

            currentIteration++;

            children = population.getNewGeneration();
            population.resizePopulation(children.getLength()+initialPopulation.getLength());
            population.joinPopulations(initialPopulation,children);
            population.sort();
            population.resizePopulation(MAX_POPULATION);
        }
        System.out.print(population);
    }
}
