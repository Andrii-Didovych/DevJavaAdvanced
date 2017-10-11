package ua.controller;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AgrFunc {
	static Scanner sc = new Scanner(System.in);
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");

	static void printMenuAgrFunc() {
		System.out.println("21 - ��� ������ ������������� ������� ������� count � Owner;");
		System.out.println("22 - ��� ������ ���������� ������� ������� count � Owner;");
		System.out.println("23 - ��� ������ ���������� ������� ������� count � Owner;");
		System.out.println("24 - ��� ������ ���� ��� ������� ������� count � Owner;");
		System.out.println("25 - ��� ����������� ������� count � Owner;");
	}

	static void maxCountFromOvner() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Integer max = manager.createQuery("SELECT max(o.count) FROM Owner o ", Integer.class).getSingleResult();
		System.out.println("max - " + max);
		manager.getTransaction().commit();
		manager.close();
	}

	static void minCountFromOvner() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Integer min = manager.createQuery("SELECT min(o.count) FROM Owner o ", Integer.class).getSingleResult();
		System.out.println("min - " + min);
		manager.getTransaction().commit();
		manager.close();
	}

	static void avgCountFromOvner() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Double avg = manager.createQuery("SELECT avg(o.count) FROM Owner o ", Double.class).getSingleResult();
		System.out.println("avg - " + avg);
		manager.getTransaction().commit();
		manager.close();
	}

	static void sumCountFromOvner() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Long sum = manager.createQuery("SELECT sum(o.count) FROM Owner o ", Long.class).getSingleResult();
		System.out.println("sum - " + sum);
		manager.getTransaction().commit();
		manager.close();
	}

	static void countCountFromOvner() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Long count = manager.createQuery("SELECT count(o.count) FROM Owner o ", Long.class).getSingleResult();
		System.out.println("count - " + count);
		manager.getTransaction().commit();
		manager.close();
	}
}
