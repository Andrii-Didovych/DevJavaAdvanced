package ua.controller;

import java.time.LocalDateTime;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Brand;
import ua.entity.City;
import ua.entity.Model;
import ua.entity.Status;
import ua.entity.Transporter;

public class GPA {
	static Scanner sc = new Scanner(System.in);
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");

	static void printMenuGPA() {
		System.out.println("\n         ћ≈Ќё  ќ–»—“”¬ј„ј");
		System.out.println("0 - щоб викликати меню;");
		System.out.println("1 - дл€ додаванн€ марки автомоб≥л€;");
		System.out.println("2 - дл€ видаленн€ марки за id;");
		System.out.println("3 - дл€ зм ≥ни марки за id;");
		System.out.println("4 - дл€ додаванн€ м≥ста;");
		System.out.println("5 - дл€ видаленн€ м≥ста за id;");
		System.out.println("6 - дл€ зм≥ни назви м≥ста за id;");
		System.out.println("7 - дл€ додаванн€ новоњ модел≥;");
		System.out.println("8 - дл€ видаленн€ модел≥ за id;");
		System.out.println("9 - дл€ зм≥ни назви модел≥ за id;");
		System.out.println("10 - дл€ додаванн€ нового транспортера;");
	}

	static void addBrandOfCar() {
		System.out.println("¬вед≥ть марку автомоб≥л€: ");
		Brand brand = new Brand(sc.next());
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(brand);
		manager.getTransaction().commit();
		manager.close();
	}

	static void deliteBrandWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("¬вед≥ть id марки, €ку потр≥бно видалити:");
		Brand brand = manager.find(Brand.class, sc.nextInt());
		manager.remove(brand);
		manager.getTransaction().commit();
		manager.close();
	}

	static void changeBrandWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("¬вед≥ть id марки, €ку потр≥бно зам≥нити:");
		Brand brand = manager.find(Brand.class, sc.nextInt());
		System.out.println("¬вед≥ть нову марку:");
		brand.setName(sc.next());
		manager.getTransaction().commit();
		manager.close();
	}

	static void addCity() {
		System.out.println("¬вед≥ть м≥сто: ");
		City city = new City(sc.next());
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(city);
		manager.getTransaction().commit();
		manager.close();
	}

	static void deleteCityWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("¬вед≥ть id м≥ста, €ке потр≥бно видалити:");
		City city = manager.find(City.class, sc.nextInt());
		manager.remove(city);
		manager.getTransaction().commit();
		manager.close();
	}

	static void changeCityWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("¬вед≥ть id м≥ста, назву €кого потр≥бно зам≥нити:");
		City city = manager.find(City.class, sc.nextInt());
		System.out.println("¬вед≥ть назву м≥ста:");
		city.setName(sc.next());
		manager.getTransaction().commit();
		manager.close();
	}

	static void addNewModel() {
		System.out.println("¬вед≥ть модель: ");
		Model model = new Model(sc.next());
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(model);
		manager.getTransaction().commit();
		manager.close();
	}

	static void deleteModelWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("¬вед≥ть id модел≥, €ку потр≥бно видалити:");
		Model model = manager.find(Model.class, sc.nextInt());
		manager.remove(model);
		manager.getTransaction().commit();
		manager.close();
	}

	static void changeModelWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("¬вед≥ть id модел≥, назву €коњ потр≥бно зм≥нити:");
		Model model = manager.find(Model.class, sc.nextInt());
		System.out.println("¬вед≥ть назву модел≥:");
		model.setName(sc.next());
		manager.getTransaction().commit();
		manager.close();
	}

	static void addNewTransporter() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		System.out.println("¬вед≥ть id м≥ста:");
		City city = manager.find(City.class, sc.nextInt());
		
		System.out.println("¬вед≥ть id марки:");
		Brand brand = manager.find(Brand.class, sc.nextInt());
		
		System.out.println("¬вед≥ть id модел≥:");
		Model model = manager.find(Model.class, sc.nextInt());

		Transporter transporter = new Transporter();
		transporter.setCityArrive(city);
		transporter.setBrand(brand);
		transporter.setModel(model);

		System.out.println("¬вед≥ть в≥к:");
		transporter.setAge(sc.nextInt());

		System.out.println("¬вед≥ть в≥к машини:");
		transporter.setCarAge(sc.nextInt());

		System.out.println("¬вед≥ть к≥льк≥сть поњздок:");
		transporter.setCount(sc.nextInt());

		System.out.println("¬вед≥ть дату прибутт€ у формат≥ - (р,м,д,г,х), не дописуючи нул≥ наперед:");
		transporter
				.setDateArrive(LocalDateTime.of(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
		System.out.println("¬вед≥ть вантажоп≥дйомн≥сть:");

		transporter.setMaxWeight(sc.nextInt());
		System.out.println("¬вед≥ть ≥м'€:");

		transporter.setName(sc.next());
		System.out.println("¬вед≥ть номер телефону:");

		transporter.setPhone(sc.next());
		transporter.setStatus(Status.FREE);

		manager.persist(transporter);
		manager.getTransaction().commit();
		manager.close();

	}

}
