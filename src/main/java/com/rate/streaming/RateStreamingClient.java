package com.rate.streaming;

import com.rate.Bank;
import com.rate.Currency;
import com.rate.RateRequest;
import com.rate.RateResponse;
import com.rate.RateStreamingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.Semaphore;

public final class RateStreamingClient {

    private static final String PRIVAT_BANK = "PRIVAT";

    private static final String UKRSIB_BANK = "UKRSIB";

    private static final int PORT = 8090;

    private static final String SERVER_NAME = "localhost";

    private RateStreamingClient() {

    }

    /**
     * Initialize method for client.
     *
     * @param args - default args.
     * @throws InterruptedException - for concurrency.
     */
    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = NettyChannelBuilder.forAddress(SERVER_NAME, PORT).usePlaintext(true).build();
        RateStreamingServiceGrpc.RateStreamingServiceStub stub = RateStreamingServiceGrpc.newStub(channel);

        Semaphore exitSemaphore = new Semaphore(0);
        StreamObserver<RateRequest> requestStream = initRequestStream(stub, exitSemaphore);

        requestStream.onNext(createRequest(PRIVAT_BANK, Currency.EUR, Currency.USD));
        requestStream.onNext(createRequest(UKRSIB_BANK, Currency.USD, Currency.UAH));
        requestStream.onNext(createRequest(PRIVAT_BANK, Currency.UAH, Currency.RUB));

        exitSemaphore.acquire();

    }

    private static RateRequest createRequest(String bankName, Currency from, Currency to) {
        return RateRequest.newBuilder()
            .setBank(Bank.newBuilder().setName(bankName)).setFrom(from).setTo(to).build();
    }

    private static StreamObserver<RateRequest> initRequestStream(RateStreamingServiceGrpc.RateStreamingServiceStub stub, Semaphore exitSemaphore) {
        return stub.observe(new StreamObserver<>() {
            @Override
            public void onNext(RateResponse rateResponse) {
                printRates(rateResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                exitSemaphore.release();
            }

            @Override
            public void onCompleted() {
                System.out.println("Call completed");
                exitSemaphore.release();
            }
        });
    }

    /**
     * Print all information that contain RateResponse.
     *
     * @param rateResponse - Response from server.
     */
    public static void printRates(RateResponse rateResponse) {
        System.out.println("Rate: {");
        System.out.println(" From: " + rateResponse.getCurrencies().getFrom());
        System.out.println(" To: " + rateResponse.getCurrencies().getTo());
        System.out.println(" Value: " + rateResponse.getCurrencies().getValue());
        System.out.println(" Bank: " + rateResponse.getBank().getName());
        System.out.println("}");
    }
}
