package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.repositories.CustomerRepository;
import com.bengkel.booking.repositories.ItemServiceRepository;

public class BengkelService {

	static int num = 1;
	private static String username = "";
	private static List<Customer> listAllCustomers = CustomerRepository.getAllCustomer();
	private static List<ItemService> listAllItemService = ItemServiceRepository.getAllItemService();
	private static List<BookingOrder> listBookingOrder = new ArrayList<>();
	private static Scanner input = new Scanner(System.in);
	
	//Silahkan tambahkan fitur-fitur utama aplikasi disini
	
	//Login
	public static void login() {
		boolean isAny = false;
		do{
			Optional<Customer> customer = Validation.validateLogin(listAllCustomers);
			if(customer.isPresent()){
				Customer cust = customer.get();
				System.out.println("Selamat Datang , "+ cust.getName());
				isAny = true;
				username =  cust.getName();
			}
		}while(!isAny);
	}

	//Info Customer
	public static void infoCustomer(){
		PrintService.printCustomer(listAllCustomers, username);
	}

	//Booking atau Reservation

	public static void reservationBengkel(){
		String id = String.format("Book-"+num);
		PrintService.printItemService(listAllItemService);
		boolean isAgain = false;
		String regex = "[Y|T]";
		String pilihan;
		List<ItemService> item = new ArrayList<>();

		Customer customer = findCustomerByUsername(username);

		do{
			Optional<ItemService> itemService = Validation.validateItemService(listAllItemService);
			if(itemService.isPresent()){
				if(!item.contains(itemService)){
					item.add(itemService.get());

					if(customer instanceof MemberCustomer){
						System.out.print("Ingin menambah 1 Item Service? : ");
						pilihan = input.nextLine().toUpperCase();

						if(pilihan.matches(regex)){
							if(pilihan.equalsIgnoreCase("Y")){
								isAgain = false;
							}else{
								isAgain = true;
							}
						}
					}else{
						isAgain = true;
					}
					
				}else{
					System.out.println("Item Service telah dipilih sebelumnya");
				}
				
			}
		}while(!isAgain);

		String paymentMethod = Validation.validationPaymentMethod();

		BookingOrder book = new BookingOrder(id, customer, item, paymentMethod, totalServicePrice(item));

		listBookingOrder.add(book);
		System.out.println("Reservasi Bengkel Berhasil");
	}

	public static Customer findCustomerByUsername(String user){
		Optional<Customer> cust;
		cust = listAllCustomers.stream()
		.filter(data -> data.getName().equals(user))
		.findAny();

		return cust.get();
	}

	public static double totalServicePrice(List<ItemService> item){
		double result = 0;

		for(ItemService it : item){
			result += it.getPrice();
		}

		return result;
	}
	
	//Top Up Saldo Coin Untuk Member Customer

	public static void topUpSaldoCoin(){
		double coin;
		System.out.print("Masukkan Jumlah Coin : ");
		coin = input.nextDouble();
		boolean isMember = false;

		for(Customer cust : listAllCustomers){
			if(cust instanceof MemberCustomer){
				MemberCustomer memCust = (MemberCustomer) cust;
				if(memCust.getName().equals(username)){
					isMember = true;
					memCust.setSaldoCoin(coin);
					System.out.println("Top Up Coin Berhasil !!");
					break;
				}
			}
		}

		if(!isMember){
			System.out.println("Anda tidak memiliki MemberCustomer");
		}
	}

	//Informasi Booking
	public static void infoBooking(){
		PrintService.printBooking(listBookingOrder);
	}
	
	//Logout
	public static void logout(){
		username = "";
	}
	
}
