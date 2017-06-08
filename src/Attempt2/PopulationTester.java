package Attempt2;
/*
    Project: Dissertation
    Created by: Joker
    Created date: 13/02/2017
*/

import Attempt2.Data.Helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class PopulationTester {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        LinkedList<String> lines = new LinkedList<>();
        lines.add("Generation\tAverage Fitness\tTotal Fitness");

        Population population = new Population(true);

        WriteTableHtml.writeFile("first",Algorithm.getFittest(population));

        System.out.printf("\n{Initial:\nTotal Fitness: %.2f\n Average Fitness: %.2f\nFittest: %s}",
                population.getTotalFitness(),population.getAverageFitness(),Algorithm.getFittest(population));

        for (int i=0;i<100;i++) {
            population = Algorithm.evolvePopulation(population);
            double totalFitness=population.getTotalFitness();
            double averageFitness=population.getAverageFitness();
            double bestFitness=Algorithm.getFitness(Algorithm.getFittest(population));
//            System.out.printf("\n\nIteration: %d\nTotal Fitness: %.2f\nAverage Fitness: %.2f\nBest Fitness: %.2f\n",
//                    i, population.getTotalFitness(),population.getAverageFitness(), bestFitness);
            lines.add(String.format("%d\t%f\t%f",i,averageFitness,totalFitness));
        }
        System.out.println();
        Helper.sortTimeTable(Algorithm.getFittest(population));
        System.out.println(Algorithm.getFittest(population));

        Path file = Paths.get("results.txt");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        WriteTableHtml.writeFile("result",Algorithm.getFittest(population));

        long finishTime = System.currentTimeMillis();
        System.out.printf("\n{Final:\nTotal Fitness: %.2f\n Average Fitness: %.2f\nFittest: %s}",
                population.getTotalFitness(),population.getAverageFitness(),Algorithm.getFittest(population));
        System.out.printf("\n%d",finishTime-startTime);
    }
}
