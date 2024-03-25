package com.bengkel.booking.services;

import java.util.List;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Car;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.Vehicle;

public class PrintService {
	
	public static void printMenu(String[] listMenu, String title) {
		String line = "+---------------------------------+";
		int number = 1;
		String formatTable = " %-2s. %-25s %n";
		
		System.out.printf("%-25s %n", title);
		System.out.println(line);
		
		for (String data : listMenu) {
			if (number < listMenu.length) {
				System.out.printf(formatTable, number, data);
			}else {
				System.out.printf(formatTable, 0, data);
			}
			number++;
		}
		System.out.println(line);
		System.out.println();
	}
	
	public static void printVechicle(List<Vehicle> listVehicle) {
		String formatTable = "| %-2s | %-15s | %-10s | %-15s | %-15s | %-5s | %-15s |%n";
		String line = "+----+-----------------+------------+-----------------+-----------------+-------+-----------------+%n";
		System.out.format(line);
	    System.out.format(formatTable, "No", "Vechicle Id", "Warna", "Brand", "Transmisi", "Tahun", "Tipe Kendaraan");
	    System.out.format(line);
	    int number = 1;
	    String vehicleType = "";
	    for (Vehicle vehicle : listVehicle) {
	    	if (vehicle instanceof Car) {
				vehicleType = "Mobil";
			}else {
				vehicleType = "Motor";
			}
	    	System.out.format(formatTable, number, vehicle.getVehiclesId(), vehicle.getColor(), vehicle.getBrand(), vehicle.getTransmisionType(), vehicle.getYearRelease(), vehicleType);
	    	number++;
	    }
	    System.out.printf(line);
	}

	public static void printCustomer(List<Customer> listCustomer, String username){
		String formatTable = "| %-2s | %-15s | %-10s | %-15s |%n";
		String line = "+----+-----------------+------------+-----------------+-----------------+-------+-----------------+%n";
		System.out.format(line);
	    System.out.format(formatTable, "No", "Customer Id", "Nama", "Alamat");
	    System.out.format(line);
		int number = 1;
		for(Customer customer : listCustomer){
			if(customer.getName().equals(username)){
				
				System.out.format(formatTable, number, customer.getCustomerId(), customer.getName(), customer.getAddress());
				number++;
			}
		}
	}
	

	public static void printItemService(List<ItemService> itemServices){
		String formatTable = "| %-2s | %-15s | %-10s | %-15s |%n";
		String line = "+----+-----------------+------------+-----------------+-----------------+-------+-----------------+%n";
		System.out.format(line);
	    System.out.format(formatTable, "No", "Service Id", "Nama Service", "Jenis Kendaraan", "Harga");
	    System.out.format(line);
		int number = 1;
		for(ItemService item : itemServices){
			System.out.format(formatTable, number, item.getServiceId(), item.getServiceName(), item.getVehicleType(), item.getPrice());
			number++;
		}
	}

	//Tampilan Booking Order
	public static void printBooking(List<BookingOrder> booking){
		String formatTable = "| %-2s | %-15s | %-10s | %-15s | %-15s | %-15s |%n";
		String line = "+----+-----------------+------------+-----------------+-----------------+-------+-----------------+%n";
		System.out.format(line);
	    System.out.format(formatTable, "No", "Booking Id", "Nama Customer", "Service", "Total Service Price", "Total Booking");
	    System.out.format(line);
		int number = 1;
		for(BookingOrder book : booking){
			System.out.format(formatTable, number, book.getBookingId(), book.getCustomer().getName(), printItemServiceBooking(book.getServices()), book.getTotalServicePrice(), book.getTotalPayment());
			number++;
		}
	}

	public static String printItemServiceBooking(List<ItemService> listItem){
		String item = "";

		for(ItemService it : listItem){
			item += it.getServiceName();
		}

		return item;
	}
	//Silahkan Tambahkan function print sesuai dengan kebutuhan.
	
}
