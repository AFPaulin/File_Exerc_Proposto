package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter a file path: ");
		String strPath = sc.nextLine();
		
		File path = new File(strPath);
		String source = path.getParent();
		
		boolean success = new File(source + "\\out").mkdir();
	//	System.out.println(source);
	//	System.out.println("Pasta criada com:" + success);
		
		String target = source + "\\out\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		
			String line = br.readLine();
			while (line != null) {
			String[] vect = line.split(",");
			String name = vect[0];
			double price = Double.parseDouble(vect[1]);
			int quantity = Integer.parseInt(vect[2]);
			
			list.add(new Product(name, price, quantity));
			
			line = br.readLine();
			}
		
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(target,true))) {
		
				for (Product item : list) {
					bw.write(item.toString());
					bw.newLine();
				}
			
				System.out.println("Criado arquivo com sucesso!");
			
			} catch (IOException e) {
				System.out.println("Erro escrita: " + e.getMessage());
			}
				
			} catch (IOException e) {
				System.out.println("Erro leitura: " + e.getMessage());
			}
	
			sc.close();
	}	
}	
	