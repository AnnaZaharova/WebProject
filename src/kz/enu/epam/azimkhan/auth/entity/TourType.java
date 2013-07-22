package kz.enu.epam.azimkhan.auth.entity;

/**
 * Tour tpe
 */
public enum TourType {
	VACATION(1), SHOPPING(2), EXCURSION(3);

	/**
	 * Type id
	 */
	private int id;

	/**
	 * Enum constructor
	 * @param id
	 */
	TourType(int id){
		this.id = id;
	}

	/**
	 * Find type by id
	 * @param id
	 * @return
	 */
	public static TourType findById(int id){
		for (TourType type: TourType.values()){
			if (type.id == id){
				return type;
			}
		}

	   return null;
	}
}
