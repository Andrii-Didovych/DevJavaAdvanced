package ua.controller;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
		boolean isRun = true;
		while (isRun) {
			System.out.println("Enter instraction");
			switch (sc.next()) {
			case "0":
				GPA.printMenuGPA();
				SimpleSelects.printMenuSimpleSelect();
				AgrFunc.printMenuAgrFunc();
				SelectJoin.printMenuSelectJoin();
				break;
			case "1":
				GPA.addBrandOfCar();
				break;
			case "2":
				GPA.deliteBrandWithID();
				break;
			case "3":
				GPA.changeBrandWithID();
				break;
			case "4":
				GPA.addCity();
				break;
			case "5":
				GPA.deleteCityWithID();
				break;
			case "6":
				GPA.changeCityWithID();
				break;
			case "7":
				GPA.addNewModel();
				break;
			case "8":
				GPA.deleteModelWithID();
				break;
			case "9":
				GPA.changeModelWithID();
				break;
			case "10":
				GPA.addNewTransporter();
				break;
			case "11":
				SimpleSelects.searchIdOfCarWithName();
				break;
			case "12":
				SimpleSelects.searchModelWithHaveLessID();
				break;
			case "13":
				SimpleSelects.searchNameWhereFirstLetterIsM();
				break;
			case "14":
				SimpleSelects.modelWhichHasIDIs1End3();
				break;
			case "15":
				SimpleSelects.priceRangeOfCargo();
				break;
			case "16":
				SimpleSelects.cargoWithEnterSize();
				break;
			case "17":
				SimpleSelects.fromCargoWheerLengthSellEnter();
				break;
			case "18":
				SimpleSelects.fromOwnerWhereAddressLike();
				break;
			case "19":
				SimpleSelects.fromOwnerWhereCountBetween1AND2();
				break;
			case "20":
				SimpleSelects.fromTransporterWhereCarAgeBetween1AND2();
				break;
			case "21":
				AgrFunc.maxCountFromOvner();
				break;
			case "22":
				AgrFunc.minCountFromOvner();
				break;
			case "23":
				AgrFunc.avgCountFromOvner();
				break;
			case "24":
				AgrFunc.sumCountFromOvner();
				break;
			case "25":
				AgrFunc.countCountFromOvner();
				break;
			case "26":
				SelectJoin.selectTransporterWithID();
				break;
			case "27":
				SelectJoin.selectTransporterWhoGoToCity();
				break;
			case "28":
				SelectJoin.selectTransporterWhoGoTolviv();
				break;
			case "29":
				SelectJoin.selectTransporterWhoHaveAgeLessEnter();
				break;
			case "30":
				CriteriaAPI.TransporterCrAPI();
				break;
			default:
				isRun = false;
				System.out.println("Немає такої команди!");
				break;
			}
		}
		factory.close();
		sc.close();
	}
}