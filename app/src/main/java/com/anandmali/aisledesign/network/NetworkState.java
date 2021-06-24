package com.anandmali.aisledesign.network;

public class NetworkState<T> {

    private Status status;
    private T response;
    private String errorMessage;

    public NetworkState<T> success(T response) {
        this.status = Status.SUCCESS;
        this.response = response;
        this.errorMessage = null;
        return this;
    }

    public NetworkState<T> error(String errorMessage) {
        this.status = Status.ERROR;
        this.response = null;
        this.errorMessage = errorMessage;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public T getResponse() {
        return response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public enum Status {
        SUCCESS,
        ERROR
    }

}
