package com.bengkel.booking.services;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;

public class Validation {
	
	public static Scanner input = new Scanner(System.in);
	public static String validasiInput(String question, String errorMessage, String regex) {
	    String result;
	    boolean isLooping = true;
	    do {
	      System.out.print(question);
	      result = input.nextLine();

	      //validasi menggunakan matches
	      if (result.matches(regex)) {
	        isLooping = false;
	      }else {
	        System.out.println(errorMessage);
	      }

	    } while (isLooping);

	    return result;
	  }
	
	public static int validasiNumberWithRange(String question, String errorMessage, String regex, int max, int min) {
	    int result;
	    boolean isLooping = true;
	    do {
	      result = Integer.valueOf(validasiInput(question, errorMessage, regex));
	      if (result >= min && result <= max) {
	        isLooping = false;
	      }else {
	        System.out.println("Pilihan angka " + min + " s.d " + max);
	      }
	    } while (isLooping);

	    return result;
	  }
	
	  public static Optional<Customer> validateLogin(List<Customer> listCustomer) {
		String nama;
		String password;
		Optional<Customer> customer;

			System.out.print("Masukkan Username : ");
			nama = input.nextLine();
			System.out.print("Masukkan Password : ");
			password = input.nextLine();

			customer = listCustomer.stream()
					.filter(data -> data.getName().equals(nama))
					.filter(data -> data.getPassword().equals(password))
					.findAny();

			if (!customer.isPresent()) {
				System.out.println("Username / Password yang anda masukan salah");
			}

		return customer;
	}

	public static Optional<ItemService> validateItemService(List<ItemService> listItem){
		String serviceId;
		Optional<ItemService> itemService;
		
		System.out.print("Masukkan Service Id : ");
		serviceId = input.nextLine();

		itemService = listItem.stream()
		.filter(data -> data.getServiceId().equals(serviceId))
		.findAny();

		if(!itemService.isPresent()){
			System.out.println("Masukkan Service Id dengan Benar");
		}

		return itemService;
	}

	public static String validationPaymentMethod(){
		String payment, result = "";
		String regex = "Cash|Saldo Coin";
		boolean isValid = false;

		do{
			System.out.print("Masukkan Metode Pembayaran : ");
			payment = input.nextLine();

			if(payment.matches(regex)){
				isValid = true;
				result = payment;
				break;
			}
		}while(!isValid);

		return result;
	}
}
