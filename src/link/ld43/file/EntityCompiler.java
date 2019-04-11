package link.ld43.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EntityCompiler {
	
	public static void main(String[] args) throws IOException {
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("C:/Users/ruspo/Desktop/Spaghetti Game levels to compile/level5entity.txt"));
		System.out.println("Insert File name");
		File file = new File("C:/Users/ruspo/workspace_ld43/In the name of Spaghetti/levels/" + in.nextLine() + ".entity");
		System.out.println(file);
		file.createNewFile();
		OutputStream out = new FileOutputStream(file);
		List<Integer> data = new ArrayList<Integer>();
		System.out.println("Insert Entity id (0 == player, 1 == police, 2 == spaghetti) and then");
		System.out.println("add attributes (say 255 to end modifing an entity).");
		System.out.println("Say 254 to end program.");
		while(true) {
			try {
				int buffer = in.nextInt();
				if(buffer == 254) {
					for(int i = 0; i < data.size(); i++) {
						out.write(data.get(i));
					}
					out.close();
					in.close();
					return;
				}
				data.add(buffer);
			} catch (InputMismatchException e) {
				continue;
			}
		}
	}
	
}