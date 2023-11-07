package be.motormouth.security.users;


import be.motormouth.security.Feature;

import java.util.List;

import static be.motormouth.security.Feature.*;

public enum UserRole {
    MANAGER(List.of(
            VIEW_ALL_DIVISIONS,
            VIEW_A_DIVISION,
            CREATE_DIVISION,
            CREATE_SUB_DIVISION,
            CREATE_PARKING_LOT,
            VIEW_ALL_PARKING_LOTS,
            VIEW_A_PARKING_LOT,
            VIEW_ALL_MEMBERS,
            VIEW_A_MEMBER,
            GET_ALL_PARKING_ALLOCATIONS,
            GET_ALL_PARKING_ALLOCATIONS_OF_MEMBER,
            GET_ALL_PARKING_ALLOCATIONS_OF_PARKING_LOT,
            GET_ALL_INVOICES,
            MARK_INVOICES_CLOSED)),
    MEMBER(List.of(
            CHANGE_MEMBERSHIP_LVL,
            ALLOCATE_PARKING_SPOT,
            END_ALLOCATION_PARKING_SPOT,
            GET_MONTHLY_INVOICE));

    private final List<Feature> featureList;

    UserRole(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
