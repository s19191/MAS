public class Aqueous extends MovementEnvironment {
    private double displacement;
    private BodyOfWater bodyOfWater;

    public Aqueous(double displacement, BodyOfWater bodyOfWater) {
        this.displacement = displacement;
        this.bodyOfWater = bodyOfWater;
    }

    public String findPermissions() {
        String result;
        if (bodyOfWater == BodyOfWater.INLAND) {
            result = "Można pływać na akweanach śródlądowych";
        } else {
            result = "Można pływach na morzach i oceanach";
        }
        return result;
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Double displacement) throws NotNullException {
        if (displacement == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.displacement = displacement;
    }

    public BodyOfWater getBodyOfWater() {
        return bodyOfWater;
    }

    public void setBodyOfWater(BodyOfWater bodyOfWater) throws NotNullException {
        if (bodyOfWater == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.bodyOfWater = bodyOfWater;
    }
}