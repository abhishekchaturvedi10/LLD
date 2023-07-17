package LLD.TemplateMethod;


public class TemplateMethodApplication {

	public static void main(String[] args) {

		HouseBuilderTemplate houseType = new WoodenHouse();
		houseType.buildHouse();

		System.out.println("************");

		houseType = new BrickHouse();
		houseType.buildHouse();
	}
}
