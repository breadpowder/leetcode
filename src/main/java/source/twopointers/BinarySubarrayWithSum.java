package source.twopointers;

public class BinarySubarrayWithSum {

    public int numSubarraysWithSum(int[] A, int S) {



        int count = 0;

        int sum =0;
        int left = 0;
        int right = -1;

        //Initiate, compute first one  (1 xxx 1) with sum to S, like 0 1 0 1 0 1 1  sum -2

        //for loop does not work !!!! for( right++)

        if(S==0){
            int i =0;
            while(i < A.length){
                int continous = 0;
                while(i< A.length && A[i++]==0) {
                    continous ++;
                }

                count += (continous + 1) * continous/2;
            }

            return  count;
        }

        while(sum<S && ++right<A.length ){

            if(A[right]==1) {
                sum+=1;
            }
        }


        // Find the next 1 in the left side and right side
        while(right<A.length){

            int leftCount = 1;

            while(left<A.length && A[left]==0) {
                leftCount++;
                left ++;
            }

            int rightCount = 1;

            while(right<A.length-1 && A[right+1]==0){
                rightCount++;
                right++;

            }


            count = count + leftCount * rightCount;

            left++;
            right++;

        }

        return count;


        //Can I use segment tree
        //Can I use BIT
    }

    public static void main(String[] args) {
      BinarySubarrayWithSum testee =  new BinarySubarrayWithSum();

      System.out.println(testee.numSubarraysWithSum(new int[]{1,0,1}, 2));

      System.out.println(testee.numSubarraysWithSum(new int[]{1,0,1},1));

        System.out.println(testee.numSubarraysWithSum(new int[]{0,1,0,1},1));

        System.out.println(testee.numSubarraysWithSum(new int[]{0,1,0,1},0));

        System.out.println(testee.numSubarraysWithSum(new int[]{0,1,0,1},10));

        System.out.println(testee.numSubarraysWithSum(new int[]{0,0,0,0},0));




    }
}
