package com.bengkel.booking.services;


public class MenuService {
	
	public static void run() {
		boolean isLooping = true;
		do {
			BengkelService.login();
			mainMenu();
		} while (isLooping);
		
	}
	
	public static void mainMenu() {
		String[] listMenu = {"Informasi Customer", "Booking Bengkel", "Top Up Bengkel Coin", "Informasi Booking", "Logout"};
		int menuChoice = 0;
		boolean isLooping = true;
		
		do {
			PrintService.printMenu(listMenu, "Booking Bengkel Menu");
			menuChoice = Validation.validasiNumberWithRange("Masukan Pilihan Menu: ", "Input Harus Berupa Angka!", "^[0-9]+$", listMenu.length-1, 0);
			System.out.println(menuChoice);
			
			switch (menuChoice) {
			case 1:
				//panggil fitur Informasi Customer
				BengkelService.infoCustomer();
				break;
			case 2:
				//panggil fitur Booking Bengkel
				BengkelService.reservationBengkel();
				break;
			case 3:
				//panggil fitur Top Up Saldo Coin
				BengkelService.topUpSaldoCoin();
				break;
			case 4:
				//panggil fitur Informasi Booking Order
				BengkelService.infoBooking();
				break;
			default:
				BengkelService.logout();
				System.out.println("Logout");
				isLooping = false;
				break;
			}
		} while (isLooping);
	}
	
	//Silahkan tambahkan kodingan untuk keperluan Menu Aplikasi
}
