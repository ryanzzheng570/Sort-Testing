/* Student Name: Ryan Zheng
 * Assingment: Sorting Assignment
 * Description: Compare different sorting method of their time   */

import java.util.*;

public class sortTest
{  
  //Create field varibles
  private static int[] randomArray;
  private static int[] backUpArray;
  private static double initialTime = 0;
  private static double finalTime = 0;
  private static double[][] diffTime = new double[5][4];
  
  //Method to restore the oringinal random array
  public static void toOriginalArray()
  {
    for(int i=0; i<backUpArray.length; i++)
    {
      randomArray[i] = backUpArray[i];
    }
  }
  
  //Method for formatted output
  public static void output()
  {
    System.out.printf("%1s %6s %,12d %,12d %,12d %,12d %,12d\n", "Num Elements" , "---->", 1000, 5000, 10000, 50000, 100000);
    System.out.printf("%1s %16.2f %1s %9.2f %1s %9.2f %1s %9.2f %1s %9.2f %1s\n", "InsertionSort",diffTime[0][0] , "ms", diffTime[1][0], "ms", diffTime[2][0], "ms", diffTime[3][0], "ms", diffTime[4][0], "ms");
    System.out.printf("%1s %20.2f %1s %9.2f %1s %9.2f %1s %9.2f %1s %9.2f %1s\n", "ShellSort", diffTime[0][1], "ms", diffTime[1][1], "ms", diffTime[2][1], "ms", diffTime[3][1], "ms", diffTime[4][1], "ms");
    System.out.printf("%1s %16.2f %1s %9.2f %1s %9.2f %1s %9.2f %1s %9.2f %1s\n", "SelectionSort", diffTime[0][2], "ms", diffTime[1][2], "ms", diffTime[2][2], "ms", diffTime[3][2], "ms", diffTime[4][2], "ms");
    System.out.printf("%1s %19.2f %1s %9.2f %1s %9.2f %1s %9.2f %1s %9.2f %1s\n", "BubbleSort",diffTime[0][3], "ms", diffTime[1][3], "ms", diffTime[2][3], "ms", diffTime[3][3], "ms", diffTime[4][3], "ms");
  }
  
  public static void main(String[] args)
  { 
    Scanner ip = new Scanner(System.in);
    int startingValue = 0;
    int topValue = 1;
    
    //User Interface, let the user choose the value for the maximum and minimum value
    do
    {
      System.out.println("Please enter the integer range of the random array(***The first number should be smaller than the second number***)");
      System.out.println("Please enter the starting value");
      startingValue = ip.nextInt();
      System.out.println("Please enter the top value");
      topValue = ip.nextInt();
    }while(topValue <= startingValue);
    
    //Array for 5 different length of the array
    int[] sortLength = {1000, 5000, 10000, 50000, 100000};
    int a = 0;
    
    //Loop for 3 different Array h=0(Unsorted Array) h=1(Reversed Array) h=2(Mostly Sorted Array)
    for(int h=0; h<3; h++)
    {
      //Variable for the index of the sortLength array
      a = 0;
      
      //Different colomn corresponds to different array length i=0 (1000 length) i=1 (5000 length)
      for(int i=0; i<5; i++)
      {
        int k = 0;
        randomArray = new int[sortLength[a]];
        backUpArray = new int[sortLength[a]];
        
        //Condition for different types of array
        if(h == 0)
        {
          for(int u=0; u<randomArray.length; u++)
          {
            //(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);
            randomArray[u] = (int)(Math.random() * ((topValue - startingValue) + 1) + startingValue);
            backUpArray[u] = randomArray[u];
          }      
        }
        else if(h == 1)
        {
          //Temporary array for the Reversed array
          int[] temp = new int[sortLength[a]];
          //Variable for counting(index of the array)
          int b = 0;
          
          //Assign the random value to the array
          for(int u=0; u<sortLength[a]; u++)
          {
            randomArray[u] = (int)(Math.random() * ((topValue - startingValue) + 1) + startingValue);
          }
          
          //Sort the randomArray
          Sort.insertSort(randomArray);
          
          //Assign the reversed array to the temporary array
          for(int u=sortLength[a]-1; u>=0; u--)
          {
            temp[b] = randomArray[u];
            b++;
          }    
          
          //Assign the reversed temp array back to the randomArray
          for(int u=0; u<sortLength[a]; u++)
          {
            randomArray[u] = temp[u];
            backUpArray[u] = temp[u];
          }
        }
        else if(h == 2)
        {
          //Create a random array
          for(int u=0; u<sortLength[a]; u++)
          {
            randomArray[u] = (int)(Math.random() * ((topValue - startingValue) + 1) + startingValue);
          }
          
          //Sort the random array
          while(k<randomArray.length)
          {
            k = k*3 + 1;
          }    
          if(k >= randomArray.length)
          {
            k = (k-1)/3;
          }                
          while(k>=1)
          {
            Sort.shellSort(randomArray, k);
            k = (k-1)/3;
          }    
          
          //80% sorted, therefore less than 20% of the array is not sorted (Ex. In 1000, 200 elements are unsorted)
          //20% of number of element should switch place. Switch 2 elements each time, 
          //therefore it takes half of the number of unsorted element in a loop to switch the number of elements.
          for(int u=0; u<sortLength[a]/10; u++)
          {
            //Generate random value to switch the value in the array
            int ran1 = (int)(Math.random() * sortLength[a]);
            int ran2 = (int)(Math.random() * sortLength[a]);
            int temp = 0;
            
            //Switch the array
            temp = randomArray[ran1];
            randomArray[ran1] = randomArray[ran2];
            randomArray[ran2] = temp;
          } 
          
          //Assign the backUpArray for back up 
          for(int u=0; u<sortLength[a]; u++)
          {
            backUpArray[u] = randomArray[u];
          }         
        }
              
        //Get the starting time and end time of the sorting method
        initialTime = System.nanoTime();
        Sort.insertSort(randomArray);
        finalTime = System.nanoTime();      
        
        //Store the differences of the time, devide 10^6 to get milli second from nano second
        diffTime[i][0] = (finalTime - initialTime) / 1000000;
        //Call the method to assign the randomArray back to unsorted position
        toOriginalArray();    
        
        while(k<randomArray.length)
        {
          k = k*3 + 1;
        }    
        if(k >= randomArray.length)
        {
          k = (k-1)/3;
        }        
        
        initialTime = System.nanoTime();
        while(k>=1)
        {
          Sort.shellSort(randomArray, k);
          k = (k-1)/3;
        }    
        finalTime = System.nanoTime();
        
        diffTime[i][1] = (finalTime - initialTime) / 1000000;
        toOriginalArray();        
        
        /*System.out.println("Sele, before: ");
         for(int u=0; u<randomArray.length; u++)
         {
         System.out.print(randomArray[u] + " ");
         }
         System.out.println();*/
        
        initialTime = System.nanoTime();
        Sort.selectSort(randomArray);
        finalTime = System.nanoTime();
        
        /*System.out.println("Sele, after: ");   
         for(int u=0; u<randomArray.length; u++)
         {
         System.out.print(randomArray[u] + " ");
         }
         System.out.println();*/
        
        diffTime[i][2] = (finalTime - initialTime) / 1000000;
        toOriginalArray();
        
        /*System.out.println("Bubb, before: ");
         for(int u=0; u<randomArray.length; u++)
         {
         System.out.print(randomArray[u] + " ");
         }
         System.out.println();*/
        
        initialTime = System.nanoTime();
        Sort.bubbleSort(randomArray);
        finalTime = System.nanoTime();
        
        /*System.out.println("Bubb, after: ");
         for(int u=0; u<randomArray.length; u++)
         {
         System.out.print(randomArray[u] + " ");
         }
         System.out.println();*/
        
        diffTime[i][3] = (finalTime - initialTime) / 1000000;
        toOriginalArray();
        
        a++;
      }     
      if(h == 0)
      {
        System.out.println("Unsorted Array: ");
        output();
      }
      else if(h == 1)
      {
        System.out.println("Reversed Array: ");
        output();     
      }
      else if(h == 2)
      {
        System.out.println("Mostly(80%) Sorted Array: ");
        output();
      }
    }  
    ip.close();
  }
}