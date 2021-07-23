import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int hourglassSum(List<List<Integer>> arr) {
        int largestNumber = Integer.MIN_VALUE;
        for (int row = 1; row < arr.size() - 1; row++) {
            List<Integer> cols = arr.get(row);
            for (int col = 1; col < cols.size() - 1; col++) {
                int hourSum = getHourGlassSum(arr, row, col);
                if (hourSum > largestNumber)
                    largestNumber = hourSum;
            }
        }
        return largestNumber;
    }
    
    private static int getHourGlassSum(List<List<Integer>> arr, int row, int col) {
        int sum = getColSum(arr.get(row - 1), col);
        
        List<Integer> middle = arr.get(row);
        sum += middle.get(col);

        return sum += getColSum(arr.get(row + 1), col);
    }
    
    private static int getColSum(List<Integer> arr, int col) {
        int sum = 0;
        for(int index = col - 1; index <= col + 1; index++)
            sum += arr.get(index);
        return sum; 
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> arr = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            String[] arrRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> arrRowItems = new ArrayList<>();

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowTempItems[j]);
                arrRowItems.add(arrItem);
            }

            arr.add(arrRowItems);
        }

        int result = Result.hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
