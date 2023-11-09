package be.motormouth.testConstants;

import be.motormouth.allocation.entities.Allocation;

import static be.motormouth.testConstants.MemberTestConstants.MEMBER_GOLD;
import static be.motormouth.testConstants.MemberTestConstants.MEMBER_SILVER;
import static be.motormouth.testConstants.ParkingLotTestConstants.PARKING_LOT_1;

public class AllocationTestConstants {
    public final static Allocation ALLOCATION_1 = new Allocation(
            PARKING_LOT_1,
            MEMBER_SILVER,
            MEMBER_SILVER.getLicencePlate().getLicensePlateNumber()
    );
    public final static Allocation ALLOCATION_2 = new Allocation(
            PARKING_LOT_1,
            MEMBER_GOLD,
            MEMBER_GOLD.getLicencePlate().getLicensePlateNumber()
    );
}
