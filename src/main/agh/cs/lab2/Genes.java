package agh.cs.lab2;

import java.util.Arrays;
import java.util.Random;

class Genes {
    final int[] genes;
    final int size;

    Genes(int[] genes) {
        this.genes = genes;
        this.size = genes.length;
    }

    Genes mergeGenes(Genes secGenes) {
        int cut1 = new Random().nextInt(this.size);
        int cut2 = new Random().nextInt(this.size);

        if (cut1 > cut2) {
            int tmp = cut2;
            cut2 = cut1;
            cut1 = cut2;
        }

        int[] babyGenes = new int[this.size];
        for (int i = 0; i < this.size; i++) {
            if (i < cut1 || i >= cut2) {
                babyGenes[i] = this.genes[i];
            } else {
                babyGenes[i] = secGenes.genes[i];
            }
        }

        // ensuring that every gene is present and adding some randomness to the new genotype
        boolean[] blocked = new boolean[this.size];
        Arrays.fill(blocked, false);
        for (int i = 0; i < Rotation.values().length; i++) {
            int j = new Random().nextInt(this.size);
            while (blocked[j]) {
                j = (j + 1) % this.size;
            }
            babyGenes[j] = i;
        }

        return new Genes(babyGenes);
    }

    boolean equals(Genes secGenes) {
        int[] thisCount = new int[Rotation.values().length];
        int[] secCount = new int[Rotation.values().length];
        Arrays.fill(thisCount, 0);
        Arrays.fill(secCount, 0);
        for (int i = 0; i < this.size; i++) {
            thisCount[this.genes[i]]++;
            secCount[secGenes.genes[i]]++;
        }
        for (int i = 0; i < Rotation.values().length; i++) {
            if (thisCount[i] != secCount[i]) {
                return false;
            }
        }
        return true;
    }

}
