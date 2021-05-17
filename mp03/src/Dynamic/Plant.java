package Dynamic;

import java.time.LocalTime;

public class Plant {
    private String name;
    private TypeOfSoil typeOfSoil;

    private PlantType plantType;

    private LocalTime wateringFrequency;
    private Double potDiameter;

    private Boolean ifPruning;
    private Boolean ifFrostResistant;

    public Plant(String name, TypeOfSoil typeOfSoil) {
        this.name = name;
        this.typeOfSoil = typeOfSoil;
        plantType = PlantType.STANDARD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NotNullException {
        if (name == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.name = name;
    }

    public TypeOfSoil getTypeOfSoil() {
        return typeOfSoil;
    }

    public void setTypeOfSoil(TypeOfSoil typeOfSoil) throws NotNullException {
        if (typeOfSoil == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.typeOfSoil = typeOfSoil;
    }

    public Plant(String name, TypeOfSoil typeOfSoil, LocalTime wateringFrequency, Double potDiameter) {
        this.name = name;
        this.typeOfSoil = typeOfSoil;
        this.wateringFrequency = wateringFrequency;
        this.potDiameter = potDiameter;
        plantType = PlantType.INDOOR;
    }

    public void changeToIndoor(LocalTime wateringFrequency, Double potDiameter) {
        ifPruning = null;
        ifFrostResistant = null;
        this.wateringFrequency = wateringFrequency;
        this.potDiameter = potDiameter;
        plantType = PlantType.INDOOR;
    }

    public LocalTime getWateringFrequency() throws Exception {
        if (plantType == PlantType.INDOOR) {
            return wateringFrequency;
        } else {
            throw new Exception("The plant is not an indoor!");
        }
    }

    public void setWateringFrequency(LocalTime wateringFrequency) throws Exception {
        if (plantType == PlantType.INDOOR) {
            if (wateringFrequency == null) {
                throw new NotNullException("Can't set value of wateringFrequency, value can not be null");
            }
            this.wateringFrequency = wateringFrequency;
        } else {
            throw new Exception("The plant is not an indoor!");
        }
    }

    public double getPotDiameter() throws Exception {
        if (plantType == PlantType.INDOOR) {
            return potDiameter;
        } else {
            throw new Exception("The plant is not an indoor!");
        }
    }

    public void setPotDiameter(Double potDiameter) throws Exception {
        if (plantType == PlantType.INDOOR) {
            if (potDiameter == null) {
                throw new NotNullException("Can't set value of potDiameter, value can not be null");
            }
            this.potDiameter = potDiameter;
        } else {
            throw new Exception("The plant is not an indoor!");
        }
    }

    public Plant(String name, TypeOfSoil typeOfSoil, Boolean ifPruning, Boolean ifFrostResistant) {
        this.name = name;
        this.typeOfSoil = typeOfSoil;
        this.ifPruning = ifPruning;
        this.ifFrostResistant = ifFrostResistant;
        plantType = PlantType.OUTDOOR;
    }

    public void changeToOutdoor(Boolean ifPruning, Boolean ifFrostResistant) {
        wateringFrequency = null;
        potDiameter = null;
        this.ifPruning = ifPruning;
        this.ifFrostResistant = ifFrostResistant;
        plantType = PlantType.OUTDOOR;
    }

    public boolean isIfPruning() throws Exception {
        if (plantType == PlantType.OUTDOOR) {
            return ifPruning;
        } else {
            throw new Exception("The plant is not an outdoor!");
        }
    }

    public void setIfPruning(Boolean ifPruning) throws Exception {
        if (plantType == PlantType.OUTDOOR) {
            if (ifPruning == null) {
                throw new NotNullException("Can't set value of potDiameter, value can not be null");
            }
            this.ifPruning = ifPruning;
        } else {
            throw new Exception("The plant is not an outdoor!");
        }
    }

    public boolean isIfFrostResistant() throws Exception {
        if (plantType == PlantType.OUTDOOR) {
            return ifFrostResistant;
        } else {
            throw new Exception("The plant is not an outdoor!");
        }
    }

    public void setIfFrostResistant(Boolean ifFrostResistant) throws Exception {
        if (plantType == PlantType.OUTDOOR) {
            if (ifFrostResistant == null) {
                throw new NotNullException("Can't set value of potDiameter, value can not be null");
            }
            this.ifFrostResistant = ifFrostResistant;
        } else {
            throw new Exception("The plant is not an outdoor!");
        }
    }

    @Override
    public String toString() {
        if (plantType == PlantType.OUTDOOR) {
            return "Outdoor plant";
        } else if (plantType == PlantType.INDOOR) {
            return "Indoor plant";
        } else {
            return "Standard plant";
        }
    }
}