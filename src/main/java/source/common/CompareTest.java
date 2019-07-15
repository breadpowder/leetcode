//package source.common;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.Random;
//
//public class CompareTest {
//
//
//    PriorityQueue
//
//    public class MyObject {
//
//        public int createOrder() {
//
//            return new Random().nextInt();
//        }
//    }
//
//
//    public class MyObjectCom extends MyObject implements Comparable<MyObject>{
//
//
//        @Override
//        public int compareTo(MyObject o) {
//            return ((Integer)this.createOrder()).compareTo(o.createOrder());
//        }
//    }
//
//
//    MyObject[] objs = {new MyObject(), new MyObject()};
//
//
//    public void testSortInPlace(){
//
//        Arrays.sort(objs, Comparator.comparing(MyObject::createOrder));
//
//    }
//
//    public void testSortWithComparableInterface(){
//
//
//    }
//
//
//}
