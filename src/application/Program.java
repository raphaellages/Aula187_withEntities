package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		String patch = "C:\\Users\\rapha\\Documents\\Workspace_eclipse\\Aula187_withEntities\\in.txt";
		//Scanner sc = null;
		List<Product> list = new ArrayList<>();
		File sourceFile = new File(patch);
		try (BufferedReader br = new BufferedReader (new FileReader(sourceFile))){
			String textLine = br.readLine();
			while (textLine!=null) {
				String[] text = textLine.split(",");
				String name = text[0];
				Integer quantity = Integer.parseInt(text[2]);
				Double price = Double.parseDouble(text[1]);
				Product product = new Product(name, quantity, price);
				list.add(product);
				textLine = br.readLine();
			}
			for (Product x : list) {
				System.out.print(x);
			}
			boolean success = new File(sourceFile.getParent()+"\\out").mkdir();
			String destinyPatch = sourceFile.getParent()+"\\out";
			System.out.println(success);
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(destinyPatch+"\\out.csv"))){
				for (Product x : list) {
					bw.write(x.toString());
					bw.newLine();
				}
			}
			catch (IOException e) {
				System.out.println("Error"+e.getMessage());
			}
		}
		catch(IOException e) {
			System.out.println("Error"+e.getMessage());
		}
	}

}
