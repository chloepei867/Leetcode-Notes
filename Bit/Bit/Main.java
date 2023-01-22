package Bit;

public class Main {
    public static boolean main(String[] args) {
        int radius = 1415;
        int xCenter = 807;
        int yCenter = -784;
        int x1 = -733;
        int y1 = 623;
        int x2 = -533;
        int y2 = 1005;
        return Solution.checkOverlap(radius, xCenter, yCenter, x1, y1, x2, y2);
    }

    // public static void main(String[] args) {
    //// int a = 10; //00001010
    //// int b = 38; //00100110
    //
    // //按位与运算： &
    // // 1 & 1 = 1
    // // 0 & 1 = 0
    // // 1 & 0 = 0
    // // 0 & 0 = 0
    // // a & b = 00000010 = 2
    //// System.out.println(a & b); //output : 2
    //
    // //按位或运算： |
    // // 1 | 1 = 1
    // // 1 | 0 = 1
    // // 0 | 1 = 1
    // // 0 | 0 = 0
    // // a | b = 00101100 = 44
    //// System.out.println(a | b);
    //
    // //按位异或运算： ^ （XOR）
    // //两个bit一样，则返回0，否则返回1
    // // 1 ^ 1 = 0
    // // 1 ^ 0 = 1
    // // 0 ^ 1 = 1
    // // 0 ^ 0 = 0
    // // a | b = 00101100 = 48
    //// System.out.println(a ^ b);
    //
    // //按位非运算： ~ ，是单目运算（取反操作，即二进制的每一位都取反）
    // //正数取反变负数，负数取反变正数
    //// System.out.println(~10);
    //// System.out.println(~-10);
    //
    // //左移操作： <<
    // //将 A 的二进制表示的整体向左移 B 位，
    // // 左边超出 32 位的截掉（如果是 int 的话），右边不足的位补 0
    // //00001010 << 1 = 00010100 = 20 = 10* 2^1
    // //00001010 << 2 = 00101000 = 40 = 10* 2^2
    //// System.out.println(10<<1); //输出20
    //// System.out.println(10<<2); //输出40
    //
    // //(算术）右移操作： >>
    // //舍弃最低位，高位用【符号位】填补
    // /**
    // * 将A的二进制表示的每一位向右移B位，右边超出的位截掉，左边不足的位【补符号位】的数（比如负数符号位是1则补充1，正数符号位是0则补充0），
    // * 所以对于算术右移，原来是负数的，结果还是负数，原来是正数的结果还是正数。
    // */
    // //00001010 >> 1 = 00000101 = 5 = 10/(2^1)
    // //00001010 >> 2 = 00000010 = 2 = 10/(2^2)
    //// System.out.println(10>>1); //输出5
    //// System.out.println(10>>2); //输出2
    //
    // //(逻辑)右移操作 ： >>>
    // //舍弃最低位，高位用【0】填补
    // /**
    // * 将A的二进制表示的每一位向右移B位，右边超出的位截掉，左边不足的位【补0】。所以对于逻辑右移，结果将会是一个正数
    // */
    //// System.out.println(-127>>2); //输出-32
    //// System.out.println(-127>>>2); //输出1073741792
    //
    //// char x = 'a';
    //// char y = 'b';
    //// System.out.println(x^x);// output: 0
    //// System.out.println(x^y);// output: 3
    //// System.out.println(0^x);// output: 97
    //
    //// System.out.println(4/3 + "");
    //
    // //String.format()
    //
    // String str1 = "Here are some fruits";
    // String str2 = "apples, oranges and bananas";
    // //concatenate two strings
    // String str3 = String.format(str1, str2);//str3 = "Here are some fruits"
    // //str4 = "The first sentence is Here are some fruits, the second one is
    // apples, oranges and bananas"
    // String str4 = String.format("The first sentence is %s, the second one is %s",
    // str1, str2);
    // // Output is given upto 8 decimal places
    // String str5 = String.format("My answer is %.8f", 47.65734);
    //
    // // Custom input string to be formatted
    // String stra = "GFG";
    // String strb = "GeeksforGeeks";
    //
    // // %1$ represents first argument
    // // %2$ second argument
    // //str6 = "My Company name is: GFG, GFG and GeeksforGeeks"
    // String str6 = String.format("My Company name is: %1$s, %1$s and %2$s", stra,
    // strb);

}
