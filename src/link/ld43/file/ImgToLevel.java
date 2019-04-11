package link.ld43.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

import link.bitmap.Bitmap;

public class ImgToLevel {
	
	//col[color][0] == id while col[color][1] == data
	public static final int[][] col = new int[0xffffff+1][2];
	
	public static void main(String[] args) throws IOException {
		initColors();
		File file = new File("C:/Users/ruspo/Desktop/Spaghetti Game levels to compile/level5.png");
		
		Bitmap bitmap = new Bitmap(ImageIO.read(file));
		Scanner in = new Scanner(System.in);
		System.out.println("Destination File Name:");
		File dest = new File("C:/Users/ruspo/workspace_ld43/In the name of Spaghetti/levels/" + in.nextLine() + ".lvl");
		dest.createNewFile();
		in.close();
		
		OutputStream out = new FileOutputStream(dest);
		
		out.write(bitmap.width);
		out.write(bitmap.height);
		for(int i = 0; i < bitmap.pixels.length; i++) {
			int[] color = col[bitmap.pixels[i]];
			out.write(color[0]);
			out.write(color[1]);
		}
		
		out.close();
	}
	
	public static void initColors() {
		col[0xffffff][0] = 0;
		
		col[0xff0000][0] = 0;
		
		col[0x000000][0] = 1;
		
		col[0x888888][0] = 2;
		
		col[0xffff00][0] = 3;
		
		col[0x222222][0] = 5;
		col[0x222222][1] = 1;
		col[0x333333][0] = 5;
		col[0x333333][1] = 2;
		col[0x444444][0] = 5;
		col[0x444444][1] = 3;
		col[0x555555][0] = 5;
		col[0x555555][1] = 4;
		col[0x666666][0] = 5;
		col[0x666666][1] = 5;
		
		col[0x00ff00][0] = 4;
		col[0x00ff00][1] = 0;
		
		col[0xffffdd][0] = 4;
		col[0xffffdd][1] = 1;
	}
	
}