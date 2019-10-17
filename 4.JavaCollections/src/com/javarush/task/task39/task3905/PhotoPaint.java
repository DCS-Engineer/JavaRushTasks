package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (image == null ||
                desiredColor == null ||
                image.length <= y ||
                image[y].length <= x ||
                image[y][x] == null ||
                image[y][x] == desiredColor)
            return false;
        paintPoint(image, x, y, image[y][x], desiredColor);
        return true;
    }

    private void paintPoint(Color[][] image, int x, int y, Color startColor, Color desiredColor){
        if (y < 0 || x < 0 )
            return;
        if (y >= image.length)
            return;
        if (image[y] == null)
            return;
        if (x >= image[y].length)
            return;
        if (image[y][x] != startColor)
            return;

        image[y][x] = desiredColor;
        paintPoint(image, x-1, y, startColor, desiredColor);
        paintPoint(image, x+1, y, startColor, desiredColor);
        paintPoint(image, x, y-1, startColor, desiredColor);
        paintPoint(image, x, y+1, startColor, desiredColor);
    }
}
