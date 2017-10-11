package ua.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Transporter;
import ua.model.view.TransporterView;

public class SelectJoin {
	static Scanner sc = new Scanner(System.in);
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");

	static void printMenuSelectJoin() {
		System.out.println("26 - для пошуку транспортера за id;");
		System.out.println("27 - для вибору транспортера за місцем прибуття;");
		System.out.println("28 - для вибору транспортера, який прибуває у львів;");
		System.out.println("4 - для вибору транспортера, який має вік менше вказаного;");
		// System.out.println("5 - для видалення міста за id;");
		// System.out.println("6 - для зміни назви міста за id;");
		// System.out.println("7 - для додавання нової моделі;");
		// System.out.println("8 - для видалення моделі за id;");
		// System.out.println("9 - для зміни назви моделі за id;");
		// System.out.println("10 - для додавання нового транспортера;");
	}

	static void selectTransporterWithID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Enter id:");
		Transporter transporter = manager
				.createQuery("FROM Transporter t JOIN FETCH t.brand JOIN FETCH t.cityArrive WHERE t.id=?1",
						Transporter.class)
				.setParameter(1, sc.nextInt()).getSingleResult();

		manager.getTransaction().commit();
		manager.close();
		System.out.println(transporter.getName());
		System.out.println(transporter.getBrand().getName());
		System.out.println(transporter.getCityArrive().getName());
	}

	static void selectTransporterWhoGoToCity() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Enter city:");
		Transporter transporter = manager.createQuery(
				"SELECT t FROM Transporter t JOIN FETCH t.brand JOIN FETCH t.model JOIN t.cityArrive c WHERE c.name=?1",
				Transporter.class).setParameter(1, sc.next()).getSingleResult();
		manager.getTransaction().commit();
		manager.close();
		System.out.println("Name - " + transporter.getName() + " Вік- " + transporter.getAge());
		System.out.println("Марка автомобіля - " + transporter.getBrand().getName());
		System.out.println("Модель - " + transporter.getModel().getName());
	}

	static void selectTransporterWhoGoTolviv() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();

		TransporterView view = manager.createQuery(
				"SELECT new ua.model.view.TransporterView(t.id, t.rate, t.maxWeight, t.photoUrl, t.version, t.name, t.count, t.age, t.phone, b.name, m.name, t.carAge, c.name, t.dateArrive, t.status) FROM Transporter t JOIN t.brand b JOIN t.model m JOIN t.cityArrive c WHERE c.name=?1",
				TransporterView.class).setParameter(1, "Lviv").getSingleResult();

		manager.getTransaction().commit();
		manager.close();
		System.out.println(view);
		System.out.println(view.getName() + " " + view.getPhone());

	}

	static void selectTransporterWhoHaveAgeLessEnter() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Enter age:");
		List<TransporterView> view = manager.createQuery(
				"SELECT new ua.model.view.TransporterView(t.id, t.rate, t.maxWeight, t.photoUrl, t.version, t.name, t.count, t.age, t.phone, b.name, m.name, t.carAge, c.name, t.dateArrive, t.status) FROM Transporter t JOIN t.brand b JOIN t.model m JOIN t.cityArrive c WHERE t.age <= ?1",
				TransporterView.class).setParameter(1, sc.nextInt()).getResultList();
		System.out.println("transporters which have age less there which you enter:");
		for (TransporterView transporterView : view) {
			System.out.println(view);
			System.out.println(transporterView.getName());

		}
		manager.getTransaction().commit();
		manager.close();

	}

}
