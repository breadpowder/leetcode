package source;

public class WindowSliding {

   java.util.Deque<Integer> window =  new java.util.ArrayDeque<Integer>(256);

    public int[] maxSlidingWindow(int[] nums, int k) {

            if (nums.length==0) return new int[0];

            for(int idx=0; idx< k -1; idx++)
            {
                push(nums[idx]);
            }

            int[] result = new int[nums.length -k +1 ];

            for(int idx=0; idx< nums.length-k+1; idx++)
            {
                 push(nums[idx+k-1]);
                 result[idx] = window.getFirst();
                 if(result[idx] == nums[idx]) {
                     window.removeFirst();
                 }
            }

            return  result;
    }


    public void  push(int num){

        while(!window.isEmpty() && num > window.getLast())
        {
            window.removeLast();
        }
        window.addLast(num);

    }


    public static void  main(String[] args){

        WindowSliding sliding = new WindowSliding();

        int[] arr = {1,-1};
        int[] result = sliding.maxSlidingWindow(arr, 1);
        System.out.println(result[0]);
        System.out.println(result[1]);

    }
}
