package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
	
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		System.out.print("Enter salary: ");
		Double parameterSalary = sc.nextDouble();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while(line != null) {
				
			String[] fields = line.split(",");
			String name = fields[0];
			String email = fields[1];
			Double salary = Double.parseDouble(fields[2]);
			
			list.add(new Employee(name, email, salary));	
			
			line = br.readLine();
			}
			
			System.out.printf("Email of people whose salary is more than %.2f: %n", parameterSalary);
			
			Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> email = list.stream()
					.filter(p -> p.getSalary() > parameterSalary)
					.map(p -> p.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			
			
			email.forEach(System.out::println);
			
			
			
			double sum = list.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0, (x,y) -> x + y);
					
			System.out.printf("Sum of salary of people whose name starts with 'M': %.2f%n",sum);
					
			
			
			
		} catch( IOException e) {
			
			System.out.println("ERROR: " + e.getMessage());
		}
		
		
		
		
		
		sc.close();
	}

}
