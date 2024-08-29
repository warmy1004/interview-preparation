package Sort;

/*
 * Like bubble sort, quicksort uses an approach based on swapping out-of-order elements, but it's more efficient
 * A recursive, divide-and-conquer algorithm:
 *      divide: rearrange the elements so that we end up with two subarrays that meet the following criterion:
 *          >> each element in left array <= each element inr ight array
 *      conquer: apply quicksort recursively to the subarrays, stopping when a subarray has a single element
 *      combine: nothing needs to be done, because of the way we formed the subarrays
 */
public class QuickSort {
    /*
    * Solution 1
    */
    void quicksort(int[] array, int left, int right) {
        int pivot = partition(array, left, right);
        
        if(left < pivot-1) {
            quicksort(array, left, pivot-1);
        }
        if ( pivot < right) {
            quicksort(array, pivot+1, right);
        }
    }

    int partition(int[] array, int left, int right) {
        //int pivot = (left+right)/2; 
        int pivot = left + (right-left)/2;
        while(left<=right) {
            // find element on left that should be on right, which means greater than pivot value
            while(array[left]<array[pivot]) left++;
            // find element on right that should be on left, which means less than pivot value
            while(array[pivot]<array[right]) right--;
            
            if(left<=right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    /*
     * Solution 2
     */
    // void quicksort(int[] array, int low, int high) {
    //     if(low < high) {
    //         int pivot = partition(array, low, high);
    //         quicksort(array, low, pivot-1);
    //         quicksort(array, pivot+1, high);
    //     }
    // }

    // int partition(int[] array, int low, int high) {
    //     int pivot = high;

    //     int i = low-1;
    //     for(int j=low; j<high; j++) {
    //         if(array[j]<array[pivot]) {
    //             i++;

    //             int temp = array[i];
    //             array[i] = array[j];
    //             array[j] = temp;
    //         }
    //     }
    //     int temp = array[pivot];
    //     array[pivot] = array[i+1];
    //     array[i+1] = temp;
    //     return i+1;
    // }

    /*
     * Solution 3
     */
    // void quicksort(int[] array, int start, int end) {
    //     if(start>=end) return; // there is only one element, then quit without sorting

    //     // partition
    //     int pivot = start;
    //     int left = start+1;
    //     int right = end;
    //     while(left<=right) {
    //         // find a bigger value than array[pivot]
    //         while(left<=end && array[left]<=array[pivot]) left++;
    //         // find a smaller value than array[pivot]
    //         while(right>start && array[right]>= array[pivot]) right--;

    //         // if left and right are crossed, swap the smallest value and pivot value
    //         if(left >= right) {
    //             int temp = array[pivot];
    //             array[pivot] = array[right];
    //             array[right] = temp;
    //         } else {
    //             // if not, swap the small value and big value
    //             int temp = array[left];
    //             array[left] = array[right];
    //             array[right] = temp;
    //         }
    //     }

    //     // quicksort for left and right parts
    //     // partition value == right, because right value is the smallest one in the array
    //     quicksort(array, start, right-1);
    //     quicksort(array, right+1, end);
    // }

    /*
     * Python
     */
    // def quicksort(array) :
    //     if len(array) <= 1:
    //         return array
    //     pivot = array[0]
    //     remains = array[1:]
    //     left_sort = [x for x in remains if x <= pivot]
    //     right_sort = [ x for x in remains if x > pivot]
    //     return quicksort(left_sort) + [pivot] + quicksort(right_sort)
    // print(quicksort(array))
}
