package com.openrubicon.core.api.vault.economy;

public class EconomyResponse extends net.milkbowl.vault.economy.EconomyResponse {

    private EconomyResponse(double amount, double balance, ResponseType type, String errorMessage) {
        super(amount, balance, type, errorMessage);
    }

    public static EconomyResponse Failure(double amount, double balance, String message) {
        return new EconomyResponse(amount, balance, ResponseType.FAILURE, message);
    }

    public static EconomyResponse Success(double amount, double balance, String message) {
        return new EconomyResponse(amount, balance, ResponseType.SUCCESS, message);
    }

    public static EconomyResponse NotImplemented(double amount, double balance, String message) {
        return new EconomyResponse(amount, balance, ResponseType.NOT_IMPLEMENTED, message);
    }

    public static EconomyResponse Failure(String message) {
        return new EconomyResponse(0, 0, ResponseType.FAILURE, message);
    }

    public static EconomyResponse Success(String message) {
        return new EconomyResponse(0, 0, ResponseType.SUCCESS, message);
    }

    public static EconomyResponse NotImplemented(String message) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, message);
    }

    public static EconomyResponse Failure() {
        return new EconomyResponse(0, 0, ResponseType.FAILURE, "Failure");
    }

    public static EconomyResponse Success() {
        return new EconomyResponse(0, 0, ResponseType.SUCCESS, "Success");
    }

    public static EconomyResponse NotImplemented() {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Not Implemented");
    }

}
