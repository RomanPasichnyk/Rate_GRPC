package com.rate.streaming;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import java.io.IOException;

public final class RateStreamingServer {

    private static final int PORT = 8090;

    private RateStreamingServer() {

    }

    /**
     * Initialize method for server.
     *
     * @param args - default.
     * @throws IOException          - auto-generate.
     * @throws InterruptedException - auto-generate.
     */
    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) throws IOException, InterruptedException {

        UkrsibRateGrabber ukrsibRateGrabber = new UkrsibRateGrabber();
        PrivatRateGrabber privatRateGrabber = new PrivatRateGrabber();
        RateStreamingService rateService = new RateStreamingService(ukrsibRateGrabber, privatRateGrabber);
        Server rateServer = NettyServerBuilder.forPort(PORT).addService(rateService).build().start();

        Runtime.getRuntime().addShutdownHook(new Thread(rateServer::shutdownNow));

        rateServer.awaitTermination();

    }

}
