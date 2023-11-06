package be.motormouth.member.entities;

public enum MembershipLevel {

    BRONZE(0,0,4),
    SILVER(10,20,6),
    GOLD(40,30,24);

    private final double monthlyCost;
    private final double reductionInPercentage;
    private final int maxAllocationTimeInHour;

    MembershipLevel(double monthlyCost, double reductionInPercentage, int maxAllocationTimeInHour) {
        this.monthlyCost = monthlyCost;
        this.reductionInPercentage = reductionInPercentage;
        this.maxAllocationTimeInHour = maxAllocationTimeInHour;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    public double getReductionInPercentage() {
        return reductionInPercentage;
    }

    public int getMaxAllocationTimeInHour() {
        return maxAllocationTimeInHour;
    }
}
