public class Main {

    public static void main(String[] args) {

        String temp1 = "", temp2 = "5321235", temp3 = "213451323154231324";

        ResolverImplTest.checkResult(new int[]{1,2,3,4,0,5,6,7}, temp1);
        ResolverImplTest.checkResult(new int[]{2,1,3,4,0,5,6,7}, temp2);
        ResolverImplTest.checkResult(new int[]{0,1,2,3,4,5,6,7}, temp3);

        ResolverImplTest.checkTime(new int[]{1,2,3,4,0,5,6,7});
        ResolverImplTest.checkTime(new int[]{2,1,3,4,0,5,6,7});
        ResolverImplTest.checkTime(new int[]{0,1,2,3,4,5,6,7});

        ResolverImplTest.checkMemory(new int[]{1,2,3,4,0,5,6,7});
        ResolverImplTest.checkMemory(new int[]{2,1,3,4,0,5,6,7});
        ResolverImplTest.checkMemory(new int[]{0,1,2,3,4,5,6,7});

    }


}
