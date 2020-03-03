package com.cucumber.definition.admin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.Scanner;

public class JavaTest {
	/*
	  
	 private static final Scanner scanner = new Scanner(System.in);
	  
	  public static void main(String[] args) {
	 
	 
	  int N = scanner.nextInt(); scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
	  
	  int result = N % 2; ValueRange range1 = ValueRange.of(2, 5); ValueRange
	  range2 = ValueRange.of(6, 20); if( result == 1){
	  
	  System.out.println("Weird");
	  
	  }
	  
	  else if (result==0 && 2<N && N<5){
	  
	  System.out.println("Not Weird");
	  
	  }
	  
	  else if(result==0 && 6<N && N<=20){
	  
	  System.out.println("Weird");
	  
	  }
	  
	  else if (result==0 && 20<N){
	  
	  System.out.println("Not Weird");
	  
	  } scanner.close(); }
	  
	 */

	/*
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		scan.nextLine();
		double d = scan.nextDouble();

		scan.nextLine();

//        s += scan.nextLine();

		String s = scan.nextLine();
		// Write your code here.
		scan.close();

		System.out.println("String: " + s);
		System.out.println("Double: " + d);
		System.out.println("Int: " + i);
	}
*/
	/*
	 public static void main(String[] args) {
         Scanner sc=new Scanner(System.in);
         System.out.println("================================");
         for(int i=0;i<3;i++){
             String s1=sc.next();
             int x=sc.nextInt();
             //Complete this line
             System.out.println(i);
             System.out.printf("%-10s %n", s1,x);
             
             
         }
         System.out.println("================================");


	
	}
	 
	 */
	
	static int simpleArraySum(int[] ar, int arCount) {
		
		String test = Arrays.toString(ar);
		String[] test1 =test.split(" ");
		System.out.println(test1);
		int sum = 0;
		 for (int arItr = 0; arItr < arCount; arItr++) {
		int test2 = Integer.parseInt(test1[arItr].trim());
		
		sum = sum+test2;
		/*
         * Write your code here.
         */
		 }
		 return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arCount = Integer.parseInt(scanner.nextLine().trim());

        int[] ar = new int[arCount];

        String[] arItems = scanner.nextLine().split(" ");

        for (int arItr = 0; arItr < arCount; arItr++) {
            int arItem = Integer.parseInt(arItems[arItr].trim());
            ar[arItr] = arItem;
        }

        int result = simpleArraySum(ar,arCount);
        System.out.println(result);
		/*
		 * bufferedWriter.write(String.valueOf(result)); bufferedWriter.newLine();
		 * 
		 * bufferedWriter.close();
		 */
    }
	 
}
