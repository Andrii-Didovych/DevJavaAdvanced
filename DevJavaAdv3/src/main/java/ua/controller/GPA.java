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
		System.out.println("\n         ���� �����������");
		System.out.println("0 - ��� ��������� ����;");
		System.out.println("1 - ��� ��������� ����� ���������;");
		System.out.println("2 - ��� ��������� ����� �� id;");
		System.out.println("3 - ��� �� ��� ����� �� id;");
		System.out.println("4 - ��� ��������� ����;");
		System.out.println("5 - ��� ��������� ���� �� id;");
		System.out.println("6 - ��� ���� ����� ���� �� id;");
		System.out.println("7 - ��� ��������� ���� �����;");
		System.out.println("8 - ��� ��������� ����� �� id;");
		System.out.println("9 - ��� ���� ����� ����� �� id;");
		System.out.println("10 - ��� ��������� ������ ������������;");
	}

	static void addBrandOfCar() {
		System.out.println("������ ����� ���������: ");
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
		System.out.println("������ id �����, ��� ������� ��������:");
		Brand brand = manager.find(Brand.class, sc.nextInt());
		manager.remove(brand);
		manager.getTransaction().commit();
		manager.close();
	}

	static void changeBrandWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("������ id �����, ��� ������� �������:");
		Brand brand = manager.find(Brand.class, sc.nextInt());
		System.out.println("������ ���� �����:");
		brand.setName(sc.next());
		manager.getTransaction().commit();
		manager.close();
	}

	static void addCity() {
		System.out.println("������ ����: ");
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
		System.out.println("������ id ����, ��� ������� ��������:");
		City city = manager.find(City.class, sc.nextInt());
		manager.remove(city);
		manager.getTransaction().commit();
		manager.close();
	}

	static void changeCityWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("������ id ����, ����� ����� ������� �������:");
		City city = manager.find(City.class, sc.nextInt());
		System.out.println("������ ����� ����:");
		city.setName(sc.next());
		manager.getTransaction().commit();
		manager.close();
	}

	static void addNewModel() {
		System.out.println("������ ������: ");
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
		System.out.println("������ id �����, ��� ������� ��������:");
		Model model = manager.find(Model.class, sc.nextInt());
		manager.remove(model);
		manager.getTransaction().commit();
		manager.close();
	}

	static void changeModelWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("������ id �����, ����� ��� ������� ������:");
		Model model = manager.find(Model.class, sc.nextInt());
		System.out.println("������ ����� �����:");
		model.setName(sc.next());
		manager.getTransaction().commit();
		manager.close();
	}

	static void addNewTransporter() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		System.out.println("������ id ����:");
		City city = manager.find(City.class, sc.nextInt());
		
		System.out.println("������ id �����:");
		Brand brand = manager.find(Brand.class, sc.nextInt());
		
		System.out.println("������ id �����:");
		Model model = manager.find(Model.class, sc.nextInt());

		Transporter transporter = new Transporter();
		transporter.setCityArrive(city);
		transporter.setBrand(brand);
		transporter.setModel(model);

		System.out.println("������ ��:");
		transporter.setAge(sc.nextInt());

		System.out.println("������ �� ������:");
		transporter.setCarAge(sc.nextInt());

		System.out.println("������ ������� ������:");
		transporter.setCount(sc.nextInt());

		System.out.println("������ ���� �������� � ������ - (�,�,�,�,�), �� ��������� ��� �������:");
		transporter
				.setDateArrive(LocalDateTime.of(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
		System.out.println("������ ����������������:");

		transporter.setMaxWeight(sc.nextInt());
		System.out.println("������ ��'�:");

		transporter.setName(sc.next());
		System.out.println("������ ����� ��������:");

		transporter.setPhone(sc.next());
		transporter.setStatus(Status.FREE);

		manager.persist(transporter);
		manager.getTransaction().commit();
		manager.close();

	}

}
