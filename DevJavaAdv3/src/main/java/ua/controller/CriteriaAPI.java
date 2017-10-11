package ua.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import ua.entity.Brand;
import ua.entity.City;
import ua.entity.Model;
import ua.entity.Status;
import ua.entity.Transporter;
import ua.model.view.TransporterView;

public class CriteriaAPI {
	static Scanner sc = new Scanner(System.in);
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");

	static void TransporterCrAPI() {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<TransporterView> cq = cb.createQuery(TransporterView.class);
		Root<Transporter> root = cq.from(Transporter.class);
		
		Join<Transporter, Brand> brandJoin = root.join("brand");
		Join<Transporter, Model> modelJoin = root.join("model");
		Join<Transporter, City> cityJoin = root.join("cityArrive");
		
		cq.multiselect(root.get("id"), root.get("rate"), root.get("maxWeight"), root.get("photoUrl"),
				root.get("version"), root.get("name"), root.get("count"), root.get("age"), root.get("phone"),
				brandJoin.get("name"), modelJoin.get("name"), root.get("carAge"), cityJoin.get("name"),
				root.get("dateArrive"), root.get("status"));
		
		
		
		
//		Predicate dataArrivePredicate = null;
//		System.out.println("1) Для вибору дати - 0, або ж пропустіть натиснувши іншу клавішу");
//		if (sc.next().equals("0")) {
//			System.out.println("Введіть дату, від якої хочете побачити можливі перевезення");
//			dataArrivePredicate = cb.ge(root.get("dataArrive"), new LocalDateTimeType());
//					}else {
//			dataArrivePredicate = cb.ge(root.get("dataArrive"), new BigDecimal(0));
//		}
		
		Predicate ratePredicate = null;
		System.out.println("1) Для вибору мінімального значення рейтенгу - 0, або ж пропустіть натиснувши іншу клавішу");
		if (sc.next().equals("0")) {
			System.out.println("Введіть мінімальне значення рейтенгу:");
			ratePredicate = cb.ge(root.get("rate"), new BigDecimal(sc.nextBigInteger()));
		}else {
			ratePredicate = cb.ge(root.get("rate"), new BigDecimal(0));
		}
		
		Predicate maxWeightPredicate = null;
		System.out.println("2) Для вибору мінімальної вантажопідйомності - 0, або ж пропустіть натиснувши іншу клавішу");
		if (sc.next().equals("0")) {
			System.out.println("Введіть мінімальну вантажопідйомність: ");
			maxWeightPredicate = cb.ge(root.get("maxWeight"), new Integer(sc.nextInt()));
		}else {
			maxWeightPredicate = cb.ge(root.get("maxWeight"), new BigDecimal(0));
		}
		
		Predicate agePredicate = null;
		System.out.println("3) Для вводу підходящого для вас віку - 0, пропустити - інша клавіша");
		if (sc.next().equals("0")) {
			System.out.println("Введіть підходящий для вас вік, від і до:");
			agePredicate = cb.between(root.get("age"), sc.nextInt(), sc.nextInt());
		}else {
			agePredicate = cb.between(root.get("age"), 18, 100);
		}
		
		Predicate countPredicate = null;
		System.out.println("4) Для вводу підходящої для вас кількості поїздок - 0, пропустити - інша клавіша");
		if (sc.next().equals("0")) {
			System.out.println("Введіть підходящу кількість поїздок, від і до:");
			countPredicate = cb.between(root.get("count"), sc.nextInt(), sc.nextInt());
		}else {
			countPredicate = cb.between(root.get("count"), 18, 100);
		}
		
		Predicate namePredicate = null ;
		System.out.println("5) Для вибору транспортера за першою літерою - 0, для пропуску - інша клавіша");
		if(sc.next().equals("0")) {
		namePredicate = cb.like(root.get("name"), "A%");
		}else {
			namePredicate = cb.like(root.get("name"), "%");
		}
		
		Predicate brandPredicate = null;
		System.out.println("6) Для вибору транспортера за моделлю машини - 0, для пропуску - інший символ");
		if(sc.next().equals("0")) {
		System.out.println("Введіть потрібну модель машини: ");
		brandPredicate = cb.equal(brandJoin.get("name"), sc.next());
		}else {
			brandPredicate = cb.like(root.get("name"), "%");
		}
		
		Predicate cityPredicate = null;
		System.out.println("7) Для вибору транспортера за моделлю машини - 0, для пропуску - інший символ");
		if(sc.next().equals("0")) {
		System.out.println("Введіть місто: ");
		cityPredicate = cityJoin.get("name").in(Arrays.asList(sc.next()));
		}else {
			cityPredicate = cb.like(root.get("name"), "%");
		}
		
		Predicate modelPredicate = null;
		System.out.println("8) Для вибору транспортера за моделлю машини - 0, для пропуску - інший символ");
		if(sc.next().equals("0")) {
		System.out.println("Введіть потрібну модель машини: ");
		modelPredicate = modelJoin.get("name").in(Arrays.asList(sc.next()));
		}else {
			modelPredicate =  cb.like(root.get("name"), "%");
		}
		
		Predicate carAgePredicate = null;
		System.out.println("9) Для вибору віку машини - 0, пропустити - інша клавіша");
		if (sc.next().equals("0")) {
			System.out.println("Введіть підходящий для вас вік, від і до:");
			carAgePredicate = cb.between(root.get("carAge"), sc.nextInt(), sc.nextInt());
		}else {
			carAgePredicate = cb.between(root.get("carAge"), 1, 100);
		}
		
		Predicate phonePredicate = null;
		System.out.println("10) Для вибору транспортера за мобільним - 0, для пропуску - інший символ");
		if(sc.next().equals("0")) {
		System.out.println("Введіть номер мобільного: ");
		phonePredicate = root.get("phone").in(Arrays.asList(sc.next()));
		}else {
			phonePredicate =  cb.like(root.get("phone"), "%");
		}
		
		Predicate statusPredicate = null;
		System.out.println("11) Для вибору статусу - 0, або ж пропустіть натиснувши іншу клавішу");
		if (sc.next().equals("0")) {
			System.out.println("Введіть потрібний вам статус:");
			String input = sc.next();
			statusPredicate = root.get("status").in(Status.valueOf(input.toUpperCase()));
		}else {
			statusPredicate = cb.ge(root.get("status"), new BigDecimal(0));
		}
		

		Predicate all = cb.and(ratePredicate, agePredicate, namePredicate, brandPredicate, modelPredicate, cityPredicate, maxWeightPredicate, countPredicate, carAgePredicate,phonePredicate, statusPredicate);
		cq.where(all);
		List<TransporterView> transporters = manager.createQuery(cq).getResultList();
		
		for (TransporterView transporterView : transporters) {
			System.out.println(transporterView);
		}

		manager.getTransaction().commit();
		manager.close();

	}
}
