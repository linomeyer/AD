package ch.hslu.ad.sw11.quicksort;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import static ch.hslu.ad.sw11.quicksort.Quicksort.partition;

public class QuicksortTask extends RecursiveAction {

    public static final int THRESHOLD = 20;
    private final int[] array;
    private int left;
    private int right;

    public QuicksortTask(final int[] array) {
        this(array, 0, array.length - 1);
    }

    public QuicksortTask(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }


    @Override
    protected void compute() {
        while (left < right) {
            // Check if array size on which we will be working is less than 10
            if (right - left < 20) {
                insertionSort(array, left, right);
                break;
            } else {
                int pivot = partition(array, left, right);

                ForkJoinTask<Void> t1 = null, t2 = null;
                if (pivot - left < pivot - right) {
                    t1 = new QuicksortTask(array, left, pivot - 1).fork();
                    left = pivot + 1;
                } else {
                    t2 = new QuicksortTask(array, pivot + 1, right).fork();
                    right = pivot - 1;
                }
                if (t1 != null) {
                    t1.join();
                } else {
                    t2.join();
                }
            }
        }
    }

    private void insertionSort(int[] array, int left, int right) {
        int element;
        int cursor;

        for (int i = left; i < right; i++) {
            element = array[i];
            cursor = i;
            while ((cursor > 0 && cursor >= left) && array[cursor - 1] < element) {
                array[cursor] = array[cursor - 1];
                cursor--;
            }
        }
    }

    private void exchange(final int firstIndex, final int secondIndex) {
        int tmp;
        tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
    }

    private int middleIndex(int left, int right) {
        return left + (right - left) / 2;
    }
}
