class Sort
{
  public Sort()
  {    
  }
  
  public static void insertSort(int[] array)
  {
    for (int top = 1; top < array.length; top++)
    {
      int item = array[top]; 
      int i = top;
      while (i > 0 && item < array[i-1])
      {
        array[i] = array[i-1];
        i--;
      }
      array[i] = item;
    }
  }
  
  public static void shellSort(int[] array, int k)
  {
    for(int u=0; u<k; u++)
    {
      for(int j=k; j<array.length; j = j+k)
      {       
        if((u+j) < array.length)
        {
          int temp = array[u+j];
          int i = u+j;
          while(i > u && temp < array[i-k])
          {
            array[i] = array[i-k];
            i = i-k;
          }
          array[i] = temp;
        }
      }
    }    
  }
  
  public static void selectSort (int[] array)
  {
    for (int top = array.length - 1; top > 0; top--)
    {
      int largeLoc = 0;
      for (int i = 1; i <= top; i++)
      {
        if (array[i] > array[largeLoc])
        {
          largeLoc = i;
        }
      }
      int temp = array[top];
      array[top] = array[largeLoc];
      array[largeLoc] = temp;
    }
  }
  
  public static void bubbleSort (int[] array)
  {
    boolean sorted = false;
    for (int top = array.length - 1; top > 0 && sorted == false; top--)
    {
      sorted = true;
      for (int i = 0; i < top; i++)
      {
        if ((array[i] > array[i+1]))
        {
          sorted = false; 
          int temp = array[i];
          array[i] = array[i+1];
          array[i+1] = temp;
        }
      }
    }
  }
  
  public static void quickSort (int[] list)
  {
    quickSort(list, 0, list.length - 1);
  }
  
  public static void quickSort (int[] list, int low, int high)
  {
    final int MOVING_LEFT = 0;
    final int MOVING_RIGHT = 1;
    
    if (low < high)
    {
      int left = low;
      int right = high;
      int currentDirection = MOVING_LEFT;
      int pivotIndex = (int)(Math.random() * (high-low + 1) + low);
      int pivot = list[pivotIndex];
      
      while (left < right)
      {
        if (currentDirection == MOVING_LEFT)
        {
          while ((list[right] >= pivot) && (left < right))
            right--;
          
          list[left] = list[right];
          currentDirection = MOVING_RIGHT;
        }
        if (currentDirection == MOVING_RIGHT)
        {
          while ((list[left] <= pivot) && (left < right))
            left++;
          
          list[right] = list[left];
          currentDirection = MOVING_LEFT;
        }
      }
      list[left] = pivot; // or list[right] = pivot, since left == right
      quickSort(list, low, left-1);
      quickSort(list, right+1, high);
    }
  }
}