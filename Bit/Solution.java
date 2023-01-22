class Solution {
    public static boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        if (xCenter < x1-radius | xCenter > x2+radius | yCenter < y1-radius | yCenter > y2+radius) {
            return false;
        }
        if (Solution.getDistance(xCenter,yCenter,x1,y1) <= radius*radius | getDistance(xCenter,yCenter,x1,y2) <= radius*radius | getDistance(xCenter,yCenter,x2,y1) <= radius*radius | getDistance(xCenter,yCenter,x2,y2) <= radius*radius ) {
            return true;
        }
        return true;
    }

    //返回点(x1,y1)和点(x2,y2)之间的距离
    static int getDistance(int x1, int y1, int x2, int y2) {
        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }
}