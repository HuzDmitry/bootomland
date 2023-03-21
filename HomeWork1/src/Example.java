public class Example {
    public static void main (String[] args) {
        int [] numbers = {2, 5, 13, 7, 6, 4};
        int size =numbers.length;
        int sum = 0;
        //int avg=0;
        int index=0;
        while (index<size){
            sum+=numbers[index];
            System.out.println (sum);
            index++;
        }
        //avg=sum/size;
        sum/=size;
        //System.out.println (avg);
        System.out.println (sum);
    }
}