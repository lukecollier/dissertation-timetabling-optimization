package com.lute;/*
    Project: Dissertation
    Created by: Joker
    Created date: 06/02/2017
*/

import java.util.Random;

class Chromosome {
    private final int MAX = 4;
    private char[] chromosomes = new char[MAX];

    /**
     * Empty constructor will randomly get the chromosomes
     */
    Chromosome() {
        for (int i=0;i<MAX;i++) {
            Random rand = new Random();
            char c = (char)(rand.nextInt(26) + 'a');
            chromosomes[i] = c;
        }
    }

    /**
     * Empty constructor will randomly get the chromosomes
     */
    Chromosome(char[] chromosomes) {
        if (chromosomes.length != MAX) {
            throw new java.lang.Error("The length of chromosomes is greater then the max");
        }
        // Copy the chromosomes into the new array
        System.arraycopy(chromosomes, 0, this.chromosomes, 0, MAX);
    }

    /**
     * Crossover two chromsomes
     * @param parent_one the first parent
     * @param parent_two the second parent
     */
    Chromosome(Chromosome parent_one, Chromosome parent_two) {
        chromosomes[0] = parent_one.getChromosomeAt(0);
        chromosomes[1] = parent_one.getChromosomeAt(1);
        chromosomes[2] = parent_two.getChromosomeAt(2);
        chromosomes[3] = parent_two.getChromosomeAt(3);
    }

    /**
     * Mutate a random chromosome
     */
    void mutate() {
        Random rand = new Random();
        char c = (char)(rand.nextInt(26) + 'a');
        int  n = rand.nextInt(MAX);
        chromosomes[n] = c;
    }

    /**
     * Get's the fitness of the chromosomes
     * @return the fitness
     */
    int getFitness(int goal) {
        int runningTotal = 0;
        for (int i=0;i<MAX;i++) {
            runningTotal += chromosomes[i];
        }
        return runningTotal-goal;
    }

    public char[] getChromosomes() {
        return chromosomes;
    }

    /**
     * Get's the chromosome at a certain position
     * @param position the position in the chromosome
     * @return return the character at the position in the chromosome
     */
    private char getChromosomeAt(int position) {
        return chromosomes[position];
    }

    public String toString() {
        String temp = "Chromosomes[";
        for (char chromosome : chromosomes) {
            temp += chromosome + ", ";
        }
        temp+="]";

        return temp;
    }
}
