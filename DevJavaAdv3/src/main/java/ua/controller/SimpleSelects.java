package ua.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Brand;
import ua.entity.Cargo;
import ua.entity.Owner;
import ua.entity.Transporter;

public class SimpleSelects {
	static Scanner sc = new Scanner(System.in);
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");

	static void printMenuSimpleSelect() {
		System.out.println("11 - введіть назву машини, id якої потрібно знайти;");
		System.out.println("12 - моделі, які мають значення менші за введене id;");
		System.out.println("13 - Назва яка починається з букви M, та id менше вказаного;");
		System.out.println("14 - Назви які мають id - 1 i 3;");
		System.out.println("15 - Товар з вказаним діапазоном ціни:;");
		System.out.println("16 - Товар з вказаними розмірами;");
		System.out.println("17 - FROM Cargo c WHERE c.length < ?1;");
		System.out.println("18 - FROM Owner o WHERE o.address LIKE ?1;");
		System.out.println("19 - FROM Owner  WHERE _count BETWEEN ?1 AND ?2;");
		System.out.println("20 -FROM Transporter t  WHERE t.carAge BETWEEN ?1 AND ?2;");
	}

	static void searchIdOfCarWithName() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Введіть назву машини, id якої потрібно знайти:");
		List<Brand> list = manager.createQuery("From Brand b WHERE b.name = ?1", Brand.class)
				.setParameter(1, new String(sc.next())).getResultList();
		for (Brand brand1 : list) {
			System.out.println("id даної моделі - " + brand1.getId());
		}
		manager.getTransaction().commit();
		manager.close();
	}

	static void searchModelWithHaveLessID() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Введіть id:");
		List<Brand> list = manager.createQuery("From Brand b WHERE b.id <= ?1", Brand.class)
				.setParameter(1, new Integer(sc.nextInt())).getResultList();
		System.out.print("Моделі, які мають значення менші за введене id - ");
		for (Brand brand1 : list) {
			System.out.print(brand1.getName() + " ");
		}
		manager.getTransaction().commit();
		manager.close();
	}

	static void searchNameWhereFirstLetterIsM() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Введіть id:");
		List<Brand> list = manager.createQuery("FROM Brand b WHERE b.name LIKE ?1 AND b.id <=?2 ", Brand.class)
				.setParameter(1, "M%").setParameter(2, new Integer(sc.nextInt())).getResultList();
		System.out.println("Назва яка починається з букви M, та id менше вказаного: ");
		for (Brand brand2 : list) {
			System.out.println(brand2.getName() + " " + brand2.getId());
		}
		manager.getTransaction().commit();
		manager.close();
	}

	static void modelWhichHasIDIs1End3() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		List<Brand> list = manager.createQuery("FROM Brand b WHERE b.id IN(?1) ", Brand.class)
				.setParameter(1, Arrays.asList(1, 3)).getResultList();
		System.out.println("Назви які мають id - 1 i 3: ");
		for (Brand brand2 : list) {
			System.out.println(brand2.getName() + " " + brand2.getId());
		}
		manager.getTransaction().commit();
		manager.close();
	}

	static void priceRangeOfCargo() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Введіть діапазон ціни:");
		List<Cargo> resultList = manager.createQuery("FROM Cargo c WHERE c.price BETWEEN  ?1 AND ?2 ", Cargo.class)
				.setParameter(1, new BigDecimal(sc.nextBigInteger()))
				.setParameter(2, new BigDecimal(sc.nextBigInteger())).getResultList();
		System.out.println("Товар з вказаним діапазоном ціни: ");
		for (Cargo cargo : resultList) {
			System.out.println(cargo.getGoods().getName());
		}
		manager.getTransaction().commit();
		manager.close();
	}

	static void cargoWithEnterSize() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Введіть діапазон висоти та ширини:");
		List<Cargo> resultList = manager
				.createQuery("FROM Cargo c WHERE c.height BETWEEN  ?1 AND ?2 AND c.width BETWEEN ?3 AND ?4 ",
						Cargo.class)
				.setParameter(1, new Integer(sc.nextInt())).setParameter(2, new Integer(sc.nextInt()))
				.setParameter(3, new Integer(sc.nextInt())).setParameter(4, new Integer(sc.nextInt())).getResultList();
		System.out.println("Товар з вказаними розмірами: ");
		for (Cargo cargo : resultList) {
			System.out.println("Товар " + cargo.getGoods().getName() + " Власник - " + cargo.getOwner().getName());
		}
		manager.getTransaction().commit();
		manager.close();

	}

	static void fromCargoWheerLengthSellEnter() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Enter length:");
		List<Cargo> resultList = manager.createQuery("FROM Cargo c WHERE c.length < ?1 ", Cargo.class)
				.setParameter(1, new Integer(sc.nextInt())).getResultList();
		for (Cargo cargo : resultList) {
			System.out.println(cargo.getCityFrom().getName() + " " + cargo.getCityTo().getName());
		}

		manager.getTransaction().commit();
		manager.close();
	}

	static void fromOwnerWhereAddressLike() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		List<Owner> resultList2 = manager.createQuery("FROM Owner o WHERE o.address LIKE ?1 ", Owner.class)
				.setParameter(1, "d%").getResultList();
		for (Owner owner : resultList2) {
			System.out.println(owner.getName());
		}
		manager.getTransaction().commit();
		manager.close();
	}

	static void fromOwnerWhereCountBetween1AND2() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Enter count max end min:");
		List<Owner> resultList2 = manager.createQuery("FROM Owner  WHERE _count BETWEEN ?1 AND ?2 ", Owner.class)
				.setParameter(1, new Integer(sc.nextInt())).setParameter(2, new Integer(sc.nextInt())).getResultList();
		for (Owner owner : resultList2) {
			System.out.println(owner.getName() + owner.getPhone());
		}
		manager.getTransaction().commit();
		manager.close();
	}

	static void fromTransporterWhereCarAgeBetween1AND2() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		System.out.println("Enter count max end min:");
		List<Transporter> resultList3 = manager
				.createQuery("FROM Transporter t  WHERE t.carAge BETWEEN ?1 AND ?2 ", Transporter.class)
				.setParameter(1, new Integer(sc.nextInt())).setParameter(2, new Integer(sc.nextInt())).getResultList();
		for (Transporter transporter2 : resultList3) {
			System.out.println(transporter2.getBrand().getName());
		}
		manager.getTransaction().commit();
		manager.close();

	}

}
